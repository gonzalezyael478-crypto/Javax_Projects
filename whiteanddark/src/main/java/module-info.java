module com.whiteanddark {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.whiteanddark to javafx.fxml;
    exports com.whiteanddark;
}
