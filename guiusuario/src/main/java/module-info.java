module com.guiusuario {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.guiusuario to javafx.fxml;
    opens com.guiusuario.controller to javafx.fxml;
    
    exports com.guiusuario;
    exports com.guiusuario.controller;
    //exports com.guiusuario.model;
}
