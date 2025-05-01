package co.edu.poli.modelo;

public interface IObserver {
    void update(AjustadorPrecios porcentaje, TipoEnum tipo);
}
