package main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Rocket {
    static final Image EXPLOSION_IMG = new Image("D:\\programming\\rocket\\images\\explosion.png");
    static final int EXPLOSION_W = 128;
    static final int EXPLOSION_ROWS = 3;
    static final int EXPLOSION_COL = 3;
    static final int EXPLOSION_H = 128;
    static final int EXPLOSION_STEPS = 15;
    private GraphicsContext gc;
    int posX, posY, size;
    boolean exploding, destroyed;
    Image img;
    int explosionStep = 0;

    public Rocket(int posX, int posY, int size,  Image image,GraphicsContext gc) {
        this.posX = posX;
        this.posY = posY;
        this.size = size;
        this.gc=gc;
        img = image;

    }

    public Shot shoot() {
        return new Shot(posX + size / 2 - Shot.size / 2, posY - Shot.size);
    }

    public void update() {
        if(exploding) explosionStep++;
        destroyed = explosionStep > EXPLOSION_STEPS;
    }

    public void draw() {
        if(exploding) {
            gc.drawImage(EXPLOSION_IMG, explosionStep % EXPLOSION_COL * EXPLOSION_W, (explosionStep / EXPLOSION_ROWS) * EXPLOSION_H + 1,
                    EXPLOSION_W, EXPLOSION_H,
                    posX, posY, size, size);
        }
        else {
            gc.drawImage(img, posX, posY, size, size);
        }
    }

    public boolean colide(SpaceInvaders.Rocket other) {
        int d = distance(this.posX + size / 2, this.posY + size /2,
                other.posX + other.size / 2, other.posY + other.size / 2);
        return d < other.size / 2 + this.size / 2 ;
    }

    public void explode() {
        exploding = true;
        explosionStep = -1;
    }
}
