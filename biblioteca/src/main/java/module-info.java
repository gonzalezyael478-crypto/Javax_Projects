module edu.utng {
    // 🛠️ Módulos del sistema requeridos para la interfaz gráfica y bases de datos
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; 
   
    

    // 🔒 PERMISOS DE APERTURA (Reflexión para JavaFX)
    // Se cambia 'edu.utng.dao' por 'edu.utng.controller' porque ahí es donde vive tu LibroController
    opens edu.utng.controller to javafx.fxml; 
    // Mantiene el acceso al modelo para que la tabla (TableView) pueda leer sus propiedades reactivas
    opens edu.utng.model to javafx.base, javafx.fxml;
    opens edu.utng.app to javafx.graphics, javafx.fxml;

    // 🌐 EXPORTACIÓN DE CÓDIGO (Para que otros paquetes se comuniquen entre sí)
    exports edu.utng.model;
    exports edu.utng.dao;
    // Agregamos la exportación de la implementación por si tu base de datos la requiere de forma externa
    exports edu.utng.dao.impl; 
    exports edu.utng.app;
}