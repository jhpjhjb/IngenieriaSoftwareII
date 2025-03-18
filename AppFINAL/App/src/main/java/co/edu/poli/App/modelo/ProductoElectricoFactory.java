package co.edu.poli.App.modelo;

public class ProductoElectricoFactory implements ProductoFactory{

    @Override
    public Producto crearProducto(int id, String descripcion, String voltaje) {
        return new ProductoElectrico(id, descripcion, voltaje);
    }
    
}
