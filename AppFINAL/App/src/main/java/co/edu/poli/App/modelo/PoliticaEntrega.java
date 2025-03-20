package co.edu.poli.App.modelo;

public class PoliticaEntrega {
    private String detalles;
    
    public PoliticaEntrega(String detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "PoliticaEntrega: " + detalles;
    }
}
