package edu.utng.controller;

// Importaciones de las capas lógicas (Patrón DAO y Modelo de datos)
import edu.utng.dao.interfaces.LibroDAO;
import edu.utng.dao.impl.LibroDAOImpl;
import edu.utng.model.Libro;

// Importaciones específicas de JavaFX para UI y reactividad
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LibroController {

    // 💡 NOTA PARA JUNIOR: La anotación @FXML vincula estas variables directamente con los fx:id del archivo XML.
    // El tipo de dato debe coincidir exactamente con la etiqueta XML (ej. TextField, TableView).
    @FXML private TextField txtTitulo;
    @FXML private TextField txtAutor;
    @FXML private TextField txtAnio;
    
    // TableView requiere un parámetro genérico <Libro> para saber qué tipo de objeto va a renderizar en sus filas.
    @FXML private TableView<Libro> tblLibros;
    
    // TableColumn requiere dos parámetros: <TipoDeObjeto, TipoDeDatoDeLaColumna>. 
    // Usamos 'Number' para datos numéricos como ID y Año para evitar conflictos entre Integer/Double.
    @FXML private TableColumn<Libro, Number> colId;
    @FXML private TableColumn<Libro, String> colTitulo;
    @FXML private TableColumn<Libro, String> colAutor;
    @FXML private TableColumn<Libro, Number> colAnio;

    // Capa de acceso a datos (Principio de inversión de dependencias: declaramos la interfaz, no la implementación)
    private LibroDAO libroDAO;
    
    // ObservableList: Lista especial de JavaFX. Si agregas o quitas un elemento aquí, 
    // la interfaz gráfica (TableView) se actualiza automáticamente sin hacer un refresh manual.
    private ObservableList<Libro> listaLibros;
    
    // Variable de estado para recordar qué fila de la tabla tocó el usuario (crucial para Modificar y Eliminar)
    private Libro libroSeleccionado;

    /**
     * El método initialize() se ejecuta AUTOMÁTICAMENTE después de que el archivo FXML se ha cargado.
     * Es el equivalente al constructor para componentes visuales de JavaFX.
     */
    @FXML
    public void initialize() {
        // Inicializamos el objeto DAO para interactuar con la base de datos
        libroDAO = new LibroDAOImpl();
        
        // Inicializamos la lista observable vacía
        listaLibros = FXCollections.observableArrayList();
        
        //  CONFIGURACIÓN DE COLUMNAS (Mapeo de datos):
        // cellData.getValue() nos da el objeto 'Libro' de la fila actual.
        // .idProperty(), .tituloProperty(), etc., exponen la propiedad reactiva definida en la clase Modelo.
        colId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        colTitulo.setCellValueFactory(cellData -> cellData.getValue().tituloProperty());
        colAutor.setCellValueFactory(cellData -> cellData.getValue().autorProperty());
        colAnio.setCellValueFactory(cellData -> cellData.getValue().anioProperty());

        // Consultamos la base de datos por primera vez para llenar la tabla
        cargarLibros();

        //  ESCUCHADOR DE SELECCIÓN (Listener):
        // Monitorea en tiempo real si el usuario hace clic en una fila de la tabla.
        tblLibros.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            // 'newSelection' contiene el objeto Libro de la fila seleccionada (o null si se deselecciona)
            if (newSelection != null) {
                libroSeleccionado = newSelection; // Guardamos la referencia del libro activo
                
                // Pasamos los datos del objeto a los campos de texto del formulario
                txtTitulo.setText(libroSeleccionado.getTitulo());
                txtAutor.setText(libroSeleccionado.getAutor());
                // String.valueOf() convierte el número (año) a texto, ya que setText() solo acepta Strings
                txtAnio.setText(String.valueOf(libroSeleccionado.getAnio()));
            }
        });
    }

    /**
     * Método auxiliar para sincronizar la lista visual con los registros reales de la Base de Datos.
     */
    private void cargarLibros() {
        listaLibros.clear(); // Vaciamos la lista local para no duplicar datos visuales
        listaLibros.addAll(libroDAO.listar()); // Consultamos al DAO y agregamos los registros frescos
        tblLibros.setItems(listaLibros); // Vinculamos la lista observable a la tabla visual
    }

    /**
     * Evento asignado al botón "Guardar" en el FXML (onAction="#guardarLibro").
     */
    @FXML
    private void guardarLibro() {
        if (validarCampos()) {
            // Integer.parseInt() convierte el texto del año en número entero para construir el objeto Libro
            Libro nuevoLibro = new Libro(txtTitulo.getText(), txtAutor.getText(), Integer.parseInt(txtAnio.getText()));
            
            // Si el DAO inserta correctamente el registro en la base de datos...
            if (libroDAO.insertar(nuevoLibro)) {
                cargarLibros();   // Recargamos la tabla para ver el nuevo libro
                limpiarCampos();  // Borramos el formulario
            }
        }
    }

    /**
     * Evento asignado al botón "Modificar" en el FXML (onAction="#modificarLibro").
     */
    @FXML
    private void modificarLibro() {
        // Validación de seguridad: Aseguramos que haya un libro seleccionado y que los inputs tengan texto
        if (libroSeleccionado != null && validarCampos()) {
            // Actualizamos los atributos del libro que ya teníamos seleccionado en memoria
            libroSeleccionado.setTitulo(txtTitulo.getText());
            libroSeleccionado.setAutor(txtAutor.getText());
            libroSeleccionado.setAnio(Integer.parseInt(txtAnio.getText()));

            // Enviamos el objeto modificado al método actualizar de la capa DAO
            if (libroDAO.actualizar(libroSeleccionado)) {
                cargarLibros();
                limpiarCampos();
            }
        }
    }

    /**
     * Evento asignado al botón "Eliminar" en el FXML (onAction="#eliminarLibro").
     */
    @FXML
    private void eliminarLibro() {
        // Validación de seguridad: No podemos eliminar nada si el usuario no ha hecho clic en una fila primero
        if (libroSeleccionado != null) {
            // Enviamos únicamente el ID numérico al método del DAO
            if (libroDAO.eliminar(libroSeleccionado.getId())) {
                cargarLibros();
                limpiarCampos();
            }
        }
    }

    /**
     * Restablece el formulario a su estado inicial limpio.
     * Evento asignado al botón "Limpiar" en el FXML (onAction="#limpiarCampos").
     */
    @FXML
    private void limpiarCampos() {
        txtTitulo.clear();
        txtAutor.clear();
        txtAnio.clear();
        libroSeleccionado = null; // Rompemos la referencia del libro seleccionado por seguridad
        tblLibros.getSelectionModel().clearSelection(); // Deseleccionamos visualmente la fila de la tabla
    }

    /**
     * Método auxiliar de validación de negocio.
     * @return true si TODOS los campos tienen texto, false si al menos uno está vacío.
     */
    private boolean validarCampos() {
        // .isEmpty() devuelve true si la cadena mide 0 caracteres. Usamos '!' para negar (que NO esté vacío).
        return !txtTitulo.getText().isEmpty() && !txtAutor.getText().isEmpty() && !txtAnio.getText().isEmpty();
    }
}
