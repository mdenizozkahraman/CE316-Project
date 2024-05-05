package com.example.berkdeneme;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.ObjectInputFilter;
import java.net.URL;
import java.nio.file.Files;
import java.util.Objects;
import java.util.ResourceBundle;

public class ConfigController implements Initializable {
    @FXML
    private ComboBox<String> configComboBox;
    @FXML
    private TextField assignmentPath;
    @FXML
    private Button assignmentImportButton;
    @FXML
    private TextField compilerPath;
    @FXML
    private TextField args;
    @FXML
    private TextField runField;
    @FXML
    private TextField expected;
    @FXML
    private Button saveButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button runButton;
    @FXML
    private Button runImportButton;
    @FXML
    private Button expectedImportButton;
    @FXML
    private TextField runFieldArgs;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("C","Java","Python");
        configComboBox.setItems(list);
        configComboBox.getSelectionModel().selectFirst();
        /*if (ObjectInputFilter.Config.getInstance()){
            compilerPath.setText();
            assignmentPath.setText();
            args.setText();
            runField.setText();
            expected.setText();
            Language
      //  }*/
        runButton.setOnAction(actionEvent -> {
            try {
                if (compilerPath.getText() == null || args.getText() == null || assignmentPath.getText() == null || expected.getText() == null || runField.getText() == null ||compilerPath.getText().isBlank() ||assignmentPath.getText().isBlank() || args.getText().isBlank() || runField.getText().isBlank() || expected.getText().isBlank()){
                    throw new Exception("There is a field not filled.");
                }
                /*
                Config
                Config
                Config

                 */
            }catch (Exception e){
                e.printStackTrace();

            }
        });

        StringBuilder selectedDirectory = new StringBuilder();
        assignmentImportButton.setOnAction(actionEvent -> {
            try {
               /* if (assignmentPath != null){

                }*/

            }catch (Exception e){
                e.printStackTrace();
            }
        });
      runImportButton.setOnAction(actionEvent -> {
          FileChooser fc = new FileChooser();
         // File selectedF = fc.showOpenDialog()
          //if ()
          try {
            // String content = Files.readString();
            //runFieldArgs.setText();
          }catch (Exception e){
              e.printStackTrace();
          }
      });
      expectedImportButton.setOnAction(actionEvent -> {
          FileChooser fc = new FileChooser();
          // File selectedF = fc.showOpenDialog()
          //if ()
          try {
              // String content = Files.readString();
              //runFieldArgs.setText();
          }catch (Exception e){
              e.printStackTrace();
          }
      });

      //saveButton.setOnAction(actionEvent -> );
        configComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String comboBoxVal = configComboBox.getValue();
             /* String directoryStr = Config;
                if (comboBoxVal == null) {
                } else if (comboBoxVal.equals("C")) {

                } else if (comboBoxVal.equals("Java")) {

                } else if (comboBoxVal.equals("Python")) {

                }*/
            }
        });

        deleteButton.setOnAction(actionEvent -> {
            String filePath = selectedDirectory + File.separator + "config";
            File f = new File(filePath);
           /* if (f.exists()){
                boolean deleted = f.delete();
                if (deleted){

                }else {

                }
            }else {

            }*/
        });
    }


    private void fillTextFields(String assignmentPathPm, String compilerPathPm, String argsPm, boolean runFieldBool, String runFieldPm){
        assignmentPath.setText(Objects.requireNonNullElse(assignmentPathPm," "));
        compilerPath.setText(compilerPathPm);
        args.setText(argsPm);
        runField.setVisible(runFieldBool);
        runField.setManaged(runFieldBool);

        runImportButton.getParent().setVisible(runFieldBool);
        runImportButton.getParent().setManaged(runFieldBool);
        runField.setText(runFieldPm);
        //expected
    }
    private void configImport(){

    }
    private void configExport(){

    }
}
