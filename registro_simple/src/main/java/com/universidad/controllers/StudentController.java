package com.universidad.controllers;

import java.util.Optional;

import com.universidad.domain.Student;
import com.universidad.usecase.ManageStudentsUseCase;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class StudentController {

    /* =========================================================================
     * COMPONENTES DE LA INTERFAZ GRÁFICA (FXML)
     * =========================================================================
     * Estos campos están vinculados directamente con los elementos visuales 
     * del archivo FXML. Permiten capturar y mostrar los datos del estudiante.
     */

    @FXML private TextField txtNombre;
    @FXML private TextField txtDireccion;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtCorreo;
    @FXML private TextArea txtAreaLista;

    //Se prepara un objeto que pertitira acceder a un conjunto de Student

    private final ManageStudentsUseCase useCase = new ManageStudentsUseCase();

    @FXML
    public void handleRegistrar() {
        // Se obtiene una variable de cada comonente
        String nombre = txtNombre.getText();
        String direccion = txtDireccion.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();


        //Se revisa si alguno de los campos se encuentra vacio
        if (nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
            mostrarAlerta("Campos Incompletos", "Por favor, rellene todos los campos del formulario.");
            return;
        }

        Student nuevoEstudiante = new Student(nombre, direccion, telefono, correo);
        useCase.registerStudent(nuevoEstudiante);
        
        actualizarLista();
        limpiarCampos();
    }

    private void actualizarLista() {
        // Este código sirve para recorrer la lista de todos los estudiantes registrados, 
        // armar un reporte de texto limpio y ordenado con sus datos, y mostrarlo en la pantalla 
        // dentro del componente TextArea (txtAreaLista) de tu interfaz gráfica

        StringBuilder sb = new StringBuilder();
        sb.append("=== ESTUDIANTES REGISTRADOS ===\n\n");
        for (Student s : useCase.getAllStudents()) {
            sb.append(s.toString()).append("\n");
            sb.append("--------------------------------------------------\n");
        }
        txtAreaLista.setText(sb.toString());
    }
    
    @FXML
    public void limpiarCampos() {
        txtNombre.clear();
        txtDireccion.clear();
        txtTelefono.clear();
        txtCorreo.clear();
        txtNombre.requestFocus();
    }

    private void mostrarAlerta(String titulo, String mensaje) {

        // Este bloque de código sirve para mostrar una ventana emergente de aviso 
        // (un mensaje de alerta) en la pantalla del usuario usando JavaFX. 
        // Se utiliza comúnmente para advertir sobre un error, un campo vacío o una validación 
        // incorrecta en un formulario.

        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    public void salir(){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Salir de la aplicación");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Estás seguro de que deseas salir?");

        // Muestra la ventana y espera la respuesta del usuario
        Optional<ButtonType> resultado = alerta.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            Platform.exit();
        }

    }
}
