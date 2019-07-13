package Model;

import java.util.Random;

public class Informator {


    public boolean isShootable(int xPlayer, int yPlayer,int xBullet,int yBullet){
        int tempX = (xPlayer + 32) - (xBullet + 16);
        int tempY = (yPlayer + 32) - (yBullet + 16);

        if(tempX < 0) tempX = -tempX;
        if(tempY < 0) tempY = -tempY;

        return tempX < 128 && tempY < 128;
    }

    public boolean isItTimeForFood(FruitOfLife fruit){

        Random random = new Random();

        return fruit == null && random.nextInt(300) == 0;
    }

    public boolean isItTimeToEndFood(FruitOfLife fruit){
        Random random = new Random();

        return fruit != null && random.nextInt(500) == 0;
    }

}
