package Model;

import Controller.Game;
import Controller.Mediator;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends CharacterObject {

    private Mediator mediator;
    private Game game;
    private BufferedImage player_image;
    private boolean isStanding = true;

    public Player(int x, int y, ID id, Mediator mediator, Game game, int gameWidth, int gameHeight) {
        super(x, y, id,gameWidth,gameHeight,SpriteContainer.getInstance().getPandaSprites(),3);
        this.mediator = mediator;
        this.game = game;
        player_image = SpriteContainer.getInstance().getGeneral_sheet().grabImage(7,1,32,32,32);

    }

    @Override
    public void tick() {


        x += velX;
        y += velY;

        checkBorders();
/*

        if(x <= 32 || x >= gameWidth - 64 || y < 32 || y >= gameHeight - 118){
            x += velX* -1;
            y += velY* -1;
            velX *= -1;
            velY *= -1 ;
        }
*/


        try{
            collision();
        } catch (Exception e){
            System.out.println("unknown error????O_o");
        }


        //movement
        if (mediator.isUp()){
            actionMoveUp();
            isStanding = false;
        }
        else if (mediator.isDown()){
            actionMoveDown();
            isStanding = false;
        }
        else if (mediator.isRight()){
            actionMoveRight();
            isStanding = false;
        }
        else if (mediator.isLeft()){
            actionMoveLeft();
            isStanding = false;
        }
        if(isStanding) {
            actionStand();
        }
        isStanding = true;
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
        g.drawImage(imagesList.get(currentState-1),x,y,null);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,64,64);
    }
}
