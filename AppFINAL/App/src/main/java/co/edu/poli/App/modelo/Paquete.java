package co.edu.poli.App.modelo;

abstract public class Paquete{
    protected Destino destino;
    public Paquete(Destino destino){
        this.destino = destino;
    }
    public abstract String enviar();

}
