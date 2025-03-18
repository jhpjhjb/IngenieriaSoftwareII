package co.edu.poli.App.modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductoElectrico extends Producto {
    private String voltajeEntrada;
    

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
        return new ProductoElectrico(this.getId(), this.getDescripcion(), this.getVoltaje());
    }
}
