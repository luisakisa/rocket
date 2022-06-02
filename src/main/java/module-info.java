module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires ojdbc11;


    opens client to javafx.fxml;
    exports client;
    exports client.controller;
    opens client.controller to javafx.fxml;
    exports client.db_thread;
    opens client.db_thread to javafx.fxml;
    exports client.elements;
    opens client.elements to javafx.fxml;
}