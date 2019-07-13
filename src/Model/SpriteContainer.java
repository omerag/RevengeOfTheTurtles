package Model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SpriteContainer {

    public static SpriteContainer INSTANCE = null;

    private BufferImageLoader loader = new BufferImageLoader();

    private BufferedImage tzav_sheet = loader.loadImage("/spritesheettsav1.png");
    private BufferedImage panda_sheet = loader.loadImage("/pandaspritesheet.png");
    private BufferedImage snorlax_sheet = loader.loadImage("/snorlaxspritesheet.png");
    private BufferedImage general_sheet = loader.loadImage("/sprite_sheet.png");
    private BufferedImage Fruits_sheet =  loader.loadImage("/fruitspritesheet.png");


    private List<BufferedImage>tzavSprites = new ArrayList<>();
    private List<BufferedImage>pandaSprites = new ArrayList<>();
    private List<BufferedImage>snorlaxSprites = new ArrayList<>();
    private List<BufferedImage>fruitsSprites = new ArrayList<>();

    /*
    0 - stand - face down
    1 - left leg - face down
    2 - right leg - face down
    3 - stand - face up
    4 - left leg - face up
    5 - right leg - face up
    6 - stand - face right
    7 - right leg - face right
    8 - left leg - face right
    9 - stand - face left
    10 - left leg - face left
    11 - right leg - face left
     */

    private SpriteContainer(){
        spriteLoader(pandaSprites,panda_sheet,false);
        spriteLoader(snorlaxSprites,snorlax_sheet,true);
        spriteLoader(tzavSprites,tzav_sheet,true);
        FruitsLoader(fruitsSprites,Fruits_sheet);
    }

    public static SpriteContainer getInstance(){
        if (INSTANCE == null){
            INSTANCE = new SpriteContainer();
        }
        return INSTANCE;
    }

    public List<BufferedImage> getTzavSprites() {
        return tzavSprites;
    }

    public List<BufferedImage> getPandaSprites() {
        return pandaSprites;
    }

    public List<BufferedImage> getSnorlaxSprites() {
        return snorlaxSprites;
    }


    public List<BufferedImage> getFruitsSprites(){ return fruitsSprites;};

    public SpriteSheet getGeneral_sheet() {
        return new SpriteSheet(general_sheet);
    }

    private void FruitsLoader(List<BufferedImage> fruitsprite ,BufferedImage bufferedImage)
    {
        SpriteSheet spriteSheet = new SpriteSheet(bufferedImage);
        for(int i = 1; i <= 5; i++){
            fruitsprite.add(spriteSheet.grabImage(i, 1, 32, 32,32));
        }

    }

    private void spriteLoader(List<BufferedImage> objectSprite, BufferedImage bufferedImage , boolean hasSpecialMove){

        SpriteSheet spriteSheet = new SpriteSheet(bufferedImage);
        int imageSize = 64;

        for(int i = 1; i < 4; i++){
            for(int j = 1; j < 5; j++){
                objectSprite.add(spriteSheet.grabImage(i, j, imageSize, imageSize,imageSize));
            }
        }

        if(hasSpecialMove){
            for(int i = 1; i < 4; i++){
                objectSprite.add(spriteSheet.grabImage(i, 5, imageSize, imageSize,imageSize));
            }
        }
    }



}


