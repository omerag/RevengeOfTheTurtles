package Model;

import Controller.Game;
import Controller.Mediator;

import java.util.concurrent.ThreadLocalRandom;

public class Factory {

    private Mediator mediator;
    private Game game;
    private ObjectsContainer objectsContainer;
    private int gameWidth;
    private int gameHeight;
    private String[] stringObjectArray = new String[]{"ENEMY","ENEMY SPAWNER","PLAYER","BLOCK","ENEMY BULLET","PLAYER BULLET","FRUIT"};

    public Factory(Game game, int gameWidth, int gameHeight, Mediator mediator){
        this.mediator = mediator;
        this.game = game;
        objectsContainer = ObjectsContainer.getInstance();
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
    }

    public void createGameObject(String objectName){

        if(objectName.intern() == stringObjectArray[6].intern()){
            newFruit();
        }
    }

    public void createGameObject(String objectName, int x, int y){

        if(objectName.intern() == stringObjectArray[0].intern()){
            newEnemy(x, y);
        }
        else if(objectName.intern() == stringObjectArray[1].intern()){
            newEnemySpawmer(x, y);
        }
        else if (objectName.intern() == stringObjectArray[2].intern()){
            newPlayer(x,y);
        }
        else if (objectName.intern() == stringObjectArray[3].intern()){
            newBlock(x,y);
        }


    }

    public void createGameObject(String objectName, int x, int y,int mx, int my){
        if(objectName.intern() == stringObjectArray[4].intern()){
            newEnemyBullet(x, y, mx, my);
        }
        else if(objectName.intern() == stringObjectArray[5].intern()){
            newFriendlyBullet(x, y, mx, my);
        }
    }

    private void newPlayer(int x, int y){
        Player player = new Player(x,y,ID.Player, mediator,game,gameWidth, gameHeight);
        objectsContainer.addPlayer(player);
    }

    private void newBlock(int x, int y){
        Block block = new Block(x,y,ID.Block,gameWidth,gameHeight);
        objectsContainer.addBlock(block);
    }

    private void newEnemySpawmer(int x, int y){
        EnemySpawner enemySpawner = new EnemySpawner(x,y,ID.Enemy, mediator,game,gameWidth,gameHeight);
        objectsContainer.addEnemySpawner(enemySpawner);
    }

    private void newEnemy(int x, int y){
        Enemy enemy = new Enemy(x,y, ID.Enemy, mediator,game,gameWidth,gameHeight);
        objectsContainer.addEnemy(enemy);
    }

    private void newEnemyBullet(int x, int y,int mx, int my){
        Bullet bullet = new Bullet(x,y,ID.Bullet, mediator,mx, my,BulletType.ENEMY,gameWidth,gameHeight);
        objectsContainer.addBullet(bullet);
    }

    private void newFriendlyBullet(int x, int y, int mx, int my){
        Bullet bullet = new Bullet(x,y,ID.Bullet, mediator,mx, my,BulletType.PLAYER,gameWidth,gameHeight);
        objectsContainer.addBullet(bullet);
    }

    private int GetRandom(int min,int max)
    {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public Game getGame() {
        return game;
    }

    private void newFruit(){
        FruitOfLife Fruit = new FruitOfLife(GetRandom(64,gameWidth-64),GetRandom(64,gameHeight-128),ID.Fruit, mediator,gameWidth,gameHeight,GetRandom(0,4),System.currentTimeMillis());
        objectsContainer.addFruit(Fruit);
    }


}

