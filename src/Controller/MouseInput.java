package Controller;

import Model.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    private Mediator mediator;

    public MouseInput(Mediator mediator) {
        this.mediator = mediator;
    }

    public void mousePressed(MouseEvent e){

        int mx = e.getX();
        int my = e.getY();

        Player player = mediator.objectsContainer.getPlayer();
        Bullet bullet = mediator.objectsContainer.getReflectableBullet();


        if(mediator.objectsContainer.getReflectableBullet() != null &&
                mediator.isShootable(player.getX(),player.getY(), bullet.getX(), bullet.getY())
            && bullet.bulletType == BulletType.ENEMY){
            mediator.factory.newFriendlyBullet(player.getX(),player.getY(),mx,my);
            mediator.objectsContainer.removeBullet(bullet);
            mediator.objectsContainer.setReflectableBullet(null);

        }
    }
}
