package Controller;

import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MediatorTest extends TestCase {

    private int screenWidth;
    private int screemHeight;

    @BeforeEach
    protected void setUp() {
        screenWidth = 1030;
        screemHeight = 728;

    }

    @AfterEach
    protected void tearDown() {
    }

    @Test
    void isShootable() {

        //first test, normal input
        int xPlayer = 500 + 32;
        int yPlayer = 500 + 32;
        int xBullet = 500 + 32;
        int yBullet = 500 + 32;

        int tempX = xPlayer - xBullet;
        int tempY = yPlayer - yBullet;

        if(tempX < 0) tempX = -tempX;
        if(tempY < 0) tempY = -tempY;

        assertTrue(tempX < 128 && tempY < 128);

        //
        xPlayer = 128 + 32;
        yPlayer = 128 + 32;
        xBullet = 0 + 32;
        yBullet = 0 + 32;

        tempX = xPlayer - xBullet;
        tempY = yPlayer - yBullet;

        if(tempX < 0) tempX = -tempX;
        if(tempY < 0) tempY = -tempY;

        assertFalse(tempX < 128 && tempY < 128);

        xPlayer = 127 + 32;
        yPlayer = 127 + 32;
        xBullet = 0 + 32;
        yBullet = 0 + 32;

        tempX = xPlayer - xBullet;
        tempY = yPlayer - yBullet;

        if(tempX < 0) tempX = -tempX;
        if(tempY < 0) tempY = -tempY;

        assertTrue(tempX < 128 && tempY < 128);


        xPlayer = 0 + 32;
        yPlayer = 0 + 32;
        xBullet = 127 + 32;
        yBullet = 127 + 32;

        tempX = xPlayer - xBullet;
        tempY = yPlayer - yBullet;

        if(tempX < 0) tempX = -tempX;
        if(tempY < 0) tempY = -tempY;

        assertTrue(tempX < 128 && tempY < 128);

        xPlayer = 0 + 32;
        yPlayer = 0 + 32;
        xBullet = 128 + 32;
        yBullet = 128 + 32;

        tempX = xPlayer - xBullet;
        tempY = yPlayer - yBullet;

        if(tempX < 0) tempX = -tempX;
        if(tempY < 0) tempY = -tempY;

        assertFalse(tempX < 128 && tempY < 128);

    }
}