package Model;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import Controller.MouseInput;
import Controller.*;


public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;
    private SpriteSheet ss;

    private BufferedImage level = null;
    private BufferedImage sprite_sheet = null;
    private BufferedImage floor = null;

    public int score = 0;
    public int hp = 100;


    public Game(){

        handler = new Handler();

        this.addKeyListener(new KeyInput(handler));

        BufferImageLoader loader = new BufferImageLoader();
        level = loader.loadImage("/wizard_level - Copy.png");
        sprite_sheet = loader.loadImage("/sprite_sheet.png");

        ss = new SpriteSheet(sprite_sheet);
        floor = ss.grabImage(4,2,32,32);

        this.addMouseListener(new MouseInput(handler,this, ss));

        loadLevel(level);

        //start();
    }


    private void start(){
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public void StartGame()
    {
        start();
        System.out.println("Game Started");
    }


    private void stop(){
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Game Ended");

    }

    @Override
    public void run() {

        System.out.println("Game is Running");
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) /ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                //updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
                //updates = 0;
            }
        }
        stop();
    }

    public void tick(){

        /*  for(int i = 0; i <handler.object.size();i++){
            if(handler.object.get(i).getId() == ID.Player){
                camera.tick(handler.object.get(i));
            }
        }*/
        handler.tick();

    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
       // Graphics2D g2d = (Graphics2D) g;
        /////////////////////////////////
     //   g2d.translate(-camera.getX(),-camera.getY());

        for(int xx = 0; xx < 30*33 ; xx+=32){
            for(int yy = 0; yy < 30*24; yy+=32){
                g.drawImage(floor,xx,yy,null);
            }
        }

        handler.render(g);

   //     g2d.translate(camera.getX(),camera.getY());

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

    //loading the level
    private void loadLevel(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();

        for(int xx = 0; xx < w; xx++){
            for (int yy = 0; yy < h; yy++){
                int pixel = image.getRGB(xx,yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if(red == 255){
                    handler.addObject(new Block(xx*32,yy*32,ID.Block, ss));
                }
                if(green == 255 && blue == 0){
                    handler.addObject(new Enemy(xx*32,yy*32,ID.Enemy, handler, ss,this));
                }
                if(blue == 255 && green == 0){
                    handler.addObject(new Player(xx*32,yy*32,ID.Player, handler,this,ss));
                }
                if(blue == 255 && green == 255){
                    handler.addObject(new EnemySpawmer(xx*32,yy*32,ID.Enemy, handler, ss,this));
                }

            }
        }
    }

    //public static void main(String[] args) {

      //  new Game();
    //}
}
