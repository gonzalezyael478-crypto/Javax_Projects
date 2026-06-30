module com.universidad {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.universidad to javafx.fxml;
    exports com.universidad;

      // 3. LA SOLUCIÓN AL ERROR: Abrir el subpaquete donde está tu controlador
    opens com.universidad.controllers to javafx.fxml;
}
