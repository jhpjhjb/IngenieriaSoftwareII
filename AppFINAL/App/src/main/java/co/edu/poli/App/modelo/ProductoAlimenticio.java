package co.edu.poli.App.modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import co.edu.poli.App.controlador.ControladorProductos;

public class ProductoAlimenticio extends Producto {

    private String aporteCalorico;

    private ControladorProductos controladorProductos;
    
    public String getAporteCalorias() {
        return aporteCalorico;
    }

    public void setAporteCalorias(String aporteCalorico) {
        this.aporteCalorico = aporteCalorico;
    }

    public ProductoAlimenticio(String descripcion, String aporteCalorico) {
        super(descripcion); 
        this.aporteCalorico = aporteCalorico;
    }

    public ProductoAlimenticio(int id, String descripcion, String aporteCalorico) {
        super(id, descripcion); 
        this.aporteCalorico = aporteCalorico;
    }
    
    public ProductoAlimenticio() {
    }

    @Override
    public String toString() {
        return "PRODUCTO ALIMENTICIO\n| ID: "+ getId() +" | Aporte Calorico: " + aporteCalorico + " | Descripcion: "+ getDescripcion() +" |";
    }

    @Override
    public void asignarParametros(PreparedStatement ps) throws SQLException {
        ps.setString(1, this.getDescripcion());
        ps.setString(2, this.getAporteCalorias());
    }

    @Override
    public Producto clonar() {
    try {
        ControladorProductos controladorProductos = new ControladorProductos();
        ProductoAlimenticio producto = new ProductoAlimenticio(0, getDescripcion(), getAporteCalorias());

        String mensaje = controladorProductos.ingresarProductoAlimenticio(producto);
        System.out.println(mensaje);

        return producto;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
    }
}