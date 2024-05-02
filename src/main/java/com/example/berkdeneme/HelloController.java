package com.example.berkdeneme;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class HelloController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label welcomeText;
    @FXML
    private Button startNewButton;
    @FXML
    private StackPane mainStackPane;


}