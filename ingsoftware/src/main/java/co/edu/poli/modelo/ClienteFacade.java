package co.edu.poli.modelo;

public class ClienteFacade {
    private InformacionPersonal infoP;
    private HistorialPedidos historial;
    private FormasPago formasPago;

    public ClienteFacade(String nombre, String correo) {
        this.infoP = new InformacionPersonal(nombre, correo);
        this.historial = new HistorialPedidos();
        this.formasPago = new FormasPago();
    }

    public void actualizarInformacion(String nombre, String correo){
        infoP.actualizarDatos(nombre, correo);
    }
    
    public String mostrarInformacion(){
        return infoP.mostrar();
    }

    public void ingresarPedidos(String pedido) {
        historial.realizarPedido(pedido);
    }

    public String verHistorialPedidos() {
        return historial.mostrarHistorial();
    }

    public void activarPago(String tipo) {
        formasPago.activarForma(tipo);
    }

    public void bloquearPago(String tipo) {
        formasPago.bloquearForma(tipo);
    }

    public String verFormasDePago() {
        return formasPago.mostrarFormas();
    }

}
