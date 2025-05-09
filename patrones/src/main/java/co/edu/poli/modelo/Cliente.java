package co.edu.poli.modelo;

public class Cliente {

    private String nombre;
    private boolean registrado;

    public Cliente(String nombre, boolean registrado) {
        this.nombre = nombre;
        this.registrado = registrado;
    }

    public void setEstado(Boolean estado){
        registrado = estado;
    }

    public boolean estaActivo(){
        if(registrado == true){
            return true;
        }
        return false;
    }

    public String getNombre(){
        return nombre;
    }
    
    @Override
    public String toString() {
        return "Cliente [nombre=" + nombre + ", registrado=" + registrado + "]";
    }
    
}
