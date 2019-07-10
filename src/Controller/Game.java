package Controller;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Controller.MouseInput;
import Controller.*;
import Model.Factory;
import Model.ObjectsContainer;
import Model.SpriteContainer;
import View.ImageRender;


public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    private boolean isRunning = false;
    private Thread thread;
    private Mediator mediator;

    private ImageRender imageRender;

    public int score = 0;
    public int hp = 100;

     private boolean isFocused = true;

    public Game(int gameWidth, int gameHeight){

        mediator = new Mediator();
        imageRender = new ImageRender(this, mediator);

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
            if(hp < 1){
                stop();

            }
        }
    }

    public void tick(){

        mediator.tick();
        imageRender.setHp(hp);
        imageRender.setScore(score);
    }

    public void render(){
        imageRender.render();
    }

    public boolean isRunning() {
        return isRunning;
    }

}
