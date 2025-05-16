package co.edu.poli.modelo.State;

public class PedidoContext {
    
    private EstadoPedido estado;

    public PedidoContext() {
        this.estado = new EstadoCreado();
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public String crearPedido() {
        return estado.hacerPedido(this);
    }

    public String pagar() {
        return estado.pagar(this);
    }

    public String enviar() {
        return estado.enviar(this);
    }

    public String cancelar() {
        return estado.cancelar(this);
    }

}
