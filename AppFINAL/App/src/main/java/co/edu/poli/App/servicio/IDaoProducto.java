package co.edu.poli.App.servicio;

import java.util.List;

public interface IDaoProducto<Producto> extends IDao<Producto>{
    List<Producto> consultaDetallada(Producto producto);
}
