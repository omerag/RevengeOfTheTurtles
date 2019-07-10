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
    private BufferedImage bullet_image4;

    private int state = 1;


    public Bullet(int x, int y, ID id, Mediator mediator, int mx, int my, BulletType bulletType, int gameWidth, int gameHeight) {
        super(x, y, id,gameWidth,gameHeight);
        this.mediator = mediator;
        if(bulletType ==BulletType.PLAYER){
            velX = (mx - x)*0.05f;
            velY = (my - y)*0.05f;
        }
        else{

            velX = (mx - x)*0.008f;
            velY = (my - y)*0.008f;
        }

        this.bulletType = bulletType;
        bullet_image = SpriteContainer.getInstance().getGeneral_sheet().grabImage(1,3,32,32,32);
        bullet_image2 = SpriteContainer.getInstance().getGeneral_sheet().grabImage(2,3,32,32,32);
        bullet_image3 = SpriteContainer.getInstance().getGeneral_sheet().grabImage(3,3,32,32,32);
        bullet_image4 = SpriteContainer.getInstance().getGeneral_sheet().grabImage(3,3,32,32,32);

    }



    @Override
    public void tick() {
        x +=velX;
        y +=velY;


        if(x <= 32 || x >= gameWidth - 64 || y < 32 || y >= gameHeight - 96){
            mediator.objectsContainer.removeBullet(this);
            return;
        }


        BufferedImage tempImage = bullet_image;
        bullet_image = bullet_image2;
        bullet_image2 = bullet_image3;
        bullet_image3 = bullet_image4;
        bullet_image4 = tempImage;
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(bullet_image,x,y,null);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }

    public BulletType getBulletType() {
        return bulletType;
    }



}
