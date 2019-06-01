package View;

import Model.Game;
import Model.Handler;
import Model.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class ImageRender {

    private Handler handler = null;
    private Game game = null;
    private BufferStrategy bs;
    private Image floor = null;
    private int hp;
    private int score;

    ImageRender(Game game){
        this .game = game;
        game.createBufferStrategy(3);
        bs = game.getBufferStrategy();

    }

    void render(){
        bs = game.getBufferStrategy();
        Graphics g = bs.getDrawGraphics();


        for(int xx = 0; xx < 30*33 ; xx+=32){
            for(int yy = 0; yy < 30*24; yy+=32){
                g.drawImage(floor,xx,yy,null);
            }
        }

        handler.render(g);

        g.setColor(Color.GRAY);
        g.fillRect(5,5,200,32);
        g.setColor(Color.GREEN);
        g.fillRect(5,5,hp*2,32);
        g.setColor(Color.BLACK);
        g.drawRect(5,5,200,32);

        g.setColor(Color.WHITE);
        g.drawString("Score: " + score,5,50);
        /////////////////////////////////
        g.dispose();
        bs.show();
    }

}
