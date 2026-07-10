package com.guiusuario.controller;

import com.guiusuario.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane mainContainer;

    @FXML
    public void initialize() {
        // Carga la pantalla de cuadrícula por defecto al iniciar
        mostrarPantallaGrid();
    }

    @FXML
    private void mostrarPantallaGrid() {
        cargarVista("primary.fxml");
    }

    @FXML
    private void mostrarPantallaResponsiva() {
        cargarVista("secondary.fxml");
    }

    @FXML
    private void mostrarPantallaLista() {
        cargarVista("primary.fxml"); 
    }

    @FXML
    private void mostrarPantallaDetalle() {
        cargarVista("secondary.fxml");
    }

    // Método utilitario centralizado para alternar las vistas en el contenedor principal
    private void cargarVista(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(fxmlFile));
            Parent vista = loader.load();
            mainContainer.setCenter(vista);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar la vista: " + fxmlFile);
        }
    }
}