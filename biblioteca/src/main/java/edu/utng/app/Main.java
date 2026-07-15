package edu.utng.app; // 🛠️ CORRECCIÓN: Ajustado al paquete raíz de tu universidad (UTNG)

// Importaciones necesarias para interactuar con tus clases locales
import edu.utng.dao.Conexion;

// Importaciones estándar del ciclo de vida de una aplicación JavaFX
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 💡 NOTA PARA JUNIOR — ¿Qué es la clase Main en JavaFX?
 * Es el punto de entrada oficial de toda tu aplicación. En JavaFX no arrancamos directamente 
 * desde el clásico método 'main', sino a través del método heredado 'start', el cual se encarga 
 * de preparar la ventana gráfica (el escenario) antes de mostrarla al usuario.
 */
public class Main extends Application {

    /**
     * El método start() es el corazón del arranque gráfico de JavaFX.
     * 
     * @param primaryStage El "Escenario Principal" (Stage). Piensa en esto como el marco de la 
     *                     ventana física de tu sistema operativo (los botones de cerrar, maximizar, etc.).
     * @throws Exception Avisa que si el archivo FXML no se encuentra o está mal escrito, el programa fallará.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        // 🧱 PASO 1: Inicialización obligatoria de la Persistencia
        // Invocamos el método estático de tu clase Conexion. Esto asegura que el archivo SQLite 
        // y la tabla 'libros' existan en el disco duro ANTES de que la ventana intente leer datos.
        Conexion.inicializarBaseDatos();

        // 🖼️ PASO 2: Carga del diseño visual (FXML)
        // FXMLLoader lee el archivo XML y lo transforma en objetos reales de Java en memoria.
        // getClass().getResource() busca el archivo "libro_view.fxml" dentro de tus carpetas de recursos o código.
        // Parent es el contenedor genérico que almacena toda la estructura visual leída.
        Parent root = FXMLLoader.load(getClass().getResource("/edu/utng/usuario_view.fxml")); 
        // 📌 NOTA: Se recomienda usar una ruta absoluta con '/' al inicio para que el IDE la encuentre sin importar dónde compiles.

        // 🏷️ PASO 3: Configuración de la Ventana
        // Le asignamos un título que aparecerá en la barra superior de la ventana.
        primaryStage.setTitle("CRUD Biblioteca - JavaFX + SQLite");
        
        // Creamos la "Escena" (Scene). La Escena es el lienzo donde se dibuja el contenido (root).
        // Los números 500 y 450 definen el ancho y alto inicial de la ventana en píxeles.
        primaryStage.setScene(new Scene(root, 600, 400));
        
        // 🔥 PASO 4: Renderizado final
        // Hace visible la ventana en la pantalla del usuario. Si olvidas esta línea, el programa correrá pero no verás nada.
        primaryStage.show();
    }

    /**
     * El método main tradicional de Java.
     * En aplicaciones JavaFX su única responsabilidad es despertar el entorno gráfico.
     */
    public static void main(String[] args) {
        // launch(args) es un método interno de JavaFX que prepara el sistema operativo,
        // levanta los hilos gráficos de renderizado y finalmente manda a llamar automáticamente al método 'start' de arriba.
        launch(args);
    }
}
