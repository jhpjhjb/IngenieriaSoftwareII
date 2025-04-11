package co.edu.poli.modelo;

public class Producto {
    private String nombre;
    private double precio;
    private Proveedor proveedor;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.proveedor = Proveedor.getInstancia(); 
    }

    @Override
    public String toString() {
        return "---PRODUCTO---\nProducto: " + nombre + "\nPrecio: " + precio + "\nProveedor: " + proveedor.getNombre();
    }

}
