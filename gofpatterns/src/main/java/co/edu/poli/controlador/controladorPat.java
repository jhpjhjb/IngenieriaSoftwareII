package co.edu.poli.controlador;

import co.edu.poli.modelo.AjustadorPrecios;
import co.edu.poli.modelo.Producto;
import co.edu.poli.modelo.TipoEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;

public class controladorPat {
    private  boolean mostrar = true;
    private TipoEnum tipo;
    private AjustadorPrecios ajustador = new AjustadorPrecios();
    private Producto producto = new Producto(2024, "Susa Gei", 1500);
    private ToggleGroup grupo = new ToggleGroup();
    @FXML
    private AnchorPane contenedorObser;
    @FXML
    private Button bttMas;

    @FXML
    private Button bttMenos;

    @FXML
    private RadioButton desc10;

    @FXML
    private RadioButton desc15;

    @FXML
    private RadioButton desc25;

    @FXML
    private RadioButton desc50;

    @FXML
    private TextField otroValor;

    @FXML
    private Button patronMemento;

    @FXML
    private Button patronObserver;

    @FXML
    public void initialize(){
        grupo.getToggles().add(desc10);
        grupo.getToggles().add(desc25);
        grupo.getToggles().add(desc50);
        grupo.getToggles().add(desc15);

        desc10.setUserData(10);
        desc15.setUserData(15);
        desc25.setUserData(25);
        desc50.setUserData(50);
        ajustador.attach(producto);
    }

    @FXML
    void clickMas(ActionEvent event) {
        tipo = TipoEnum.REDUCIR;
        ajustarPrecios(tipo);
    }
    @FXML
    void clickMenos(ActionEvent event) {
        tipo = TipoEnum.INCREMENTAR;
        ajustarPrecios(tipo);

    }


    public void ajustarPrecios(TipoEnum tipo){
        String anterior = "Producto sin Variacion de " + (tipo.equals(TipoEnum.INCREMENTAR)? "Aumento" : "Reduccion" ) + "\n" + producto.toString();
        if (grupo.getSelectedToggle() != null) {
            int porcentaje = (int) grupo.getSelectedToggle().getUserData();
            ajustador.setPorcentaje(porcentaje, tipo);
            mostrarAlerta(anterior + "\nDescuento " + porcentaje + "% "+(tipo.equals(TipoEnum.INCREMENTAR)? "Sumado" : "Restado") + " a Todos los Productos\n" + producto.toString(), AlertType.INFORMATION);
        } 
        else if (!otroValor.getText().isEmpty()) {
            try {
                int porcentaje = Integer.parseInt(otroValor.getText());
                ajustador.setPorcentaje(porcentaje, tipo);
                mostrarAlerta(anterior + "\nDescuento " + porcentaje + "% "+(tipo.equals(TipoEnum.INCREMENTAR)? "Sumado" : "Restado") + " a Todos los Productos\n" + producto.toString(), AlertType.INFORMATION);
            } catch (NumberFormatException e) {
                mostrarAlerta("Porcentaje ingresado no es válido", AlertType.ERROR);
            }
        } 
        
        else {
            mostrarAlerta("Selecciona o digita alguna opción", AlertType.ERROR);
        }
        grupo.selectToggle(null);
        otroValor.clear();
    }

    @FXML
    void clickMemento(ActionEvent event) {
        mostrar = !mostrar;
        patronObserver.setVisible(mostrar);
        contenedorObser.setVisible(false);
    }

    @FXML
    void clickObserver(ActionEvent event) {
        mostrar = !mostrar;
        patronMemento.setVisible(mostrar);
        contenedorObser.setVisible(!mostrar);
    }


    private void mostrarAlerta(String mensaje, AlertType alert){
        Alert alerta = new Alert(alert);
        alerta.setHeaderText(null);
        TextArea textArea = new TextArea(mensaje);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        alerta.getDialogPane().setContent(textArea);
        alerta.show();
    }


    @FXML
    void clickOtroValor(MouseEvent event) {
        grupo.selectToggle(null);
    }
}
