package Model;

import Controller.Mediator;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject{

    private Mediator mediator;
    public BulletType bulletType;
    private BufferedImage bullet_image;
    private BufferedImage bullet_image2;
    private BufferedImage bullet_image3;
    private BufferedImage bullet_image4;




    public Bullet(int x, int y, ID id, Mediator mediator, int mx, int my, BulletType bulletType, int gameWidth, int gameHeight) {
        super(x, y, id,gameWidth,gameHeight);
        this.mediator = mediator;

        calculateVeloity(x,y,mx,my);

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
            mediator.getObjectsContainer().removeBullet(this);
            return;
        }

        if(Math.abs(velX) < 1 && Math.abs(velY) < 1){
            mediator.getObjectsContainer().removeBullet(this);
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
        return new Rectangle(x,y,16,16);
    }

    public BulletType getBulletType() {
        return bulletType;
    }

    private void calculateVeloity(float x, float y, float mx, float my){

        float tempX = mx - x;
        float tempY = my - y;

        //finding highest velocity lower then 4
        while (Math.abs(tempX) > 4 || Math.abs(tempY) > 4){
            tempX = tempX / 2;
            tempY = tempY / 2;
        }

        velX = tempX;
        velY = tempY;

        System.out.println("velX = " + velX);
        System.out.println("velY = " + velY);

    }


}
