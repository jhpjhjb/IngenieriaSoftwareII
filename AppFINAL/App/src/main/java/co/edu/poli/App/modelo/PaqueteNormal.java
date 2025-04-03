package co.edu.poli.App.modelo;

public class PaqueteNormal extends Paquete{
    public PaqueteNormal(Destino destino){
        super(destino);
    }

    @Override
    public String enviar() {
        return destino.getDestino() + "\nPaquete de Tipo: Normal";
    }

}
