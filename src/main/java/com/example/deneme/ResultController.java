package com.example.deneme;

import com.example.deneme.Compilers.CCompiler;
import com.example.deneme.Compilers.Compiler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Comparator;
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
        File f = new File(Config.getInstance().assignmentPath + "/results.txt");
        if (f.exists()) {
            resultFileImport(f);
            setupTable();
        } else {
            try {
                File[] submissions = new File(Config.getInstance().assignmentPath).listFiles();
                for (File file : submissions) {
                    if (!file.isFile()) { // if it is a directory
                        Compiler compiler;
                        switch (Config.getInstance().selectedLanguage) {
                            case C:
                                compiler = new CCompiler(file);
                                break;
                            case JAVA:
                                // compiler = new JavaCompiler(file);
                                break;
                            case PYTHON:
                                //compiler = new PythonCompiler(file);
                                break;
                            default:
                                compiler = new CCompiler(file);
                                break;
                        }
                        Result result;

                        setupTable();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupTable() {
     /*   TableColumn<HWSubmission> idColumn = new TableColumn<>("ID", true, Comparator.comparing(HWSubmission::getId));
        TableColumn<HWSubmission> outputColumn = new TableColumn<>("Output", true, Comparator.comparing(HWSubmission::getOutput));
        TableColumn<HWSubmission> expectedValueColumn = new TableColumn<>("Expected Value", true, Comparator.comparing(HWSubmission::getExpectedValue));
        TableColumn<HWSubmission> resultColumn = new TableColumn<>("Result", true, Comparator.comparing(HWSubmission::getResult));
        TableColumn<HWSubmission> statusColumn = new TableColumn<>("Status", true, Comparator.comparing(HWSubmission::getStatus));
        TableColumn<HWSubmission> errorColumn = new TableColumn<>("Error", true, Comparator.comparing(HWSubmission::getError));
        idColumn.setRowCellFactory(submission -> new TableRowCell<>(HWSubmission::getId));
        outputColumn.setRowCellFactory(submission -> new TableRowCell<>(HWSubmission::getOutput));
        resultColumn.setRowCellFactory(submission -> new TableRowCell<>(HWSubmission::getResult));
        statusColumn.setRowCellFactory(submission -> new TableRowCell<>(HWSubmission::getStatus));
        errorColumn.setRowCellFactory(submission -> new TableRowCell<>(HWSubmission::getError));
        expectedValueColumn.setRowCellFactory(submission -> new MFXTableRowCell<>(HWSubmission::getExpected));
        table.getTableColumns().addAll(idColumn, outputColumn, resultColumn, statusColumn, errorColumn, expectedValueColumn);
        table.setItems(submissions);

        table.getFilters().addAll(
                new StringFilter<>("ID", Submission::getId),
                new StringFilter<>("Output", Submission::getOutput), //details
                new StringFilter<>("Expected Value", Submission::getExpectedValue),
                new StringFilter<>("Result", Submission::getResult),
                new IntegerFilter<>("Status", Submission::getStatus),
                new StringFilter<>("Error", Submission::getError) //details
        );*/
    }


}