package Model;

import Controller.Game;
import Controller.Mediator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Player extends CharacterObject {

    private Mediator mediator;
    private Game game;
    private boolean isStanding = true;

    public Player(int x, int y, ID id, Mediator mediator, Game game, int gameWidth, int gameHeight) {
        super(x, y, id,gameWidth,gameHeight,SpriteContainer.getInstance().getPandaSprites(),3);
        this.mediator = mediator;
        this.game = game;

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
        //check is player got hit by an enemy's bullet
        List<Bullet> bulletList = mediator.getObjectsContainer().getBulletList();
        FruitOfLife fruit = mediator.getObjectsContainer().getFruit();
        for(int i = 0;i < bulletList.size(); i++ ){
            Bullet bullet = bulletList.get(i);
            if(bullet.bulletType == BulletType.ENEMY && getBounds().intersects(bullet.getBounds())){
                SoundContainer.INJURED.play();
                bulletList.remove(i);
                game.setPlayerHP(game.getPlayerHP() - 25);
                break;
            }
        }


        if(fruit != null && getBounds().intersects(fruit.getBounds())){
            SoundContainer.INJURED.play();
            mediator.getObjectsContainer().RemoveFruit();
            SoundContainer.EAT.play();
            game.setPlayerHP(game.getPlayerHP() + 15);

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
