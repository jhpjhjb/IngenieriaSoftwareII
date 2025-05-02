package co.edu.poli.modelo;

import java.util.ArrayList;
import java.util.List;

public class AjustadorPrecios implements IObservable {

    private int porcentaje;
    private List<IObserver> suscrip = new ArrayList<>();
    
    public int getPorcentaje(){
        return porcentaje;
    }

    public void setPorcentaje(int nuevoPorcentaje, TipoEnum tipo){
        this.porcentaje =nuevoPorcentaje;
        notify(tipo);
    }
    
    @Override
    public void attach(IObserver suscriptor) {
        suscrip.add(suscriptor);
    }

    @Override
    public void dettach(IObserver suscriptor) {
        suscrip.remove(suscriptor);
    }

    @Override
    public void notify(AjustadorPrecios.TipoEnum tipo) {
        for (IObserver iObserver : suscrip) {
            iObserver.update(this, tipo);
        }
    }

    public enum TipoEnum{
        INCREMENTAR,
        REDUCIR
    }

    public List<IObserver> devolverLista(){
        return this.suscrip;
    }

    public String listaRecorrida(){
        String objetos="";
        for (IObserver iObserver : suscrip) {
            objetos += iObserver.toString() + "\n";
        }
        return objetos;
    }
}
