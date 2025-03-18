package co.edu.poli.App.servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.poli.App.modelo.Producto;
import co.edu.poli.App.modelo.ProductoElectrico;

public class DaoProductoElectrico implements IDaoProducto<Producto> {

    private Connection conexionProducto;

    public DaoProductoElectrico() throws ClassNotFoundException, SQLException {
        this.conexionProducto = Conexion.getConexion();
    }

    @Override
    public List<Producto> listarObjetos() {
        List<Producto> productos = new ArrayList<>();

        var sql = "SELECT id, descripcion, voltaje FROM producto_electrico";

        try (PreparedStatement ps = conexionProducto.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String descripcion = rs.getString("descripcion");
                String voltaje = rs.getString("voltaje");

                productos.add(new ProductoElectrico(id, descripcion, voltaje));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    @Override
    public String ingresarObjeto(Producto producto) {
        var sql = "INSERT INTO producto_electrico (descripcion, voltaje) VALUES (?, ?)";

        try (PreparedStatement ps = conexionProducto.prepareStatement(sql)) {
            ps.setString(1, producto.getDescripcion());
            ps.setString(2, ((ProductoElectrico) producto).getVoltaje());
            int filasAfectadas = ps.executeUpdate();
            return (filasAfectadas > 0) ? "Producto eléctrico agregado correctamente." : "No se pudo agregar el producto.";

        } catch (Exception e) {
            e.printStackTrace();
            return "No se pudo ingresar el producto.";
        }
    }

    @Override
    public Producto buscarPorId(int id) {
        var sql = "SELECT * FROM producto_electrico WHERE id=?";
        Producto producto = null;

        try (PreparedStatement ps = conexionProducto.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    producto = new ProductoElectrico(rs.getInt("id"), rs.getString("descripcion"), rs.getString("voltaje"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return producto;
    }

    @Override
    public String actualizarObjeto(Producto producto) {
        var sql = "UPDATE producto_electrico SET descripcion=?, voltaje=? WHERE id=?";
        try (PreparedStatement ps = conexionProducto.prepareStatement(sql)) {
            ps.setString(1, producto.getDescripcion());
            ps.setString(2, ((ProductoElectrico) producto).getVoltaje() + " Vl");
            ps.setInt(3, producto.getId());

            int filasAfectadas = ps.executeUpdate();
            return (filasAfectadas > 0) ? "Producto eléctrico actualizado con éxito" : "No se pudo actualizar el producto";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al actualizar el producto";
        }
    }

    @Override
    public Producto eliminarObjeto(int id) {
        var consulta = "SELECT * FROM producto_electrico WHERE id=?";
        var eliminacion = "DELETE FROM producto_electrico WHERE id=?";
        Producto producto = null;

        try (PreparedStatement ps = conexionProducto.prepareStatement(consulta);
             PreparedStatement pr = conexionProducto.prepareStatement(eliminacion)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    producto = this.buscarPorId(id);
                    pr.setInt(1, id);
                    pr.executeUpdate();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return producto;
    }

    @Override
    public List<Producto> consultaDetallada(Producto producto) {
        return null;
    }
}
