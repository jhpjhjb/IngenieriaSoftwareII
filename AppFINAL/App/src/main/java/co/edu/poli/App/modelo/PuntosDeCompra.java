package co.edu.poli.App.modelo;

public class PuntosDeCompra extends CarritoDecorador {
    public PuntosDeCompra(Carrito carrito) {
        super(carrito);
    }

    public int getPuntos() {
        return (int)(carrito.getCosto() / 10);
    }

    @Override
    public String getDescripcion() {
        return carrito.getDescripcion() + ", con Puntos de Compra";
    }

    @Override
    public double getCosto() {
        return carrito.getCosto();
    }
}
