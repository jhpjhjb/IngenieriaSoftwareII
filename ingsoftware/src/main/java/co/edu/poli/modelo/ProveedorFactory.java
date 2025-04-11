package co.edu.poli.modelo;

import java.util.Map;
import java.util.HashMap;

public class ProveedorFactory {
    private static Map<String, Proveedor> proveedores = new HashMap<>();

    public static Proveedor obtenerProveedor(String nombre) {
        if (!proveedores.containsKey(nombre)) {
            proveedores.put(nombre, new Proveedor(nombre));
        }
        return proveedores.get(nombre);
    }

}

