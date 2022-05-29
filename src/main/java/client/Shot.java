package client;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Shot {

    public boolean toRemove;


    int posX, posY, speed = 10;
    static final int size = 6;

    public Shot(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void update() {
        posY-=speed;
    }


    public void draw(GraphicsContext gc, int score) {
        gc.setFill(Color.RED);
        if (score >=50 && score<=70 || score>=120) {
            gc.setFill(Color.YELLOWGREEN);
            speed = 50;
            gc.fillRect(posX-5, posY-10, size+10, size+30);
        } else {
            gc.fillOval(posX, posY, size, size);
        }
    }

    public boolean colide(Rocket Rocket) {
        int distance = distance(this.posX + size / 2, this.posY + size / 2,
                Rocket.posX + Rocket.size / 2, Rocket.posY + Rocket.size / 2);
        return distance  < Rocket.size / 2 + size / 2;
    }
    int distance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

}