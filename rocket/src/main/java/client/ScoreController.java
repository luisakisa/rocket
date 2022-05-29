package client;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ScoreController {
    public Button PlayAgainButton;
    public Button ExitGameButton;
    public TableColumn<String, String> Score;
    public TableColumn<String, String> Nick;

    public void PlayAgain(InputEvent a) {
        final Node source = (Node) a.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


//    DB db = new DB();

}
