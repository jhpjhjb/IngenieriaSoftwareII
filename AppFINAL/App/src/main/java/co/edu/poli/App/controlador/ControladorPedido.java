package co.edu.poli.App.controlador;

import co.edu.poli.App.modelo.Pedido;
import co.edu.poli.App.servicio.DaoPedido;
import java.sql.SQLException;
import java.util.List;

public class ControladorPedido {
    
    private DaoPedido daoPedido;

    public ControladorPedido() throws ClassNotFoundException, SQLException {
        this.daoPedido = new DaoPedido();
    }

    public List<Pedido> mostrarPedidos() {
        return daoPedido.listarObjetos();
    }

    public Pedido mostrarPedido(int numero) {
        return daoPedido.buscarPorId(numero);
    }

    public String ingresarPedido(Pedido pedidoIngresado) {
        return daoPedido.ingresarObjeto(pedidoIngresado);
    }

    public String actualizarPedido(Pedido pedidoActualizado) {
        return daoPedido.actualizarObjeto(pedidoActualizado);
    }

    public Pedido eliminarPedido(int numero) {
        return daoPedido.eliminarObjeto(numero);
    }
}

