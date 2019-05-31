package Controller;

import Model.Handler;
import Model.Camera;
import Model.Game;
import Model.SpriteSheet;
import Model.Bullet;
import Model.ID;
import Model.GameObject;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Model.BulletType;

public class MouseInput extends MouseAdapter {

    private Handler handler;
    private Camera camera;
    private Game game;
    protected SpriteSheet ss;

    private Bullet tempBullet = null;



    public MouseInput(Handler handler,Camera camera, Game game, SpriteSheet ss) {
        this.handler = handler;
        this.camera = camera;
        this.game = game;
        this.ss = ss;

    }

    public void mousePressed(MouseEvent e){
       // int mx = (int)(e.getX()+camera.getX());
      //  int my = (int)(e.getY()+camera.getY());
        int mx = e.getX();
        int my = e.getY();

        for (int i = 0; i < handler.getObject().size(); i++){
            GameObject tempObject = handler.getObject().get(i);



            if (tempObject.getId() == ID.Player  && handler.getTempBullet() != null){
                if(isShootable(tempObject.getX(),tempObject.getY(),handler.getTempBullet().getX(),handler.getTempBullet().getY())
                    && handler.getTempBulletType() == BulletType.ENEMY){
                    handler.addObject(new Bullet(tempObject.getX() + 16, tempObject.getY() + 24, ID.Bullet, handler, mx, my, ss, BulletType.PALYER));
                    handler.removeObject(handler.getTempBullet());
                    //game.ammo--;
                    handler.setTempBulletToNull();
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

}
