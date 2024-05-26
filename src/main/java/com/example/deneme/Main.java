package com.example.deneme;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        URL fxmlLocation = getClass().getResource("scene1.fxml");
        if (fxmlLocation == null) {
            System.err.println("scene1.fxml not found!");
            System.exit(1);
        }
        Parent root = FXMLLoader.load(fxmlLocation);
        Scene scene = new Scene(root);
        stage.setTitle("Integrated Environment System");
        stage.setScene(scene);
        stage.show();
    }

    public static void showCreateProject() throws IOException {
        try {
            URL fxmlLocation = Main.class.getResource("createProject.fxml");
            if (fxmlLocation == null) {
                System.err.println("createProject.fxml not found!");
                System.exit(1);
            }
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            BorderPane createProject = loader.load();
            Stage createProjectStage = new Stage();
            createProjectStage.setTitle("Create Project");
            createProjectStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(createProject);
            createProjectStage.setScene(scene);
            createProjectStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void showResultScene(String filePath, String output, String expectedOutput, String result) throws IOException {
        try {
            URL fxmlLocation = Main.class.getResource("resultScene.fxml");
            if (fxmlLocation == null) {
                System.err.println("resultScene.fxml not found!");
                System.exit(1);
            }
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            AnchorPane resultScene = loader.load();

            ResultController controller = loader.getController();
            controller.addResult(filePath, output, expectedOutput, result);

            Stage resultSceneStage = new Stage();
            resultSceneStage.setTitle("ResultScene!");
            resultSceneStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(resultScene);
            resultSceneStage.setScene(scene);
            resultSceneStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
