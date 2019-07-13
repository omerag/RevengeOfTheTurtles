package Model;

import Controller.Game;
import Controller.Mediator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FruitOfLife extends GameObject {

    private Mediator mediator;
    private long CreationTime;
    protected List<BufferedImage> imagesList;
    private int FruitNumber;

    public FruitOfLife(int x, int y, ID id, Mediator mediator, int gameWidth, int gameHeight,int fruitnumber,long CreationTime) {
        super(x, y, id, gameWidth, gameHeight);
        this.mediator = mediator;
        this.imagesList = SpriteContainer.getInstance().getFruitsSprites();
        this.FruitNumber = fruitnumber;
        this.CreationTime = CreationTime;
    }

    @Override
    public void tick() { }

    @Override
    public void render(Graphics g) {
        g.drawImage(imagesList.get(FruitNumber),x,y,null);
    }



    private int GetRandom(int min,int max)
    {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public long GetCreationTime()
    {
        return this.CreationTime;
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }

}
