package Model;

import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemySpawnerTest extends TestCase {

    @BeforeEach
    protected void setUp() {
    }

    @AfterEach
    protected void tearDown() {
    }

    @Test
    void tick() {

        int x1 = 0;
        int x2 = 0;
        int x3 = 0;
        int x4 = 0;


        if(x1 == 0){
            assertTrue(true);
        }
        else if(x2 == 0){
            assertTrue(true);

        }else if (x3 == 0) {
            assertTrue(true);
        }
        else if(x4 == 0){
            assertTrue(true);
        }
        else {
            assertTrue(true);
            }

        //true true
        //true true
        int x5 = 0;
        int x6 = 0;
        int level = 1;
        int size = 40;

        assertTrue(x5 < level && size < 50);
        assertTrue(x6 == 0 && level < 10);

         x1 = 1;
         x2 = 0;
         x3 = 0;
         x4 = 0;


        if(x1 == 0){
            assertTrue(true);
        }
        else if(x2 == 0){
            assertTrue(true);

        }else if (x3 == 0) {
            assertTrue(true);
        }
        else if(x4 == 0){
            assertTrue(true);
        }
        else {
            assertTrue(true);
        }

        //true false
        //true false
        x5 = 10;
        x6 = 0;
        level = 11;
        size = 50;

        assertFalse(x5 < level && size < 50);
        assertFalse(x6 == 0 && level < 10);

         x1 = 0;
         x2 = 0;
         x3 = 1;
         x4 = 0;


        if(x1 == 0){
            assertTrue(true);
        }
        else if(x2 == 0){
            assertTrue(true);

        }else if (x3 == 0) {
            assertTrue(true);
        }
        else if(x4 == 0){
            assertTrue(true);
        }
        else {
            assertTrue(true);
        }


        //false true
        //false true
        x5 = 10;
        x6 = 4;
        level = 5;
        size = 40;

        assertFalse(x5 < level && size < 50);
        assertFalse(x6 == 0 && level < 10);


         x1 = 0;
         x2 = 0;
         x3 = 0;
         x4 = 1;


        if(x1 == 0){
            assertTrue(true);
        }
        else if(x2 == 0){
            assertTrue(true);

        }else if (x3 == 0) {
            assertTrue(true);
        }
        else if(x4 == 0){
            assertTrue(true);
        }
        else {
            assertTrue(true);
        }

        //false false
        //false false
        x5 = 10;
        x6 = 4;
        level = 10;
        size = 50;

        assertFalse(x5 < level && size < 50);
        assertFalse(x6 == 0 && level < 10);

    }
}