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
        else if(isStanding) {
            actionStand();
        }
        else {
            isStanding = true;
        }
    }

    private void collision(){


        for(Bullet bullet : mediator.objectsContainer.getBulletList()){
            if(bullet.bulletType == BulletType.ENEMY && getBounds().intersects(bullet.getBounds())){
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
