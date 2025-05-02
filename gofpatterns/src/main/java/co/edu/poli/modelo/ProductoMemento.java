package co.edu.poli.modelo;

class ProductoMemento {
    private final String nombre;
    private final double precio;

    public ProductoMemento(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}