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
    private int hp;
    private int score;


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
        floor = ss.grabImage(4,2,32,32,32);
    }

    public void render(){

        BufferStrategy bs = game.getBufferStrategy();

        if (bs == null) {
            game.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        for (int xx = 0; xx < 30 * 33; xx += 32) {
            for (int yy = 0; yy < 30 * 24; yy += 32) {
                g.getClipBounds(); //redraw only tiles that changed
                g.drawImage(floor, xx, yy, null);
            }
        }

        ObjectsContainer objectsContainer = mediator.objectsContainer;

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

        g.setColor(Color.CYAN);
        g.fillRect(5, 5, 200, 32);
        g.setColor(Color.RED);
        g.fillRect(5, 5, hp * 2, 32);
        g.setColor(Color.BLACK);
        g.drawRect(5, 5, 200, 32);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier", Font.BOLD,28));
        g.drawString("Score: " + score, 5, 65);

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
                    mediator.factory.newBlock(xx*32,yy*32);
                }
                if(green == 255 && blue == 0){
                    mediator.factory.newEnemy(xx*32,yy*32);
                }
                if(blue == 255 && green == 0){
                    mediator.factory.newPlayer(xx*32,yy*32);
                }
                if(blue == 255 && green == 255){
                    mediator.factory.newEnemySpawmer(xx*32,yy*32);
                }
            }
        }
    }

    public SpriteSheet getSs() {
        return ss;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setScore(int score) {
        this.score = score;
    }

}