package co.edu.poli.modelo.Chain;

import co.edu.poli.modelo.Pedido;

public abstract class Manejador {
    protected Manejador manejador;

    public Manejador setManejador(Manejador siguiente){
        this.manejador = siguiente;
        return manejador;
    }
    public abstract String handle(Pedido pedido);
}
