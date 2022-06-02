package client.elements;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Player {

    private final StringProperty Nickname;
    private final IntegerProperty Score;
    public int score;

    public int getScore() {
        return score;
    }

    public Player(String Nickname, int Score) {
        this.Nickname = new SimpleStringProperty(Nickname);
        this.Score = new SimpleIntegerProperty(Score);
        this.score = Score;

    }
    public StringProperty nameProperty() {
        return Nickname;
    }
    public IntegerProperty scoreProperty() {
        return Score;
    }


}