
package com.guiusuario;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("mainview.fxml"));
        Parent root = fxmlLoader.load();
        
        Scene scene = new Scene(root, 900, 600);
        // Vinculamos los estilos CSS globales del nuevo paquete
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        
        stage.setTitle("Sistema Guiusuario");
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
