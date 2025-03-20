package co.edu.poli.App.modelo;

public class Evaluacion {
    private String resultado;
    
    public Evaluacion(String resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "" + resultado;
    }
}
