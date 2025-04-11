package co.edu.poli.modelo;

public class Proveedor {
    private static final Proveedor INSTANCIA = new Proveedor("Proveedor Paulis");
    private String nombre;

    public Proveedor(String nombre) {
        this.nombre = nombre;
    }

    public static Proveedor getInstancia() {
        return INSTANCIA;
    }

    public String getNombre() {
        return nombre;
    }

}
