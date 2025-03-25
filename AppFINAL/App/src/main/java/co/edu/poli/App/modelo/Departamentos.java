package co.edu.poli.App.modelo;

import java.util.ArrayList;
import java.util.List;

public class Departamentos implements IGeneral{
    private String area;
    private List<IGeneral> componentes;

    
    public void setArea(String area) {
        this.area = area;
    }


    public void setComponentes(List<IGeneral> componentes) {
        this.componentes = componentes;
    }

    
    public String getArea() {
        return area;
    }


    public List<IGeneral> getComponentes() {
        return componentes;
    }


    public Departamentos(String area) {
        this.area = area;
        this.componentes = new ArrayList<>();
    }

    public void addComponent(IGeneral nuevoComponenete){
        componentes.add(nuevoComponenete);
    }
    public void deleteComponent(IGeneral exComponente){
        componentes.remove(exComponente);
    }


    @Override
    public String detalles() {
        return String.format("Departamento 📂 {Area: %s}", area);
    }
    
}
