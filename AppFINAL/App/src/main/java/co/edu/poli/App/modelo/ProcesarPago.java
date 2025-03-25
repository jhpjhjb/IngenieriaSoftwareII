package co.edu.poli.App.modelo;

import javax.swing.JOptionPane;

public class ProcesarPago {

    public static String procesarPago(String metodo, double monto) {
        IPagoExterno pagoExterno = null;
        if (metodo.equalsIgnoreCase("Nequi")) {
            String telefono = JOptionPane.showInputDialog("Ingrese Numero de Telefono");
            String nombre = JOptionPane.showInputDialog("Ingrese Nombre");
            pagoExterno = new NequiAdapter(new Nequi(telefono, nombre));
        } else if (metodo.equalsIgnoreCase("Paypal")) {
            String email = JOptionPane.showInputDialog("Ingrese Email");
            String cuenta = JOptionPane.showInputDialog("Ingrese el numero de Cuenta");
            pagoExterno = new PaypalAdapter(new Paypal(email, cuenta));
        } else {
            JOptionPane.showMessageDialog(null, "Metodo de Pago Invalido");
        }
        return pagoExterno != null ? pagoExterno.pagar(monto) : "Error en el método de pago";
    }
}
