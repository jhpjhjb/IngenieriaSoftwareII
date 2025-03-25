module co.edu.poli.IngSoftware {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.web;
    requires javafx.media;
    
    requires java.sql;
    requires mysql.connector.j;
    requires java.desktop; 

    opens co.edu.poli.App.view to javafx.fxml;
    opens co.edu.poli.App.controlador to javafx.fxml;
    
    exports co.edu.poli.App.controlador;
    exports co.edu.poli.App.modelo;
    exports co.edu.poli.App.servicio;
    exports co.edu.poli.App.view;
}
