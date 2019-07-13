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
    private Game game;
    private int isStuck = 60;

    public Enemy(int x, int y, ID id, Mediator mediator, Game game, int gameWidth, int gameHeight) {
        super(x, y, id ,gameWidth, gameHeight,SpriteContainer.getInstance().getTzavSprites(),1);
        this.mediator = mediator;
        this.game = game;

    }

    @Override
    public void tick() {

        //update x y position
        x += velX;
        y += velY;


        checkBorders();

        //getting play's position to aim at him
        xPlayer = mediator.getObjectsContainer().getPlayer().getX() + 32;
        yPlayer = mediator.getObjectsContainer().getPlayer().getY() + 32;


        //checking if this enemy got hit by one of the player bullets
        for (int i = 0; i < mediator.getObjectsContainer().getBulletList().size(); i++) {
            Bullet bullet = mediator.getObjectsContainer().getBulletList().get(i);
            if (getBounds().intersects(bullet.getBounds()) &&
                    bullet.getBulletType() == BulletType.PLAYER) {
                SoundContainer.SQUISH.play();
                mediator.getObjectsContainer().removeBullet(bullet);
                mediator.getObjectsContainer().removeEnemy(this);
                game.setScore(game.getScore() + 100);
                return;
            }
        }


        //randomly change the direction of the enemy movement
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

        //shoot bullets
        if(r.nextInt(200) == 0){
            mediator.getFactory().createGameObject("ENEMY BULLET",x,y,xPlayer,yPlayer);
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
