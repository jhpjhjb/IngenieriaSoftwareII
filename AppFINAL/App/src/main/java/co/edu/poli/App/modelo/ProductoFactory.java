package co.edu.poli.App.modelo;

public interface ProductoFactory {
    Producto crearProducto(int id, String descripcion, String atributo);
}
