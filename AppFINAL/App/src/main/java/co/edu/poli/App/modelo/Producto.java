package co.edu.poli.App.modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public abstract class Producto implements Clonable {
    private int id;
    private String descripcion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    protected Producto(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }



    protected Producto(String descripcion) {
        this.descripcion = descripcion;
    }

    protected Producto(int id) {
        this.id = id;
    }

    protected Producto() {}

    public abstract void asignarParametros(PreparedStatement ps) throws SQLException;

    @Override
    public String toString() {
        return "Producto [id=" + id + ", descripcion=" + descripcion +"]";
    }

}

