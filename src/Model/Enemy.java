package Model;

import Controller.Game;
import Controller.Mediator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends CharacterObject {

    private Mediator mediator;
    private Random r = new Random();
    private int xPlayer = 0, yPlayer = 0;
    private BufferedImage enemy_image;
    private Game game;
    private int isStuck = 60;

    public Enemy(int x, int y, ID id, Mediator mediator, Game game, int gameWidth, int gameHeight) {
        super(x, y, id ,gameWidth, gameHeight,SpriteContainer.getInstance().getTzavSprites(),1);
        this.mediator = mediator;
        this.game = game;
        enemy_image = SpriteContainer.getInstance().getGeneral_sheet().grabImage(7,2,32,32,32);

    }

    @Override
    public void tick() {
        x += velX;
        y += velY;


/*        if (x <= 32 || x >= gameWidth - 64 || y < 32 || y >= gameHeight - 96) {
            x += velX * 3 * -1;
            y += velY * 3 * -1;
            velX *= -1;
            velY *= -1;
        }*/

        checkBorders();


        xPlayer = mediator.objectsContainer.getPlayer().getX();
        yPlayer = mediator.objectsContainer.getPlayer().getY();


        for (int i = 0; i < mediator.objectsContainer.getBulletList().size(); i++) {
            Bullet bullet = mediator.objectsContainer.getBulletList().get(i);
            if (getBounds().intersects(bullet.getBounds()) &&
                    bullet.getBulletType() == BulletType.PLAYER) {
                mediator.objectsContainer.removeBullet(bullet);
                mediator.objectsContainer.removeEnemy(this);
                game.score++;
                return;
            }
        }



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


        if(r.nextInt(200) == 0){
            mediator.factory.newEnemyBullet(x,y,xPlayer,yPlayer);

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
