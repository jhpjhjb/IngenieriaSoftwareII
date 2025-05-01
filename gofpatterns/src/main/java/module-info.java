module co.edu.poli {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.sql;
    requires java.desktop;

    opens co.edu.poli.controlador to javafx.fxml;
    opens co.edu.poli.modelo to javafx.base;
    opens co.edu.poli.vista to javafx.fxml;

    exports co.edu.poli.controlador;
    exports co.edu.poli.vista;
    exports co.edu.poli.modelo;
}
