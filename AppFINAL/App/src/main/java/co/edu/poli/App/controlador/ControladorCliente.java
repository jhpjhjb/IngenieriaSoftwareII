package co.edu.poli.App.controlador;
import java.sql.SQLException;
import java.util.List;
import co.edu.poli.App.modelo.Cliente;
import co.edu.poli.App.servicio.DaoCliente;

public class ControladorCliente {
    private DaoCliente daocliente;

    public  ControladorCliente() throws ClassNotFoundException, SQLException{
        this.daocliente = new DaoCliente();
    }

    public  List<Cliente> consultarCliente(){
        return daocliente.listarObjetos();
    }
    public  Cliente buscarId(int id){
        return daocliente.buscarPorId(id);
    }
    public  String ingresarCliente(Cliente cliente){
        return daocliente.ingresarObjeto(cliente);
    }
    public  String actualizarCliente(Cliente cliente){
        return daocliente.actualizarObjeto(cliente);
    }
    public  Cliente eliminarClientes(int id){
        return daocliente.eliminarObjeto(id);
    }
}
