package co.edu.poli.modelo.State;

public class EstadoEntregado implements EstadoPedido{

    @Override
    public String hacerPedido(PedidoContext pedido) {
        return "Pedido entregado";
    }

    @Override
    public String pagar(PedidoContext pedido) {
        return "Ya pagaste y tu pedido fué entregado";
    }

    @Override
    public String enviar(PedidoContext pedido) {
        return "No puedes enviar el pedido, porque ya fué entregado";
    }

    @Override
    public String cancelar(PedidoContext pedido) {
        return "No puedes cancelar el pedido, porque ya fué entregado";
    }
    
}
