package Model;

import java.awt.*;
import java.util.ArrayList;

import java.util.LinkedList;

public class Handler {
   // LinkedList<GameObject> object = new LinkedList<>();
    ArrayList<GameObject> object = new ArrayList<>();


    private boolean up = false, down = false, right = false,left = false;

    public Bullet tempBullet = null;
    public Wizard tempWizard = null;


    public void tick(){
        for(int i = 0; i <object.size(); i++){
            GameObject tempObject = object.get(i);

            if(tempObject.getId()== ID.Player){
                tempWizard = (Wizard)tempObject;
            }

            if(tempObject.getId() == ID.Bullet && tempWizard != null){
                if(isShootable(tempObject.getX(),tempObject.getY(),tempWizard.getX(),tempWizard.getY())){

                    tempBullet = (Bullet)tempObject;
                }
            }

            tempObject.tick();
        }
    }

    public void render(Graphics g){
        for(int i = 0; i <object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }

    public ArrayList<GameObject> getObject()
    {
        return this.object;
    }


    public void addObject(GameObject tempObject){
        object.add(tempObject);
    }

    public void removeObject(GameObject tempObject){
        object.remove(tempObject);
    }


    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }



    public Bullet getTempBullet() {
        return tempBullet;
    }

    public BulletType getTempBulletType()
    {
        return this.tempBullet.bulletType;
    }

    public void setTempBulletToNull() {
        tempBullet = null;
    }


    private boolean isShootable(int xPlayer, int yPlayer,int xBullet,int yBullet){
        int tempX = xPlayer - xBullet;
        int tempY = yPlayer - yBullet;

        if(tempX < 0) tempX = -tempX;
        if(tempY < 0) tempY = -tempY;

        if(tempX < 64 && tempY < 64)
            return true;

        return false;
    }
}
