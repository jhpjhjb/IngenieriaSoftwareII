package co.edu.poli.App.modelo;

public class Proveedor {
    private String nombre;
    private Evaluacion evaluacion;
    private PoliticaEntrega politicaEntrega;
    private Certificacion certificacion;

    private Proveedor(Builder builder) {
        this.nombre = builder.nombre;
        this.evaluacion = builder.evaluacion;
        this.politicaEntrega = builder.politicaEntrega;
        this.certificacion = builder.certificacion;
    }

    @Override
    public String toString() {
        String resultado = "Proveedor: " + nombre + "\n";

        if (certificacion != null) {
            resultado += "Certificación: " + certificacion + "\n";
        } else {
            resultado += "Certificación: No asignada\n";
        }

        if (evaluacion != null) {
            resultado += "Evaluación: " + evaluacion + "\n";
        } else {
            resultado += "Evaluación: No asignada\n";
        }

        if (politicaEntrega != null) {
            resultado += "Política de Entrega: " + politicaEntrega + "\n";
        } else {
            resultado += "Política de Entrega: No asignada\n";
        }

    return resultado;
}
    public static class Builder {
        private String nombre;
        private Evaluacion evaluacion;
        private PoliticaEntrega politicaEntrega;
        private Certificacion certificacion;

        public Builder(String nombre) {
            this.nombre = nombre;
        }

        public Builder conEvaluacion(Evaluacion evaluacion) {
            this.evaluacion = evaluacion;
            return this;
        }

        public Builder conPoliticaEntrega(PoliticaEntrega politicaEntrega) {
            this.politicaEntrega = politicaEntrega;
            return this;
        }

        public Builder conCertificacion(Certificacion certificacion) {
            this.certificacion = certificacion;
            return this;
        }

        public Proveedor build() {
            return new Proveedor(this);
        }
    }
}
