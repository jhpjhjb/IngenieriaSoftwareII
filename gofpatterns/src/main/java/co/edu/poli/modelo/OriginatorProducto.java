package co.edu.poli.modelo;

public class OriginatorProducto {
    
    private Producto producto;

    public OriginatorProducto(Producto producto){
        this.producto = producto;
    }

    public ProductoMemento guardarEstado() {
        return new ProductoMemento(producto.getNombre(), producto.getPrecio());
    }

    public void restaurarEstado (ProductoMemento memento) {
        producto.setNombre(memento.getNombre());
        producto.setPrecio(memento.getPrecio());
    }

}
