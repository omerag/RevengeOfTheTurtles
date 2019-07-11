package Model;

import Controller.Game;
import Controller.Mediator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

public class EnemySpawner extends CharacterObject {

    private Mediator mediator;
    private Random r = new Random();
    private int level = 1;
    private BufferedImage enemy_image;
    private Game game;



    public EnemySpawner(int x, int y, ID id, Mediator mediator , Game game , int gameWidth, int gameHeight){
        super(x, y, id,gameWidth, gameHeight,SpriteContainer.getInstance().getSnorlaxSprites(),2);
        this.mediator = mediator;
        this.game = game;
        enemy_image = SpriteContainer.getInstance().getGeneral_sheet().grabImage(6,1,32,32,32);

    }

    @Override
    public void tick() {

        x += velX;
        y += velY;

        checkBorders();

        List<Bullet> bulltetList = mediator.objectsContainer.getBulletList();
        for(int i = 0; i <bulltetList.size();i++ ){
            Bullet bullet = bulltetList.get(i);
            if(bullet.bulletType == BulletType.PLAYER && bullet.getBounds().intersects(getBounds())){
                game.snorlaxHP--;
                bulltetList.remove(i);
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
        if(r.nextInt(280) < level && mediator.objectsContainer.getEnemyList().size() < 50){
            mediator.factory.newEnemy(x,y);

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

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,64,64);

    }

}
