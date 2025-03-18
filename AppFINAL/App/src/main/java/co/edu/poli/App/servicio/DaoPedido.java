package co.edu.poli.App.servicio;

import co.edu.poli.App.modelo.Cliente;
import co.edu.poli.App.modelo.Pedido;
import co.edu.poli.App.modelo.Producto;
import co.edu.poli.App.modelo.ProductoAlimenticio;
import co.edu.poli.App.modelo.ProductoElectrico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DaoPedido implements IDao<Pedido> {
    private Connection conexionPedido;

    public DaoPedido() throws ClassNotFoundException, SQLException {
        this.conexionPedido = Conexion.getConexion();
    }

    @Override
    public List<Pedido> listarObjetos() {
        List<Pedido> pedidos = new ArrayList<>();
        var sql = "SELECT * FROM pedido";
        var sql1 = "SELECT * FROM cliente where id = ?";
        try (PreparedStatement ps = conexionPedido.prepareStatement(sql);
             
            PreparedStatement ps1 = conexionPedido.prepareStatement(sql1);
        
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String numero = rs.getString("numero");
                String fecha = rs.getString("fecha");
                
                int clienteId = rs.getInt("cliente_id");
                ps1.setInt(1, clienteId);
                ResultSet rs1 = ps1.executeQuery();
                Cliente cliente1 = new Cliente();
                if (rs1.next()) {
                    cliente1.setId(rs1.getInt("id"));
                    cliente1.setNombre(rs1.getString("nombre"));
                }
                String listaProductosStr = rs.getString("productos");

                List<Producto> productos = obtenerProductos(listaProductosStr);

                pedidos.add(new Pedido(numero, fecha, cliente1, productos));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    @Override
    public Pedido buscarPorId(int id) {
        /*var sql = "SELECT * FROM pedido WHERE numero=?";
        Pedido pedido = null;
        try (PreparedStatement ps = conexionPedido.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String numero = rs.getString("numero");
                    String fecha = rs.getString("fecha");
                    int clienteId = rs.getInt("cliente_id");
                    String listaProductosStr = rs.getString("productos");

                    List<Producto> productos = obtenerProductos(listaProductosStr);

                    //var cliente = new Cliente(clienteId);

                    pedido = new Pedido(numero, fecha, cliente, productos);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedido;*/
        Pedido pedido = new Pedido();
        return pedido;
    }

    @Override
public String ingresarObjeto(Pedido pedido) {
    var sql = "INSERT INTO pedido(numero, fecha, cliente_id, productos) VALUES (?,?,?,?)";
    var sql1 = "SELECT id FROM producto WHERE id = ?";
    
    try (PreparedStatement ps1 = conexionPedido.prepareStatement(sql1);
         PreparedStatement ps = conexionPedido.prepareStatement(sql)) {
        
        ps.setString(1, pedido.getNumero());
        ps.setString(2, pedido.getFecha());
        ps.setInt(3, pedido.getCliente().getId());

        StringBuilder productosIds = new StringBuilder();

        for (Producto producto : pedido.getProductos()) {
            ps1.setInt(1, producto.getId());
            try (ResultSet rs = ps1.executeQuery()) {
            
                if (rs.next()) {
                    if (productosIds.length() > 0) {
                        productosIds.append(","); 
                    }
                    productosIds.append(rs.getInt("id")); 
                } else {
                    System.out.println("Producto con ID " + producto.getId() + " no encontrado.");
                }
            }
        }

        if (productosIds.length() > 0) {
            ps.setString(4, productosIds.toString()); 
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                return filasAfectadas + " Pedido ingresado con éxito.";
            }
        } else {
            return "No se encontraron productos válidos.";
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return "No se pudo ingresar el pedido.";
}


    @Override
    public String actualizarObjeto(Pedido pedido) {
        var sql = "UPDATE pedido SET fecha=?, cliente_id=?, productos=? WHERE numero=?";
        try (PreparedStatement ps = conexionPedido.prepareStatement(sql)) {
            ps.setString(1, pedido.getFecha());
            ps.setInt(2, pedido.getCliente().getId());
            //ps.setString(3, obtenerIdsProductosComoCadena(pedido.getProductos()));
            ps.setString(4, pedido.getNumero());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                return "Pedido actualizado con éxito: " + filasAfectadas;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "No se pudo actualizar el pedido.";
    }

    @Override
    public Pedido eliminarObjeto(int id) {
        var sql = "DELETE FROM pedido WHERE numero=?";
        Pedido pedidoEliminado = buscarPorId(id);
        if (pedidoEliminado != null) {
            try (PreparedStatement ps = conexionPedido.prepareStatement(sql)) {
                ps.setInt(1, id);
                int filasAfectadas = ps.executeUpdate();
                if (filasAfectadas == 0) {
                    pedidoEliminado = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pedidoEliminado;
    }
    
    private List<Producto> obtenerProductos(String listaProductosStr) {
        List<Producto> productos = new ArrayList<>();
        if (listaProductosStr != null && !listaProductosStr.isEmpty()) {
            String[] ids = listaProductosStr.split(",");
            for (String id : ids) {
                try {
                    int productoId = Integer.parseInt(id.trim());
                    Producto producto = buscarProductoPorId(productoId);
                    if (producto != null) {
                        productos.add(producto);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        return productos;
    }

    private Producto buscarProductoPorId(int id) {
        var sql = "SELECT p.id, p.descripcion, p.tipo, pa.aporteCalorico, pe.voltajeEntrada "
                + "FROM producto p "
                + "LEFT JOIN productoalimenticio pa ON p.id = pa.id "
                + "LEFT JOIN productoelectrico pe ON p.id = pe.id "
                + "WHERE p.id = ?";

        try (PreparedStatement ps = conexionPedido.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String tipo = rs.getString("tipo");
                    if ("Alimento".equalsIgnoreCase(tipo)) {
                        return new ProductoAlimenticio(
                                rs.getInt("id"),
                                rs.getString("descripcion"),
                                rs.getString("aporteCalorico")
                        );
                    } else if ("Electrico".equalsIgnoreCase(tipo)) {
                        return new ProductoElectrico(
                                rs.getInt("id"),
                                rs.getString("descripcion"),
                                rs.getString("voltajeEntrada")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
