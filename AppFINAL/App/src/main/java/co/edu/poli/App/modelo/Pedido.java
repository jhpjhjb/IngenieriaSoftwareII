package co.edu.poli.App.modelo;
import java.util.List;

public class Pedido {
    
    private String numero;
    private String fecha;
    private Cliente cliente;
    private List <Producto> productos;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Pedido(String numero, String fecha, Cliente cliente, List<Producto> productos) {
        this.numero = numero;
        this.fecha = fecha;
        this.cliente = cliente;
        this.productos = productos;
    }

    public Pedido(){}

    @Override
    public String toString() {
        return "Pedido{" + "numero=" + numero + ", fecha=" + fecha + ", cliente=" + cliente + ", productos=" + productos + '}';
    }

    
}

