package co.edu.poli.controlador;

import java.util.function.Predicate;

import co.edu.poli.modelo.Cliente;
import co.edu.poli.modelo.Manejador;
import co.edu.poli.modelo.ManejadorCliente;
import co.edu.poli.modelo.ManejadorProducto;
import co.edu.poli.modelo.ManjeadorPedido;
import co.edu.poli.modelo.Pedido;
import co.edu.poli.modelo.Producto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class controladorPatrones {
    private boolean mostrar = true;
    private ToggleGroup grupo = new ToggleGroup();
    private Cliente cliente = new Cliente("Andres", false);
    private Pedido pedido = new Pedido(cliente);

    @FXML
    private Button bttAñadir, bttChain, bttStrategy, bttValidar, bttCambiar;
    @FXML
    private AnchorPane contenedorChain;

    @FXML
    private TextField descripcionProducto,precioProducto;

    @FXML
    private RadioButton radioActivo, radioInactivo;

    @FXML
    public void initialize(){
        radioActivo.setToggleGroup(grupo);
        radioInactivo.setToggleGroup(grupo);
        radioActivo.setUserData(true);
        radioInactivo.setUserData(false);
    }

    @FXML
    void clickAñadir(ActionEvent event) {
        if(!(descripcionProducto.getText().isEmpty() && precioProducto.getText().isEmpty())){
            double precio = Double.parseDouble(precioProducto.getText());
            Producto producto = new Producto(descripcionProducto.getText(),precio);
            pedido.agregarProductos(producto);
            mostrarAlerta("Producto Agregado Con Exito\n" +producto.toString(), AlertType.INFORMATION);
            descripcionProducto.clear();
            precioProducto.clear();
        }
        else{
            mostrarAlerta("Rellena todos los campos", AlertType.ERROR);
        }
    }

    @FXML
    void clickChain(ActionEvent event) {
        mostrar= !mostrar;
        bttStrategy.setVisible(mostrar);
        contenedorChain.setVisible(!mostrar);
    }

    @FXML
    void clickStrategy(ActionEvent event) {
        mostrar = !mostrar;
        bttChain.setVisible(mostrar);
    }

    @FXML
    void clickValidar(ActionEvent event) {
        Manejador manejador = new ManejadorCliente();
        manejador.setManejador(new ManejadorProducto()).setManejador(new ManjeadorPedido());
        mostrarAlerta(manejador.handle(pedido) + "\n" + pedido.toString() + "\nEl total Seria:$"+pedido.getTotal() , AlertType.INFORMATION);
    }

    @FXML
    void clickCambiar(ActionEvent event) {
        if(grupo.getSelectedToggle() !=null){
            cliente.setEstado((boolean)grupo.getSelectedToggle().getUserData());
            mostrarAlerta("Estado del Cliente Modificado\n" + cliente.toString(), AlertType.INFORMATION);
            grupo.selectToggle(null);
        }
        else{
            mostrarAlerta("Selecciona Un estado", AlertType.ERROR);
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
