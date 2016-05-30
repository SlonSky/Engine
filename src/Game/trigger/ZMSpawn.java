package Game.trigger;

import Game.Level;
import Game.enemy.Enemy;

import java.util.ArrayList;

/**
 * Created by Slon on 30.05.2016.
 */
public class ZMSpawn implements TriggerEvent {

    private ArrayList<Enemy> enemiesToSpawn;

    public ZMSpawn(ArrayList<Enemy> enemiesToSpawn) {
        this.enemiesToSpawn = enemiesToSpawn;
    }

    @Override
    public void run() {
        for(Enemy enemy: enemiesToSpawn){
            Level.getObjects().add(enemy);
        }
    }
}
