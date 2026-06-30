package com.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SaludoController {

    private static String mensaje;

    @FXML
    private Label lblSaludo;

    public static void setMensaje(String texto) {
        mensaje = texto;
    }

    @FXML
    public void initialize() {
        lblSaludo.setText(mensaje);
    }

    @FXML
    private void regresar() throws IOException {
        App.setRoot("primary");
    }
}
