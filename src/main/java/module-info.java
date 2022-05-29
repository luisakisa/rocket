module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens client to javafx.fxml;
    exports client;
}