package co.edu.poli.App.controlador;

import java.sql.SQLException;
import java.util.List;

import co.edu.poli.App.modelo.Producto;
import co.edu.poli.App.modelo.ProductoAlimenticio;
import co.edu.poli.App.modelo.ProductoElectrico;
import co.edu.poli.App.servicio.DaoProductoAlimenticio;
import co.edu.poli.App.servicio.DaoProductoElectrico;


public class ControladorProductos {
    
    DaoProductoAlimenticio producto_Alimenticio;
    DaoProductoElectrico producto_Electrico;
    
    public ControladorProductos() throws ClassNotFoundException, SQLException{
        this.producto_Alimenticio = new DaoProductoAlimenticio();
        this.producto_Electrico = new DaoProductoElectrico();
    }

    // ---------------- PRODUCTO ALIMENTICIO CONTROLADOR 

    public List<Producto> mostrarProductosAlimenticios(){
        return producto_Alimenticio.listarObjetos();
    }

    public Producto mostrarProductoAlimenticio(int id){
        return producto_Alimenticio.buscarPorId(id);
    }

    public String ingresarProductoAlimenticio(ProductoAlimenticio productoAlimenticio){
        return producto_Alimenticio.ingresarObjeto(productoAlimenticio);
    }

    public String actualizarProductoAlimenticio(ProductoAlimenticio producto_actualizado){
        return producto_Alimenticio.actualizarObjeto(producto_actualizado);
    }

    public Producto eliminarProductoAlimenticio(int id){
        return producto_Alimenticio.eliminarObjeto(id);
    }

    // ------------------ PRODUCTO ELECTRICO CONTROLADOR 

    public List<Producto> mostrarProductosElectricos(){
        return producto_Electrico.listarObjetos();
    }

    public Producto mostrarProductoElectrico(int id){
        return producto_Electrico.buscarPorId(id);
    }

    public String ingresarProductoElectrico(ProductoElectrico productoElectrico){
        return producto_Electrico.ingresarObjeto(productoElectrico);
    }

    public String actualizarProductoElectrico(ProductoElectrico producto_actualizado){
        return producto_Electrico.actualizarObjeto(producto_actualizado);
    }

    public Producto eliminarProductoElectrico(int id){
        return producto_Electrico.eliminarObjeto(id);
    }

    public List<Producto> consultaEspecial(Producto productos){
        return producto_Electrico.consultaDetallada(productos);
    }

}
