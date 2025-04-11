package co.edu.poli.modelo;

import java.util.ArrayList;
import java.util.List;

class HistorialPedidos {
    private List<String> pedidos = new ArrayList<>();
    
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

}