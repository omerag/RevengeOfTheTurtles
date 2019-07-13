package Controller;

import Model.*;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class Mediator {

    private boolean up = false, down = false, right = false,left = false;

    private Factory factory;
    private ObjectsContainer objectsContainer = ObjectsContainer.getInstance();
    private Informator informator = new Informator();

    public void tick(){

        //activating  tick method of bullet objects
        for(int i = 0; i < objectsContainer.getBulletList().size(); i++){
            Bullet bullet = objectsContainer.getBulletList().get(i);

            //check if an enemy's bullet can be reflectable
            if(informator.isShootable(objectsContainer.getPlayer().getX(), objectsContainer.getPlayer().getY(),
                    bullet.getX(),bullet.getY()) && bullet.bulletType == BulletType.ENEMY){
                objectsContainer.setReflectableBullet(bullet);
            }

            bullet.tick();
        }

        //activating tick method of enemy objects
        for (int i = 0; i < objectsContainer.getEnemyList().size(); i++) {
            Enemy enemy = objectsContainer.getEnemyList().get(i);
            enemy.tick();
        }

        //activating tick method of enemySpawner object
        objectsContainer.getEnemySpawner().tick();

        //activating tick method of player object
        objectsContainer.getPlayer().tick();

        friutCheck();


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

     void setDown(boolean down) {
        this.down = down;
    }

     void setRight(boolean right) {
        this.right = right;
    }

     void setLeft(boolean left) {
        this.left = left;
    }




    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public Factory getFactory() {
        return factory;
    }

    public ObjectsContainer getObjectsContainer() {
        return objectsContainer;
    }

    public Informator getInformator() {
        return informator;
    }

    private void friutCheck(){
        if(informator.isItTimeForFood(objectsContainer.getFruit()))
        {
            factory.createGameObject("FRUIT");
        }

        if(informator.isItTimeToEndFood(objectsContainer.getFruit()))
        {
            objectsContainer.RemoveFruit();
        }
    }
}
