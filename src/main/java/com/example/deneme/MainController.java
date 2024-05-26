package com.example.deneme;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
                Main.showCreateProject();
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Error", "An error occurred while trying to create a new project.");
            }
        });

        helpButton.setOnAction(actionEvent -> {
            String helpTXT = "To use the Integrated Assignment Environment (IAE) software, follow these steps: \n\n" +
                    "When you click on the \"Create Project\" button, a new window will open with various configuration settings.";
            createHelp(helpTXT, "Help");
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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
