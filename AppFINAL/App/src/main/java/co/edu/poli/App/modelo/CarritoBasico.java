package co.edu.poli.App.modelo;

public class CarritoBasico implements Carrito {
    private double precioBase;
    private double costoEnvio;

    public CarritoBasico(double precioBase, double costoEnvio) {
        this.precioBase = precioBase;
        this.costoEnvio = costoEnvio;
    }

    @Override
    public String getDescripcion() {
        return "Carrito de compras";
    }

    @Override
    public double getCosto() {
        return precioBase + costoEnvio;
    }
}
