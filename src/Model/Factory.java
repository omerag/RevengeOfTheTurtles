package Model;

import Controller.Mediator;

public class Factory {

    private Mediator mediator;
    private Game game;
    private SpriteSheet spriteSheet;
    private ObjectsContainer objectsContainer;
    private int gameWidth;
    private int gameHeight;

    public Factory(Game game, int gameWidth, int gameHeight,
                   Mediator mediator, SpriteSheet spriteSheet, ObjectsContainer objectsContainer){
        this.mediator = mediator;
        this.game = game;
        this.spriteSheet = spriteSheet;
        this.objectsContainer = objectsContainer;
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
    }

    public Player newPlayer(int x, int y){
        Player player = new Player(x,y,ID.Player, mediator,game,spriteSheet,gameWidth, gameHeight);
        objectsContainer.addPlayer(player);
        return player;
    }

    public Block newBlock(int x, int y){
        Block block = new Block(x,y,ID.Block,spriteSheet,gameWidth,gameHeight);
        return block;
    }

    public EnemySpawner newEnemySpawmer(int x, int y){
        EnemySpawner enemySpawner = new EnemySpawner(x,y,ID.Enemy, mediator,spriteSheet,game,gameWidth,gameHeight);
        objectsContainer.addEnemySpawner(enemySpawner);
        return enemySpawner;
    }

    public Enemy newEnemy(int x, int y){
        Enemy enemy = new Enemy(x,y, ID.Enemy, mediator,spriteSheet,game,gameWidth,gameHeight);
        objectsContainer.addEnemy(enemy);
        return enemy;
    }

    public Bullet newEnemyBullet(int x, int y,int mx, int my){
        Bullet bullet = new Bullet(x,y,ID.Bullet, mediator,mx, my,spriteSheet,BulletType.ENEMY,gameWidth,gameHeight);
        objectsContainer.addBullet(bullet);
        return bullet;
    }

    public Bullet newFriendlyBullet(int x, int y, int mx, int my){
        Bullet bullet = new Bullet(x,y,ID.Bullet, mediator,mx, my,spriteSheet,BulletType.PALYER,gameWidth,gameHeight);
        objectsContainer.addBullet(bullet);
        return bullet;
    }
}

