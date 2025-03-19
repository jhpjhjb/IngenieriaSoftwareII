package co.edu.poli.App.modelo;

public class Nequi {
    private String numero;
    private String nombre;
    public Nequi(String numero, String nombre){
        this.numero = numero;
        this.nombre = nombre;
    }
    public String transferir(double monto){
        return "Transferencia exitosa de " + nombre + "\nCon Numero de Telefono: " + numero + "\nMonto: " + monto;
    }
}
