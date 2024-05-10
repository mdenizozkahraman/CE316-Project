package com.example.deneme;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;


public class ResultController implements Initializable {
    @FXML
    private TableView<ResultSceneClass> resultsTable;

    @FXML
    private TableColumn<ResultSceneClass, String> path;

    @FXML
    private TableColumn<ResultSceneClass, String> expectedOutput;

    @FXML
    private TableColumn<ResultSceneClass, String> output;

    @FXML
    private TableColumn<ResultSceneClass, String> result;

    ObservableList<ResultSceneClass> resultsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        path.setCellValueFactory(new PropertyValueFactory<>("path"));
        expectedOutput.setCellValueFactory(new PropertyValueFactory<>("expectedOutput"));
        output.setCellValueFactory(new PropertyValueFactory<>("output"));
        result.setCellValueFactory(new PropertyValueFactory<>("result"));

        resultsTable.setItems(resultsList);
    }

    public void addResult(String path, String output, String expectedOutput, String result) {
        ResultSceneClass resultSceneClass = new ResultSceneClass(path, output, expectedOutput, result);
        resultsList.add(resultSceneClass);
    }
}

