package co.edu.poli.modelo.Strategy;

import co.edu.poli.modelo.Pedido;

public class DescuentoMetodoPago implements DescuentoStrategy {
    
    @Override
    public double aplicarDescuento(Pedido pedido) {
        if (pedido.getMetodoPago().equalsIgnoreCase("PayPal")) {
            return pedido.getTotal() * 0.05;
        } else if (pedido.getMetodoPago().equalsIgnoreCase("Tarjeta Debito")) {
            return pedido.getTotal() * 0.03;
        }
        return 0.0;
    }
}
