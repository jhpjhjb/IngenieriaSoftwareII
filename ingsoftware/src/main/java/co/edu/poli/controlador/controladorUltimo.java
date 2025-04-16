package co.edu.poli.controlador;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import co.edu.poli.modelo.Facade;
import co.edu.poli.modelo.FormasPago;
import co.edu.poli.modelo.Historial;
import co.edu.poli.modelo.InformacionPersonal;
import co.edu.poli.modelo.Producto;
import co.edu.poli.modelo.Productos;
import co.edu.poli.modelo.Proxy;
import co.edu.poli.modelo.Ussers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;


public class controladorUltimo {
    private String accionSeleccionada = null;
    private Facade facade;
    private Historial historia = new Historial();
    private FormasPago formasPago = new FormasPago();
    private InformacionPersonal infoP;
    private ToggleGroup grupo2;
    private ToggleGroup grupo;
    
    private boolean proxyActivo = false;
    private boolean facadeActivo = false;
    private boolean flyweight = false;
    @FXML private RadioButton bttElon, bttPetro, bttSusa, paypalRadio, nequiRadio, daviplataRadio;

    @FXML private CheckBox bttFerrari, bttHelicoptero, bttInvencible;

    @FXML private Button flyweightButton, bttProxy, bttdetallesProxy, crearProductoBtt, productosFlyBtt,
                         // Facade 
                         facadeButton, gestionarBtt, bloquearBtt, activarBtt, historialBtt, mostrarMetBtt;

    @FXML private AnchorPane anchorBalanza, anchorBarrera, anchorTemplo, metodoPagoAnchor;
    @FXML private TextField nombreProductoTA, precioTA;
    @FXML private Label productoLabel, precioLabel;




    @FXML
    public void initialize(){
        grupo = new ToggleGroup();
        grupo2 = new ToggleGroup();
        paypalRadio.setToggleGroup(grupo2);
        nequiRadio.setToggleGroup(grupo2);
        daviplataRadio.setToggleGroup(grupo2);
        bttElon.setToggleGroup(grupo);
        bttPetro.setToggleGroup(grupo);
        bttSusa.setToggleGroup(grupo);
        ocultarComponentes();
        mostrarMenuPrincipal();
    }

    private void ocultarComponentes() {
        bttFerrari.setVisible(false);
        bttHelicoptero.setVisible(false);
        bttInvencible.setVisible(false);
        bttElon.setVisible(false);
        bttPetro.setVisible(false);
        bttSusa.setVisible(false);
        bttdetallesProxy.setVisible(false);
        mostrarMetBtt.setVisible(false);
        activarBtt.setVisible(false);

        historialBtt.setVisible(false);
        bloquearBtt.setVisible(false);
        metodoPagoAnchor.setVisible(false);
        paypalRadio.setVisible(false);
        nequiRadio.setVisible(false);
        daviplataRadio.setVisible(false);
        activarBtt.setVisible(false);
        productoLabel.setVisible(false);
        precioLabel.setVisible(false);
        nombreProductoTA.setVisible(false);
        precioTA.setVisible(false);
        productosFlyBtt.setVisible(false);
        crearProductoBtt.setVisible(false);
    }

    @FXML
    void mostrarProxy(ActionEvent event) {
        if(grupo.getSelectedToggle() == null){
            JOptionPane.showMessageDialog(null, "Selecciona Un Usuario", "Ouch Error :(",JOptionPane.ERROR_MESSAGE);
        }
        else{
            Ussers usuario = (bttElon.isSelected())? new Ussers("Elon Musk", 10): (bttPetro.isSelected()) ? new Ussers("Gustavo Petro", 5) : new Ussers("Diego Susa", 1);
            List<Productos> productos = new ArrayList<>();
            if (bttFerrari.isSelected()) {
                productos.add(new Productos("Premium", "Ferrari Laferrari", 10_000_000, "SUPER WOW", 10));
            }
        
            if (bttHelicoptero.isSelected()) {
                productos.add(new Productos("Estatal", "Helicóptero de Viaje", 50_000, "MEDIO WOW", 5));
            }
        
            if (bttInvencible.isSelected()) {
                productos.add(new Productos("Promedio", "Cómic Invencible", 100, "NADA WOW", 1));
            }
            if (productos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Selecciona Un Producto para: " + usuario.getNombreUsuario(), "Ouch Error :(",JOptionPane.ERROR_MESSAGE);
            } else {
                Proxy proxy = new Proxy(productos, usuario);
                mostrarAlerta("Lista De Accesos :)", proxy.detalles());
            }

        }
    }

    private void mostrarAlerta(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);

        TextArea textArea = new TextArea(contenido);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        alert.getDialogPane().setContent(textArea);
        alert.showAndWait();
    }

    @FXML
    void proxyClick(ActionEvent event) {
        if (!proxyActivo) {
            bttProxy.setLayoutX(202);
            bttProxy.setLayoutY(120);
            flyweightButton.setVisible(false);
            facadeButton.setVisible(false);
            bttFerrari.setVisible(true);
            bttHelicoptero.setVisible(true);
            bttInvencible.setVisible(true);
            bttElon.setVisible(true);
            bttPetro.setVisible(true);
            bttSusa.setVisible(true);
            bttdetallesProxy.setVisible(true);
            anchorBalanza.setVisible(false);
            anchorBarrera.setVisible(false);
            anchorTemplo.setVisible(false);
            proxyActivo = true;
        } else {
            mostrarMenuPrincipal();
        }
    }

    @FXML
    void facadeClick(ActionEvent event) {
        if(!facadeActivo){
            bttProxy.setVisible(false);
            flyweightButton.setVisible(false);
            anchorBalanza.setVisible(false);
            anchorBarrera.setVisible(false);
            anchorTemplo.setVisible(false);
            mostrarMetBtt.setVisible(true);
            activarBtt.setVisible(true);
            historialBtt.setVisible(true);
            bloquearBtt.setVisible(true);
            metodoPagoAnchor.setVisible(true);
            paypalRadio.setVisible(true);
            nequiRadio.setVisible(true);
            daviplataRadio.setVisible(true);
            facadeButton.setLayoutX(216);
            facadeButton.setLayoutY(120);
            // Facade
            gestionarBtt.setVisible(true);
            facadeActivo = true;
        } else {
            mostrarMenuPrincipal();
        }
    }

    @FXML
    void flyweightClick(ActionEvent event) {
        if (!flyweight) {
            flyweightButton.setLayoutX(216);
            flyweightButton.setLayoutY(120);
            productoLabel.setVisible(true);
            precioLabel.setVisible(true);
            nombreProductoTA.setVisible(true);
            precioTA.setVisible(true);
            productosFlyBtt.setVisible(true);
            crearProductoBtt.setVisible(true);
            facadeButton.setVisible(false);
            bttProxy.setVisible(false);
            anchorBalanza.setVisible(false);
            anchorBarrera.setVisible(false);
            anchorTemplo.setVisible(false);
            
            flyweight = true;
        } else {
            mostrarMenuPrincipal();
        }
       
    }

    private void mostrarMenuPrincipal() {
        proxyActivo = false;
        facadeActivo = false;
        flyweight = false;
        facadeButton.setLayoutX(132);
        facadeButton.setLayoutY(133);
        bttProxy.setLayoutX(137);
        bttProxy.setLayoutY(211);
        flyweightButton.setLayoutX(102);
        flyweightButton.setLayoutY(291);
        bttFerrari.setVisible(false);
        bttHelicoptero.setVisible(false);
        bttInvencible.setVisible(false);
        bttElon.setVisible(false);
        bttPetro.setVisible(false);
        bttSusa.setVisible(false);
        bttdetallesProxy.setVisible(false);
        anchorBalanza.setVisible(true);
        anchorBarrera.setVisible(true);
        anchorTemplo.setVisible(true);
        facadeButton.setVisible(true);
        flyweightButton.setVisible(true);
        bttProxy.setVisible(true);
        mostrarMetBtt.setVisible(false);
        activarBtt.setVisible(false);
        historialBtt.setVisible(false);
        activarBtt.setVisible(false);
        bloquearBtt.setVisible(false);
        metodoPagoAnchor.setVisible(false);
        paypalRadio.setVisible(false);
        nequiRadio.setVisible(false);
        daviplataRadio.setVisible(false);
        gestionarBtt.setVisible(false);
        productoLabel.setVisible(false);
        precioLabel.setVisible(false);
        nombreProductoTA.setVisible(false);
        precioLabel.setVisible(false);
        crearProductoBtt.setVisible(false);
        productosFlyBtt.setVisible(false);
        precioTA.setVisible(false);
    }

    @FXML
    void gestionarClick(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Actualizar Cliente");
        dialog.setHeaderText("Ingrese su nombre");
        dialog.setContentText("Nombre:");

        String nombre = dialog.showAndWait().orElse("");
        if (nombre.isEmpty()) return;

        dialog.getEditor().clear();
        dialog.setHeaderText("Ingrese su correo");
        dialog.setContentText("Correo: ");

        String correo = dialog.showAndWait().orElse("");
        if (correo.isEmpty()) return;

        infoP = new InformacionPersonal(nombre, correo);

        facade = new Facade(infoP, historia, formasPago);
        mostrarAlerta("Cliente", facade.actualizarInformacion(nombre, correo));
    }

    @FXML
    void activarClick(ActionEvent event) {
        accionSeleccionada = "activar";
        activarBtt.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        bloquearBtt.setStyle("");
    }

    @FXML
    void bloquearClick(ActionEvent event) {
        accionSeleccionada = "bloquear";
        bloquearBtt.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        activarBtt.setStyle("");
    }

    @FXML
    void historialClick(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Actualizar Cliente");
        dialog.setHeaderText("Ingrese su pedido");
        dialog.setContentText("Pedido:");

        String pedido = dialog.showAndWait().orElse("");
        if (pedido.isEmpty()) return;

        facade = new Facade(infoP, historia, formasPago);
        mostrarAlerta("Hsitorial Pedido", facade.consultarPedidos(pedido));
    }

    @FXML
    void monedaClick(ActionEvent event) {
        Toggle metodoPagoSeleccionado = grupo2.getSelectedToggle();
        facade = new Facade(infoP, historia, formasPago);
        
        if (metodoPagoSeleccionado == null || accionSeleccionada == null){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un método de pago y una acción (activar o bloquear).");
            alert.showAndWait();
            return;
        }

        RadioButton metodoPago = (RadioButton) metodoPagoSeleccionado;
        String metodo = metodoPago.getText();

        if (accionSeleccionada.equals("activar")) {
            mostrarAlerta("Metodos de Pago:", facade.accionPago(accionSeleccionada, metodo));
            grupo2.selectToggle(null);
            accionSeleccionada = null;
            activarBtt.setStyle("");
        } else if (accionSeleccionada.equals("bloquear")) {
            mostrarAlerta("Metodos de Pago:", facade.accionPago(accionSeleccionada, metodo));
            grupo2.selectToggle(null);
            accionSeleccionada = null;
            bloquearBtt.setStyle("");
        }
    }


    @FXML
    void crearProductoBtt(ActionEvent event) {
        String nombre = nombreProductoTA.getText().trim();
        String precioTexto = precioTA.getText().trim();

        if (nombre.isEmpty() || precioTexto.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, completa todos los campos vacios");
            alert.showAndWait();            
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioTexto);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("El precio debe ser un número valido");
            alert.showAndWait();  
            return;
        }
        Producto producto = new Producto(nombre, precio, "Paulis Shop");
        historia.agregarProducto(producto);
        mostrarAlerta("EXITO", "Producto creado de manera exitosa");
        nombreProductoTA.clear();
        precioTA.clear();
    }

    @FXML
    void productosFlyClick(ActionEvent event) {
        mostrarAlerta("Historial de Productos", historia.mostrarHistorialProducto());
    }
}
