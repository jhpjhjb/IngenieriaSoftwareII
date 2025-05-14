package co.edu.poli.controlador;

import java.util.function.Predicate;

import co.edu.poli.modelo.Cliente;
import co.edu.poli.modelo.DescuentoContext;
import co.edu.poli.modelo.DescuentoMetodoPago;
import co.edu.poli.modelo.DescuentoPorCantidad;
import co.edu.poli.modelo.DescuentoProductoEspecifico;
import co.edu.poli.modelo.Pedido;
import co.edu.poli.modelo.Producto;
import co.edu.poli.modelo.Chain.Manejador;
import co.edu.poli.modelo.Chain.ManejadorCliente;
import co.edu.poli.modelo.Chain.ManejadorProducto;
import co.edu.poli.modelo.Chain.ManjeadorPedido;
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
    private Cliente cliente = new Cliente("Andres", false);
    private Pedido pedido = new Pedido(cliente);
    private Producto producto1 = new Producto("Zapatos Nike", 200.0);
    private Producto producto2 = new Producto("Camisa", 80.0);
    private Producto producto3 = new Producto("Pantalón", 120.0);
    private ObservableList<Producto> productosParaPedido = FXCollections.observableArrayList();
    private DescuentoContext contextoDescuento = new DescuentoContext(new DescuentoPorCantidad());
    private ObservableList<Producto> productosDisponibles = FXCollections.observableArrayList();

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

        
        radioActivo.setToggleGroup(grupo);
        radioInactivo.setToggleGroup(grupo);
        radioActivo.setUserData(true);
        radioInactivo.setUserData(false);

      
        RadioPaypal.setToggleGroup(grupoPago);
        RadioTarjeta.setToggleGroup(grupoPago);
        RadioPaypal.setUserData("PayPal");
        RadioTarjeta.setUserData("Tarjeta Debito");

       
        contenedorChain.setVisible(false);
        contenedorSrategy.setVisible(false);
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