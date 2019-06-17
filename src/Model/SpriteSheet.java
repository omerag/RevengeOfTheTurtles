package Model;


import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage image;

    public SpriteSheet(BufferedImage image){
        this.image = image;
    }

    public BufferedImage grabImage(int col, int row,int width, int height,int size){
        return image.getSubimage(col*size-size,row*size-size,width,height);
    }
}
