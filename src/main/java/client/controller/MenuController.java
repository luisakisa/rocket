package client.controller;


import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class MenuController {


    @FXML
    public TextField nickname;

    @FXML
    public void initialize(InputEvent a) throws IOException {
        final Node source = (Node) a.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        GameController game = new GameController();
        game.StartGame(nickname.getText());
    }


}