package co.edu.poli.modelo;

public class Productos implements IProducto{
    private String tipo;
    private String nombre;
    private int costo;
    private String detalles;
    private int autorizacion;
    
    public String getNombre() {
        return nombre;
    }

    public int getAutorizacion() {
        return autorizacion;
    }

    public Productos(String tipo, String nombre, int costo, String detalles, int autorizacion) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.costo = costo;
        this.detalles = detalles;
        this.autorizacion = autorizacion;
    }

    @Override
    public String detalles() {
        return "Tipo de Producto: " + tipo + "\n Nombre Producto: " + nombre + " - Costo del Producto: $"+costo + " - Especificaciones: " + detalles;
    }
    

    
}
