package co.edu.poli.modelo;

import java.util.HashMap;
import java.util.Map;

public class Historial {
    private final Map<String, ProductoMemento> historialPorClave;

    public Historial() {
        this.historialPorClave = new HashMap<>();
    }

    public void guardar(int año, String nombreProducto, ProductoMemento memento) {
        String clave = generarClave(año, nombreProducto);
        historialPorClave.put(clave, memento);
    }

    public ProductoMemento obtener(int año, String nombreProducto) {
        String clave = generarClave(año, nombreProducto);
        return historialPorClave.get(clave);
    }

    private String generarClave(int año, String nombreProducto) {
        return año + "_" + nombreProducto.trim().toLowerCase(); 
    }
}