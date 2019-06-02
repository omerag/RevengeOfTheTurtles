package Model;

import java.util.ArrayList;
import java.util.List;

public class ObjectsContainer {

    private Player player = null;
    private EnemySpawner enemySpawner = null;
    private Bullet reflectableBullet = null;
    private List<Enemy> enemyList = new ArrayList<Enemy>();
    private List<Bullet> bulletList = new ArrayList<Bullet>();
    private List<Block> blockList = new ArrayList<Block>();

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

    void addEnemy(Enemy enemy){
        enemyList.add(enemy);
    }
    
    void removeEnemy(Enemy enemy){
        enemyList.remove(enemy);
    }

    void addBullet(Bullet bullet){
        bulletList.add(bullet);
    }
    
    void removeBullet(Bullet bullet){
        bulletList.remove(bullet);
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

    public List<Bullet> getBulletList() {
        return bulletList;
    }

    public List<Block> getBlockList() {
        return blockList;
    }
}

