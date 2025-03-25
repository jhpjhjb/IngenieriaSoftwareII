package co.edu.poli.App.modelo;

public class Empleado implements IGeneral {
    private String nombre;
    private String responsabilidad;
    private String salario;

    public String getNombre() {
        return nombre;
    }


    public String getResponsabilidad() {
        return responsabilidad;
    }


    public String getSalario() {
        return salario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void setResponsabilidad(String responsabilidad) {
        this.responsabilidad = responsabilidad;
    }


    public void setSalario(String salario) {
        this.salario = salario;
    }
    
    public Empleado(String nombre, String responsabilidad, String salario) {
        this.nombre = nombre;
        this.responsabilidad = responsabilidad;
        this.salario = salario;
    }


    @Override
    public String detalles(){
        return String.format("Empleado 👤 {Nombre: %s - Responsabilidad: %s - Salario: $%s}", nombre, responsabilidad, salario);
    }
}
