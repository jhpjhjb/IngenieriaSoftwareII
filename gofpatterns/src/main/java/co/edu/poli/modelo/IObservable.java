package co.edu.poli.modelo;

public interface IObservable {
    void attach(IObserver suscriptor);
    void dettach(IObserver suscriptor);
    void notify(TipoEnum tipo);
}
