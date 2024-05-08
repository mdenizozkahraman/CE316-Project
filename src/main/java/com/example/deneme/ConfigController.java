package com.example.deneme;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfigController implements Initializable{


    @FXML
    private ChoiceBox<String> mychoiceBox;
    @FXML
    private Button cancelButton;
    @FXML
    private Button okeyButton;


    @FXML
    private String[] langugages = {"C","Python","JAVA"};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        mychoiceBox.getItems().addAll(langugages);

    }




    @FXML
    private Button fileChooser;
    @FXML
    private Label fileChooserLabel;

    @FXML
    private TextField pathtextField;



    /*
   public void fileChooser(ActionEvent event){
       FileChooser fc = new FileChooser();
       fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Word Files ",listfile));
       File f = fc.showOpenDialog(null);
    }
*/
    @FXML
    public void fileChooser(ActionEvent event) {
        Node source = (Node) event.getSource();
        Window window = source.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(window);

        if (file != null) {
            String filePath = file.getAbsolutePath();
            pathtextField.setText(filePath); // Update TextField directly
            System.out.println("Selected file path: " + filePath); // Optional for debugging
        } else {
            System.out.println("No file selected");
        }

        event.consume();
    }
}






