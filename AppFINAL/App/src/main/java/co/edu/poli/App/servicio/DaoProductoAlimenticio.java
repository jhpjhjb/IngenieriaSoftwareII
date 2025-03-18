package co.edu.poli.App.servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.poli.App.modelo.Producto;
import co.edu.poli.App.modelo.ProductoAlimenticio;

public class DaoProductoAlimenticio implements IDaoProducto <Producto> {
    
    private Connection conexionProducto;
    public DaoProductoAlimenticio() throws ClassNotFoundException, SQLException{
        this.conexionProducto = Conexion.getConexion();
    }

    @Override
    public List<Producto> listarObjetos() {
        List<Producto> productos = new ArrayList<>();

        var sql = "SELECT id, descripcion, aportecalorico FROM producto_alimenticio";

        try (PreparedStatement ps = conexionProducto.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String descripcion = rs.getString("descripcion");
                String aporteCalorico = rs.getString("aportecalorico");

                productos.add(new ProductoAlimenticio(id, descripcion, aporteCalorico));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }


    @Override
    public String ingresarObjeto(Producto producto) {
        var sql = "INSERT INTO producto_alimenticio (descripcion, aportecalorico) VALUES (?, ?)";
        try (PreparedStatement ps = conexionProducto.prepareStatement(sql)) {
            ps.setString(1, producto.getDescripcion());
            ps.setString(2,((ProductoAlimenticio) producto).getAporteCalorias());
            int filasAfectadas = ps.executeUpdate();
            return (filasAfectadas > 0) ? "Producto alimenticio agregado correctamente." : "No se pudo agregar el producto.";

        } catch (Exception e) {
            e.printStackTrace();
            return "No se pudo ingresar el producto.";
        }
    }

    @Override
    public Producto buscarPorId(int id) {
        var sql = "SELECT * FROM producto_alimenticio WHERE id=?";
        Producto producto = null;

        try (PreparedStatement ps = conexionProducto.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    producto = new ProductoAlimenticio(rs.getInt("id"), rs.getString("descripcion"), rs.getString("aporteCalorico"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return producto;
    }

    @Override
    public String actualizarObjeto(Producto producto) {
        var sql = "UPDATE producto_alimenticio SET descripcion=?, aporteCalorico=? WHERE id=?";
        try (PreparedStatement ps = conexionProducto.prepareStatement(sql)) {
            ps.setString(1, producto.getDescripcion());
            ps.setString(2, ((ProductoAlimenticio)producto).getAporteCalorias() + " Kcal");
            ps.setInt(3, producto.getId());

            int filasAfectadas = ps.executeUpdate();
            return (filasAfectadas > 0) ? "Producto alimenticio actualizado con éxito" : "No se pudo actualizar el producto";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al actualizar el producto";
        }
    }

    @Override
    public Producto eliminarObjeto(int id) {
        var consulta = "SELECT * FROM producto_alimenticio WHERE id=?";
        var eliminacion = "DELETE FROM producto_alimenticio WHERE id=?";
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
        // TODO Auto-generated method stub
        return null;
    }


}
