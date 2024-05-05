package com.example.berkdeneme;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    @FXML
    private Button createButton;
    @FXML
    private Button helpButton;
    @FXML
    private Button exitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createButton.setOnAction(actionEvent -> {
            try {

            }catch (Exception e){
                throw new RuntimeException(e);
            }
        });

        helpButton.setOnAction(actionEvent -> {
            String helpText = "INTEGRATED ASSIGNMENT ENVIRONMENT";

        });

        exitButton.setOnAction(actionEvent -> System.exit(0));
    }
}