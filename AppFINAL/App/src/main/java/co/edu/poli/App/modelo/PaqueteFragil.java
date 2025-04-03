package co.edu.poli.App.modelo;

public class PaqueteFragil extends Paquete{
    public PaqueteFragil(Destino destino){
        super(destino);
    }

    @Override
    public String enviar() {
        return destino.getDestino() + "\nPaquete de Tipo: Fragil";
    }
    
}
