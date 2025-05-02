package co.edu.poli.modelo;

public class Producto implements IObserver{

    private int año;
    private String nombre;
    private double precio;

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "--- PRODUCTO ---\n"+
        "Año: " + año + 
        "\nNombre: " + nombre + 
        "\nPrecio: " + precio +"\n";
    }

    public Producto(int año, String nombre, double precio) {
        this.año = año;
        this.nombre = nombre;
        this.precio = precio;
    }

    @Override
    public void update(AjustadorPrecios porcentaje, TipoEnum implicacion) {
        int valor = porcentaje.getPorcentaje();
        if(implicacion==TipoEnum.INCREMENTAR){
            precio+=(precio*valor)/100;
        }
        else if (implicacion == TipoEnum.REDUCIR){
            precio-=(precio*valor)/100;
        }
    }
}
