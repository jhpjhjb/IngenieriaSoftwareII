package co.edu.poli.modelo.Visitor;

import co.edu.poli.modelo.Pedido;
import co.edu.poli.modelo.Producto;

public class ConcreteVisitor implements IVisitor{
    private double tasaCambio;
    private String modena;


    public ConcreteVisitor(double tasaCambio, String moneda){
        this.tasaCambio = tasaCambio;
        this.modena = moneda.toUpperCase();
    }
    
    @Override
    public String visit(Producto producto) {
        double cambio = producto.getPrecio() * tasaCambio;
        return String.format("El Precio del Producto %s en %s es:\n$%.2f %s",producto.getDescripcion(),modena,cambio, modena);
    }

    @Override
    public String visit(Pedido pedido) {
        double cambio = pedido.getTotal() * tasaCambio;
        return String.format("El Precio del Pedido de: %s en %s es:\n$%.2f %s",pedido.getCliente().getNombre(),modena,cambio, modena);
    }

}
