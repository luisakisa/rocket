package main;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import java.util.ArrayList;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class GameController {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    boolean gameOver = false;

    @FXML
    private AnchorPane gameRoot;

    @FXML
    private GraphicsContext gc;
    private double mouseX;
    private Rocket player;
    List<Shot> shots;
    final int MAX_BOMBS = 10,  MAX_SHOTS = MAX_BOMBS * 2;
    List<Universe> univ;
    List<Bomb> Bombs;
    private int score;
    private static final Random RAND = new Random();

    private static final int PLAYER_SIZE = 60;//размер ракет
    static final Image PLAYER_IMG = new Image("D:\\programming\\rocket\\images\\player.png");



    static final Image[] BOMBS_IMG = {
            new Image("D:\\programming\\rocket\\images\\1.png"),
            new Image("D:\\programming\\rocket\\images\\2.png"),
            new Image("D:\\programming\\rocket\\images\\3.png"),
            new Image("D:\\programming\\rocket\\images\\4.png"),
            new Image("D:\\programming\\rocket\\images\\5.png"),
            new Image("D:\\programming\\rocket\\images\\6.png"),
            new Image("D:\\programming\\rocket\\images\\7.png"),
            new Image("D:\\programming\\rocket\\images\\8.png"),
            new Image("D:\\programming\\rocket\\images\\9.png"),
            new Image("D:\\programming\\rocket\\images\\10.png"),
    };

    @FXML
    public void initialize() {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> run(gc)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        canvas.setCursor(Cursor.MOVE);
        canvas.setOnMouseMoved(e -> mouseX = e.getX());
        canvas.setOnMouseClicked(e -> {
            if(shots.size() < MAX_SHOTS) shots.add(player.shoot());
            if(gameOver) {
                gameOver = false;
                setup();
            }
        });
        setup();

    }

    private void setup() {
        univ = new ArrayList<>();
        shots = new ArrayList<>();
        Bombs = new ArrayList<>();
        player = new Rocket(WIDTH / 2, HEIGHT - PLAYER_SIZE, PLAYER_SIZE, PLAYER_IMG, gc);
        score = 0;
        IntStream.range(0, MAX_BOMBS).mapToObj(i -> this.newBomb()).forEach(Bombs::add);
    }
    Bomb newBomb() {
        return new Bomb(50 + RAND.nextInt(WIDTH - 100), 0, PLAYER_SIZE, BOMBS_IMG[RAND.nextInt(BOMBS_IMG.length)], gc,score);
    }
    private void run(GraphicsContext gc) {
        gc.setFill(Color.grayRgb(20));
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(Font.font(20));
        gc.setFill(Color.WHITE);
        gc.fillText("Score: " + score, 60,  20);


        if(gameOver) {
            gc.setFont(Font.font(35));
            gc.setFill(Color.YELLOW);
            gc.fillText("Game Over \n Your Score is: " + score + " \n Click to play again", WIDTH / 2, HEIGHT /2.5);
            //	return;
        }
        univ.forEach(universe -> universe.draw(gc));

        player.update();
        player.draw();
        player.posX = (int) mouseX;

        Bombs.stream().peek(Rocket::update).peek(Rocket::draw).forEach(e -> {
            if(player.colide(e) && !player.exploding) {
                player.explode();
            }
        });


        for (int i = shots.size() - 1; i >=0 ; i--) {
            Shot shot = shots.get(i);
            if(shot.posY < 0 || shot.toRemove)  {
                shots.remove(i);
                continue;
            }
            shot.update();
            shot.draw(gc, score);
            for (Bomb bomb : Bombs) {
                if(shot.colide(bomb) && !bomb.exploding) {
                    score++;
                    bomb.explode();
                    shot.toRemove = true;
                }
            }
        }

        for (int i = Bombs.size() - 1; i >= 0; i--){
            if(Bombs.get(i).destroyed)  {
                Bombs.set(i, newBomb());
            }
        }

        gameOver = player.destroyed;
        if(RAND.nextInt(10) > 2) {
            univ.add(new Universe(RAND));
        }
        for (int i = 0; i < univ.size(); i++) {
            if(univ.get(i).posY > HEIGHT)
                univ.remove(i);
        }
    }
}
