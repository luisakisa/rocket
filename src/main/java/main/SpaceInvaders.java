package main;



import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SpaceInvaders extends Application {

    //variables








    //start
    public void start(Stage stage) {


    }

    //setup the game
    private void setup() {
    }

    //run Graphics
    private void run(GraphicsContext gc) {

    }

    //player
    public class Rocket {

    }

    //computer player
    public class Bomb extends Rocket {
    }

    //bullets
    public class Shot {

    }

    //environment
    public class Universe {

    }




    int distance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

}