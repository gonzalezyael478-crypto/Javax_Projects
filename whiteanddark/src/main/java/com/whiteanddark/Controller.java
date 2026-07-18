package com.whiteanddark;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class Controller {
    
    @FXML
    private BorderPane parent;
    
    @FXML
    private Label labelMode;

    @FXML
    private Button btnMode;

    @FXML
    private ImageView imgMode;

    private boolean isLightMode = true;


    public void changeMode(ActionEvent event){
        isLightMode = !isLightMode;
        if (isLightMode) {
            setLightMode();
        }else{
            setDarkMode();
        }
    }

    private void setLightMode(){
        parent.getStylesheets().remove(getClass().getResource("/com/whiteanddark/styles/darkMode.css").toExternalForm());
        parent.getStylesheets().add(getClass().getResource("/com/whiteanddark/styles/lightMode.css").toExternalForm());
        Image image = new Image(getClass().getResourceAsStream("/com/whiteanddark/img/luna.png"));
        labelMode.setText("Light Mode");
        labelMode.setTextFill(new Color(0,0,0,1));
        imgMode.setImage(image);
    }

    private void setDarkMode(){
        parent.getStylesheets().remove(getClass().getResource("/com/whiteanddark/styles/lightMode.css").toExternalForm());
        parent.getStylesheets().add(getClass().getResource("/com/whiteanddark/styles/darkMode.css").toExternalForm());
        Image image = new Image(getClass().getResourceAsStream("/com/whiteanddark/img/sol.png"));
        labelMode.setText("Dark Mode");
        labelMode.setTextFill(new Color(1,1,1,1));
        imgMode.setImage(image);
    }

}
