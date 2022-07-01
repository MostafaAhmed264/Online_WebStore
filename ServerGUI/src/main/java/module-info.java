module com.example.servergui {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    opens com.example.servergui to javafx.fxml;
    exports com.example.servergui;
}