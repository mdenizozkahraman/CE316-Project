package com.example.deneme;

import com.example.deneme.Compilers.CCompiler;
import com.example.deneme.Compilers.JavaCompiler;
import com.example.deneme.Compilers.PythonInterpreter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ConfigController implements Initializable {

    public ConfigController() {
    }

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
    private ChoiceBox<String> savesChoiceBox;
    @FXML
    private Button refreshButton;
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
    private JButton saveButton;
    @FXML
    private JButton deleteButton;

    @FXML
    private String[] langugages = {"C", "Python", "JAVA"};

    public static String[] getFilenames(String directoryPath) {
        File directory = new File(directoryPath);

        if (!directory.exists() || !directory.isDirectory()) {
            return null;
        }

        File[] files = directory.listFiles();

        String[] filenames = new String[files.length];

        for (int i = 0; i < files.length; i++) {
            filenames[i] = files[i].getName();
        }

        return filenames;
    }

    private String[] files = getFilenames("JSONFiles");

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        mychoiceBox.getItems().addAll(langugages);
        mychoiceBox.getSelectionModel().selectFirst();

        savesChoiceBox.getItems().addAll(files);
        savesChoiceBox.getSelectionModel().selectFirst();





      /*  if (Config.getInstance().COMPILERPATH != null) {
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

        }*/

        refreshButton.setOnAction(actionEvent -> {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("createProject.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root);
            Stage stage = (Stage) refreshButton.getScene().getWindow();
            stage.setScene(scene);
        });





        okeyButton.setOnAction(actionEvent -> {

            Path path = Paths.get(pathtextField.getText());


            try {
                ResultSceneClass result = runButtonClicked();
                Main.showResultScene(path.getFileName().toString(),result.getRunOutput(),result.getExpectedOutput(),result.getResult());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });




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
                compilerInterpreterargsfield.setText(JavaCompiler.ARGS);
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
    public ResultSceneClass runButtonClicked() throws IOException {
        String runOutput = null;
        String expectedOutput = null;
        String result = null;


        if (mychoiceBox.getSelectionModel().getSelectedItem() == "C") {

            runOutput = compileAndRunC(pathtextField.getText());
            expectedOutput = compileAndRunC(expectedOutcomepathfield.getText());

            if (runOutput.equals(expectedOutput)) {
                result = "Correct";
            } else {
                result = "Incorrect";
            }


        } else if (mychoiceBox.getSelectionModel().getSelectedItem() == "Python") {

            runOutput = runPythonInterpreter(pathtextField.getText());
            expectedOutput = runPythonInterpreter(expectedOutcomepathfield.getText());

            if (runOutput.equals(expectedOutput)) {
                result = "Correct";
            } else {
                result = "Incorrect";
            }
        }

        else if (mychoiceBox.getSelectionModel().getSelectedItem() == "JAVA") {

            runOutput = compileAndRunJava(pathtextField.getText());
            expectedOutput = compileAndRunJava(expectedOutcomepathfield.getText());

            if (runOutput.equals(expectedOutput)) {
                result = "Correct";
            } else {
                result = "Incorrect";
            }
        }
        return new ResultSceneClass(pathtextField.getText(),runOutput, expectedOutput, result);
    }

    @FXML
    public String runPythonInterpreter(String filePath) {


        File workingDirectory = new File(filePath);

        PythonInterpreter pythonInterpreter = new PythonInterpreter(workingDirectory);

        try {
            Result runResult = pythonInterpreter.run(compilerPathfield.getText(), compilerInterpreterargsfield.getText());


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
            Result compileResult = javaCompiler.compile(compilerPathfield.getText(), compilerInterpreterargsfield.getText());


            if (compileResult.getStatus() == 0) {
                Result runResult = javaCompiler.run(runcommandfield.getText(), "");


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
            Result compileResult = cCompiler.compile(compilerPathfield.getText(), compilerInterpreterargsfield.getText());


            if (compileResult.getStatus() == 0) {
                Result runResult = cCompiler.run(workingDirectory + runcommandfield.getText(), "");


                return runResult.getOutput();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-3";

    }

    public void json() {
        String folderPath = "JSONFiles";

        ObjectMapper objectMapper = new ObjectMapper();

        try {

            String jsonFileName = "JsonFile_" + System.currentTimeMillis() + ".json";
            String jsonFilePath = folderPath + File.separator + jsonFileName;

            JsonNode jsonData = objectMapper.createObjectNode();

            JsonNode data = objectMapper.createObjectNode()
                    .put("Language", mychoiceBox.getSelectionModel().getSelectedItem())
                    .put("chooseFile", pathtextField.getText())
                    .put("compilerPath", compilerPathfield.getText())
                    .put("compiler", compilerInterpreterargsfield.getText())
                    .put("runCommand", runcommandfield.getText())
                    .put("expected", expectedOutcomepathfield.getText());

            List<JsonNode> infos = new ArrayList<>();
            infos.add(data);

            ((ObjectNode) jsonData).set("Requirements", objectMapper.valueToTree(infos));

            // JSON dosyasını yaz
            ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();
            writer.writeValue(new File(jsonFilePath), jsonData);

            System.out.println("Added to JSON: " + data.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void loadSelectedJson(ActionEvent event) {

        String selectedJsonFileName = savesChoiceBox.getSelectionModel().getSelectedItem();


        String selectedJsonFilePath = "JSONFiles" + File.separator + selectedJsonFileName;

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(new File(selectedJsonFilePath));


            String language = rootNode.path("Requirements").path(0).path("Language").asText();
            String chooseFile = rootNode.path("Requirements").path(0).path("chooseFile").asText();
            String compilerPath = rootNode.path("Requirements").path(0).path("compilerPath").asText();
            String interpreterArgs = rootNode.path("Requirements").path(0).path("compiler").asText();
            String runCommand = rootNode.path("Requirements").path(0).path("runCommand").asText();
            String expected = rootNode.path("Requirements").path(0).path("expected").asText();


            mychoiceBox.getSelectionModel().select(language);
            pathtextField.setText(chooseFile);
            compilerPathfield.setText(compilerPath);
            compilerInterpreterargsfield.setText(interpreterArgs);
            runcommandfield.setText(runCommand);
            expectedOutcomepathfield.setText(expected);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    public void clearJson() {
        // Seçilen JSON dosyasının adını al
        String selectedJsonFileName = savesChoiceBox.getSelectionModel().getSelectedItem();

        // Seçilen JSON dosyasının tam yolunu oluştur
        String selectedJsonFilePath = "JSONFiles" + File.separator + selectedJsonFileName;

        try {
            // Seçilen JSON dosyasını sil
            File file = new File(selectedJsonFilePath);
            if (file.delete()) {
                System.out.println("Selected JSON file has been deleted successfully.");
            } else {
                System.out.println("Failed to delete the selected JSON file.");
            }

            // UI'daki boşlukları temizle
            mychoiceBox.getSelectionModel().clearSelection();
            pathtextField.clear();
            compilerPathfield.clear();
            compilerInterpreterargsfield.clear();
            runcommandfield.clear();
            expectedOutcomepathfield.clear();

            // Silinen dosyayı ChoiceBox'tan da kaldır
            savesChoiceBox.getItems().remove(selectedJsonFileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}





