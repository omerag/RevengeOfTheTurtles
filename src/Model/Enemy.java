package Model;

import Controller.Game;
import Controller.Mediator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends GameObject {

    private Mediator mediator;
    private Random r = new Random();
    private int xPlayer = 0, yPlayer = 0;
    private BufferedImage enemy_image;
    private Game game;
    private int isStuck = 60;

    public Enemy(int x, int y, ID id, Mediator mediator, SpriteSheet ss, Game game, int gameWidth, int gameHeight) {
        super(x, y, id, ss ,gameWidth, gameHeight);
        this.mediator = mediator;
        this.game = game;
        enemy_image = ss.grabImage(7,2,32,32);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;


        if(x <= 32 || x >= gameWidth - 64 || y < 32 || y >= gameHeight - 96){
            x += velX*3 * -1;
            y += velY*3 * -1;
            velX *= -1;
            velY *= -1 ;
        }


        xPlayer = mediator.objectsContainer.getPlayer().getX();
        yPlayer = mediator.objectsContainer.getPlayer().getY();


        for(int i = 0; i < mediator.objectsContainer.getBulletList().size(); i++){
            Bullet bullet = mediator.objectsContainer.getBulletList().get(i);
            if(getBounds().intersects(bullet.getBounds()) &&
                    bullet.getBulletType() == BulletType.PLAYER){
                mediator.objectsContainer.removeBullet(bullet);
                mediator.objectsContainer.removeEnemy(this);
                game.score++;
                return;
            }
        }


        if(r.nextInt(100) == 0){
            velX = (r.nextInt(1 - -1) + -1);
            velY = (r.nextInt(1 - -1) + -1);

        }
        if(r.nextInt(200) == 0){
            mediator.factory.newEnemyBullet(x,y,xPlayer,yPlayer);

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
