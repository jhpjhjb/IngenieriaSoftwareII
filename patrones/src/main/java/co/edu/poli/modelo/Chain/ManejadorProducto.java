package co.edu.poli.modelo.Chain;

import co.edu.poli.modelo.Pedido;

public class ManejadorProducto extends Manejador {

    @Override
    public String handle(Pedido pedido) {
        if(pedido.getProductos().isEmpty()){
            return "La Lista de Producto en el Pedido no puede estar vacia\n";
        }
        if(manejador != null){
           return manejador.handle(pedido);
        }
        return "Producto no puede procesar la solicitud";
    }
    
}
