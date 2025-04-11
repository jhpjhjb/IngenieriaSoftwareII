package co.edu.poli.controlador;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import co.edu.poli.modelo.ClienteFacade;
import co.edu.poli.modelo.Productos;
import co.edu.poli.modelo.Proxy;
import co.edu.poli.modelo.Ussers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;


public class controladorUltimo {
    private ClienteFacade cliente;
    private ToggleGroup grupo2;
    private ToggleGroup grupo;
    private boolean proxyActivo = false;
    private boolean facadeActivo = false;
    @FXML private RadioButton bttElon, bttPetro, bttSusa, paypalRadio, nequiRadio, daviplataRadio;
    @FXML private CheckBox bttFerrari, bttHelicoptero, bttInvencible;
    @FXML private Button facadeButton, flyweightButton, bttProxy, bttdetallesProxy, activarBtt, actualizarBtt, bloquearBtt, hacerPedidoBtt, historialBtt, mostrarMetBtt, mostrarClienteBtt;
    @FXML private AnchorPane anchorBalanza, anchorBarrera, anchorTemplo, metodoPagoAnchor;

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
        mostrarClienteBtt.setVisible(false);
        mostrarMetBtt.setVisible(false);
        activarBtt.setVisible(false);
        hacerPedidoBtt.setVisible(false);
        historialBtt.setVisible(false);
        bloquearBtt.setVisible(false);
        metodoPagoAnchor.setVisible(false);
        paypalRadio.setVisible(false);
        nequiRadio.setVisible(false);
        daviplataRadio.setVisible(false);
        activarBtt.setVisible(false);
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
            bttProxy.setLayoutX(216);
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
        if (!facadeActivo) {
            if (cliente == null) {
                String nombre = null;
                do {
                    nombre = JOptionPane.showInputDialog(null, "Ingrese su nombre:", "Datos del cliente", JOptionPane.QUESTION_MESSAGE);
                    if (nombre == null) return;
                    nombre = nombre.trim();
                } while (nombre.isEmpty());

                String correo = null;
                do {
                    correo = JOptionPane.showInputDialog(null, "Ingrese su correo:", "Datos del cliente", JOptionPane.QUESTION_MESSAGE);
                    if (correo == null) return;
                    correo = correo.trim();
                } while (correo.isEmpty());

                cliente = new ClienteFacade(nombre, correo);
                JOptionPane.showMessageDialog(null, "Cliente creado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }

            bttProxy.setVisible(false);
            flyweightButton.setVisible(false);
            anchorBalanza.setVisible(false);
            anchorBarrera.setVisible(false);
            anchorTemplo.setVisible(false);
            mostrarClienteBtt.setVisible(true);
            mostrarMetBtt.setVisible(true);
            activarBtt.setVisible(true);
            hacerPedidoBtt.setVisible(true);
            historialBtt.setVisible(true);
            bloquearBtt.setVisible(true);
            metodoPagoAnchor.setVisible(true);
            paypalRadio.setVisible(true);
            nequiRadio.setVisible(true);
            daviplataRadio.setVisible(true);
            facadeButton.setLayoutX(216);
            facadeButton.setLayoutY(120);
            actualizarBtt.setVisible(true);
            facadeActivo = true;
        } else {
            mostrarMenuPrincipal();
        }
    }

    @FXML
    void flyweightClick(ActionEvent event) {

    }

    private void mostrarMenuPrincipal() {
        proxyActivo = false;
        facadeActivo = false;
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
        mostrarClienteBtt.setVisible(false);
        mostrarMetBtt.setVisible(false);
        activarBtt.setVisible(false);
        hacerPedidoBtt.setVisible(false);
        historialBtt.setVisible(false);
        activarBtt.setVisible(false);
        bloquearBtt.setVisible(false);
        metodoPagoAnchor.setVisible(false);
        paypalRadio.setVisible(false);
        nequiRadio.setVisible(false);
        daviplataRadio.setVisible(false);
        actualizarBtt.setVisible(false);
    }

    @FXML
    void actualizarClick(ActionEvent event) {
        String nombre = null;
        do {
            nombre = JOptionPane.showInputDialog(null, "Ingrese su nombre:", "Datos del cliente", JOptionPane.QUESTION_MESSAGE);
            if (nombre == null) return; 
            nombre = nombre.trim();
        } while (nombre.isEmpty());

        String correo = null;
        do {
            correo = JOptionPane.showInputDialog(null, "Ingrese su correo:", "Datos del cliente", JOptionPane.QUESTION_MESSAGE);
            if (correo == null) return;
            correo = correo.trim();
        } while (correo.isEmpty());

        cliente.actualizarInformacion(nombre, correo);
        JOptionPane.showMessageDialog(null, "Cliente actualizado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    void activarClick(ActionEvent event) {
        if (grupo2.getSelectedToggle() != null) {
            String metodo = ((RadioButton) grupo2.getSelectedToggle()).getText();
            cliente.activarPago(metodo);
            JOptionPane.showMessageDialog(null, "Método activado: " + metodo);
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un método de pago primero", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void bloquearClick(ActionEvent event) {
        if (grupo2.getSelectedToggle() != null) {
            String metodo = ((RadioButton) grupo2.getSelectedToggle()).getText();
            cliente.bloquearPago(metodo);
            JOptionPane.showMessageDialog(null, "Método bloqueado: " + metodo);
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un método de pago primero", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void hacerPedidoClick(ActionEvent event) {
        String producto = null;
        do {
            producto = JOptionPane.showInputDialog(null, "Ingrese su producto:", "Datos del cliente", JOptionPane.QUESTION_MESSAGE);
            if (producto == null) return;
            producto = producto.trim();
        } while (producto.isEmpty());

        cliente.ingresarPedidos(producto);
        JOptionPane.showMessageDialog(null, "Producto ingresado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    void historialClick(ActionEvent event) {
        String info = cliente.verHistorialPedidos();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Historial de pedidos");
        alert.setHeaderText(null);
        alert.setContentText(info);
        alert.showAndWait();
    }

    @FXML
    void monedaClick(ActionEvent event) {
        String info = cliente.verFormasDePago();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Metodos de Pago");
        alert.setHeaderText(null);
        alert.setContentText(info);
        alert.showAndWait();
    }

    @FXML
    void mostrarClienteClick(ActionEvent event) {
        String info = cliente.mostrarInformacion();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información del Cliente");
        alert.setHeaderText(null);
        alert.setContentText(info);
        alert.showAndWait();
    }

}
