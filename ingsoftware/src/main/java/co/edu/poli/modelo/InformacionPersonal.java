package co.edu.poli.modelo;

public class InformacionPersonal {
    private String nombre;
    private String correo;


    public InformacionPersonal(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    public String actualizarDatos(String nuevoNombre, String nuevoCorreo) {
        this.nombre = nuevoNombre;
        this.correo = nuevoCorreo;
        return "Informacion actualizada";
    }

    

    public String mostrar() {
        return "--InformacionPersonal-- \nNombre: " + nombre + "\nCorreo: " + correo;
    }
}
