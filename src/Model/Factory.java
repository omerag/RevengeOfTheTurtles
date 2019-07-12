package Model;

import Controller.Game;
import Controller.Mediator;

public class Factory {

    private Mediator mediator;
    private Game game;
    private ObjectsContainer objectsContainer;
    private int gameWidth;
    private int gameHeight;

    public Factory(Game game, int gameWidth, int gameHeight,
                   Mediator mediator, SpriteSheet spriteSheet){
        this.mediator = mediator;
        this.game = game;
        objectsContainer = ObjectsContainer.getInstance();
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
    }



    public void newPlayer(int x, int y){
        Player player = new Player(x,y,ID.Player, mediator,game,gameWidth, gameHeight);
        objectsContainer.addPlayer(player);
    }

    public void newBlock(int x, int y){
        Block block = new Block(x,y,ID.Block,gameWidth,gameHeight);
        objectsContainer.addBlock(block);
    }

    public void newEnemySpawmer(int x, int y){
        EnemySpawner enemySpawner = new EnemySpawner(x,y,ID.Enemy, mediator,game,gameWidth,gameHeight);
        objectsContainer.addEnemySpawner(enemySpawner);
    }

    public void newEnemy(int x, int y){
        Enemy enemy = new Enemy(x,y, ID.Enemy, mediator,game,gameWidth,gameHeight);
        objectsContainer.addEnemy(enemy);
    }

    public void newEnemyBullet(int x, int y,int mx, int my){
        Bullet bullet = new Bullet(x,y,ID.Bullet, mediator,mx, my,BulletType.ENEMY,gameWidth,gameHeight);
        objectsContainer.addBullet(bullet);
    }

    public void newFriendlyBullet(int x, int y, int mx, int my){
        Bullet bullet = new Bullet(x,y,ID.Bullet, mediator,mx, my,BulletType.PLAYER,gameWidth,gameHeight);
        objectsContainer.addBullet(bullet);
    }



}

