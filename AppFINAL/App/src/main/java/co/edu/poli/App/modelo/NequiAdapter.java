package co.edu.poli.App.modelo;

public class NequiAdapter implements IPagoExterno{
    private Nequi nequi;
    public NequiAdapter(Nequi nequi){
        this.nequi = nequi;
    }
    @Override
    public String pagar(double monto) {
        return nequi.transferir(monto);
    }
    
}
