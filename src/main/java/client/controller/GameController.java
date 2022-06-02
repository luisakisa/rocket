package client.controller;

import client.db_thread.DB;
import client.elements.Bomb;
import client.elements.Rocket;
import client.elements.Shot;
import client.elements.Universe;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class GameController {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    DB db = new DB();
    int n =1;

    boolean gameOver = false;
    boolean checkWindow = false;

    @FXML
    private GraphicsContext gc;
    private double mouseX;
    private Rocket player;
    List<Shot> shots;
    final int MAX_BOMBS = 10, MAX_SHOTS = MAX_BOMBS * 2;
    List<Universe> univ;
    List<Bomb> Bombs;
    private int score;
    private static final Random RAND = new Random();

    private static final int PLAYER_SIZE = 70;//размер ракет
    static final Image PLAYER_IMG = new Image(GameController.class.getResourceAsStream("/view/images/player.png"));

    static final Image[] BOMBS_IMG = {
            new Image(GameController.class.getResourceAsStream("/view/images/1.png")),
            new Image(GameController.class.getResourceAsStream("/view/images/2.png")),
            new Image(GameController.class.getResourceAsStream("/view/images/3.png")),
            new Image(GameController.class.getResourceAsStream("/view/images/4.png")),
            new Image(GameController.class.getResourceAsStream("/view/images/5.png")),
            new Image(GameController.class.getResourceAsStream("/view/images/6.png")),
            new Image(GameController.class.getResourceAsStream("/view/images/7.png")),
            new Image(GameController.class.getResourceAsStream("/view/images/8.png")),
            new Image(GameController.class.getResourceAsStream("/view/images/9.png")),
            new Image(GameController.class.getResourceAsStream("/view/images/10.png")),
    };
    MenuController menu = new MenuController();

    @FXML
    public void StartGame() {

        db.insertNick(menu.nick);

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();

        Group root = new Group();
        root.getChildren().add(canvas);
        Stage stage = new Stage();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            try {
                run(gc);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        canvas.setCursor(Cursor.MOVE);
        canvas.setOnMouseMoved(e -> mouseX = e.getX());
        canvas.setOnMouseClicked(e -> {
            if (shots.size() < MAX_SHOTS) shots.add(player.shoot());
            if (gameOver) {
                gameOver = false;
                setup();
            }

        });
        setup();

    }

    public void setScore(DB db) throws SQLException, ClassNotFoundException {
        db.insertScore(score);
    }

    private void setup() {
        univ = new ArrayList<>();
        shots = new ArrayList<>();
        Bombs = new ArrayList<>();
        player = new Rocket(WIDTH / 2, HEIGHT - 110, PLAYER_SIZE, PLAYER_IMG, gc);
        score = 0;
        IntStream.range(0, MAX_BOMBS).mapToObj(i -> this.newBomb()).forEach(Bombs::add);
        checkWindow = true;
    }

    Bomb newBomb() {
        return new Bomb(50 + RAND.nextInt(WIDTH - 100), 0, PLAYER_SIZE, BOMBS_IMG[RAND.nextInt(BOMBS_IMG.length)], gc, score);
    }

    private void run(GraphicsContext gc) throws IOException {
        gc.setFill(Color.grayRgb(20));
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(Font.font(20));
        gc.setFill(Color.WHITE);
        gc.fillText("Score: " + score, 60, 20);


        if (gameOver) {
            gc.setFont(Font.font(35));
            gc.setFill(Color.WHITE);
            gc.fillText("Game Over \n Your Score is: " + score + " \n Click to play again", WIDTH/2, HEIGHT / 2.5);

        }
        univ.forEach(universe -> universe.draw(gc));

        player.update();
        player.draw();
        player.posX = (int) mouseX;

        Bombs.stream().peek(Rocket::update).peek(Rocket::draw).forEach(e -> {
            if (player.colide(e) && !player.exploding) {
                player.explode();
            }
        });


        for (int i = shots.size() - 1; i >= 0; i--) {
            Shot shot = shots.get(i);
            if (shot.posY < 0 || shot.toRemove) {
                shots.remove(i);
                continue;
            }
            shot.update();
            shot.draw(gc, score);
            for (Bomb bomb : Bombs) {
                if (shot.colide(bomb) && !bomb.exploding) {
                    score++;
                    bomb.explode();
                    shot.toRemove = true;
                }
            }
        }

        for (int i = Bombs.size() - 1; i >= 0; i--) {
            if (Bombs.get(i).destroyed) {
                Bombs.set(i, newBomb());
            }
        }

        gameOver = player.destroyed;
        if (RAND.nextInt(10) > 2) {
            univ.add(new Universe(RAND));
        }
        for (int i = 0; i < univ.size(); i++) {
            if (univ.get(i).posY > HEIGHT)
                univ.remove(i);
        }
        if (gameOver){
            if(checkWindow) {
                ScoreController SC = new ScoreController();
                try {
                    SC.secondWindow();
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                try {
                    setScore(db);
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                checkWindow = false;
            }
        }
    }


}


