package co.edu.poli.modelo;

public class Ussers {
    private String nombreUsuario;
    private int nivelAutorizacion;
    
    
    public int getNivelAutorizacion() {
        return nivelAutorizacion;
    }

    public Ussers(String nombreUsuario, int nivelAutorizacion) {
        this.nombreUsuario = nombreUsuario;
        this.nivelAutorizacion = nivelAutorizacion;
    }

    @Override
    public String toString() {
        return "Ussers [nombreUsuario=" + nombreUsuario + ", nivelAutorizacion=" + nivelAutorizacion + "]";
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
}
