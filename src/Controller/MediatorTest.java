package Controller;

import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MediatorTest extends TestCase {

    int xPlayer ;
    int yPlayer ;
    int xBullet ;
    int yBullet ;

    @BeforeEach
    protected void setUp() {
        Random random = new Random();
        int width = 1030;
        int height = 728;
         xPlayer = 500 + 32;
         yPlayer = 500 + 32;
         xBullet = 500 + 16;
         yBullet = 500 + 16;

    }

    @AfterEach
    protected void tearDown() {
    }

    @Test
    void isShootable() {
        int tempX = xPlayer - xBullet;
        int tempY = yPlayer - yBullet;

        if(tempX < 0) tempX = -tempX;
        if(tempY < 0) tempY = -tempY;

        assertTrue(tempX < 128 && tempY < 128);

    }
}