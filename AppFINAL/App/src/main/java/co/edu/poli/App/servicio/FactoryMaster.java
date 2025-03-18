package co.edu.poli.App.servicio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import co.edu.poli.App.modelo.Producto;
import co.edu.poli.App.modelo.ProductoAlimenticioFactory;
import co.edu.poli.App.modelo.ProductoElectricoFactory;

public class FactoryMaster {

    public static Producto crearProducto(ResultSet rs, Connection conexion) throws SQLException {
        int id = rs.getInt("id");
        String descripcion = rs.getString("descripcion");
        String tipo = rs.getString("tipo");
    
        if ("ALIMENTICIO".equalsIgnoreCase(tipo)) {
            return obtenerProductoAlimenticio(id, descripcion, conexion);
        } else if ("ELECTRICO".equalsIgnoreCase(tipo)) {
            return obtenerProductoElectrico(id, descripcion, conexion);
        }
        
        return null;
    }

    private static Producto obtenerProductoAlimenticio(int id, String descripcion, Connection conexion) throws SQLException {
        var sql = "SELECT aporteCalorico FROM producto_alimenticio WHERE id=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ProductoAlimenticioFactory().crearProducto(id, descripcion, rs.getString("aporteCalorico"));
                }
            }
        }
        return null;
    }

    private static Producto obtenerProductoElectrico(int id, String descripcion, Connection conexion) throws SQLException {
        var sql = "SELECT voltaje FROM producto_electrico WHERE id=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ProductoElectricoFactory().crearProducto(id, descripcion, rs.getString("voltaje"));
                }
            }
        }
        return null;
    }
}