package main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
public class Bomb extends Rocket {

    private static final int HEIGHT = 600;
    int score;
    public Bomb(int posX, int posY, int size, Image image, GraphicsContext gc, int score) {
        super(posX, posY, size, image, gc);
        this.score=score;
    }

    public void update() {
        super.update();
        int SPEED = (score/5)+2;
        if(!exploding && !destroyed) posY += SPEED;
        if(posY > HEIGHT) destroyed = true;
    }
}