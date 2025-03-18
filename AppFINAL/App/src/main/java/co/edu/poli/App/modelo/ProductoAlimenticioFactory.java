package co.edu.poli.App.modelo;

public class ProductoAlimenticioFactory implements ProductoFactory{

    @Override
    public Producto crearProducto(int id, String descripcion, String aportecalorico) {
        return new ProductoAlimenticio(id, descripcion, aportecalorico);
    }
}
