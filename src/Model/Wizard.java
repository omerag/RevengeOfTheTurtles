package Model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Wizard extends GameObject {

    Handler handler;
    Game game;
    private BufferedImage wizard_image;

    public Wizard(int x, int y, ID id, Handler handler,Game game, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;
        this.game = game;
        wizard_image = ss.grabImage(7,1,32,32);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        try{
            collision();
        } catch (Exception e){
            System.out.println("unknown error????O_o");
        }


        //movement
        if (handler.isUp()) velY = -3;
        else if (!handler.isDown()) velY = 0;

        if (handler.isDown()) velY = 3;
        else if (!handler.isUp()) velY = 0;

        if (handler.isRight()) velX = 3;
        else if (!handler.isLeft()) velX = 0;

        if (handler.isLeft()) velX = -3;
        else if (!handler.isRight()) velX = 0;
    }

    private void collision(){
        for(int i = 0; i < handler.object.size();i++){

            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Block){
                if(getBounds().intersects(tempObject.getBounds())){
                    x += velX * -1 ;
                    y += velY * -1 ;
                }
            }
            /*
            if(tempObject.getId() == ID.Crate){
                if(getBounds().intersects(tempObject.getBounds())){
                    handler.removeObject(tempObject);
                }
            }*/

            if(tempObject.getId() == ID.Enemy){
                if(getBounds().intersects(tempObject.getBounds())){
                    game.hp -= 3;
                    if(game.hp <= 0) ;
                }
            }

            if(tempObject.getId() == ID.Bullet){
                Bullet tempBullet = (Bullet)tempObject;
                if((tempBullet.getBulletType() == BulletType.ENEMY) &&
                        getBounds().intersects(tempObject.getBounds())){
                    game.hp -= 1;
                    if(game.hp <= 0) ;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(wizard_image,x,y,null);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }
}
