package View;

import Controller.Game;
import Controller.Mediator;
import Model.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class ImageRender{

    private Mediator mediator;
    private Game game;



    private SpriteSheet ss;

    private BufferedImage level;
    private BufferedImage floor;

    public ImageRender(Game game, Mediator mediator){
        this.game = game;
        this.mediator = mediator;

        BufferImageLoader loader = new BufferImageLoader();
        level = loader.loadImage("/wizard_level - Copy.png");
        BufferedImage sprite_sheet = loader.loadImage("/sprite_sheet.png");

        ss = new SpriteSheet(sprite_sheet);
        floor = ss.grabImage(1,2,32,32,32);
    }

    public void render(){

        BufferStrategy bs = game.getBufferStrategy();

        if (bs == null) {
            game.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        for (int xx = 0; xx < 30 * 34; xx += 32) {
            for (int yy = 0; yy < 30 * 24; yy += 32) {
                g.getClipBounds(); //redraw only tiles that changed
                g.drawImage(floor, xx, yy, null);
            }
        }

        ObjectsContainer objectsContainer = mediator.getObjectsContainer();

        for(Block block : objectsContainer.getBlockList()){
            block.render(g);
        }

        for(int i = 0; i <objectsContainer.getEnemyList().size() ; i++){
            Enemy enemy = objectsContainer.getEnemyList().get(i);
            enemy.render(g);
        }
        for(int i = 0; i < objectsContainer.getBulletList().size(); i++){
            Bullet bullet = objectsContainer.getBulletList().get(i);
            bullet.render(g);
        }

        objectsContainer.getEnemySpawner().render(g);
        objectsContainer.getPlayer().render(g);
        if(objectsContainer.getFruit() != null){
            objectsContainer.getFruit().render(g);

        }


        //player's health bar
        g.setColor(Color.CYAN);
        g.fillRect(5, 5, 200, 32);
        g.setColor(Color.RED);
        g.fillRect(5, 5, game.getPlayerHP() * 2, 32);
        g.setColor(Color.BLACK);
        g.drawRect(5, 5, 200, 32);

        //player's score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier", Font.BOLD,28));
        g.drawString("Score: " + game.getScore(), 5, 65);


        //snorlax's health bar
        g.setColor(Color.CYAN);
        g.fillRect(820, 5, 200, 32);
        g.setColor(Color.RED);
        g.fillRect(820, 5, game.getSnorlaxHP() * 2, 32);
        g.setColor(Color.BLACK);
        g.drawRect(820, 5, 200, 32);

        //player's score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier", Font.BOLD,28));
        g.drawString("Snorlax", 820, 65);


        g.dispose();
        bs.show();

    }



    public void loadLevel(){
        int w = level.getWidth();
        int h = level.getHeight();

        for(int xx = 0; xx < w; xx++){
            for (int yy = 0; yy < h; yy++){
                int pixel = level.getRGB(xx,yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if(red == 255){
                    mediator.getFactory().createGameObject("BLOCK",xx*32,yy*32);
                }
                if(green == 255 && blue == 0){
                    mediator.getFactory().createGameObject("ENEMY",xx*32,yy*32);
                }
                if(blue == 255 && green == 0){
                    mediator.getFactory().createGameObject("PLAYER",xx*32,yy*32);
                  }
                if(blue == 255 && green == 255){
                    mediator.getFactory().createGameObject("ENEMY SPAWNER",xx*32,yy*32);
                }
            }
        }
    }

    public SpriteSheet getSs() {
        return ss;
    }

}