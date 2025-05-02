package co.edu.poli.modelo;

public class ProductoMemento {
    private final String nombre;
    private final double precio;
    private final int año;

    public ProductoMemento(int año, String nombre, double precio) {
        this.año = año;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getAño() {
        return año;
    }
    public String getNombre() {
        return nombre;
    }
    public double getPrecio() {
        return precio;
    }
}