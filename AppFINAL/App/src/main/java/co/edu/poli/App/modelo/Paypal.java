package co.edu.poli.App.modelo;

public class Paypal {
    private String email;
    private String cuenta;
    public Paypal(String email, String cuenta){
        this.email = email;
        this.cuenta = cuenta;
    }

    public String enviarPago(double monto){
        return "Pago enviado desde: " + email +"\n Con Numero de cuenta " + cuenta + "\n Por un  monto de: $" + monto;
    }
}
