package Model;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class fruitsoflife extends GameObject {

    private long time = System.currentTimeMillis();
    private int positionX;
    private int positionY;


    public fruitsoflife(int x, int y, ID id, int gameWidth, int gameHeight) {
        super(x, y, id, gameWidth, gameHeight);
    }

    @Override
    public void tick() {

        if(System.currentTimeMillis() > time + 6000)
        {
            positionX = GetRandomX(1,gameWidth-1);
            positionY = GetRandomY(1,gameHeight-1);



        }




    }

    @Override
    public void render(Graphics g) {

    }

    private int GetRandomX(int min,int max)
    {
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

        return randomNum;
    }

    private int GetRandomY(int min,int max)
    {
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

        return randomNum;
    }


    @Override
    public Rectangle getBounds() {
        return null;
    }
}
