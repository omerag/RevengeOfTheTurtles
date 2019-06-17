package Model;

import Controller.Game;
import Controller.Mediator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class EnemySpawner extends CharacterObject {

    private Mediator mediator;
    Random r = new Random();
    int level = 1;
    private BufferedImage enemy_image;
    Game game;



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


        if(r.nextInt(100) == 0){
            actionMoveUp();
            velX = 0;
            lastMove = MOVE_UP;
        }
        else if(r.nextInt(100) == 0){
            actionMoveDown();
            velX = 0;
            lastMove = MOVE_DOWN;

        }else if (r.nextInt(100) == 0) {
            //velX = (r.nextInt(1 - -1) + -1);
            actionMoveRight();
            velY = 0;
            lastMove = MOVE_RIGHT;
        }
        else if(r.nextInt(100) == 0){
            actionMoveLeft();
            velY = 0;
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

/*        if(x <= 32 || x >= gameWidth - 64 || y < 32 || y >= gameHeight - 118){
                x += velX*5 * -1;
                y += velY*5 * -1;
                velX *= -1;
                velY *= -1 ;
        }

        if(r.nextInt(100) == 0) {
            velX = (r.nextInt(4 - -4) + -4);
            velY = 0;

        }*/

/*        else if (r.nextInt(100) == 0) {
            velY = (r.nextInt(4 - -4) + -4);
            velX = 0;
        }*/


        if(r.nextInt(250) < level){
            mediator.factory.newEnemy(x,y);
            if(r.nextInt(10) == 0 && level < 10){
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

    public Rectangle getBoundsBig() {
        return new Rectangle(x-16,y-16,64,64);
    }

}
