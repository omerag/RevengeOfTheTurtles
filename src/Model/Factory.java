package Model;

public class Factory {

    private Handler handler;
    private Game game;
    private SpriteSheet spriteSheet;
    private int gameWidth;
    private int gameHeight;

    public Factory(Game game, int gameWidth, int gameHeight, Handler handler, SpriteSheet spriteSheet){
        this.handler = handler;
        this.game = game;
        this.spriteSheet = spriteSheet;
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
    }

    public Player newPlayer(int x, int y){

        return new Player(x,y,ID.Player,handler,game,spriteSheet,gameWidth, gameHeight);
    }

    public Block newBlock(int x, int y){

        return new Block(x,y,ID.Block,spriteSheet,gameWidth,gameHeight);
    }

    public EnemySpawmer newEnemySpawmer(int x, int y){

        return new EnemySpawmer(x,y,ID.Enemy,handler,spriteSheet,game,gameWidth,gameHeight);
    }

    public Enemy newEnemy(int x, int y){

        return new Enemy(x,y, ID.Enemy,handler,spriteSheet,game,gameWidth,gameHeight);
    }

    public Bullet newEnemyBullet(int x, int y,int mx, int my){

        return new Bullet(x,y,ID.Bullet,handler,mx, my,spriteSheet,BulletType.ENEMY,gameWidth,gameHeight);
    }

    public Bullet newFriendlyBullet(int x, int y, int mx, int my){

        return new Bullet(x,y,ID.Bullet,handler,mx, my,spriteSheet,BulletType.PALYER,gameWidth,gameHeight);
    }
}

