package Model;

import java.awt.image.BufferedImage;
import java.util.List;

public abstract class CharacterObject extends GameObject{
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

    protected final int MOVE_RIGHT = 13;
    protected final int MOVE_LEFT = 14;
    protected final int MOVE_UP = 15;
    protected final int MOVE_DOWN = 16;
    protected final int MOVE_STAND = 17;
    protected int lastMove = MOVE_STAND;



    protected int counter = 20;
    protected int resetCounter = 20;


    protected int currentState = STAND_FACE_DOWN;
    protected int myVel;






    protected List<BufferedImage> imagesList;
    public CharacterObject(int x, int y, ID id , int gameWidth, int gameHeight, List<BufferedImage> imagesList, int myVel) {
        super(x, y, id, gameWidth, gameHeight);
        this.imagesList = imagesList;
        this.myVel = myVel;
    }

    void checkBorders(){
        if (x <= 32 || x >= gameWidth - 32*3 || y < 32 || y >= gameHeight - 16*9) {
            x += velX * 3 * -1;
            y += velY * 3 * -1;
            velX *= -1;
            velY *= -1;
        }
    }


    void actionStand(){

        velX = 0;
        velY = 0;

        switch (currentState){
            case STAND_FACE_DOWN:
            case LEFT_LEG_FACE_DOWN:
            case RIGHT_LEG_FACE_DOWN:
                currentState = STAND_FACE_DOWN;
                return;
            case STAND_FACE_UP:
            case LEFT_LEG_FACE_UP:
            case RIGHT_LEG_FACE_UP:
                currentState = STAND_FACE_UP;
                return;
            case STAND_FACE_RIGHT:
            case LEFT_LEG_FACE_RIGHT:
            case RIGHT_LEG_FACE_RIGHT:
                currentState = STAND_FACE_RIGHT;
                return;
            case STAND_FACE_LEFT:
            case LEFT_LEG_FACE_LEFT:
            case RIGHT_LEG_FACE_LEFT:
                currentState = STAND_FACE_LEFT;
                return;
        }
    }

    void actionMoveRight(){

        velX = myVel;
        counter--;
        if(currentState != LEFT_LEG_FACE_RIGHT && counter > resetCounter/2){
            currentState = LEFT_LEG_FACE_RIGHT;
        }
        else if(counter <= resetCounter/2 && counter >=0){
            currentState = RIGHT_LEG_FACE_RIGHT;
        }
        else if(counter < 0){
            counter = resetCounter;

        }
    }

    void actionMoveDown(){

        velY = myVel;
        counter--;

        if(currentState != LEFT_LEG_FACE_DOWN && counter > resetCounter/2){
            currentState = LEFT_LEG_FACE_DOWN;
        }
        else if(counter <= resetCounter/2 && counter >=0){
            currentState = RIGHT_LEG_FACE_DOWN;
        }
        else if(counter < 0){
            counter = resetCounter;

        }

    }

    void actionMoveLeft(){

        velX = -myVel;
        counter--;

        if(currentState != LEFT_LEG_FACE_LEFT && counter > resetCounter/2){
            currentState = LEFT_LEG_FACE_LEFT;

        }
        else if(counter <= resetCounter/2 && counter >=0){
            currentState = RIGHT_LEG_FACE_LEFT;
        }
        else if (counter < 0){
            counter = resetCounter;

        }


    }

    void actionMoveUp(){

        velY = -myVel;
        counter--;
        if(currentState != LEFT_LEG_FACE_UP && counter > resetCounter/2 ){
            currentState = LEFT_LEG_FACE_UP;
        }
        else if(counter <= resetCounter/2 && counter >=0){
            currentState = RIGHT_LEG_FACE_UP;
        }
        else if (counter < 0){
            counter = resetCounter;
        }

    }
}
