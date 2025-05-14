package co.edu.poli.modelo;

import java.util.ArrayList;
import java.util.List;

import co.edu.poli.modelo.Visitor.IElemento;
import co.edu.poli.modelo.Visitor.IVisitor;

public class Pedido implements IElemento {

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

    public Producto ultimProducto(){
        return getProductos().get(getProductos().size()-1);
    }
    
    @Override
    public String toString() {
        return "Pedido:\n" + cliente + "\n" + productos;
    }

    @Override
    public String aceptar(IVisitor visitor) {
        return visitor.visit(this);
    }
    
}
