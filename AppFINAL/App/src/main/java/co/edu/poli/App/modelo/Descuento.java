package co.edu.poli.App.modelo;

public class Descuento extends CarritoDecorador {
    private double porcentajeDescuento;

    public Descuento(Carrito carrito, double porcentajeDescuento) {
        super(carrito);
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public String getDescripcion() {
        return carrito.getDescripcion() + " con Descuento del " + porcentajeDescuento + "%";
    }

    @Override
    public double getCosto() {
        return carrito.getCosto() * (1 - porcentajeDescuento / 100);
    }
}