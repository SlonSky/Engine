package Game;

import Game.trigger.Trigger;
import Game.player.Player;

import java.util.ArrayList;

/**
 * Created by Slon on 30.05.2016.
 */
public class GameplayManager {
    private static GameplayManager instance = new GameplayManager();

    private int difficult;

    private boolean levelEnded = false;
    private boolean gameOver = false;

    private Player player;
    private ArrayList<Trigger> triggers;

    public static GameplayManager getInstance() {
        return instance;
    }

    private GameplayManager(){
        triggers = new ArrayList<>();
    }

    public void update(){
        for(Trigger trigger: triggers){
            trigger.check(player.getPosition());
        }
        gameOver = player.isDead();
    }

    public boolean isLevelEnded() {
        return levelEnded;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setLevelEnded(boolean levelEnded) {
        this.levelEnded = levelEnded;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void addTrigger(Trigger trigger){
        triggers.add(trigger);
    }
}
