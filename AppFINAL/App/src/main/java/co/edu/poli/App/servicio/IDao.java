package co.edu.poli.App.servicio;
import java.util.List;

public interface IDao <T> {
    List<T> listarObjetos();
    T buscarPorId(int id);
    String ingresarObjeto(T object);
    String actualizarObjeto(T object);
    T eliminarObjeto(int id);
}
