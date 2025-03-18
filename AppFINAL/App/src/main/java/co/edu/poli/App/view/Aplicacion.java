package co.edu.poli.App.view;

import co.edu.poli.App.controlador.ControladorCliente;
import co.edu.poli.App.controlador.ControladorClienteFX;
import co.edu.poli.App.controlador.ControladorProductos;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Aplicacion extends Application{
    private static Scene escena;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfazCliente.fxml"));
        Scene scene = new Scene(loader.load());
        ControladorClienteFX controladorFX = loader.getController();
        System.out.println("se asigna correctamente");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestión de Clientes");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch();
    }
    


}
