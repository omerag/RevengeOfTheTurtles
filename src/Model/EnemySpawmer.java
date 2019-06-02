package Model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class EnemySpawmer extends GameObject {

    private Handler handler;
    Random r = new Random();
    int level = 1;
    private BufferedImage enemy_image;
    Game game;



    public EnemySpawmer(int x, int y, ID id, Handler handler, SpriteSheet ss,Game game ,int gameWidth, int gameHeight){
        super(x, y, id, ss,gameWidth, gameHeight);
        this.handler = handler;
        this.game = game;
        enemy_image = ss.grabImage(6,1,32,32);
    }

    @Override
    public void tick() {

        x += velX;
        y += velY;

        if(x <= 32 || x >= gameWidth - 64 || y < 32 || y >= gameHeight - 118){
                x += velX*5 * -1;
                y += velY*5 * -1;
                velX *= -1;
                velY *= -1 ;
        }

        if(r.nextInt(100) == 0) {
            velX = (r.nextInt(4 - -4) + -4);
            velY = (r.nextInt(4 - -4) + -4);

        }
        if(r.nextInt(500) < level){
            handler.addObject(new Enemy(this.getX() + 16,this.getY() + 16,ID.Enemy,handler,ss,game,gameWidth, gameHeight));
            if(r.nextInt(10) == 0 && level < 10){
                    level++;
                System.out.println("level = " + level);
            }
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
