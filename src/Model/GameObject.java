package Model;

import java.awt.*;

public abstract class GameObject {

    protected int x,y;
    protected float velX = 0, velY = 0;
    protected ID id;
    int gameWidth;
    int gameHeight;

    public GameObject(int x, int y,ID id,int gameWidth, int gameHeight) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public ID getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float getVelX() {
        return velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }


}
