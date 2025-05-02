package co.edu.poli.controlador;

import co.edu.poli.modelo.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class controladorPat {
    private boolean mostrar = true;
    private AjustadorPrecios.TipoEnum tipo;
    private AjustadorPrecios ajustador = new AjustadorPrecios();
    private Producto productoActual = new Producto(2024, "Burguer", 1500);
    private Producto productoNuevo = new Producto(2025, "Hot Dog", 1200);
    private ToggleGroup grupo = new ToggleGroup();
    private Historial historial = new Historial();

    // Componentes FXML (se mantienen igual)
    @FXML private Button BttGuadrar;
    @FXML private Button BttHistorial;
    @FXML private TextField TextFieldAnno;
    @FXML private TextField TextFieldNombre, productoEliminar;
    @FXML private TextField TextFieldPrecio;
    @FXML private AnchorPane contenedorObser;
    @FXML private Button bttMas,bttUnsus;
    @FXML private Button bttMenos;
    @FXML private RadioButton desc10;
    @FXML private RadioButton desc15;
    @FXML private RadioButton desc25;
    @FXML private RadioButton desc50;
    @FXML private TextField otroValor;
    @FXML private Button patronMemento;
    @FXML private Button patronObserver;
    @FXML private AnchorPane contenedorMem;


    @FXML
    public void initialize() {
        grupo.getToggles().add(desc10);
        grupo.getToggles().add(desc25);
        grupo.getToggles().add(desc50);
        grupo.getToggles().add(desc15);

        desc10.setUserData(10);
        desc15.setUserData(15);
        desc25.setUserData(25);
        desc50.setUserData(50);
        ajustador.attach(productoActual);
        ajustador.attach(productoNuevo);
        
        // Inicializar campos con valores del producto actual
        TextFieldAnno.setText(String.valueOf(productoActual.getAño()));
        TextFieldNombre.setText(productoActual.getNombre());
        TextFieldPrecio.setText(String.valueOf(productoActual.getPrecio()));
    }

    @FXML
    void clickMas(ActionEvent event) {
        tipo = AjustadorPrecios.TipoEnum.REDUCIR;
        ajustarPrecios(tipo);
    }

    @FXML
    void clickMenos(ActionEvent event) {
        tipo = AjustadorPrecios.TipoEnum.INCREMENTAR;
        ajustarPrecios(tipo);
    }

    public void ajustarPrecios(AjustadorPrecios.TipoEnum tipo) {
        String anterior = "Productos sin Variacion de " + (tipo.equals(AjustadorPrecios.TipoEnum.INCREMENTAR) ? "Aumento" : "Reduccion") + "\n" + ajustador.devolverLista();

        if (grupo.getSelectedToggle() != null) {
            int porcentaje = (int) grupo.getSelectedToggle().getUserData();
            ajustador.setPorcentaje(porcentaje, tipo);
            mostrarAlerta(anterior + "\nDescuento " + porcentaje + "% " + (tipo.equals(AjustadorPrecios.TipoEnum.INCREMENTAR) ? "Sumado" : "Restado") + " a Todos los Productos\n" + ajustador.devolverLista(), AlertType.INFORMATION);
        } else if (!otroValor.getText().isEmpty()) {
            try {
                int porcentaje = Integer.parseInt(otroValor.getText());
                ajustador.setPorcentaje(porcentaje, tipo);
                mostrarAlerta(anterior + "\nDescuento " + porcentaje + "% " + (tipo.equals(AjustadorPrecios.TipoEnum.INCREMENTAR) ? "Sumado" : "Restado") + " a Todos los Productos\n" + ajustador.devolverLista(),AlertType.INFORMATION);
            } catch (NumberFormatException e) {
                mostrarAlerta("Porcentaje ingresado no es válido", AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Selecciona o digita alguna opción", AlertType.ERROR);
        }
        grupo.selectToggle(null);
        otroValor.clear();
    }

    @FXML
    void clickMemento(ActionEvent event) {
        mostrar = !mostrar;
        patronObserver.setVisible(!mostrar);
        contenedorMem.setVisible(mostrar);
    }

    @FXML
    void clickObserver(ActionEvent event) {
        mostrar = !mostrar;
        patronMemento.setVisible(mostrar);
        contenedorObser.setVisible(!mostrar);
    }

    @FXML
    void guardarProducto(ActionEvent event) {
        String nombre = TextFieldNombre.getText().trim();
        String anioStr = TextFieldAnno.getText().trim();
        String precioStr = TextFieldPrecio.getText().trim();

        if (nombre.isEmpty() || anioStr.isEmpty() || precioStr.isEmpty()) {
            mostrarAlerta("Todos los campos deben estar llenos", AlertType.ERROR);
            return;
        }

        try {
            int anio = Integer.parseInt(anioStr);
            double precio = Double.parseDouble(precioStr);

            // Primero actualiza el producto
            productoActual.setAño(anio);
            productoActual.setNombre(nombre);
            productoActual.setPrecio(precio);

            // Luego guarda el nuevo estado
            OriginatorProducto originator = new OriginatorProducto(productoActual);
            ProductoMemento memento = originator.guardarEstado();
            historial.guardar(anio, nombre, memento);

            mostrarAlerta("Estado guardado correctamente:\n" + productoActual.toString(), AlertType.INFORMATION);

        } catch (NumberFormatException e) {
            mostrarAlerta("Año debe ser entero y precio debe ser decimal", AlertType.ERROR);
        }
    }

    @FXML
    void mostrarHistorial(ActionEvent event) {
        // Crear diálogo de búsqueda
        TextField inputAnio = new TextField();
        TextField inputNombre = new TextField();
        inputAnio.setPromptText("Año del producto");
        inputNombre.setPromptText("Nombre del producto");

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.add(new Label("Año:"), 0, 0);
        grid.add(inputAnio, 1, 0);
        grid.add(new Label("Nombre:"), 0, 1);
        grid.add(inputNombre, 1, 1);

        Alert dialogo = new Alert(AlertType.CONFIRMATION);
        dialogo.setTitle("Buscar en Historial");
        dialogo.setHeaderText("Ingrese los datos del producto");
        dialogo.getDialogPane().setContent(grid);
        dialogo.showAndWait();

        try {
            int anio = Integer.parseInt(inputAnio.getText().trim());
            String nombre = inputNombre.getText().trim().toLowerCase();

            ProductoMemento memento = historial.obtener(anio, nombre);

            if (memento != null) {
                OriginatorProducto originator = new OriginatorProducto(productoActual);
                originator.restaurarEstado(memento);

                // Ahora actualizamos los campos visuales
                TextFieldAnno.setText(String.valueOf(productoActual.getAño()));
                TextFieldNombre.setText(productoActual.getNombre());
                TextFieldPrecio.setText(String.valueOf(productoActual.getPrecio()));

                mostrarAlerta("Estado restaurado exitosamente", AlertType.INFORMATION);
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("El año debe ser un número válido", AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String mensaje, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.show();
    }

    @FXML
    void clickOtroValor(MouseEvent event) {
        grupo.selectToggle(null);
    }


    @FXML
    void clickDesuscribir(ActionEvent event) {
        if(!productoEliminar.getText().trim().isEmpty()){
            var lista = ajustador.devolverLista();
                for (IObserver iObserver : lista) {
                    if(((Producto) iObserver).getNombre().equals(productoEliminar.getText())){
                        ajustador.dettach(iObserver);
                        break;
                    }
                }
                mostrarAlerta("Producto Eliminado Con Exito\n" + ajustador.listaRecorrida(), AlertType.INFORMATION);
        }
    }

}