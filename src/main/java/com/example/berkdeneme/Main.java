package com.example.berkdeneme;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
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

    public static void main(String[] args) {
        launch();
    }
}