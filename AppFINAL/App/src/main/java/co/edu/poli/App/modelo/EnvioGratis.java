package co.edu.poli.App.modelo;

public class EnvioGratis extends CarritoDecorador {
    public EnvioGratis(Carrito carrito) {
        super(carrito);
    }

    @Override
    public String getDescripcion() {
        return carrito.getDescripcion() + " con Envío Gratis";
    }

    @Override
    public double getCosto() {
        return carrito.getCosto() - 30000; 
    }
}
