module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires ojdbc11;


    opens spaceInvaders to javafx.fxml;
    exports spaceInvaders;
    exports spaceInvaders.controller;
    opens spaceInvaders.controller to javafx.fxml;
    exports spaceInvaders.net;
    opens spaceInvaders.net to javafx.fxml;
    exports spaceInvaders.elements;
    opens spaceInvaders.elements to javafx.fxml;
}