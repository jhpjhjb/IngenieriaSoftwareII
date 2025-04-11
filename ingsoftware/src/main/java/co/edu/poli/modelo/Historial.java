package co.edu.poli.modelo;

import java.util.ArrayList;
import java.util.List;

public class Historial {
    private List<String> pedidos = new ArrayList<>();
    private List<Producto> productos = new ArrayList<>();
    
    public String realizarPedido(String pedido){
        pedidos.add(pedido);
        return "Pedido realizado: " + pedido;
    }

    public String mostrarHistorial(){
        String historial = "";
        for (String pedido : pedidos) {
            historial += "\n" + pedido;
        }
        return "--Historial productos--"+historial;
    }

    public String agregarProducto(Producto producto) {
        productos.add(producto);
        return "Producto realizado: " + producto;
    }

    public String mostrarHistorialProducto(){
        String historial = "";
        for (Producto producto : productos) {
            historial += "\n" + producto;
        }
        return "--Historial productos--"+historial;
    }

}