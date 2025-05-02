package co.edu.poli.modelo;

public class OriginatorProducto {
    private Producto producto;

    public OriginatorProducto(Producto producto) {
        this.producto = producto;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public ProductoMemento guardarEstado() {
        return new ProductoMemento(
            producto.getAño(), 
            producto.getNombre(), 
            producto.getPrecio()
        );
    }

    public void restaurarEstado(ProductoMemento memento) {
        producto.setAño(memento.getAño());  // Actualizar año
        producto.setNombre(memento.getNombre());
        producto.setPrecio(memento.getPrecio());
    }
}

