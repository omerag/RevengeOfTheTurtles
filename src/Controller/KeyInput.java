package Controller;

import Model.GameObject;
import Model.ID;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
     Mediator mediator;

    public KeyInput(Mediator mediator){
        this.mediator = mediator;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        for (int i = 0; i< mediator.getObject().size(); i++){
            GameObject tempObject = mediator.getObject().get(i);

            if(tempObject.getId()== ID.Player){
                if(key == KeyEvent.VK_W) mediator.setUp(true);
                if(key == KeyEvent.VK_S) mediator.setDown(true);
                if(key == KeyEvent.VK_A) mediator.setLeft(true);
                if(key == KeyEvent.VK_D) mediator.setRight(true);
            }
        }
    }

    public void keyReleased(KeyEvent e){

        int key = e.getKeyCode();
        for (int i = 0; i< mediator.getObject().size(); i++){
            GameObject tempObject = mediator.getObject().get(i);

            if(tempObject.getId()== ID.Player){
                if(key == KeyEvent.VK_W) mediator.setUp(false);
                if(key == KeyEvent.VK_S) mediator.setDown(false);
                if(key == KeyEvent.VK_A) mediator.setLeft(false);
                if(key == KeyEvent.VK_D) mediator.setRight(false);

            }
        }

    }

}
