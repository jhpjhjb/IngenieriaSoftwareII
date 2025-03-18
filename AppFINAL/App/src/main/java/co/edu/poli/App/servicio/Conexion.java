package co.edu.poli.App.servicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Conexion {
    private static Connection conexion = null;
    private static final ResourceBundle recursos = ResourceBundle.getBundle("db");
    
    private Conexion() {}
    
    public static Connection getConexion() throws SQLException, ClassNotFoundException   {
        if (conexion == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = recursos.getString("db.url");
            String baseDatos = recursos.getString("db.base_Datos");
            String usuario = recursos.getString("db.user");
            String password = recursos.getString("db.password");
            conexion = DriverManager.getConnection(url + baseDatos,usuario,password);
        }
        return conexion;
    }
}