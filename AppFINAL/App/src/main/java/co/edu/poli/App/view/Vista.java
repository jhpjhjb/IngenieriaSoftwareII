package co.edu.poli.App.view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.edu.poli.App.controlador.ControladorCliente;
import co.edu.poli.App.controlador.ControladorPedido;
import co.edu.poli.App.controlador.ControladorProductos;
import co.edu.poli.App.modelo.Cliente;
import co.edu.poli.App.modelo.Pedido;
import co.edu.poli.App.modelo.Producto;
import co.edu.poli.App.modelo.ProductoAlimenticio;
import co.edu.poli.App.modelo.ProductoElectrico;

public class Vista {

    private ControladorCliente controlador_cliente;
    private ControladorProductos controlador_producto;
    private ControladorPedido controlador_pedido;
    private static Scanner entrada = new Scanner(System.in);


    public Vista() throws ClassNotFoundException, SQLException{
        this.controlador_cliente = new ControladorCliente();
        this.controlador_producto = new ControladorProductos();
        this.controlador_pedido = new ControladorPedido();
    }

    public void mostrarMenu() throws ClassNotFoundException, SQLException{
        System.out.println("**** BIENVENIDOS A PAULIS STORE ****");
        System.out.println("=============\n1. Cliente\n2. Producto\n3. Pedido\n4. Salir\n=============");
        int opcion = validarOpcion(1, 4);

        switch (opcion) {
            case 1:
                mostrarSubmenuCliente();
                break;
            case 2:
                mostrarSubmenuProducto();
                break;
            case 3:
                mostrarSubmenuPedido();
                break;
            case 4:
                System.out.println("Vuelva Pronto :)");
                break;
        }
    }

    public static int validarOpcion(int menor, int mayor){
        while(true){
            try {
                System.out.println("Digite la opcion de su interes:");
                int opcion = entrada.nextInt();
                
                if (menor <= opcion && mayor>= opcion){
                    return opcion;
                }
                else{
                    System.err.println("Opcion fuera de rango");
                    entrada.nextLine();
                }
            } catch (Exception e) {
                System.err.println("Opcion No valida");
                entrada.nextLine();
            }
        }
    }
    public void mostrarSubmenuCliente() throws ClassNotFoundException, SQLException{
        System.out.println("**** ¿QUE DESEA REALIZAR? ****\n------ MENU DE CLIENTE ------");
        System.out.println("=============\n1: Ingresar\n"+
                                          "2: Consultar Clientes\n"+
                                          "3: Consultar por ID\n"+
                                          "4: Eliminar\n"+
                                          "5: Actualizar\n"+
                                          "6: Volver\n=============");

        int opcion = validarOpcion(1, 6);
        pedirCliente(opcion);
    }

    public void pedirCliente(int opcion) throws ClassNotFoundException, SQLException{
        
        switch (opcion) {
            case 1:
                System.out.println("Digita el nombre del cliente: ");
                String nombre = entrada.next();
                System.out.println(controlador_cliente.ingresarCliente(new Cliente(nombre)));
                break;

            case 2:
                System.out.println("====== LISTA DE CLIENTES ======\n");
                controlador_cliente.consultarCliente().forEach(System.out::println);
                break;

            case 3:
                System.out.println("Digita el id del cliente para consultar");
                int id_cliente = entrada.nextInt();
                System.out.println(controlador_cliente.buscarId(id_cliente));
                break;

            case 4:
                System.out.println("Digita el id del cliente para eliminar");
                int id_eliminar = entrada.nextInt();
                System.out.println(controlador_cliente.eliminarClientes(id_eliminar));
                break;

            case 5:
                System.out.println("Digita el id del cliente a actualizar: ");
                int id_nuevo = entrada.nextInt();
                System.out.println("Digite el nuevo nombre del cliente: ");
                String nuevo_nombre = entrada.next();
                System.out.println(controlador_cliente.actualizarCliente(new Cliente(id_nuevo, nuevo_nombre)));
                break;

            case 6:
                mostrarMenu();
                break;
            default:
                System.out.println("Opcion no Valida");;
        }
        mostrarSubmenuCliente();
    }

    public  void mostrarSubmenuProducto() throws ClassNotFoundException, SQLException{
        System.out.println("**** ¿QUE DESEA REALIZAR? ****\n------ MENU DE PRODUCTO ------");
        System.out.println("=============\n1: Ingresar\n"+
                                          "2: Consultar Producto\n"+
                                          "3: Consultar por ID\n"+
                                          "4: Eliminar Producto\n"+
                                          "5: Actualizar Producto\n"+
                                          "6: Consulta Detallada\n"+
                                          "7: Volver\n=============");
        int opcion = validarOpcion(1, 7);
        pedirProducto(opcion);
    }

    public void pedirProducto(int opcion) throws ClassNotFoundException, SQLException{  
        int opcion1;
        switch (opcion) {

            // INGRESAR

            case 1:
                opcion1 = pedirTipoProducto("INGRESAR?");
                System.out.println("Digite la Descripcion del producto:");
                String descripcion = entrada.next();
                switch (opcion1) {
                    case 1:
                        System.out.println("Digite el Voltaje Del Producto:");
                        String voltaje = entrada.next();
                        System.out.println(controlador_producto.ingresarProductoElectrico(new ProductoElectrico(descripcion, voltaje)));                       
                        break;
                    case 2:
                        System.out.println("Digite el Aporte Calorico Del Producto:");
                        String calorias = entrada.next();
                        System.out.println(controlador_producto.ingresarProductoAlimenticio(new ProductoAlimenticio(descripcion, calorias)));                       
                        break;
                }
                break;
            case 2:

                // CONSULTA PRODUCTOS 

                opcion1 = pedirTipoProducto("CONSULTAR?");
                switch (opcion1) {
                    case 1: 
                        controlador_producto.mostrarProductosElectricos().forEach(System.out::println);
                        break;
                    case 2:
                        controlador_producto.mostrarProductosAlimenticios().forEach(System.out::println);
                        break;
                }          
                break;
            case 3:

                // CONSULTA POR ID

                opcion1 = pedirTipoProducto("CONSULTAR POR ID?");
                switch (opcion1) {
                    case 1: 
                        System.out.println("Digita el id del Producto a Consultar");
                        int id_producto_electrico = entrada.nextInt();
                        entrada.nextLine();
                        System.out.println(controlador_producto.mostrarProductoElectrico(id_producto_electrico));
                        break;
                    case 2:
                        System.out.println("Digita el id del Producto a Consultar");
                        int id_producto_alimenticio = entrada.nextInt();
                        entrada.nextLine();
                        System.out.println(controlador_producto.mostrarProductoAlimenticio(id_producto_alimenticio));
                        break;
                }
                break;
            case 4:
                opcion1 = pedirTipoProducto("ELIMINAR?"); 
                switch (opcion1) {
                    case 1: 
                        System.out.println("Digita el id del Producto a Eliminar");
                        int id_producto_electrico = entrada.nextInt();
                        entrada.nextLine();
                        System.out.println(controlador_producto.eliminarProductoElectrico(id_producto_electrico));
                        break;
                    case 2:
                        System.out.println("Digita el id del Producto a Eliminar");
                        int id_producto_alimenticio = entrada.nextInt();
                        entrada.nextLine();
                        System.out.println(controlador_producto.eliminarProductoAlimenticio(id_producto_alimenticio));
                        break;
                }
                break;
            case 5:
                opcion1 = pedirTipoProducto("ACTUALIZAR?"); 
                switch (opcion1) {
                    case 1: 
                        System.out.println("Digita el id del Producto a Actualizar");
                        int id_producto_electrico = entrada.nextInt();
                        System.out.println("Digita la nueva descripción: ");
                        String nueva_descripcion = entrada.nextLine();
                        System.out.println("Digita el nuevo voltaje: ");
                        String voltaje = entrada.nextLine();
                        System.out.println(controlador_producto.actualizarProductoElectrico(new ProductoElectrico(id_producto_electrico, nueva_descripcion, voltaje)));
                        break;
                    case 2:
                        System.out.println("Digita el id del Producto a Actualizar");
                        int id_producto_alimenticio = entrada.nextInt();
                        System.out.println("Digita la nueva descripción: ");
                        String nueva_descripcion1 = entrada.nextLine();
                        System.out.println("Digita el nuevo aporte calorico: ");
                        String aporte_calorico = entrada.nextLine();
                        System.out.println(controlador_producto.actualizarProductoAlimenticio(new ProductoAlimenticio(id_producto_alimenticio, nueva_descripcion1, aporte_calorico)));
                        break;
                }
                break;
            case 6:
                pedirTipoProducto("Consultar");
                break;
            case 7:
                mostrarMenu();
                break;
            default:
                System.out.println("Opcion no Valida");;
        }
        mostrarSubmenuProducto();
    }

    public int pedirTipoProducto(String intencion) throws ClassNotFoundException, SQLException{
        System.out.println("¿QUE TIPO DE PRODUCTO DESEA "+ intencion + "?");
        System.out.println("=============\n1: Producto Electrico\n"+
                                          "2: Producto Alimenticio\n=============");
        int opcion =  validarOpcion(1,2);
        entrada.nextLine();

        return opcion;

        }

    
    public void mostrarSubmenuPedido() throws ClassNotFoundException, SQLException {
        System.out.println("**** Digite su interés ***");
        System.out.println("1: Ingresar Pedido");
        System.out.println("2: Consultar Pedidos");
        System.out.println("3: Consultar Pedido por número");
        System.out.println("4: Eliminar Pedido");
        System.out.println("5: Actualizar Pedido");
        System.out.println("6: Volver");
        int opcion = validarOpcion(1, 6);
        pedirPedido(opcion);
    }

    public void pedirPedido(int opcion) throws ClassNotFoundException, SQLException {
        switch (opcion) {
            case 1:
                System.out.println("Digita el número del Pedido:");
                String numero = entrada.next();
                System.out.println("Digita la fecha del Pedido:");
                String fecha = entrada.next();

                System.out.println("Digita el ID del Cliente:");
                int idCliente = entrada.nextInt();
                var clientes = controlador_cliente.buscarId(idCliente);
                if (clientes == null) {
                    System.out.println("Cliente no encontrado. Inténtalo nuevamente.");
                    mostrarSubmenuPedido();
                    return;
                }

                List<Producto> productosPedido = new ArrayList<>();
                System.out.println("¿Cuántos productos desea agregar al pedido?");
                int numProductos = entrada.nextInt();
                for (int i = 0; i < numProductos; i++) {
                    System.out.println("Digita el ID del Producto " + (i + 1) + ":");
                    int idProducto = entrada.nextInt();
                    Producto productoSeleccionado = controlador_producto.mostrarProductoAlimenticio(idProducto);
                    if (productoSeleccionado != null) {
                        productosPedido.add(productoSeleccionado);
                    } else {
                        System.out.println("Producto no encontrado. Inténtalo nuevamente.");
                    }
                }

                Pedido nuevoPedido = new Pedido(numero, fecha, clientes, productosPedido);
                System.out.println(controlador_pedido.ingresarPedido(nuevoPedido));
                break;

            case 2:
                controlador_pedido.mostrarPedidos().forEach(System.out::println);
                break;

            case 3:
                System.out.println("Digita el número del Pedido a consultar:");
                int numPedido = entrada.nextInt();
                System.out.println(controlador_pedido.mostrarPedido(numPedido));
                break;

            case 4:
                System.out.println("Digita el número del Pedido a eliminar:");
                int numEliminar = entrada.nextInt();
                System.out.println(controlador_pedido.eliminarPedido(numEliminar));
                break;

            case 5:
                System.out.println("Digita el número del Pedido a actualizar:");
                int numActualizar = entrada.nextInt();
                Pedido pedidoExistente = controlador_pedido.mostrarPedido(numActualizar);
                if (pedidoExistente == null) {
                    System.out.println("Pedido no encontrado.");
                    mostrarSubmenuPedido();
                    return;
                }

                System.out.println("Digita la nueva fecha del Pedido:");
                String nuevaFecha = entrada.next();

                List<Producto> productosActualizados = new ArrayList<>();
                System.out.println("¿Cuántos productos deseas agregar al pedido?");
                int numProdAct = entrada.nextInt();
                for (int i = 0; i < numProdAct; i++) {
                    System.out.println("Digita el ID del Producto " + (i + 1) + ":");
                    int idProducto = entrada.nextInt();
                    Producto productoSeleccionado = controlador_producto.mostrarProductoAlimenticio(idProducto);
                    if (productoSeleccionado != null) {
                        productosActualizados.add(productoSeleccionado);
                    } else {
                        System.out.println("Producto no encontrado. Inténtalo nuevamente.");
                    }
                }

                pedidoExistente.setFecha(nuevaFecha);
                pedidoExistente.setProductos(productosActualizados);
                System.out.println(controlador_pedido.actualizarPedido(pedidoExistente));
                break;

            case 6:
                mostrarMenu();
                break;

            default:
                System.out.println("Opción no válida.");
        }
        mostrarSubmenuPedido();
    }



  public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Vista vista = new Vista();
        vista.mostrarMenu();
    }
}
