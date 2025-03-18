package co.edu.poli.App.modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import co.edu.poli.App.controlador.ControladorProductos;

public class ProductoElectrico extends Producto {
    private String voltajeEntrada;
    private ControladorProductos controladorProductos;
    

    public String getVoltaje() {
        return voltajeEntrada;
    }

    public void setVoltaje(String voltajeEntrada) {
        this.voltajeEntrada = voltajeEntrada;
    }

    public ProductoElectrico(String descripcion, String voltajeEntrada) {
        super(descripcion); 
        this.voltajeEntrada = voltajeEntrada;
    }

    public ProductoElectrico(int id, String descripcion, String voltajeEntrada) {
        super(id, descripcion);
        this.voltajeEntrada = voltajeEntrada;
    }

    public ProductoElectrico(int id) {
        super(id, "ELECTRICO");
    }

    public ProductoElectrico() throws ClassNotFoundException, SQLException {
        super("ELECTRICO");
        this.controladorProductos = new ControladorProductos();
    }

    @Override
    public String toString() {
        return "PRODUCTO ELECTRICO\n| ID: " + getId() + " | Voltaje: " + voltajeEntrada + " | Descripcion: " + getDescripcion() + " |";
    }

    @Override
    public void asignarParametros(PreparedStatement ps) throws SQLException {
        ps.setString(1, this.getDescripcion());
        ps.setString(2, this.getVoltaje());
    }

    @Override
    public Producto clonar() {
    try {
        ControladorProductos controladorProductos = new ControladorProductos();
        ProductoElectrico productoElectrico = new ProductoElectrico(0, getDescripcion(), getVoltaje());

        String mensaje = controladorProductos.ingresarProductoElectrico(productoElectrico);
        System.out.println(mensaje);

        return productoElectrico;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
    }
}
