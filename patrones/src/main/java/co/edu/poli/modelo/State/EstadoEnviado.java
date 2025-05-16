package co.edu.poli.modelo.State;

public class EstadoEnviado implements EstadoPedido{

    @Override
    public String hacerPedido(PedidoContext pedido) {
        return "No se pueden agregar productos o hacer pedido. El pedido no está en estado 'Creado' o 'Cancelado";
    }

    @Override
    public String pagar(PedidoContext pedido) {
        return "Ya pagaste";
    }

    @Override
    public String enviar(PedidoContext pedido) {
        pedido.setEstado(new EstadoCreado());
        return "Enviando tu pedido a Wilson... \nPedido entregado exitosamente...";
    }

    @Override
    public String cancelar(PedidoContext pedido) {
        pedido.setEstado(new EstadoCancelado());
        return "Pedido cancelado, reembolsando...\nPedido cancelado con exito. ";
    }
    
    
}
