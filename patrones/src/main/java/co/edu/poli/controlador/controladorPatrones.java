package co.edu.poli.controlador;

import co.edu.poli.modelo.Cliente;
import co.edu.poli.modelo.Pedido;
import co.edu.poli.modelo.Producto;
import co.edu.poli.modelo.Chain.Manejador;
import co.edu.poli.modelo.Chain.ManejadorCliente;
import co.edu.poli.modelo.Chain.ManejadorProducto;
import co.edu.poli.modelo.Chain.ManjeadorPedido;
import co.edu.poli.modelo.State.EstadoCancelado;
import co.edu.poli.modelo.State.EstadoCreado;
import co.edu.poli.modelo.State.PedidoContext;
import co.edu.poli.modelo.Strategy.DescuentoContext;
import co.edu.poli.modelo.Strategy.DescuentoMetodoPago;
import co.edu.poli.modelo.Strategy.DescuentoPorCantidad;
import co.edu.poli.modelo.Strategy.DescuentoProductoEspecifico;
import co.edu.poli.modelo.Visitor.ConcreteVisitor;
import co.edu.poli.modelo.Visitor.IVisitor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;



public class controladorPatrones {
    private boolean mostrar = true;
    private ToggleGroup grupo = new ToggleGroup();
    private ToggleGroup grupoPago = new ToggleGroup();  
    private ToggleGroup grupoPago2 = new ToggleGroup();  
    private Cliente cliente = new Cliente("Andres", false);
    private Pedido pedido = new Pedido(cliente);
    private Producto producto1 = new Producto("Zapatos Nike", 200.0);
    private Producto producto2 = new Producto("Camisa", 80.0);
    private Producto producto3 = new Producto("Pantalón", 120.0);
    private ObservableList<Producto> productosParaPedido = FXCollections.observableArrayList();
    private DescuentoContext contextoDescuento = new DescuentoContext(new DescuentoPorCantidad());
    private ObservableList<Producto> productosDisponibles = FXCollections.observableArrayList();
    private PedidoContext context = new PedidoContext();
    

    @FXML
    private Button bttCancelarState;
    @FXML
    private AnchorPane contenedorState;
    @FXML
    private TableView<Producto> tableViewProductos2;
    @FXML
    private TableColumn<Producto, String> descripcionColumn2;
    @FXML
    private TableColumn<Producto, String> precioColumn2;
    @FXML
    private Button bttAgregarState;
    @FXML
    private Button bttHacerPedidoState;
    @FXML
    private RadioButton RadioPaypal2;
    @FXML
    private RadioButton RadioTarjeta2;
    @FXML
    private Button bttPagarState;
    @FXML
    private Button bttEnviarState;
    @FXML
    private Button bttRevisarPedido;
    @FXML
    private TableColumn<Producto, String> descripcionColumn;
    @FXML
    private TableColumn<Producto, Double> precioColumn;
    @FXML
    private TableView<Producto> tableViewProductos;
    @FXML
    private Button bttAñadir, bttChain, bttStrategy, bttValidar, bttCambiar, bttVisitor,bttUsd,bttState,bttMx,bttEur, bttagregarProductos, bttAgregar2, bttMostrarP, bttMostrarPe;
    @FXML
    private AnchorPane contenedorChain, visitorA,  contenedorProductos;
    @FXML
    private TextField descripcionProducto, precioProducto;
    @FXML
    private RadioButton radioActivo, radioInactivo;
    @FXML
    private Button agregarTableView;
    @FXML
    private Button hacerPedido;
    @FXML
    private RadioButton RadioPaypal;
    @FXML
    private RadioButton RadioTarjeta;
    @FXML
    private AnchorPane contenedorSrategy;

    @FXML
    public void initialize() {
  
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tableViewProductos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        productosDisponibles.addAll(producto1, producto2, producto3);
        tableViewProductos.setItems(productosDisponibles);
        descripcionColumn2.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        precioColumn2.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tableViewProductos2.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableViewProductos2.setItems(productosDisponibles);
        

        radioActivo.setToggleGroup(grupo);
        radioInactivo.setToggleGroup(grupo);
        radioActivo.setUserData(true);
        radioInactivo.setUserData(false);

      
        RadioPaypal.setToggleGroup(grupoPago);
        RadioTarjeta.setToggleGroup(grupoPago);
        RadioPaypal.setUserData("PayPal");
        RadioTarjeta.setUserData("Tarjeta Debito");
        RadioPaypal2.setToggleGroup(grupoPago2);
        RadioTarjeta2.setToggleGroup(grupoPago2);
        RadioPaypal2.setUserData("PayPal");
        RadioTarjeta2.setUserData("Tarjeta Debito");


       
        contenedorChain.setVisible(false);
        contenedorSrategy.setVisible(false);
        contenedorState.setVisible(false);
    }


    @FXML
    void clickAgregarP(ActionEvent event) {
        mostrar = !mostrar;
        contenedorProductos.setVisible(mostrar);
    }
    
    @FXML
    void clickChain(ActionEvent event) {
        mostrar = !mostrar;
        bttStrategy.setVisible(mostrar);
        contenedorChain.setVisible(!mostrar);
        contenedorSrategy.setVisible(false); 
        contenedorProductos.setVisible(false);
        bttVisitor.setVisible(mostrar);
        bttState.setVisible(mostrar);
    }
    @FXML
    void clickState(ActionEvent event) {
        mostrar = !mostrar;
        bttStrategy.setVisible(mostrar);
        bttChain.setVisible(mostrar);
        bttVisitor.setVisible(mostrar);
        contenedorState.setVisible(!mostrar);
        contenedorChain.setVisible(false);
        contenedorSrategy.setVisible(false);
        visitorA.setVisible(false);
        contenedorProductos.setVisible(false);
    }

    @FXML
    void clickStrategy(ActionEvent event) {
        mostrar = !mostrar;
        bttChain.setVisible(mostrar);
        contenedorSrategy.setVisible(!mostrar);
        contenedorChain.setVisible(false); 
        contenedorProductos.setVisible(false);
        bttVisitor.setVisible(mostrar);
        bttState.setVisible(mostrar);
    }
    @FXML
    void clickVisitor(ActionEvent event) {
        mostrar = !mostrar;
        bttState.setVisible(mostrar);
        bttStrategy.setVisible(mostrar);
        bttChain.setVisible(mostrar);
        contenedorChain.setVisible(false);
        contenedorSrategy.setVisible(false);
        visitorA.setVisible(!mostrar);
        contenedorProductos.setVisible(false);

    }

    @FXML
    void clickValidar(ActionEvent event) {
        Manejador manejador = new ManejadorCliente();
        manejador.setManejador(new ManejadorProducto()).setManejador(new ManjeadorPedido());
        mostrarAlerta(manejador.handle(pedido) + "\n" + pedido.toString() + "\nEl total Seria:$" + pedido.getTotal(), AlertType.INFORMATION);
    }

   

    @FXML
    void realizarPedido(ActionEvent event) {
        if (grupoPago.getSelectedToggle() == null) {
            mostrarAlerta("Debe seleccionar un método de pago", AlertType.ERROR);
            return;
        }

        for (Producto producto : productosParaPedido) {
            pedido.agregarProductos(producto);
        }
        productosParaPedido.clear();

    
        double totalOriginal = pedido.getTotal();
        double totalConDescuentos = totalOriginal; 
        contextoDescuento.setEstrategia(new DescuentoPorCantidad());
        double descuentoCantidad = contextoDescuento.calcularTotalConDescuento(pedido);
        totalConDescuentos -= descuentoCantidad;  
        contextoDescuento.setEstrategia(new DescuentoProductoEspecifico());
        double descuentoProducto = contextoDescuento.calcularTotalConDescuento(pedido);
        totalConDescuentos -= descuentoProducto;  

    
        String metodoPago = grupoPago.getSelectedToggle().getUserData().toString();
        pedido.setMetodoPago(metodoPago);
        contextoDescuento.setEstrategia(new DescuentoMetodoPago());
        double descuentoPago = contextoDescuento.calcularTotalConDescuento(pedido);
        totalConDescuentos -= descuentoPago; 
        double totalSinDescuento = totalOriginal;
        double totalFinal = totalConDescuentos;

        String resumen = "Pedido Realizado\n" +
                pedido.toString() +
                "\nTotal sin descuentos: $" + totalSinDescuento +
                "\nDescuento por cantidad: $" + descuentoCantidad +
                "\nDescuento por producto específico: $" + descuentoProducto +
                "\nDescuento por método de pago (" + metodoPago + "): $" + descuentoPago +
                "\n---------------------------------------" +
                "\nTotal a pagar: $" + totalFinal;

        mostrarAlerta(resumen, AlertType.INFORMATION);
    }


    @FXML
    void clickAñadir(ActionEvent event) {
        if (!(descripcionProducto.getText().isEmpty() || precioProducto.getText().isEmpty())) {
            try {
                double precio = Double.parseDouble(precioProducto.getText());
                Producto producto = new Producto(descripcionProducto.getText(), precio);
                pedido.agregarProductos(producto); 
                productosDisponibles.add(producto); 
                mostrarAlerta("Producto Agregado Con Éxito\n" + producto.toString(), AlertType.INFORMATION);
                descripcionProducto.clear();
                precioProducto.clear();
            } catch (NumberFormatException e) {
                mostrarAlerta("El precio debe ser un número válido", AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Rellena todos los campos", AlertType.ERROR);
        }
        contenedorProductos.setVisible(false);
    }

    @FXML
    void clickCambiar(ActionEvent event) {
        if (grupo.getSelectedToggle() != null) {
            cliente.setEstado((boolean) grupo.getSelectedToggle().getUserData());
            mostrarAlerta("Estado del Cliente Modificado\n" + cliente.toString(), AlertType.INFORMATION);
            grupo.selectToggle(null);
        } else {
            mostrarAlerta("Selecciona Un estado", AlertType.ERROR);
        }
    }

    @FXML
    void añadirProductosDesdeTabla(ActionEvent event) {

        if (!(context.getEstado() instanceof EstadoCreado)) {
            mostrarAlerta("No puedes agregar productos en este estado del pedido.", AlertType.ERROR);
            return;
        }

        ObservableList<Producto> productosSeleccionados = tableViewProductos.getSelectionModel().getSelectedItems();

        if (!productosSeleccionados.isEmpty()) {
            Producto productoSeleccionado = productosSeleccionados.get(0);
            productosParaPedido.add(productoSeleccionado);
            mostrarAlerta("Producto añadido al pedido: " + productoSeleccionado.getDescripcion(), AlertType.INFORMATION);
        } else {
            mostrarAlerta("No hay productos seleccionados", AlertType.WARNING);
        }
    }

    @FXML
    void agregarTable(ActionEvent event) {
        añadirProductosDesdeTabla(event);
    }

    //Patron Visitor
    @FXML
    void clickAgregar2(ActionEvent event) {
        mostrar = !mostrar;
        contenedorProductos.setVisible(!mostrar);
    }
    @FXML
    void clickUSD(ActionEvent event) {
        IVisitor visitor = new ConcreteVisitor(0.00024, "USD");
        if(bttMostrarP.isDisable()){
            mostrarAlerta(pedido.aceptar(visitor), AlertType.INFORMATION);
        }
        else{
            Producto ultimProducto = pedido.ultimProducto();
            mostrarAlerta(ultimProducto.aceptar(visitor), AlertType.INFORMATION);
        }
    }
    
    @FXML
    void clickEur(ActionEvent event) {
        IVisitor visitor = new ConcreteVisitor(0.00021, "EUR");
        if(bttMostrarP.isDisable()){
            mostrarAlerta(pedido.aceptar(visitor), AlertType.INFORMATION);
        }
        else{
            Producto ultimProducto = pedido.ultimProducto();
            mostrarAlerta(ultimProducto.aceptar(visitor), AlertType.INFORMATION);
        }
      
    }
    @FXML
    void clickMx(ActionEvent event) {
         IVisitor visitor = new ConcreteVisitor(0.0046, "MX");
        if(bttMostrarP.isDisable()){
            mostrarAlerta(pedido.aceptar(visitor), AlertType.INFORMATION);
        }
        else{
            Producto ultimProducto = pedido.ultimProducto();
            mostrarAlerta(ultimProducto.aceptar(visitor), AlertType.INFORMATION);
        }
    }

    @FXML
    void clickPedido(ActionEvent event) {
        mostrar = !mostrar;
        bttMostrarP.setDisable(!mostrar);
        bttAgregar2.setDisable(!mostrar);
        if(bttAgregar2.isDisable()){
            mostrarAlerta(pedido.toString(),AlertType.INFORMATION);
        }
    }
    @FXML
    void clickMostrar(ActionEvent event) {
        mostrar = !mostrar;
        bttMostrarPe.setDisable(!mostrar);
        if(!pedido.getProductos().isEmpty() && bttMostrarPe.isDisable()){
            Producto ultiProducto = pedido.ultimProducto();
            mostrarAlerta(ultiProducto.toString(), AlertType.INFORMATION);
        }
        else if (pedido.getProductos().isEmpty() && bttMostrarPe.isDisable()){
            mostrarAlerta("Tu pedido Aun esta Vacio\nAgrega Productos :)\n"+ pedido.toString(), AlertType.ERROR);
        }
    }

    @FXML
    void agregarProductoState(ActionEvent event) {

        if (!(context.getEstado() instanceof EstadoCreado || context.getEstado() instanceof EstadoCancelado)) {
            mostrarAlerta(context.crearPedido(), AlertType.ERROR);
            return;
        }

        String nombreCliente = pedido.getCliente().getNombre();

        ObservableList<Producto> productosSeleccionados = tableViewProductos2.getSelectionModel().getSelectedItems();

        if (!productosSeleccionados.isEmpty()) {
            Producto productoSeleccionado = productosSeleccionados.get(0);
            productosParaPedido.add(productoSeleccionado);
            mostrarAlerta("Pedido de: "+nombreCliente+"\nProducto añadido al pedido: " + productoSeleccionado.getDescripcion(), AlertType.INFORMATION);
        } else {
            mostrarAlerta("No hay productos seleccionados", AlertType.ERROR);
        }
    }

    @FXML
    void realizarPedidoState(ActionEvent event) {

        if (!(context.getEstado() instanceof EstadoCreado || context.getEstado() instanceof EstadoCancelado)) {
            mostrarAlerta(context.crearPedido(), AlertType.ERROR);
            return; 
        }

        if (productosParaPedido.isEmpty()) {
            mostrarAlerta("Error: Debes seleccionar al menos un producto para realizar el pedido.", Alert.AlertType.ERROR);
            return; 
        }

        for (Producto producto : productosParaPedido) {
            pedido.agregarProductos(producto);
        }

        productosParaPedido.clear();
        String resultado = context.crearPedido();
        mostrarAlerta(resultado, Alert.AlertType.INFORMATION);
    }

    @FXML
    void pagarPedidoState(ActionEvent event) {
        if (grupoPago2.getSelectedToggle() == null) {
            mostrarAlerta("Debe seleccionar un método de pago", AlertType.ERROR);
            return;
        }

        String resultado = context.pagar();
        mostrarAlerta(resultado, Alert.AlertType.INFORMATION);
    }

    @FXML
    void enviarPedido(ActionEvent event) {
        String resultado = context.enviar();
        mostrarAlerta(resultado, Alert.AlertType.INFORMATION);
    }

    @FXML
    void revisarPedidoState(ActionEvent event) {

        if (!(context.getEstado() instanceof EstadoCreado || context.getEstado() instanceof EstadoCancelado)) {
            mostrarAlerta("No se puede revisar el pedido. El pedido está en proceso.", AlertType.ERROR);
            return;
        }

        StringBuilder mensaje = new StringBuilder();
        double total = 0.0;

        for (Producto p : productosParaPedido) {
            mensaje.append(p.toString()).append("\n");
            total += p.getPrecio(); // Aquí vas sumando los precios
        }

        if (mensaje.length() == 0) {
            mostrarAlerta("Aún no hay productos en el pedido.", Alert.AlertType.INFORMATION);
        } else {
            mensaje.append("\nTotal: $").append(String.format("%.2f", total));
            mostrarAlerta("Hola " + pedido.getCliente().getNombre() + ", estos son tus productos:\n\n" + mensaje.toString(), Alert.AlertType.INFORMATION);
        }
    }
    
    @FXML
    void cancelarState(ActionEvent event) {
        String resultado = context.cancelar();
        mostrarAlerta(resultado, Alert.AlertType.INFORMATION);
    }

    private void mostrarAlerta(String mensaje, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setHeaderText(null);
        alerta.setTitle("Información");
        TextArea areaTexto = new TextArea(mensaje);
        areaTexto.setEditable(false);
        areaTexto.setWrapText(true);
        areaTexto.setMaxWidth(100);
        areaTexto.setMaxHeight(200);
        alerta.getDialogPane().setContent(areaTexto);
        alerta.showAndWait();
    }
}