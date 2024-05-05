package com.example.berkdeneme;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ResultController implements Initializable {

    private final ObservableList<HWSubmission> hws = FXCollections.observableArrayList();
    @FXML
    private TableView<HWSubmission> table;
    @FXML
    private Button backButton;
    @FXML
    private Button recompileButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        compile();

        backButton.setOnAction(actionEvent -> {
            try {

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        recompileButton.setOnAction(actionEvent -> {
            table.getColumns().clear();
            hws.clear();
            File file = new File(Config.getInstance().assignmentPath + "/finish.txt");
            if (file.exists()) {
                if (file.delete()) {
                    compile();
                }
            } else {
                compile();
            }
        });
    }


    private void compile() {
    }

    private void resultFileImport(File file) {
        try (Scanner s = new Scanner(file)) {
            StringBuilder submission = new StringBuilder();
            while (s.hasNext()) {
                String line = s.nextLine();
                submission.append(line);
                if (line.endsWith("$")) {
                    String submissionString = submission.toString();
                    String[] values = submissionString.split(",");
                    if (values.length == 6) {
                        String id = values[0];
                        String output = values[1];
                        String result = values[2];
                        String status = values[3];
                        String error = values[4];
                        String expectedValue = values[5].substring(0, values[5].length() - 1);

                        hws.add(new HWSubmission(id, output, result, Integer.parseInt(status), error, expectedValue));
                    }
                    submission.setLength(0);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void tableToFileExport(File file) {
        try (PrintWriter wr = new PrintWriter(file)) {
            for (HWSubmission submission : hws) {
                wr.println(submission.getId() + "," + submission.getOutput() + "," +
                        submission.getResult() + "," + submission.getStatus() + "," +
                        submission.getError() + "," + submission.getExpected() + "$"); // $ indicates the submission's end position
            }
            // success
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupTable() {
    }

}
