package Model;

import Controller.Game;
import Controller.Mediator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EnemySpawner extends CharacterObject {

    private Mediator mediator;
    private Random r = new Random();
    private int level = 1;
    private Game game;



    public EnemySpawner(int x, int y, ID id, Mediator mediator , Game game , int gameWidth, int gameHeight){
        super(x, y, id,gameWidth, gameHeight,SpriteContainer.getInstance().getSnorlaxSprites(),2);
        this.mediator = mediator;
        this.game = game;

    }

    @Override
    public void tick() {

        //update x y position
        x += velX;
        y += velY;

        checkBorders();

        //check if got hit by a player's bullet
        List<Bullet> bulletList = mediator.getObjectsContainer().getBulletList();
        for(int i = 0; i <bulletList.size();i++ ){
            Bullet bullet = bulletList.get(i);
            if(bullet.bulletType == BulletType.PLAYER && bullet.getBounds().intersects(getBounds())){
               game.setSnorlaxHP(game.getSnorlaxHP() - 1);
                bulletList.remove(i);
                break;
            }
        }

        //randomly change the direction of enemySpawner movement
        if(r.nextInt(100) == 0){
            actionMoveUp();
            lastMove = MOVE_UP;
        }
        else if(r.nextInt(100) == 0){
            actionMoveDown();
            lastMove = MOVE_DOWN;

        }else if (r.nextInt(100) == 0) {
            //velX = (r.nextInt(1 - -1) + -1);
            actionMoveRight();
            lastMove = MOVE_RIGHT;
        }
        else if(r.nextInt(100) == 0){
            actionMoveLeft();
            lastMove = MOVE_LEFT;
        }
        else switch (lastMove){
                case MOVE_UP: actionMoveUp();
                    break;
                case MOVE_DOWN: actionMoveDown();
                    break;
                case MOVE_RIGHT: actionMoveRight();
                    break;
                case MOVE_LEFT: actionMoveLeft();
                    break;
            }


        //create new enemies
        if(r.nextInt(280) < level && mediator.getObjectsContainer().getEnemyList().size() < 50){
            mediator.getFactory().createGameObject("ENEMY",x,y);




            //increase chance of creating new enemies
            if(r.nextInt(10 + level*5) == 0 && level < 10){
                    level++;
                System.out.println("level = " + level);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(imagesList.get(currentState - 1),x,y,null);

    }

    private int GetRandom(int min,int max)
    {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,64,64);

    }

}
