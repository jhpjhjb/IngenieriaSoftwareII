package co.edu.poli.modelo;

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
