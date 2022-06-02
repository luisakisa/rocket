package client.controller;


import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;


public class MenuController {


    @FXML
    public TextField nickname;

    public String nick;

    @FXML
    public void initialize(InputEvent a) {
        final Node source = (Node) a.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        GameController game = new GameController();
        game.StartGame();
        nick = nickname.getText();
    }


}