package co.edu.poli.App.controlador;

import java.lang.ProcessHandle.Info;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import co.edu.poli.App.modelo.Cliente;
import co.edu.poli.App.modelo.IPagoExterno;
import co.edu.poli.App.modelo.Nequi;
import co.edu.poli.App.modelo.NequiAdapter;
import co.edu.poli.App.modelo.Paypal;
import co.edu.poli.App.modelo.PaypalAdapter;
import co.edu.poli.App.modelo.ProcesarPago;
import co.edu.poli.App.modelo.Producto;
import co.edu.poli.App.modelo.ProductoElectrico;
import co.edu.poli.App.servicio.DaoCliente;
import co.edu.poli.App.servicio.DaoProductoAlimenticio;
import co.edu.poli.App.servicio.DaoProductoElectrico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControladorClienteFX {

    private Boolean mostrar = false;
    private DaoCliente controladorCliente;
    private Button ultimoBotonPresionado;
    private DaoProductoAlimenticio controladorProductoAlimento;
    private DaoProductoElectrico controlaProductoElectrico;


    public ControladorClienteFX() throws ClassNotFoundException, SQLException {
        this.controladorProductoAlimento = new DaoProductoAlimenticio();
        this.controladorCliente = new DaoCliente();
        this.controlaProductoElectrico = new DaoProductoElectrico();
    }
    @FXML
    private Button adapter;

    @FXML
    private Button bttBuild;

    @FXML
    private Button bttNequi;

    @FXML
    private Button bttOk;

    @FXML
    private Button bttPaypal;

    @FXML
    private Button clonar;

    @FXML
    private Button composite;

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
    private TextField montoPagar;

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
        List<Cliente> cliente = controladorCliente.listarObjetos();
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

    private void mostrarAlerta(String titulo, String header, String contenido) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.show();
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

        if(ultimoBotonPresionado == consultid){
            cliente= controladorCliente.buscarPorId(id);
            mostrarAlerta("Cliente" + cliente.getNombre(), "Informacion Sobre: " + cliente.getNombre() + " :)", cliente.toString());
        }
        else if(ultimoBotonPresionado == delete){
            cliente = controladorCliente.eliminarObjeto(id);
            mostrarAlerta("Cliente Eliminado: " + cliente.getNombre(), "Cliente Eliminado Exitosamente: " + cliente.getNombre() + " :(", cliente.toString());
        }
        else if(ultimoBotonPresionado == insert){
            cliente = new Cliente(nombres);
            mostrarAlerta("Cliente Ingresado: " + cliente.getNombre(), "Cliente Insertado Exitosamente: " + cliente.getNombre(), controladorCliente.ingresarObjeto(cliente) + ": " + cliente.getNombre());
        }
        else if (ultimoBotonPresionado == update){
            cliente = new Cliente(id,nombres);
            mostrarAlerta("Cliente Actualizado: " + cliente.getNombre(), "Cliente Actualizado Exitosamente: " + cliente.getNombre(), controladorCliente.actualizarObjeto(cliente) + ": " + cliente.getNombre());
        }
        else if (ultimoBotonPresionado == clonar){
            if(nombre.getText().equalsIgnoreCase("electrico")){
                Producto electrico = controlaProductoElectrico.buscarPorId(id);
                Producto electrico2 = electrico.clonar();
                controlaProductoElectrico.ingresarObjeto(electrico2);
                mostrarAlerta("Producto Electrico" + electrico2.getDescripcion(), "Producto Electrico Clonado Exitosamente: " + electrico2.getDescripcion(), electrico2.toString());
            }
            else if(nombre.getText().equalsIgnoreCase("alimento")){
                Producto alimento = controladorProductoAlimento.buscarPorId(id);
                Producto alimento2 = alimento.clonar();
                controladorProductoAlimento.ingresarObjeto(alimento2);
                mostrarAlerta("Producto Alimento " + alimento2.getDescripcion(), "Producto Alimento Clonado Exitosamente: " + alimento2.getDescripcion(), alimento2.toString());
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

    @FXML
    void clickComposite(ActionEvent event) {
        mostrar = !mostrar;
        adapter.setVisible(!mostrar);
        bttBuild.setVisible(!mostrar);
    }

    @FXML
    void clickAdapter(ActionEvent event) {
        mostrar = !mostrar;
        montoPagar.setVisible(mostrar);
        bttNequi.setVisible(mostrar);
        bttPaypal.setVisible(mostrar);
        composite.setVisible(!mostrar);
        bttBuild.setVisible(!mostrar);

    }

    @FXML
    void clickBuilder(ActionEvent event) {   
    }

    @FXML
    void NequiClick(ActionEvent event) {
        double monto = Double.parseDouble(montoPagar.getText());
        String mensaje = ProcesarPago.procesarPago("Nequi", monto);
        montoPagar.clear();
        JOptionPane.showMessageDialog(null, mensaje); 
    }

    @FXML
    void PaypalClick(ActionEvent event) {
        double monto = Double.parseDouble(montoPagar.getText());
        String mensaje = ProcesarPago.procesarPago("Paypal hola", monto);
        montoPagar.clear();
        JOptionPane.showMessageDialog(null, mensaje);
    }

    @FXML
    private void clickCrearProveedor(ActionEvent event) {
    // Código del botón
    }

    /* 
    void ProcesarPago(String metodo, double monto){
        IPagoExterno pagoExterno = null;
        if(metodo.equalsIgnoreCase("Nequi")){
            String telefono = JOptionPane.showInputDialog("Ingrese Numero de Telefono");
            String nombre = JOptionPane.showInputDialog("Ingrese Nombre");
            pagoExterno = new NequiAdapter(new Nequi(telefono, nombre));
        }
        else if (metodo.equalsIgnoreCase("Paypal")){
            String email = JOptionPane.showInputDialog("Ingrese Email");
            String cuenta = JOptionPane.showInputDialog("Ingrese el numero de Cuenta");
            pagoExterno = new PaypalAdapter(new Paypal(email, cuenta));
        }
        else{
            JOptionPane.showMessageDialog(null, "Metodo de Pago Invalido");
        }
        JOptionPane.showMessageDialog(null, pagoExterno.pagar(monto));

    } */
}

