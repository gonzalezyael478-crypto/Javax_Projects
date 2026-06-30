package com.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CapturaController {

    @FXML
    private TextField txtNombre;

    @FXML
    private void saludar() throws IOException {

        //Se obtiene el texto de la caja de texto txtNombre
        String nombre = txtNombre.getText();
        System.out.println("Hola"+nombre);  //Salida a consola
        //Se crea un objeto de la Clase Generar Saludo
        GeneradorSaludo saludo = new GeneradorSaludo();
        String mensaje = saludo.generar(nombre);
        SaludoController.setMensaje(mensaje);
        App.setRoot("secondary");
        // Aquí posteriormente se carga
        // la segunda pantalla

    }
}
