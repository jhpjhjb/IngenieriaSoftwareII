package co.edu.poli.App.modelo;

public class CarritoVIP extends CarritoDecorador{
    public CarritoVIP (Carrito carrito){
        super(carrito);
    }

    @Override
    public String getDescripcion() {
        return carrito.getDescripcion() + " con Carrito VIP (Carrito 2 x 1!! Y 50% descuento)";
    }

    @Override
    public double getCosto() {
        return carrito.getCosto() * 0.5;
    }
    

}
