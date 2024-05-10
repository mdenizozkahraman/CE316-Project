
package com.example.deneme;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MainController implements Initializable {
    @FXML
    private Button createNewButton;
    @FXML
    private Button helpButton;
    @FXML
    private Button exitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createNewButton.setOnAction(actionEvent -> {
            try {
                com.example.deneme.Main.showCreateProject();
            } catch (IOException e) {
            }
        });


        helpButton.setOnAction(actionEvent -> {
            String helpTXT = " Integrated Assignment Environment ";

            MainController.createHelp(helpTXT, "Help");

        });


        exitButton.setOnAction(actionEvent -> Platform.exit());
    }


    public static void createHelp(String content, String header) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("HELP");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }



}
