module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires ojdbc11;


    opens client to javafx.fxml;
    exports client;
}