package co.edu.poli.modelo;

import java.util.ArrayList;
import java.util.List;

public class Proxy implements IProducto{
    private List<Productos> producto = new ArrayList<>();
    private Ussers usuario;
    public Proxy(List<Productos> productos, Ussers usuario){
        this.producto = productos;
        this.usuario = usuario;
    }
    @Override
    public String detalles() {
        String detalles = "";
        for (Productos productos: producto){
            if(productos.getAutorizacion() <= usuario.getNivelAutorizacion()){
                detalles+= productos.detalles() + "\n\n";
            }
            else{
                detalles+= "Acceso Denagado a: " + productos.getNombre() + "\n\n";
            }
        }
        return detalles;
    }


}
