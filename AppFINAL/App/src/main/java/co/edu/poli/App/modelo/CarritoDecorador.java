package co.edu.poli.App.modelo;

public abstract class CarritoDecorador implements Carrito{
    protected Carrito carrito;

    public CarritoDecorador(Carrito carrito) {
        this.carrito = carrito;
    }
}
