
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
            String helpTXT = "To use the Integrated Assignment Environment (IAE) software, follow these steps: \n\n When you click on the \"Create Project\" button, a new window will open with various configuration settings. To save the current configuration, use the \"Save\" button located below. If you wish to delete the configuration, you can use the \"Delete\" button. After clicking either the \"Save\" or \"Delete\" button, you must click the \"Refresh\" button to update the window.\n" +
                    "\n" +
                    "When you press the \"Run\" button, a new window will open displaying information about the relevant folder, the file output, the expected output, and the result.";

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
