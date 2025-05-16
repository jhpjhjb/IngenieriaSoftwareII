package co.edu.poli.modelo.Strategy;

import co.edu.poli.modelo.Pedido;
import co.edu.poli.modelo.Producto;

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