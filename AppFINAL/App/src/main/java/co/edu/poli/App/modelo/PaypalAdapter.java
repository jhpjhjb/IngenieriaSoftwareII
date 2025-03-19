package co.edu.poli.App.modelo;

public class PaypalAdapter implements IPagoExterno {
    private Paypal paypal;

    public PaypalAdapter(Paypal paypal) {
        this.paypal = paypal;
    }

    @Override
    public String pagar(double monto) {
        return paypal.enviarPago(monto);
    }

}
