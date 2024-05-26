package com.example.deneme;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.HashSet;
import java.util.Set;

public class ResultController implements Initializable {
    public ResultController() {}

    @FXML
    private TableView<ResultSceneClass> resultsTable;

    @FXML
    private TableColumn<ResultSceneClass, String> path;

    @FXML
    private TableColumn<ResultSceneClass, String> expectedOutput;

    @FXML
    private TableColumn<ResultSceneClass, String> output;

    @FXML
    private Button helpButton2;

    @FXML
    private TableColumn<ResultSceneClass, String> result;

    ObservableList<ResultSceneClass> resultsList = FXCollections.observableArrayList();
    Set<String> uniquePaths = new HashSet<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        path.setCellValueFactory(new PropertyValueFactory<>("path"));
        output.setCellValueFactory(new PropertyValueFactory<>("runOutput"));
        expectedOutput.setCellValueFactory(new PropertyValueFactory<>("expectedOutput"));
        result.setCellValueFactory(new PropertyValueFactory<>("result"));

        resultsTable.setItems(resultsList);

        helpButton2.setOnAction(actionEvent -> {
            String helpTXT = "The \"ID\" column, which is the first of the four columns you see in this window, gives the student number, the \"OUTPUT\" column gives the outputs of the code, the \"EXPECTED OUTCOME\" column gives the actual output that the code should give, and the \"RESULT\" column gives the result of comparing the expected output with the student output." +
                    "If two values are the same, it displays \"Correct\"; if they are different, it displays \"Incorrect\".";

            ResultController.help(helpTXT, "Help");
        });

        showAllResults();
    }

    public static void help(String content, String header) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("HELP");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void addResult(String path, String output, String expectedOutput, String result) {
        if (uniquePaths.add(path)) { // Only add if the path is unique
            ResultSceneClass resultSceneClass = new ResultSceneClass(path, output, expectedOutput, result);
            resultsList.add(resultSceneClass);
        }
    }

    public void showAllResults() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("results.json")));
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String path = jsonObject.getString("path");
                String runOutput = jsonObject.getString("runOutput");
                String expectedOutput = jsonObject.getString("expectedOutput");
                String result = jsonObject.getString("result");

                addResult(path, runOutput, expectedOutput, result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
