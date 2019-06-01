package View;

import Model.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class ImageRender{

    private Handler handler = null;
    private Game game = null;
    private int hp;
    private int score;

    private SpriteSheet ss;

    private BufferedImage level;
    private BufferedImage floor;

    public ImageRender(Game game,Handler handler){
        this.game = game;
        this.handler = handler;

        BufferImageLoader loader = new BufferImageLoader();
        level = loader.loadImage("/wizard_level - Copy.png");
        BufferedImage sprite_sheet = loader.loadImage("/sprite_sheet.png");

        ss = new SpriteSheet(sprite_sheet);
        floor = ss.grabImage(4,2,32,32);
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

        handler.render(g);

        g.setColor(Color.GRAY);
        g.fillRect(5, 5, 200, 32);
        g.setColor(Color.GREEN);
        g.fillRect(5, 5, hp * 2, 32);
        g.setColor(Color.BLACK);
        g.drawRect(5, 5, 200, 32);

        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 5, 50);
        /////////////////////////////////

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
                    handler.addObject(new Block(xx*32,yy*32, ID.Block, ss));
                }
                if(green == 255 && blue == 0){
                    handler.addObject(new Enemy(xx*32,yy*32,ID.Enemy, handler, ss,game));
                }
                if(blue == 255 && green == 0){
                    handler.addObject(new Player(xx*32,yy*32,ID.Player, handler,game,ss));
                }
                if(blue == 255 && green == 255){
                    handler.addObject(new EnemySpawmer(xx*32,yy*32,ID.Enemy, handler, ss,game));
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