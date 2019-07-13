package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BulletTest {

    @Test
    void calculateVelocity() {
        float mx = 500;
        float x = 400;
        float my = 500;
        float y = 400;

        float tempX = mx - x;
        float tempY = my - y;

        //true true
        while (Math.abs(tempX) > 4 || Math.abs(tempY) > 4){
            tempX = tempX / 2;
            tempY = tempY / 2;
        }

        assertTrue(tempX <= 4 && tempY <= 4);



         mx = 500;
         x = 400;
         my = 500;
         y = 500;

         tempX = mx - x;
         tempY = my - y;

        //true false
        while (Math.abs(tempX) > 4 || Math.abs(tempY) > 4){
            tempX = tempX / 2;
            tempY = tempY / 2;
        }

        assertTrue(tempX <= 4 && tempY <= 4);

        mx = 500;
        x = 500;
        my = -500;
        y = -400;

        tempX = mx - x;
        tempY = my - y;

        //false true
        while (Math.abs(tempX) > 4 || Math.abs(tempY) > 4){
            tempX = tempX / 2;
            tempY = tempY / 2;
        }

        assertTrue(tempX <= 4 && tempY <= 4);


        mx = 0;
        x = 0;
        my = 0;
        y = 0;

        tempX = mx - x;
        tempY = my - y;

        //false false
        while (Math.abs(tempX) > 4 || Math.abs(tempY) > 4){
            tempX = tempX / 2;
            tempY = tempY / 2;
        }

        assertTrue(tempX <= 4 && tempY <= 4);

    }


}