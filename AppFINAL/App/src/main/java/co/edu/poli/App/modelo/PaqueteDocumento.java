package co.edu.poli.App.modelo;

public class PaqueteDocumento extends Paquete{
    public PaqueteDocumento(Destino destino){
        super(destino);
    }

    @Override
    public String enviar() {
        return destino.getDestino() + "\nPaquete de Tipo: Documento";
    }
    

}
