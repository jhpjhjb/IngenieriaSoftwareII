package co.edu.poli.modelo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private Cliente cliente;
    private List<Producto> productos;
    private String metodoPago;

    public Pedido(Cliente cliente, String metodoPago) {
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.metodoPago = metodoPago;
    }

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.productos = new ArrayList<>();
    }

    public void agregarProductos(Producto producto){
        productos.add(producto);
    }

    public Cliente getCliente(){
        return cliente;
    }

    public List<Producto> getProductos(){
        return productos;
    }

    

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public double getTotal(){
        double precio = 0;
        for (Producto producto : productos) {
            precio+=producto.getPrecio();
        }
        return precio;
    }
    
    @Override
    public String toString() {
        return "Pedido:\n" + cliente + "\n" + productos;
    }
    
}
