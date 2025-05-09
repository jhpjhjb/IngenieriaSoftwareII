package co.edu.poli.modelo;

public class DescuentoProductoEspecifico implements DescuentoStrategy {

    @Override
    public double aplicarDescuento(Pedido pedido) {
        for (Producto producto : pedido.getProductos()) {
            if (producto.getDescripcion().equalsIgnoreCase("Zapatos Nike")) {
                return pedido.getTotal() * 0.10;
            }
        }
        return 0.0;
    }
    
}