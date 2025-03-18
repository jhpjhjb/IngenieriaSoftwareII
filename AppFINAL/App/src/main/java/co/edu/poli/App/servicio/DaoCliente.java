package co.edu.poli.App.servicio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import co.edu.poli.App.modelo.Cliente;

public class DaoCliente implements IDao <Cliente> {
    private Connection conexionCliente;

    public DaoCliente() throws ClassNotFoundException, SQLException{
        this.conexionCliente = Conexion.getConexion();
    }

    @Override
    public List<Cliente> listarObjetos() {
        List<Cliente> clientes = new ArrayList<>();
        var sql = "SELECT * FROM cliente";
        try (PreparedStatement ps = conexionCliente.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();){
            while (rs.next()){
                var cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public Cliente buscarPorId(int id) {
        var sql = "SELECT * FROM cliente WHERE id=?";
        Cliente cliente = new Cliente();
        try (PreparedStatement ps = conexionCliente.prepareStatement(sql);){
            ps.setInt(1, id);
            try( ResultSet rs = ps.executeQuery();){
                if(rs.next()){
                    cliente.setId(rs.getInt("id"));
                    cliente.setNombre(rs.getString("nombre"));
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public String ingresarObjeto(Cliente cliente) {
        var sql = "INSERT INTO cliente(nombre) " + "VALUES (?)";
        try (PreparedStatement ps = conexionCliente.prepareStatement(sql);){
            ps.setString(1, cliente.getNombre());
            int filasAfectadas = ps.executeUpdate();
            if(filasAfectadas>0){
                return filasAfectadas +" Cliente ingresado con exito";
            }
            }
            catch (SQLException e) {
            e.printStackTrace();
            }
        return "No se pudo ingresar Cliente";

    }

    @Override
    public String actualizarObjeto(Cliente cliente) {
        var sql = "UPDATE cliente SET nombre=? " +"WHERE id=?";
        try (PreparedStatement ps = conexionCliente.prepareStatement(sql);){
            ps.setInt(2, cliente.getId());
            ps.setString(1, cliente.getNombre());
            int filasAfectadas = ps.executeUpdate();
            if(filasAfectadas>0){
                return "Cliente Actualizado con exito: " + filasAfectadas;
            }
            }
            catch (SQLException e) {
            e.printStackTrace();
            }
        return "No se pudo Actualizar Cliente";
    }

    @Override
    public Cliente eliminarObjeto(int id) {
        var sql2 = "SELECT * FROM cliente WHERE id = ?";
        var sql = "DELETE FROM cliente WHERE id=?";
        Cliente clienteEliminado = null;
        try (PreparedStatement ps = conexionCliente.prepareStatement(sql);
            PreparedStatement ls = conexionCliente.prepareStatement(sql2);){
            ls.setInt(1, id);
                try (ResultSet rs = ls.executeQuery();){
                    if(rs.next()){
                        String nombre = rs.getString("nombre");
                        clienteEliminado = new Cliente(id,nombre);
                        ps.setInt(1, id);
                        int filasAfectadas = ps.executeUpdate();
                        if(filasAfectadas == 0){
                            clienteEliminado = null;
                        }
                    }
                }
            }
            catch (SQLException e) {
            e.printStackTrace();
            }
        return  clienteEliminado;
    }
}
