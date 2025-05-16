package co.edu.poli.modelo.State;

public class EstadoCancelado implements EstadoPedido{

    @Override
    public String hacerPedido(PedidoContext pedido) {
        pedido.setEstado(new EstadoCreado());
        return "Puede realizar nuevamente un pedido";
    }

    @Override
    public String pagar(PedidoContext pedido) {
        return "Su pedido está cancelado";
    }

    @Override
    public String enviar(PedidoContext pedido) {
        return "Su pedido está cancelado";
    }

    @Override
    public String cancelar(PedidoContext pedido) {
        return "Su pedido está cancelado";
    }

    
}