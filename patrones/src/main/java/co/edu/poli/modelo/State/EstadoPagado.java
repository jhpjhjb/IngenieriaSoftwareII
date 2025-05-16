package co.edu.poli.modelo.State;

public class EstadoPagado implements EstadoPedido{

    @Override
    public String hacerPedido(PedidoContext pedido) {
        return "No se pueden agregar productos o hacer pedido. El pedido no está en estado 'Creado' o 'Cancelado";
    }

    @Override
    public String pagar(PedidoContext pedido) {
        pedido.setEstado(new EstadoEnviado());
        return "Pagando... \n Pago exitoso.";
    }

    @Override
    public String enviar(PedidoContext pedido) {
        return "No puedes enviar el pedido si no has pagado";
    }

    @Override
    public String cancelar(PedidoContext pedido) {
        pedido.setEstado(new EstadoCancelado());
        return "Pedido cancelado, reembolsando...";
    }



}
