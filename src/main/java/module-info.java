module com.example.berkdeneme {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.berkdeneme to javafx.fxml;
    exports com.example.berkdeneme;
}