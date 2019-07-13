package Model;

import Controller.Mediator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FruitOfLife extends GameObject {

    private Mediator mediator;
    private long time = System.currentTimeMillis();
    private long timer;
    protected List<BufferedImage> imagesList;

    public FruitOfLife(int x, int y, ID id, Mediator mediator, int gameWidth, int gameHeight) {
        super(x, y, id, gameWidth, gameHeight);
        this.mediator = mediator;
        this.imagesList = SpriteContainer.getInstance().getFruitsSprites();

    }

    @Override
    public void tick() {

        long appear;
        if(System.currentTimeMillis() > time + 6000) {
            // x = GetRandom(1, gameWidth - 1);
            //  y = GetRandom(1, gameHeight - 1);
        }




    }

    @Override
    public void render(Graphics g) {
        g.drawImage(imagesList.get(GetRandom(1,5)),x,y,null);
    }


    private int GetRandom(int min,int max)
    {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }


    @Override
    public Rectangle getBounds() {
        return null;
    }
}
