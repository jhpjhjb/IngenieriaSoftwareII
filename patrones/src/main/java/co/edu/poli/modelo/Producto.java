package co.edu.poli.modelo;

import co.edu.poli.modelo.Visitor.IElemento;
import co.edu.poli.modelo.Visitor.IVisitor;

public class Producto implements IElemento{
    private String descripcion;
    private double precio;

    public Producto(String descripcion, double precio) {
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Producto \nDescripcion: " + descripcion + "\nPrecio: " + precio + "\n";
    }

    @Override
    public String aceptar(IVisitor visitor) {
        return visitor.visit(this);
    }
    
}
