package co.edu.poli.modelo;

public interface IObserver {
    void update(AjustadorPrecios porcentaje, AjustadorPrecios.TipoEnum tipo);
}
