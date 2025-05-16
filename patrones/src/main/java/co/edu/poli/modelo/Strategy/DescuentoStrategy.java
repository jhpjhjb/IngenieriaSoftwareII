package co.edu.poli.modelo.Strategy;

import co.edu.poli.modelo.Pedido;

public interface DescuentoStrategy {
    double aplicarDescuento(Pedido pedido);
}