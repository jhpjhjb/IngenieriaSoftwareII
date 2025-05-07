package co.edu.poli.modelo;

public class ManjeadorPedido extends Manejador{

    @Override
    public String handle(Pedido pedido) {
        if(pedido.getTotal() <=0){
            return "-------El total del Pedido debe ser Superior a 0, verifica los precios de Productos-----\n";
        }
        if(manejador!=null){
            return manejador.handle(pedido);
        }
        else{
            return "-----------Pedido Procesa Exitosamente-----------\n";
        }
    }
    
}
