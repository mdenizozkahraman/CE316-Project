package com.example.deneme;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;


public class Main extends Application {
    private Parent root;
    private Stage stage;
    private Scene scene;
    private Stack<Scene> sceneStack;


    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("scene1.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Integrated Environment System");
        stage.setScene(scene);
        stage.show();
    }
    public static void showCreateProject() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("createProject.fxml"));
        BorderPane createProject = loader.load();
        Stage createProjectStage = new Stage();
        createProjectStage.setTitle("Create Project");
        createProjectStage.initModality(Modality.WINDOW_MODAL);
        // createProjectStage.initOwner();
        Scene scene = new Scene(createProject);
        createProjectStage.setScene(scene);
        createProjectStage.showAndWait();

    }

    public static void showResultScene(String filePath, String output, String expectedOutput, String result) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("resultScene.fxml"));
        AnchorPane resultScene = loader.load();

        ResultController controller = loader.getController();
        controller.addResult(filePath, output,expectedOutput , result);

        Stage resultSceneStage = new Stage();
        resultSceneStage.setTitle("ResultScene!");
        resultSceneStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(resultScene);
        resultSceneStage.setScene(scene);
        resultSceneStage.showAndWait();
    }






    public static void main(String[] args) {
        launch();
    }
}