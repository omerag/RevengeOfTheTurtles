package Model;

import Controller.Mediator;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject{

    private Mediator mediator;
    //int mx, my;
    public BulletType bulletType;
    private BufferedImage bullet_image;
    private BufferedImage bullet_image2;
    private BufferedImage bullet_image3;
    private int state = 1;


    public Bullet(int x, int y, ID id, Mediator mediator, int mx, int my, SpriteSheet ss, BulletType bulletType, int gameWidth, int gameHeight) {
        super(x, y, id, ss,gameWidth,gameHeight);
        this.mediator = mediator;
        if(bulletType ==BulletType.PALYER){
            velX = (mx - x)*0.05f;
            velY = (my - y)*0.05f;
        }
        else{

            velX = (mx - x)*0.008f;
            velY = (my - y)*0.008f;
        }

        this.bulletType = bulletType;
        bullet_image = ss.grabImage(1,3,16,16);
        bullet_image2 = ss.grabImage(2,3,16,16);
        bullet_image3 = ss.grabImage(3,3,16,16);
    }



    @Override
    public void tick() {
        x +=velX;
        y +=velY;


        if(x <= 32 || x >= gameWidth - 64 || y < 32 || y >= gameHeight - 96){
            mediator.removeObject(this);

        }

/*
        for(int i = 0; i < mediator.object.size();i++) {
            GameObject tempObject = mediator.object.get(i);

            if (tempObject.getId() == ID.Block){
                if (getBounds().intersects(tempObject.getBounds())) {
                    mediator.removeObject(this);
                }
            }
        }
        */

        BufferedImage tempImage = bullet_image;
        bullet_image = bullet_image2;
        bullet_image2 = bullet_image3;
        bullet_image3 = tempImage;
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(bullet_image,x,y,null);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,16,16);
    }

    public BulletType getBulletType() {
        return bulletType;
    }



}
