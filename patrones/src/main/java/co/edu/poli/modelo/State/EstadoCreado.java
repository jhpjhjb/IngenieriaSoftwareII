package co.edu.poli.modelo.State;

public class EstadoCreado implements EstadoPedido {

    @Override
    public String hacerPedido(PedidoContext pedido) {
        pedido.setEstado(new EstadoPagado());
        return "Pedido creado correctamente";
    }

    @Override
    public String pagar(PedidoContext pedido) {
        return "Aún no puedes pagar, debes tener un pedido creado";
    }

    @Override
    public String enviar(PedidoContext pedido) {
        return "Aún no puedes enviar, debes tener un pedido creado";
    }

    @Override
    public String cancelar(PedidoContext pedido) {
        return "Para cancelar debe tener al menos un pedido creado";
    }
}