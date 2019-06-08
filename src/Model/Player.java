package Model;

import Controller.Game;
import Controller.Mediator;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject {

    private Mediator mediator;
    private Game game;
    private BufferedImage player_image;

    public Player(int x, int y, ID id, Mediator mediator, Game game, SpriteSheet ss, int gameWidth, int gameHeight) {
        super(x, y, id, ss,gameWidth,gameHeight);
        this.mediator = mediator;
        this.game = game;
        player_image = ss.grabImage(7,1,32,32);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;


        if(x <= 32 || x >= gameWidth - 64 || y < 32 || y >= gameHeight - 118){
            x += velX* -1;
            y += velY* -1;
            velX *= -1;
            velY *= -1 ;
        }


        try{
            collision();
        } catch (Exception e){
            System.out.println("unknown error????O_o");
        }


        //movement
        if (mediator.isUp()) velY = -3;
        else if (!mediator.isDown()) velY = 0;

        if (mediator.isDown()) velY = 3;
        else if (!mediator.isUp()) velY = 0;

        if (mediator.isRight()) velX = 3;
        else if (!mediator.isLeft()) velX = 0;

        if (mediator.isLeft()) velX = -3;
        else if (!mediator.isRight()) velX = 0;
    }

    private void collision(){



        for(Enemy enemy : mediator.objectsContainer.getEnemyList()){
            if(getBounds().intersects(enemy.getBounds())){
                game.hp -= 3;
                if(game.hp <= 0){
                    //...
                }
            }
        }

        for(Bullet bullet : mediator.objectsContainer.getBulletList()){
            if(getBounds().intersects(bullet.getBounds())){
                game.hp -= 1;
                if(game.hp <= 0){
                    //...
                }
            }
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(player_image,x,y,null);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }
}
