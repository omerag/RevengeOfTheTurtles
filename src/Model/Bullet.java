package Model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject{

    private Handler handler;
    //int mx, my;
    BulletType bulletType;
    private BufferedImage bullet_image;
    private BufferedImage bullet_image2;
    private BufferedImage bullet_image3;
    private int state = 1;


    public Bullet(int x, int y, ID id, Handler handler, int mx, int my, SpriteSheet ss, BulletType bulletType) {
        super(x, y, id, ss);
        this.handler = handler;
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

        for(int i = 0; i < handler.object.size();i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Block){
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(this);
                }
            }
        }
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
