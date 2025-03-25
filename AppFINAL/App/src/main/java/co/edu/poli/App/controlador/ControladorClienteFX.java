package co.edu.poli.App.controlador;

import java.lang.ProcessHandle.Info;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

import co.edu.poli.App.modelo.Certificacion;
import co.edu.poli.App.modelo.Cliente;
import co.edu.poli.App.modelo.Departamentos;
import co.edu.poli.App.modelo.Empleado;
import co.edu.poli.App.modelo.Evaluacion;
import co.edu.poli.App.modelo.IGeneral;
import co.edu.poli.App.modelo.IPagoExterno;
import co.edu.poli.App.modelo.Nequi;
import co.edu.poli.App.modelo.NequiAdapter;
import co.edu.poli.App.modelo.Paypal;
import co.edu.poli.App.modelo.PaypalAdapter;
import co.edu.poli.App.modelo.PoliticaEntrega;
import co.edu.poli.App.modelo.ProcesarPago;
import co.edu.poli.App.modelo.Producto;
import co.edu.poli.App.modelo.ProductoElectrico;
import co.edu.poli.App.modelo.Proveedor;
import co.edu.poli.App.servicio.DaoCliente;
import co.edu.poli.App.servicio.DaoProductoAlimenticio;
import co.edu.poli.App.servicio.DaoProductoElectrico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ControladorClienteFX {

    private Boolean mostrar = false;
    private DaoCliente controladorCliente;
    private Button ultimoBotonPresionado;
    private DaoProductoAlimenticio controladorProductoAlimento;
    private DaoProductoElectrico controlaProductoElectrico;
    @FXML
    private CheckBox CheckCertificacion;

    @FXML
    private CheckBox CheckEvaluacion;

    @FXML
    private CheckBox CheckPolitica;

    @FXML
    private Label LabelProveedor;
    @FXML
    private Button adapter;

    @FXML
    private Button bttBuild;

    @FXML
    private Button bttNequi;

    @FXML
    private Button bttOk;

    @FXML
    private Button bttPaypal;

    @FXML
    private Button clonar;

    @FXML
    private Button composite;

    @FXML
    private Button consult;

    @FXML
    private Button consultid;

    @FXML
    private Button delete;

    @FXML
    private TextField id1;

    @FXML
    private Button insert;

    @FXML
    private Button bttCrearProveedor;

    @FXML
    private TextField montoPagar;

    @FXML
    private TextField nombre;

    @FXML
    private Button update;

    @FXML
    private ListView<String> jerarquia;


    public ControladorClienteFX() throws ClassNotFoundException, SQLException {
        this.controladorProductoAlimento = new DaoProductoAlimenticio();
        this.controladorCliente = new DaoCliente();
        this.controlaProductoElectrico = new DaoProductoElectrico();
    }



    @FXML
    void clickActualizar(ActionEvent event) {
        ultimoBotonPresionado = update;
        id1.setDisable(false);
        id1.clear();
        nombre.setDisable(false);
        nombre.clear();
    }

    @FXML
    void clickClonarId(ActionEvent event) {
        ultimoBotonPresionado = clonar;
        id1.setPromptText("Id Producto");
        nombre.setPromptText("Tipo de Producto");
        id1.setDisable(false);
        nombre.setDisable(false);
        id1.clear();
        nombre.clear();

    }

    @FXML
    void clickConsultar(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        List<Cliente> cliente = controladorCliente.listarObjetos();
        id1.clear();
        nombre.clear();
        String clientes = "";
        for (Cliente clientex: cliente){
            clientes+= clientex + "\n";
        }
        alert.setContentText(clientes);
        alert.setTitle("Lista de Clientes :)");
        alert.show();
    }

    @FXML
    void clickConsultarId(ActionEvent event) {
        ultimoBotonPresionado = consultid;
        id1.setDisable(false);
        nombre.setDisable(true);
        bttOk.setDisable(false);
    }

    @FXML
    void clickEliminar(ActionEvent event) {
        ultimoBotonPresionado = delete;
        id1.setDisable(false);
        nombre.setDisable(true);

    }

    @FXML
    void clickInsertar(ActionEvent event) {
        ultimoBotonPresionado = insert;
        id1.setDisable(true);
        nombre.setDisable(false);

    }

    private void mostrarAlerta(String titulo, String header, String contenido) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.show();
    }

    @FXML
    void clickOk(ActionEvent event) {
        int id = 0;
        if (!id1.getText().isEmpty()){
            bttOk.setDisable(false);
            id = Integer.parseInt(id1.getText());
        }
        Cliente cliente = null;
        String nombres = nombre.getText();

        if(ultimoBotonPresionado == consultid){
            cliente= controladorCliente.buscarPorId(id);
            mostrarAlerta("Cliente" + cliente.getNombre(), "Informacion Sobre: " + cliente.getNombre() + " :)", cliente.toString());
        }
        else if(ultimoBotonPresionado == delete){
            cliente = controladorCliente.eliminarObjeto(id);
            mostrarAlerta("Cliente Eliminado: " + cliente.getNombre(), "Cliente Eliminado Exitosamente: " + cliente.getNombre() + " :(", cliente.toString());
        }
        else if(ultimoBotonPresionado == insert){
            cliente = new Cliente(nombres);
            mostrarAlerta("Cliente Ingresado: " + cliente.getNombre(), "Cliente Insertado Exitosamente: " + cliente.getNombre(), controladorCliente.ingresarObjeto(cliente) + ": " + cliente.getNombre());
        }
        else if (ultimoBotonPresionado == update){
            cliente = new Cliente(id,nombres);
            mostrarAlerta("Cliente Actualizado: " + cliente.getNombre(), "Cliente Actualizado Exitosamente: " + cliente.getNombre(), controladorCliente.actualizarObjeto(cliente) + ": " + cliente.getNombre());
        }
        else if (ultimoBotonPresionado == clonar){
            if(nombre.getText().equalsIgnoreCase("electrico")){
                Producto electrico = controlaProductoElectrico.buscarPorId(id);
                Producto electrico2 = electrico.clonar();
                controlaProductoElectrico.ingresarObjeto(electrico2);
                mostrarAlerta("Producto Electrico" + electrico2.getDescripcion(), "Producto Electrico Clonado Exitosamente: " + electrico2.getDescripcion(), electrico2.toString());
            }
            else if(nombre.getText().equalsIgnoreCase("alimento")){
                Producto alimento = controladorProductoAlimento.buscarPorId(id);
                Producto alimento2 = alimento.clonar();
                controladorProductoAlimento.ingresarObjeto(alimento2);
                mostrarAlerta("Producto Alimento " + alimento2.getDescripcion(), "Producto Alimento Clonado Exitosamente: " + alimento2.getDescripcion(), alimento2.toString());
            }
        }
    }
    @FXML
    void validarEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            boolean habilitar = !id1.getText().trim().isEmpty() || !nombre.getText().trim().isEmpty();
            bttOk.setDisable(!habilitar);
        }
    }

    @FXML
    void clickComposite(ActionEvent event) {
        mostrar = !mostrar;
        adapter.setVisible(!mostrar);
        bttBuild.setVisible(!mostrar);
        jerarquia.setVisible(mostrar);
        jerarquia.getItems().clear();

        Departamentos departamentoTI = new Departamentos("Tecnología");
        Departamentos departamentoRH = new Departamentos("Recursos Humanos");

        Empleado empleado1 = new Empleado("Juan Pérez", "Desarrollador", "3,500,000");
        Empleado empleado2 = new Empleado("Ana Gómez", "Analista de Sistemas", "4,200,000");
        Empleado empleado3 = new Empleado("Carlos López", "Reclutador", "3,000,000");
        Empleado empleado4 = new Empleado("Carlos Gomez", "Jefe de Todos", "30,000,000");

        departamentoTI.addComponent(empleado1);
        departamentoTI.addComponent(empleado2);
        departamentoRH.addComponent(empleado3);

        Departamentos empresa = new Departamentos("Empresa XYZ");
        empresa.addComponent(departamentoTI);
        empresa.addComponent(departamentoRH);
        empresa.addComponent(empleado4);

        jerarquia.getItems().add(empresa.detalles());
        for (IGeneral dep : empresa.getComponentes()) {
            jerarquia.getItems().add("  " + dep.detalles());
            if (dep instanceof Departamentos) {
                for (IGeneral emp : ((Departamentos) dep).getComponentes()) {
                jerarquia.getItems().add("    " + emp.detalles()); 
            }
            }   
        }
    }


    @FXML
    void NequiClick(ActionEvent event) {
        if (!montoPagar.getText().isEmpty()) {
            double monto = Double.parseDouble(montoPagar.getText());
            String mensaje = ProcesarPago.procesarPago("Nequi", monto);
            montoPagar.clear();
            JOptionPane.showMessageDialog(null, mensaje); 
        }
        else{
            JOptionPane.showMessageDialog(null, "Ingrese Un Valor Numerico", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void PaypalClick(ActionEvent event) {
        if (!montoPagar.getText().isEmpty()) {
            double monto = Double.parseDouble(montoPagar.getText());
            String mensaje = ProcesarPago.procesarPago("Paypal", monto);
            montoPagar.clear();
            JOptionPane.showMessageDialog(null, mensaje); 
        }
        else{
            JOptionPane.showMessageDialog(null, "Ingrese Un Valor Numerico", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void clickAdapter(ActionEvent event) {
        mostrar = !mostrar;
        montoPagar.setVisible(mostrar);
        bttNequi.setVisible(mostrar);
        bttPaypal.setVisible(mostrar);
        composite.setVisible(!mostrar);
        bttBuild.setVisible(!mostrar);

    }

    @FXML
    void clickBuilder(ActionEvent event) {   
        mostrar = !mostrar;
        adapter.setVisible(!mostrar);
        composite.setVisible(!mostrar);
        LabelProveedor.setVisible(mostrar);
        CheckCertificacion.setVisible(mostrar);
        CheckEvaluacion.setVisible(mostrar);
        CheckPolitica.setVisible(mostrar);
        bttCrearProveedor.setVisible(mostrar);
    }

    @FXML
    void clickCrearProveedor(ActionEvent event) {
        Proveedor.Builder builder = new Proveedor.Builder("Paulis INC");
        Alert alert = new Alert(AlertType.INFORMATION);
        if (CheckCertificacion.isSelected()) {
            builder.conCertificacion(new Certificacion("ISO 9001"));
        } 
        if (CheckEvaluacion.isSelected()) {
            builder.conEvaluacion(new Evaluacion("5 Estrellas"));
        } 
        if (CheckPolitica.isSelected()) {
            builder.conPoliticaEntrega(new PoliticaEntrega("Entrega en 48 horas"));
        } 
        
        Proveedor proveedor = builder.build();

        alert.setContentText(proveedor.toString());
        alert.setTitle("iNFORMACIÓN PROVEEDOR");
        alert.show();

    }

}