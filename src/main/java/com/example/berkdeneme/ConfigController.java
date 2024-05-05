package com.example.berkdeneme;

import com.example.berkdeneme.Compilers.CCompiler;
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
import java.io.IOException;
import java.io.ObjectInputFilter;
import java.net.URL;
import java.nio.file.Files;
import java.util.Objects;
import java.util.ResourceBundle;
import static com.example.berkdeneme.Main.stage;

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
        if (Config.getInstance().compilerPath != null){
            compilerPath.setText(Config.getInstance().compilerPath);
            assignmentPath.setText(Config.getInstance().assignmentPath);
            args.setText(Config.getInstance().args);
            runField.setText(Config.getInstance().run);
            expected.setText(Config.getInstance().expected);
            Language selectedLang = Config.getInstance().selectedLanguage;
            if (selectedLang == null){
            }else if (selectedLang.toString().equals("C")){
                configComboBox.getSelectionModel().select(1);
            }else if (selectedLang.toString().equals("Java")){
                configComboBox.getSelectionModel().select(2);
            }else if (selectedLang.toString().equals("Python")){
                configComboBox.getSelectionModel().select(3);
            }
        }
        runButton.setOnAction(actionEvent -> {
            try {
                if (compilerPath.getText() == null || args.getText() == null || assignmentPath.getText() == null || expected.getText() == null || runField.getText() == null ||compilerPath.getText().isBlank() ||assignmentPath.getText().isBlank() || args.getText().isBlank() || runField.getText().isBlank() || expected.getText().isBlank()){
                    throw new Exception("There is a field not filled.");
                }
                Config.getInstance().compilerPath = compilerPath.getText();
                Config.getInstance().assignmentPath = assignmentPath.getText();
                Config.getInstance().args = args.getText();
                Config.getInstance().run = runField.getText();
                Config.getInstance().expected = expected.getText();
                Config.getInstance().runArgs = runFieldArgs.getText();
            }catch (Exception e){
                e.printStackTrace();

            }
        });

        StringBuilder selectedDirectory = new StringBuilder();
        assignmentImportButton.setOnAction(actionEvent -> {
            try {
                configImport(selectedDirectory,list);
                if (assignmentPath != null){
                    Config.getInstance().assignmentPath = assignmentPath.getText();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });
      runImportButton.setOnAction(actionEvent -> {
          FileChooser fc = new FileChooser();
          File selectedF = fc.showOpenDialog(stage);
          if (selectedF != null) {
              try {
                  String content = Files.readString(selectedF.toPath());
                  runFieldArgs.setText(content);
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      });
      expectedImportButton.setOnAction(actionEvent -> {
          FileChooser fc = new FileChooser();
          File selectedF = fc.showOpenDialog(stage);
          if (selectedF != null) {
              try {
                  String content = Files.readString(selectedF.toPath());
                  expected.setText(content);
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      });

      saveButton.setOnAction(actionEvent -> configExport(selectedDirectory));
        configComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String comboBoxVal = configComboBox.getValue();
              String directoryStr = Config.getInstance().assignmentPath;
                if (comboBoxVal == null) {
                } else if (comboBoxVal.equals("C")) {
                    Config.getInstance().selectedLanguage = Language.C;
                } else if (comboBoxVal.equals("Java")) {
                    Config.getInstance().selectedLanguage = Language.JAVA;
                } else if (comboBoxVal.equals("Python")) {
                    Config.getInstance().selectedLanguage = Language.PYTHON;
                }
            }
        });

        deleteButton.setOnAction(actionEvent -> {
            String filePath = selectedDirectory + File.separator + "config";
            File f = new File(filePath);
            if (f.exists()) {
                boolean deleted = f.delete();
            }
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
        expected.setText(Config.getInstance().expected);
    }
    private void configImport(){

    }
    private void configExport(){

    }
}
