package co.edu.poli.modelo;

public class Facade {
    private InformacionPersonal infoP;
    private Historial historial;
    private FormasPago formasPago;

    public Facade(InformacionPersonal infoP, Historial historial, FormasPago formasPago) {
        this.infoP = infoP;
        this.historial = historial;
        this.formasPago = formasPago;
    }

    public String actualizarInformacion(String nombre, String correo){
        infoP.actualizarDatos(nombre, correo);
        String clienteActual = infoP.mostrar();
        return clienteActual;
    }


    public String consultarPedidos(String pedido) {
        historial.realizarPedido(pedido);
        String historialDespues = historial.mostrarHistorial();
        return historialDespues;
    }

    public String accionPago(String accion, String metodoPago) {
        if (accion.equals("activar")) {
            formasPago.activarForma(metodoPago);
            return formasPago.mostrarFormas();
        } else if (accion.equals("bloquear")) {
            formasPago.bloquearForma(metodoPago);
            return formasPago.mostrarFormas();
        }
        return "Accion no reconocida: "+ accion;
    }
}
