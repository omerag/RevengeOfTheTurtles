package Model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends GameObject {

    private Handler handler;
    Random r = new Random();
    int choose = 0;
    int hp = 100;
    int xPlayer = 0, yPlayer = 0;
    private BufferedImage enemy_image;
    private Game game;
    private int isStuck = 60;

    public Enemy(int x, int y, ID id,Handler handler, SpriteSheet ss, Game game) {
        super(x, y, id, ss);
        this.handler = handler;
        this.game = game;
        enemy_image = ss.grabImage(7,2,32,32);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        choose = r.nextInt(100);

        for(int i = 0; i< handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId()== ID.Player){
                xPlayer = tempObject.getX();
                yPlayer = tempObject.getY();
            }

            if(tempObject.getId() == ID.Block){
                if(getBoundsBig().intersects(tempObject.getBounds())){
                    x += velX*5 * -1;
                    y += velY*5 * -1;
                    velX *= -1;
                    velY *= -1 ;
                    isStuck--;
                    if(isStuck < 1){
                        handler.removeObject(this);
                    }
                }
                else
                    if(choose == 0) {
                        velX = (r.nextInt(1 - -1) + -1);
                        velY = (r.nextInt(1 - -1) + -1);

                    }
            }


            if(tempObject.getId() == ID.Bullet ){
                Bullet tempBullet = (Bullet)tempObject;
                if(getBounds().intersects(tempObject.getBounds()) &&
                        tempBullet.getBulletType() == BulletType.PALYER){
                    hp -= 100;
                    handler.removeObject(tempObject);
                    game.score++;


                }
            }
        }

        if(hp <= 0){
            handler.removeObject(this);
        }

        if(choose == 0){
            velX = (r.nextInt(1 - -1) + -1);
            velY = (r.nextInt(1 - -1) + -1);

        }
        if(r.nextInt(200) == 0){
            handler.addObject(new Bullet(this.getX() +16 , this.getY()+ 16,
                    ID.Bullet, handler, xPlayer, yPlayer, ss,BulletType.ENEMY));

        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(enemy_image,x,y,null);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }

    public Rectangle getBoundsBig() {
        return new Rectangle(x-16,y-16,64,64);
    }
}
