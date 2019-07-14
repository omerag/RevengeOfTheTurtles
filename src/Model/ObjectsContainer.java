package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ObjectsContainer {

    public static ObjectsContainer container=  new ObjectsContainer();

    private Player player = null;
    private EnemySpawner enemySpawner = null;
    private Bullet reflectableBullet = null;
    private List<Enemy> enemyList = Collections.synchronizedList(new ArrayList<Enemy>());
    private List<Bullet> bulletList = Collections.synchronizedList(new ArrayList<Bullet>());
    private List<Block> blockList = Collections.synchronizedList(new ArrayList<Block>());
    private FruitOfLife fruit = null;


    private ObjectsContainer(){
        //nothing...
    }

    public static ObjectsContainer getInstance(){
        if(container == null){
            container = new ObjectsContainer();
        }
        return container;
    }

    void addPlayer(Player player){
        if(this.player == null){
            this.player = player;
        }
    }

    void addEnemySpawner(EnemySpawner enemySpawner){
        if (this.enemySpawner == null) {
            this.enemySpawner = enemySpawner;
        }
    }

    public void setReflectableBullet(Bullet reflectableBullet) {
        this.reflectableBullet = reflectableBullet;
    }

    public void addEnemy(Enemy enemy){
        enemyList.add(enemy);
    }
    
    public void removeEnemy(Enemy enemy){
        enemyList.remove(enemy);
    }

    public void addBullet(Bullet bullet){
        bulletList.add(bullet);
    }
    
   public void removeBullet(Bullet bullet){
       bulletList.remove(bullet);
    }

   public void addBlock(Block block) {
        blockList.add(block);
    }

    public Player getPlayer() {
        return player;
    }

    public EnemySpawner getEnemySpawner() {
        return enemySpawner;
    }

    public List<Enemy> getEnemyList() {
        return enemyList;
    }

    public Bullet getReflectableBullet(){ return reflectableBullet;}

    public List<Bullet> getBulletList() {
        return bulletList;
    }

    public List<Block> getBlockList() {
        return blockList;
    }

    public void addFruit (FruitOfLife _fruit) {
        fruit = _fruit;}

    public FruitOfLife getFruit() {
        return fruit;
    }


    public void RemoveFruit()
    {
        this.fruit = null;
    }

    public void clearContainer(){
        player = null;
        enemySpawner = null;
        reflectableBullet = null;
        enemyList.clear();
        bulletList.clear();
        blockList.clear();
        fruit = null;

    }

}

