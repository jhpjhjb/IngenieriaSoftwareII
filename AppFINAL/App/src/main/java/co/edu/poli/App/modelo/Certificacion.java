package co.edu.poli.App.modelo;

public class Certificacion {
    private String tipo;
    
    public Certificacion(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "" + tipo;
    }
}
