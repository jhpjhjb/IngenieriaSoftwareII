package co.edu.poli.modelo.State;

public interface EstadoPedido {
    String hacerPedido(PedidoContext pedido);
    String pagar(PedidoContext pedido);
    String enviar(PedidoContext pedido);
    String cancelar(PedidoContext pedido);
}
