package Model;

import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterObjectTest extends TestCase {

    protected final int STAND_FACE_DOWN = 1;
    protected final int LEFT_LEG_FACE_DOWN = 5;
    protected final int RIGHT_LEG_FACE_DOWN = 9;
    protected final int STAND_FACE_UP = 2;
    protected final int LEFT_LEG_FACE_UP = 6;
    protected final int RIGHT_LEG_FACE_UP = 10;
    protected final int STAND_FACE_RIGHT = 3;
    protected final int LEFT_LEG_FACE_RIGHT = 7;
    protected final int RIGHT_LEG_FACE_RIGHT = 11;
    protected final int STAND_FACE_LEFT = 4;
    protected final int LEFT_LEG_FACE_LEFT = 8;
    protected final int RIGHT_LEG_FACE_LEFT = 12;


    private int gameWidth;
    private int gameHeight;
    private int x;
    private int y;

    @BeforeEach
    protected void setUp() {
        gameWidth = 1030;
        gameHeight = 728;
    }

    @AfterEach
    protected void tearDown() {
    }

    @Test
    void checkBorders() {

        //true false true false
        x = 32;
        y = 31;
        assertTrue(x <= 32 || x >= gameWidth - 32*3 || y < 32 || y >= gameHeight - 16*9);

        //false true false true
        x = gameWidth;
        y = gameHeight;
        assertTrue(x <= 32 || x >= gameWidth - 32*3 || y < 32 || y >= gameHeight - 16*9);

        //false true true false
        x = gameWidth;
        y = 31;
        assertTrue(x <= 32 || x >= gameWidth - 32*3 || y < 32 || y >= gameHeight - 16*9);

        //true false false true
        x = 32;
        y = gameHeight;
        assertTrue(x <= 32 || x >= gameWidth - 32*3 || y < 32 || y >= gameHeight - 16*9);

        //false false false true
        x = 100;
        y = gameHeight;
        assertTrue(x <= 32 || x >= gameWidth - 32*3 || y < 32 || y >= gameHeight - 16*9);

        //false false true false
        x = 100;
        y = 30;
        assertTrue(x <= 32 || x >= gameWidth - 32*3 || y < 32 || y >= gameHeight - 16*9);

        //false true false false
        x = gameWidth;
        y = 500;
        assertTrue(x <= 32 || x >= gameWidth - 32*3 || y < 32 || y >= gameHeight - 16*9);

        //true false false false
        x = 32;
        y = 500;
        assertTrue(x <= 32 || x >= gameWidth - 32*3 || y < 32 || y >= gameHeight - 16*9);

        //false false false false
        x = 500;
        y = 500;
        assertFalse(x <= 32 || x >= gameWidth - 32*3 || y < 32 || y >= gameHeight - 16*9);

    }


    @Test
    void actionMoveRight() {

        //true false false
        int counter = 11;
        int resetCounter = 20;
        int currentState = LEFT_LEG_FACE_UP;
        //true true
        assertTrue(currentState != LEFT_LEG_FACE_RIGHT && counter > resetCounter/2);
        //false true
        assertFalse(counter <= resetCounter/2 && counter >=0);
        //false
        assertFalse(counter < 0);

        //false true false
        counter = 9;
        resetCounter = 20;
        currentState = LEFT_LEG_FACE_UP;
        //true false
        assertFalse(currentState != LEFT_LEG_FACE_RIGHT && counter > resetCounter/2);
        //true true
        assertTrue(counter <= resetCounter/2 && counter >=0);
        //false
        assertFalse(counter < 0);

        //false false false
        counter = 11;
        resetCounter = 20;
        currentState = LEFT_LEG_FACE_RIGHT;
        //false true
        assertFalse(currentState != LEFT_LEG_FACE_RIGHT && counter > resetCounter/2);
        //false true
        assertFalse(counter <= resetCounter/2 && counter >=0);
        //false
        assertFalse(counter < 0);

        //false false false
        counter = 9;
        resetCounter = 20;
        currentState = LEFT_LEG_FACE_RIGHT;
        //false false
        assertFalse(currentState != LEFT_LEG_FACE_RIGHT && counter > resetCounter/2);
        //true true
        assertTrue(counter <= resetCounter/2 && counter >=0);
        //false
        assertFalse(counter < 0);

        //false false true
        counter = -1;
        resetCounter = 20;
        currentState = LEFT_LEG_FACE_RIGHT;
        //false false
        assertFalse(currentState != LEFT_LEG_FACE_RIGHT && counter > resetCounter/2);
        //true false
        assertFalse(counter <= resetCounter/2 && counter >=0);
        //true
        assertTrue(counter < 0);

    }

    @Test
    void actionMoveDown() {

        //true false false
        int counter = 11;
        int resetCounter = 20;
        int currentState = LEFT_LEG_FACE_UP;
        //true true
        assertTrue(currentState != LEFT_LEG_FACE_DOWN && counter > resetCounter/2);
        //false true
        assertFalse(counter <= resetCounter/2 && counter >=0);
        //false
        assertFalse(counter < 0);

        //false true false
        counter = 9;
        resetCounter = 20;
        currentState = LEFT_LEG_FACE_UP;
        //true false
        assertFalse(currentState != LEFT_LEG_FACE_RIGHT && counter > resetCounter/2);
        //true true
        assertTrue(counter <= resetCounter/2 && counter >=0);
        //false
        assertFalse(counter < 0);

        //false false false
        counter = 11;
        resetCounter = 20;
        currentState = LEFT_LEG_FACE_RIGHT;
        //false true
        assertFalse(currentState != LEFT_LEG_FACE_RIGHT && counter > resetCounter/2);
        //false true
        assertFalse(counter <= resetCounter/2 && counter >=0);
        //false
        assertFalse(counter < 0);

        //false false false
        counter = 9;
        resetCounter = 20;
        currentState = LEFT_LEG_FACE_RIGHT;
        //false false
        assertFalse(currentState != LEFT_LEG_FACE_RIGHT && counter > resetCounter/2);
        //true true
        assertTrue(counter <= resetCounter/2 && counter >=0);
        //false
        assertFalse(counter < 0);

        //false false true
        counter = -1;
        resetCounter = 20;
        currentState = LEFT_LEG_FACE_RIGHT;
        //false false
        assertFalse(currentState != LEFT_LEG_FACE_RIGHT && counter > resetCounter/2);
        //true false
        assertFalse(counter <= resetCounter/2 && counter >=0);
        //true
        assertTrue(counter < 0);
    }

    @Test
    void actionMoveLeft() {

        //true false false
        int counter = 11;
        int resetCounter = 20;
        int currentState = LEFT_LEG_FACE_UP;
        //true true
        assertTrue(currentState != LEFT_LEG_FACE_LEFT && counter > resetCounter/2);
        //false true
        assertFalse(counter <= resetCounter/2 && counter >=0);
        //false
        assertFalse(counter < 0);

        //false true false
        counter = 9;
        resetCounter = 20;
        currentState = LEFT_LEG_FACE_UP;
        //true false
        assertFalse(currentState != LEFT_LEG_FACE_RIGHT && counter > resetCounter/2);
        //true true
        assertTrue(counter <= resetCounter/2 && counter >=0);
        //false
        assertFalse(counter < 0);

        //false false false
        counter = 11;
        resetCounter = 20;
        currentState = LEFT_LEG_FACE_RIGHT;
        //false true
        assertFalse(currentState != LEFT_LEG_FACE_RIGHT && counter > resetCounter/2);
        //false true
        assertFalse(counter <= resetCounter/2 && counter >=0);
        //false
        assertFalse(counter < 0);

        //false false false
        counter = 9;
        resetCounter = 20;
        currentState = LEFT_LEG_FACE_RIGHT;
        //false false
        assertFalse(currentState != LEFT_LEG_FACE_RIGHT && counter > resetCounter/2);
        //true true
        assertTrue(counter <= resetCounter/2 && counter >=0);
        //false
        assertFalse(counter < 0);

        //false false true
        counter = -1;
        resetCounter = 20;
        currentState = LEFT_LEG_FACE_RIGHT;
        //false false
        assertFalse(currentState != LEFT_LEG_FACE_RIGHT && counter > resetCounter/2);
        //true false
        assertFalse(counter <= resetCounter/2 && counter >=0);
        //true
        assertTrue(counter < 0);
    }

    @Test
    void actionMoveUp() {

        //true false false
        int counter = 11;
        int resetCounter = 20;
        int currentState = LEFT_LEG_FACE_DOWN;
        //true true
        assertTrue(currentState != LEFT_LEG_FACE_UP && counter > resetCounter/2);
        //false true
        assertFalse(counter <= resetCounter/2 && counter >=0);
        //false
        assertFalse(counter < 0);

        //false true false
        counter = 9;
        resetCounter = 20;
        currentState = LEFT_LEG_FACE_DOWN;
        //true false
        assertFalse(currentState != LEFT_LEG_FACE_RIGHT && counter > resetCounter/2);
        //true true
        assertTrue(counter <= resetCounter/2 && counter >=0);
        //false
        assertFalse(counter < 0);

        //false false false
        counter = 11;
        resetCounter = 20;
        currentState = LEFT_LEG_FACE_RIGHT;
        //false true
        assertFalse(currentState != LEFT_LEG_FACE_RIGHT && counter > resetCounter/2);
        //false true
        assertFalse(counter <= resetCounter/2 && counter >=0);
        //false
        assertFalse(counter < 0);

        //false false false
        counter = 9;
        resetCounter = 20;
        currentState = LEFT_LEG_FACE_RIGHT;
        //false false
        assertFalse(currentState != LEFT_LEG_FACE_RIGHT && counter > resetCounter/2);
        //true true
        assertTrue(counter <= resetCounter/2 && counter >=0);
        //false
        assertFalse(counter < 0);

        //false false true
        counter = -1;
        resetCounter = 20;
        currentState = LEFT_LEG_FACE_RIGHT;
        //false false
        assertFalse(currentState != LEFT_LEG_FACE_RIGHT && counter > resetCounter/2);
        //true false
        assertFalse(counter <= resetCounter/2 && counter >=0);
        //true
        assertTrue(counter < 0);
    }
}