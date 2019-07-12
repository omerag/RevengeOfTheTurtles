package Controller;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import Model.*;
import View.ImageRender;


public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    private boolean isRunning = false;
    private Thread thread;
    private Mediator mediator;
    private HOFController hofc;
    private ImageRender imageRender;

    public int score = 0;
    public int playerHP = 100;
    public int snorlaxHP = 100;


    private boolean isFocused = true;

    // Constructor
    public void SoundContainer() {
        // Pre-load all the sound files
        SoundContainer.init();
        SoundContainer.volume = SoundContainer.Volume.LOW;
    }

    public Game(int gameWidth, int gameHeight){

        mediator = new Mediator();
        imageRender = new ImageRender(this, mediator);
        hofc = new HOFController();
        setFocusable(true);
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                isFocused = true;
                System.out.println("game focused");
            }

            @Override
            public void focusLost(FocusEvent e) {
                isFocused = false;
                System.out.println("game lost focus");

            }
        });

        ObjectsContainer.getInstance();
        SpriteContainer.getInstance();

        Factory factory = new Factory(this,gameWidth,gameHeight,mediator,imageRender.getSs());
        mediator.setFactory(factory);

        MouseInput mouseInput = new MouseInput(mediator);

        this.addMouseListener(mouseInput);
        this.addKeyListener(new KeyInput(mediator));

        imageRender.loadLevel();
    }

    private void start(){
        isRunning = true;
        thread = new Thread(this);
        thread.start();
        SoundContainer.THEME.play();
    }

    public void StartGame()
    {
        start();
        System.out.println("Game Started");
    }


    public void stop(){
        isRunning = false;
        ObjectsContainer.getInstance().clearContainer();
        try {


            if(playerHP < 1 || snorlaxHP < 1) {
                hofc.checkifhighscore(Integer.toString(score));
            }

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
        int updates = 0;
        int frames = 0;
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) /ns;
            lastTime = now;
            while(delta >= 1) {
                if(isFocused) tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
                updates = 0;
            }
            //System.out.println("frames = " + frames +'\n' + "updates = " + updates);
            if(playerHP < 1 || snorlaxHP < 1){
                stop();
                // request name for high score.



            }
        }
    }

    public void tick(){

        mediator.tick();
        imageRender.setPlayerHP(playerHP);
        imageRender.setScore(score);
        imageRender.setSnorlaxHP(snorlaxHP);
    }

    public void render(){
        imageRender.render();
    }

    public boolean isRunning() {
        return isRunning;
    }

}
