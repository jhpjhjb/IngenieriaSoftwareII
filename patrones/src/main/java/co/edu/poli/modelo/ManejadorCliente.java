package co.edu.poli.modelo;

public class ManejadorCliente extends Manejador{

    @Override
    public String handle(Pedido pedido) {
        if(pedido.getCliente().estaActivo()==false){
            return "El Cliente \n" + pedido.getCliente().toString() + "\n--------No esta activo!!!--------\n";
        }
        if(manejador !=null){
            return manejador.handle(pedido);
        }
        return "Solicitud no se puede Procesar";
    }
    
}
