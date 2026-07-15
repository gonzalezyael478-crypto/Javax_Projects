package edu.utng.controller;

// Importaciones de las capas lógicas (Patrón DAO y Modelo de datos)
import edu.utng.dao.interfaces.UsuarioDAO;
import edu.utng.dao.impl.UsuarioDAOImpl;
import edu.utng.model.Usuario;

// Importaciones específicas de JavaFX para UI y reactividad
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UsuarioController {

    // 💡 NOTA PARA JUNIOR: La anotación @FXML vincula estas variables directamente con los fx:id del archivo XML.
    // El tipo de dato debe coincidir exactamente con la etiqueta XML (ej. TextField, TableView).
    @FXML private TextField txtNombre;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtTelefono;
    
    // TableView requiere un parámetro genérico <Usuario> para saber qué tipo de objeto va a renderizar en sus filas.
    @FXML private TableView<Usuario> tblUsuarios;
    
    // TableColumn requiere dos parámetros: <TipoDeObjeto, TipoDeDatoDeLaColumna>. 
    // Usamos 'Number' para datos numéricos como ID y Año para evitar conflictos entre Integer/Double.
    @FXML private TableColumn<Usuario, Number> colId;
    @FXML private TableColumn<Usuario, String> colNombre;
    @FXML private TableColumn<Usuario, String> colCorreo;
    @FXML private TableColumn<Usuario, String> colTelefono;

    // Capa de acceso a datos (Principio de inversión de dependencias: declaramos la interfaz, no la implementación)
    private UsuarioDAO usuarioDAO;
    
    // ObservableList: Lista especial de JavaFX. Si agregas o quitas un elemento aquí, 
    // la interfaz gráfica (TableView) se actualiza automáticamente sin hacer un refresh manual.
    private ObservableList<Usuario> listaUsuarios;
    
    // Variable de estado para recordar qué fila de la tabla tocó el usuario (crucial para Modificar y Eliminar)
    private Usuario usuarioSeleccionado;

    /**
     * El método initialize() se ejecuta AUTOMÁTICAMENTE después de que el archivo FXML se ha cargado.
     * Es el equivalente al constructor para componentes visuales de JavaFX.
     */
    @FXML
    public void initialize() {
        // Inicializamos el objeto DAO para interactuar con la base de datos
        usuarioDAO = new UsuarioDAOImpl();
        
        // Inicializamos la lista observable vacía
        listaUsuarios = FXCollections.observableArrayList();
        
        //  CONFIGURACIÓN DE COLUMNAS (Mapeo de datos):
        // cellData.getValue() nos da el objeto 'Usuario' de la fila actual.
        // .idProperty(), .tituloProperty(), etc., exponen la propiedad reactiva definida en la clase Modelo.
        colId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        colNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        colCorreo.setCellValueFactory(cellData -> cellData.getValue().correoProperty());
        colTelefono.setCellValueFactory(cellData -> cellData.getValue().telefonoProperty());

        // Consultamos la base de datos por primera vez para llenar la tabla
        cargarUsuarios();

        //  ESCUCHADOR DE SELECCIÓN (Listener):
        // Monitorea en tiempo real si el usuario hace clic en una fila de la tabla.
        tblUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            // 'newSelection' contiene el objeto Usuario de la fila seleccionada (o null si se deselecciona)
            if (newSelection != null) {
                usuarioSeleccionado = newSelection; // Guardamos la referencia del Usuario activo
                
                // Pasamos los datos del objeto a los campos de texto del formulario
                txtNombre.setText(usuarioSeleccionado.getNombre());
                txtCorreo.setText(usuarioSeleccionado.getCorreo());
                // String.valueOf() convierte el número (año) a texto, ya que setText() solo acepta Strings
                txtTelefono.setText(String.valueOf(usuarioSeleccionado.getTelefono()));
            }
        });
    }

    /**
     * Método auxiliar para sincronizar la lista visual con los registros reales de la Base de Datos.
     */
    private void cargarUsuarios() {
        listaUsuarios.clear(); // Vaciamos la lista local para no duplicar datos visuales
        listaUsuarios.addAll(usuarioDAO.listar()); // Consultamos al DAO y agregamos los registros frescos
        tblUsuarios.setItems(listaUsuarios); // Vinculamos la lista observable a la tabla visual
    }

    /**
     * Evento asignado al botón "Guardar" en el FXML (onAction="#guardarUsuario").
     */
    @FXML
    private void guardarUsuario() {
        if (validarCampos()) {

            Usuario nuevoUsuario = new Usuario(txtNombre.getText(), txtCorreo.getText(), txtTelefono.getText());

            // Si el DAO inserta correctamente el registro en la base de datos...
            if (usuarioDAO.insertar(nuevoUsuario)) {
                cargarUsuarios();   // Recargamos la tabla para ver el nuevo usuario
                limpiarCampos();  // Borramos el formulario
            }
        }
    }

    /**
     * Evento asignado al botón "Modificar" en el FXML (onAction="#modificarUsuario").
     */
    @FXML
    private void modificarUsuario() {
        // Validación de seguridad: Aseguramos que haya un usuario seleccionado y que los inputs tengan texto
        if (usuarioSeleccionado != null && validarCampos()) {
            // Actualizamos los atributos del usuario que ya teníamos seleccionado en memoria
            usuarioSeleccionado.setNombre(txtNombre.getText());
            usuarioSeleccionado.setCorreo(txtCorreo.getText());
            usuarioSeleccionado.setTelefono(txtTelefono.getText());

            // Enviamos el objeto modificado al método actualizar de la capa DAO
            if (usuarioDAO.actualizar(usuarioSeleccionado)) {
                cargarUsuarios();
                limpiarCampos();
            }
        }
    }

    /**
     * Evento asignado al botón "Eliminar" en el FXML (onAction="#eliminarUsuario").
     */
    @FXML
    private void eliminarUsuario() {
        // Validación de seguridad: No podemos eliminar nada si el usuario no ha hecho clic en una fila primero
        if (usuarioSeleccionado != null) {
            // Enviamos únicamente el ID numérico al método del DAO
            if (usuarioDAO.eliminar(usuarioSeleccionado.getId())) {
                cargarUsuarios();
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
        txtNombre.clear();
        txtCorreo.clear();
        txtTelefono.clear();
        usuarioSeleccionado = null; // Rompemos la referencia del usuario seleccionado por seguridad
        tblUsuarios.getSelectionModel().clearSelection(); // Deseleccionamos visualmente la fila de la tabla
    }

    /**
     * Método auxiliar de validación de negocio.
     * @return true si TODOS los campos tienen texto, false si al menos uno está vacío.
     */
    private boolean validarCampos() {
        // .isEmpty() devuelve true si la cadena mide 0 caracteres. Usamos '!' para negar (que NO esté vacío).
        return !txtNombre.getText().isEmpty() && !txtCorreo.getText().isEmpty() && !txtTelefono.getText().isEmpty();
    }
}
