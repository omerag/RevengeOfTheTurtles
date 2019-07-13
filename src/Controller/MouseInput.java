package Controller;

import Model.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class MouseInput extends MouseAdapter {

    private Mediator mediator;
    private Random r = new Random();

    public MouseInput(Mediator mediator) {
        this.mediator = mediator;
    }

    public void mousePressed(MouseEvent e){

        int mx = e.getX();
        int my = e.getY();

        Player player = mediator.getObjectsContainer().getPlayer();
        Bullet bullet = mediator.getObjectsContainer().getReflectableBullet();


        if(mediator.getObjectsContainer().getReflectableBullet() != null &&
                mediator.isShootable(player.getX(),player.getY(), bullet.getX(), bullet.getY())
                && bullet.bulletType == BulletType.ENEMY){

            int i = r.nextInt(9);
            switch (i+1)
            {
                case 1:
                    SoundContainer.PANDA1.play();
                    break;
                case 2:
                    SoundContainer.PANDA2.play();
                    break;
                case 3:
                    SoundContainer.PANDA3.play();
                    break;
                case 4:
                    SoundContainer.PANDA4.play();
                    break;
                case 5:
                    SoundContainer.PANDA5.play();
                    break;
                case 6:
                    SoundContainer.PANDA6.play();
                    break;
                case 7:
                    SoundContainer.PANDA7.play();
                    break;
                case 8:
                    SoundContainer.PANDA8.play();
                    break;
                case 9:
                    SoundContainer.PANDA9.play();
                    break;

            }

            mediator.getFactory().createGameObject("PLAYER BULLET",bullet.getX(),bullet.getY(),mx + 16,my + 16);
            mediator.getObjectsContainer().removeBullet(bullet);
            mediator.getObjectsContainer().setReflectableBullet(null);

        }
    }
}
