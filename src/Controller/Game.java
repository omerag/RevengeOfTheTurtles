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
    //private GameTimer Timer;
    private Mediator mediator;
    private HOFController hofc;
    private ImageRender imageRender;
    private long timer;
    private int score = 0;
    private int playerHP = 100;
    private int snorlaxHP = 100;


    private boolean isFocused = true;

    // Constructor
    public void SoundContainer() {
        // Pre-load all the sound files
        SoundContainer.init();
        SoundContainer.volume = SoundContainer.Volume.LOW;
    }

    public Game(int gameWidth, int gameHeight){

        mediator = new Mediator();
        //Timer = new GameTimer();
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

        Factory factory = new Factory(this,gameWidth,gameHeight,mediator);
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
        timer = System.currentTimeMillis();
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

    }

    public void render(){
        imageRender.render();
    }

    public boolean isRunning() {
        return isRunning;
    }

    public int getScore() {
        return score;
    }

    public long GetTimer()
    {
        return timer;

    }

    public int getPlayerHP() {
        return playerHP;
    }

    public int getSnorlaxHP() {
        return snorlaxHP;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setPlayerHP(int playerHP) {
        this.playerHP = playerHP;
        if(this.playerHP > 100){
            this.playerHP = 100;
        }
        else if(this.playerHP  < 1){
            this.playerHP = 0;
        }
    }

    public void setSnorlaxHP(int snorlaxHP) {
        this.snorlaxHP = snorlaxHP;

        if(this.snorlaxHP > 100){
            this.snorlaxHP = 100;
        }
        else if(this.snorlaxHP  < 1){
            this.snorlaxHP = 0;
        }
    }
}
