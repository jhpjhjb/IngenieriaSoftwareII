package co.edu.poli.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class controladorPat {
    private  boolean mostrar = true;
    @FXML
    private Button patronMemento,patronObserver;

    @FXML
    void clickMemento(ActionEvent event) {
        mostrar = !mostrar;
        patronObserver.setVisible(mostrar);
    }

    @FXML
    void clickObserver(ActionEvent event) {
        mostrar = !mostrar;
        patronMemento.setVisible(mostrar);
    }
}
