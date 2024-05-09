module com.example.berkdeneme {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.fasterxml.jackson.databind;
    requires java.desktop;


    opens com.example.deneme to javafx.fxml;
    exports com.example.deneme;
}