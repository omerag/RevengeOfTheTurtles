package Model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Block extends GameObject {

    private BufferedImage block_image;

    public Block(int x, int y, ID id, SpriteSheet ss,int gameWidth,int gameHeight) {
        super(x, y, id, ss,gameWidth,gameHeight);
        block_image = ss.grabImage(5,2,32,32);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(block_image,x,y,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }
}