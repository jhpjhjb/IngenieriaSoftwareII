package co.edu.poli.App.modelo;

public class Cliente {
    private int id;
    private String nombre;

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    @Override
    public String toString(){
        return "Cliente: " + nombre + " Id: " + id;
    }

    public Cliente(){}

    public Cliente(String nombre){
        this.nombre = nombre;
    }
    public Cliente(int id, String nombre){
        this.nombre = nombre;
        this.id = id;
    }
}
