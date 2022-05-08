package main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.GameController;
public class Bomb extends Rocket {

    private static final int HEIGHT = 600;
    int SPEED = (score/5)+2;

    public Bomb(int posX, int posY, int size, Image image, GraphicsContext gc) {
        super(posX, posY, size, image, gc);
    }

    public void update() {
        super.update();
        if(!exploding && !destroyed) posY += SPEED;
        if(posY > HEIGHT) destroyed = true;
    }
}