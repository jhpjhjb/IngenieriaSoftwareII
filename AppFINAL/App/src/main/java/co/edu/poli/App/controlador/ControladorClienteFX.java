package co.edu.poli.App.controlador;

import java.sql.SQLException;
import java.util.List;

import co.edu.poli.App.modelo.Cliente;
import co.edu.poli.App.modelo.Producto;
import co.edu.poli.App.modelo.ProductoElectrico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControladorClienteFX {


    private ControladorCliente controladorCliente;
    private Button ultimoBotonPresionado;
    private ControladorProductos controladorProductos;

    public ControladorClienteFX() throws ClassNotFoundException, SQLException {
        this.controladorProductos = new ControladorProductos();
        this.controladorCliente = new ControladorCliente();
    }
    
    @FXML
    private Button bttOk;

    @FXML
    private Button clonar;

    @FXML
    private Button consult;

    @FXML
    private Button consultid;

    @FXML
    private Button delete;

    @FXML
    private TextField id1;

    @FXML
    private Button insert;

    @FXML
    private TextField nombre;

    @FXML
    private Button update;

    @FXML
    void clickActualizar(ActionEvent event) {
        ultimoBotonPresionado = update;
        id1.setDisable(false);
        id1.clear();
        nombre.setDisable(false);
        nombre.clear();
    }

    @FXML
    void clickClonarId(ActionEvent event) {
        ultimoBotonPresionado = clonar;
        id1.setPromptText("Id Producto");
        nombre.setPromptText("Tipo de Producto");
        id1.setDisable(false);
        nombre.setDisable(false);
        id1.clear();
        nombre.clear();

    }

    @FXML
    void clickConsultar(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        List<Cliente> cliente = controladorCliente.consultarCliente();
        id1.clear();
        nombre.clear();
        String clientes = "";
        for (Cliente clientex: cliente){
            clientes+= clientex + "\n";
        }
        alert.setContentText(clientes);
        alert.setTitle("Lista de Clientes :)");
        alert.show();
    }

    @FXML
    void clickConsultarId(ActionEvent event) {
        ultimoBotonPresionado = consultid;
        id1.setDisable(false);
        nombre.setDisable(true);
        bttOk.setDisable(false);
    }

    @FXML
    void clickEliminar(ActionEvent event) {
        ultimoBotonPresionado = delete;
        id1.setDisable(false);
        nombre.setDisable(true);

    }

    @FXML
    void clickInsertar(ActionEvent event) {
        ultimoBotonPresionado = insert;
        id1.setDisable(true);
        nombre.setDisable(false);

    }

    @FXML
    void clickOk(ActionEvent event) {
        int id = 0;
        if (!id1.getText().isEmpty()){
            bttOk.setDisable(false);
            id = Integer.parseInt(id1.getText());
        }
        Cliente cliente = null;
        String nombres = nombre.getText();
        Alert alert = new Alert(AlertType.INFORMATION);

        if(ultimoBotonPresionado == consultid){
            cliente= controladorCliente.buscarId(id);
            alert.setHeaderText("Informacion Sobre:  " + cliente.getNombre() + " :)");
            alert.setContentText(cliente.toString());
            alert.setTitle("Cliente: " + cliente.getNombre());
            alert.show();
        }
        else if(ultimoBotonPresionado == delete){
            cliente = controladorCliente.eliminarClientes(id);
            alert.setContentText(cliente.toString());
            alert.setHeaderText("Cliente Eliminado Exitosamente: " + cliente.getNombre() + " :(");
            alert.setTitle("Cliente Eliminado: " + cliente.getNombre());
            alert.show();
        }
        else if(ultimoBotonPresionado == insert){
            cliente = new Cliente(nombres);
            alert.setContentText(controladorCliente.ingresarCliente(cliente) + ": " +cliente.getNombre());
            alert.setHeaderText("Cliente Insertado Exitosamente: " + cliente.getNombre());
            alert.setTitle("Cliente Ingresado: " + cliente.getNombre());
            alert.show();
        }
        else if (ultimoBotonPresionado == update){
            cliente = new Cliente(id,nombres);
            alert.setHeaderText("Cliente Actualizado Exitosamente: " + cliente.getNombre());
            alert.setContentText(controladorCliente.actualizarCliente(cliente) + ": " +cliente.getNombre());
            alert.setTitle("Cliente Actualizado: " + cliente.getNombre());
            alert.show();
        }
        else if (ultimoBotonPresionado == clonar){
            if(nombre.getText().equalsIgnoreCase("electrico")){
                Producto electrico = controladorProductos.mostrarProductoElectrico(id);
                Producto electrico2 = electrico.clonar();
                alert.setHeaderText("Producto Electrico Clonado Exitosamente: " + electrico2.getDescripcion());
                alert.setContentText(electrico2.toString());
                alert.setTitle("Producto Electrico" + electrico2.getDescripcion());
                alert.show();
            }
            else if(nombre.getText().equalsIgnoreCase("alimento")){
                Producto alimento = controladorProductos.mostrarProductoAlimenticio(id);
                Producto alimento2 = alimento.clonar();
                alert.setHeaderText("Producto Alimento Clonado Exitosamente: " + alimento2.getDescripcion());
                alert.setContentText (alimento2.toString());
                alert.setTitle("Producto Alimento " + alimento2.getDescripcion());
                alert.show();
            }

        }
    }
    @FXML
    void validarEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            boolean habilitar = !id1.getText().trim().isEmpty() || !nombre.getText().trim().isEmpty();
            bttOk.setDisable(!habilitar);
        }
    }

}

