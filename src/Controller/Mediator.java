package Controller;

import Model.*;


public class Mediator {

    private boolean up = false, down = false, right = false,left = false;

    public Factory factory;
    public ObjectsContainer objectsContainer = ObjectsContainer.getInstance();

    public void tick(){


        for(int i = 0; i < objectsContainer.getBulletList().size(); i++){
            Bullet bullet = objectsContainer.getBulletList().get(i);

            if(isShootable(objectsContainer.getPlayer().getX(), objectsContainer.getPlayer().getY(),
                    bullet.getX(),bullet.getY()) && bullet.bulletType == BulletType.ENEMY){
                objectsContainer.setReflectableBullet(bullet);
            }

            bullet.tick();
        }

        for (int i = 0; i < objectsContainer.getEnemyList().size(); i++) {
            Enemy enemy = objectsContainer.getEnemyList().get(i);
            enemy.tick();
        }

        objectsContainer.getEnemySpawner().tick();

        objectsContainer.getPlayer().tick();

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

     boolean isShootable(int xPlayer, int yPlayer,int xBullet,int yBullet){
        int tempX = xPlayer - xBullet;
        int tempY = yPlayer - yBullet;

        if(tempX < 0) tempX = -tempX;
        if(tempY < 0) tempY = -tempY;

        return tempX < 128 && tempY < 128;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }
}
