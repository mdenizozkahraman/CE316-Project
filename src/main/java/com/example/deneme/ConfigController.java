package com.example.deneme;

import com.example.deneme.Compilers.CCompiler;
import com.example.deneme.Compilers.JavaCompiler;
import com.example.deneme.Compilers.PythonInterpreter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfigController implements Initializable{

    @FXML
    private Button runCommandArgsChooser;
    @FXML
    private Button expectedoutcomeChooser;
    @FXML
    private Button fileChooser;
    @FXML
    private Label fileChooserLabel;
    @FXML
    private TextField pathtextField;
    @FXML
    private ChoiceBox<String> mychoiceBox;
    @FXML
    private Button cancelButton;
    @FXML
    private Button okeyButton;
    @FXML
    private TextField compilerPathfield;
    @FXML
    private TextField compilerInterpreterargsfield;
    @FXML
    private TextField runcommandfield;
    @FXML
    private TextField runcommandArgspathfield;
    @FXML
    private TextField expectedOutcomepathfield;

    @FXML
    private String[] langugages = {"C","Python","JAVA"};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        mychoiceBox.getItems().addAll(langugages);
        mychoiceBox.getSelectionModel().selectFirst();

        if(Config.getInstance().COMPILERPATH != null){
            compilerPathfield.setText(Config.getInstance().COMPILERPATH);
            compilerInterpreterargsfield.setText(Config.getInstance().COMPILERINTERPRETERARGS);
            runcommandfield.setText(Config.getInstance().RUNCOMMAND);
            runcommandArgspathfield.setText(Config.getInstance().RUNCOMMANDARGS);
            expectedOutcomepathfield.setText(Config.getInstance().EXPECTEDOUTCOME);

            Language selectedLanguage = Config.getInstance().SELECTEDLANGUAGE;
            switch (selectedLanguage.toString()) {
                case "C":
                    mychoiceBox.getSelectionModel().select(1);
                    break;
                case "JAVA":
                    mychoiceBox.getSelectionModel().select(2);
                    break;
                case "PYTHON":
                    mychoiceBox.getSelectionModel().select(3);
                    break;
                default:
                    // Handle default case if needed
                    break;
            }

        }

    }

    @FXML
    public void pathDirectoryChooser(ActionEvent event) {
        Node source = (Node) event.getSource();
        Window window = source.getScene().getWindow();

        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(window);

        if (selectedDirectory != null) {
            String directoryPath = selectedDirectory.getAbsolutePath();
            pathtextField.setText(directoryPath);
            System.out.println("Selected directory path: " + directoryPath);
        } else {
            System.out.println("No directory selected");
        }
    }

    @FXML
    public void expectedDirectoryChooser(ActionEvent event) {
        Node source = (Node) event.getSource();
        Window window = source.getScene().getWindow();

        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(window);

        if (selectedDirectory != null) {
            String directoryPath = selectedDirectory.getAbsolutePath();
            expectedOutcomepathfield.setText(directoryPath);
            System.out.println("Selected directory path: " + directoryPath);
        } else {
            System.out.println("No directory selected");
        }
    }



    public void choiceBoxChanged(ActionEvent event) {


        String selectedLanguage = mychoiceBox.getSelectionModel().getSelectedItem();

        switch (selectedLanguage) {
            case "C":
                compilerPathfield.setText(CCompiler.COMPILER_PATH);
                compilerInterpreterargsfield.setText(CCompiler.ARGS);
                runcommandfield.setText(CCompiler.RUN_COMMAND);
                break;
            case "JAVA":
                compilerPathfield.setText(JavaCompiler.COMPILER_PATH);
                compilerInterpreterargsfield.setText("");
                runcommandfield.setText(JavaCompiler.RUN_COMMAND);
                break;
            case "Python":
                compilerPathfield.setText(PythonInterpreter.COMPILER_PATH);
                compilerInterpreterargsfield.setText(PythonInterpreter.ARGS);
                runcommandfield.setText("");

                break;
            default:
                break;
        }
    }

    @FXML
    public void runButtonClicked(ActionEvent event) {
        if (mychoiceBox.getSelectionModel().getSelectedItem() == "C"){
            String runOutput = compileAndRunC(pathtextField.getText());
            String expectedOutput = compileAndRunC(expectedOutcomepathfield.getText());

            if (runOutput.equals(expectedOutput)) {
                System.out.println("Correct");
            } else {
                System.out.print("Incorrect");
            }

        }
        else if (mychoiceBox.getSelectionModel().getSelectedItem() == "Python"){
            String runOutput = runPythonInterpreter(pathtextField.getText());
            String expectedOutput = runPythonInterpreter(expectedOutcomepathfield.getText());

            if (runOutput.equals(expectedOutput)) {
                System.out.println("Correct");
            } else {
                System.out.print("Incorrect");
            }
        }

        if (mychoiceBox.getSelectionModel().getSelectedItem() == "JAVA"){
            String runOutput = compileAndRunJava(pathtextField.getText());
            String expectedOutput = compileAndRunJava(expectedOutcomepathfield.getText());

            if (runOutput.equals(expectedOutput)) {
                System.out.println("Correct");
            } else {
                System.out.print("Incorrect");
            }
        }


    }
    @FXML
    public String runPythonInterpreter(String filePath) {


        File workingDirectory = new File(filePath);

        PythonInterpreter pythonInterpreter = new PythonInterpreter(workingDirectory);

        try {
            Result runResult = pythonInterpreter.run(PythonInterpreter.COMPILER_PATH, PythonInterpreter.ARGS);



            return runResult.getOutput();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }

    @FXML
    public String compileAndRunJava(String filePath) {



        File workingDirectory = new File(filePath);

        JavaCompiler javaCompiler = new JavaCompiler(workingDirectory);

        try {
            Result compileResult = javaCompiler.compile(JavaCompiler.COMPILER_PATH, "Test.java");



            if (compileResult.getStatus() == 0) {
                Result runResult = javaCompiler.run(JavaCompiler.RUN_COMMAND, "Test");


                return runResult.getOutput();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-2";
    }

    @FXML
    public String compileAndRunC(String filePath) {


        File workingDirectory = new File(filePath);

        CCompiler cCompiler = new CCompiler(workingDirectory);

        try {
            Result compileResult = cCompiler.compile(CCompiler.COMPILER_PATH, CCompiler.ARGS);



            if (compileResult.getStatus() == 0) {
                Result runResult = cCompiler.run(workingDirectory + CCompiler.RUN_COMMAND, "");


                return runResult.getOutput();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-3";

    }








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






