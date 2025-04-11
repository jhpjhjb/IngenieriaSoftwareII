package co.edu.poli.modelo;

public class FormasPago {
    private boolean paypal = true;
    private boolean nequi = true;
    private boolean daviplata = true;    


    public String bloquearForma(String tipo){
        if (tipo.equals("Paypal")) {
            paypal = false;
        } 
        if (tipo.equals("Nequi")) {
            nequi = false;
        } 
        if (tipo.equals("Daviplata")) {
            daviplata = false;
        } 
        return "Metodo de pago |" + tipo + "| ha sido bloqueado";
    }

    public String activarForma(String tipo){
        if (tipo.equals("Paypal")) {
            paypal = true;
        } 
        if (tipo.equals("Nequi")) {
            nequi = true;
        } 
        if (tipo.equals("Daviplata")) {
            daviplata = true;
        } 
        return "Metodo de pago |" + tipo + "| ha sido activada";
    }

    public String mostrarFormas() {
        return "--Metodos de Pago--\n" + "Nequi: " + activa(nequi)+"\nPaypal: "+ activa(paypal) +"\nDaviplata: "+activa(daviplata);
    }

    private String activa(boolean tipo){
        if(tipo == true){
            return "Activo";
        } else {
            return "Bloqueada";
        }
    }
}
