package Controller;

import Model.Game;
import Model.SpriteSheet;
import Model.Bullet;
import Model.ID;
import Model.GameObject;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Model.BulletType;

public class MouseInput extends MouseAdapter {

    private Mediator mediator;
    private Game game;
    protected SpriteSheet ss;

    private Bullet tempBullet = null;
    int gameWidth;
    int gameHeight;



    public MouseInput(Mediator mediator, Game game, SpriteSheet ss) {
        this.mediator = mediator;
        this.game = game;
        this.ss = ss;

    }

    public void mousePressed(MouseEvent e){

        int mx = e.getX();
        int my = e.getY();

        for (int i = 0; i < mediator.getObject().size(); i++){
            GameObject tempObject = mediator.getObject().get(i);



            if (tempObject.getId() == ID.Player  && mediator.getTempBullet() != null){
                if(isShootable(tempObject.getX(),tempObject.getY(), mediator.getTempBullet().getX(), mediator.getTempBullet().getY())
                    && mediator.getTempBulletType() == BulletType.ENEMY){
                    mediator.addObject(new Bullet(tempObject.getX() + 16, tempObject.getY() + 24, ID.Bullet, mediator, mx, my, ss, BulletType.PALYER, gameWidth, gameHeight));
                    mediator.removeObject(mediator.getTempBullet());
                    mediator.setTempBulletToNull();
                }
            }
        }



    }



    private boolean isShootable(int xPlayer, int yPlayer,int xBullet,int yBullet){
        int tempX = xPlayer - xBullet;
        int tempY = yPlayer - yBullet;

        if(tempX < 0) tempX = -tempX;
        if(tempY < 0) tempY = -tempY;

        if(tempX < 64 && tempY < 64)
            return true;

        return false;
    }

    public void setGameWidth(int gameWidth) {
        this.gameWidth = gameWidth;
    }

    public void setGameHeight(int gameHeight) {
        this.gameHeight = gameHeight;
    }
}
