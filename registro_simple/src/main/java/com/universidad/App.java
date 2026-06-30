package com.universidad;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // Se especifica el nombre de la vista dentro de resources
        scene = new Scene(loadFXML("student_view"), 640, 480);
        stage.setScene(scene);
         // ======= CONFIGURACIONES DE LA VENTANA (STAGE) =======
    
        // 2. Establecer el título de la ventana
        stage.setTitle("Gestión de Estudiantes - Universidad");
    
        // 3. Evitar que el usuario cambie el tamaño de la ventana
        stage.setResizable(false);
    
    // =====================================================
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}