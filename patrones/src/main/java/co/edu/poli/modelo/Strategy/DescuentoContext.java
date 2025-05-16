package co.edu.poli.modelo.Strategy;

import co.edu.poli.modelo.Pedido;

public class DescuentoContext {
    private DescuentoStrategy estrategia;

    public DescuentoContext(DescuentoStrategy estrategia) {
        this.estrategia = estrategia;
    }

    public void setEstrategia(DescuentoStrategy estrategia) {
        this.estrategia = estrategia;
    }

    public double calcularTotalConDescuento(Pedido pedido) {
        return estrategia.aplicarDescuento(pedido);
    }
}
