module com.example.berkdeneme {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.deneme to javafx.fxml;
    exports com.example.deneme;
}