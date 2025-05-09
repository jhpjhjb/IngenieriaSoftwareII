package co.edu.poli.modelo;

public class DescuentoPorCantidad implements DescuentoStrategy {

    @Override
    public double aplicarDescuento(Pedido pedido) {
        if (pedido.getProductos().size() > 3) {
            return pedido.getTotal() * 0.15;
        } else {
            return 0.0;
        }
    }    
}
