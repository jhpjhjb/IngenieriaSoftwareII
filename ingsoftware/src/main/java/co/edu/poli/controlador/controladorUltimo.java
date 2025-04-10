package co.edu.poli.controlador;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.JobHoldUntil;
import javax.swing.JOptionPane;

import co.edu.poli.modelo.Productos;
import co.edu.poli.modelo.Proxy;
import co.edu.poli.modelo.Ussers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;

public class controladorUltimo {
    private ToggleGroup grupo;
    @FXML private RadioButton bttElon, bttPetro, bttSusa;
    @FXML private CheckBox bttFerrari, bttHelicoptero, bttInvencible;
    @FXML private Button bttdetallesProxy;
    @FXML
    public void initialize(){
        grupo = new ToggleGroup();
        bttElon.setToggleGroup(grupo);
        bttPetro.setToggleGroup(grupo);
        bttSusa.setToggleGroup(grupo);
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



}
