package Game.enemy;

import Engine.audio.Source;
import Engine.rendering.animation.Animation;
import Game.entities.CombatManager;

import java.util.Random;

/**
 * Created by Slon on 26.05.2016.
 */
public class Attack implements EnemyState {
    private static final int DAMAGE = 10;
    private static final int MAX_ABERRATION = 4;

    //  store here animation too;
    private Source source;
    private Animation animation;
    private Random random;

    public Attack(Source source, Animation animation) {
        this.source = source;
        this.animation = animation;
        this.random = new Random();
    }

    @Override
    public void enter() {
        damage();
    }

    @Override
    public void update() {
        if(!animation.isPlaying()) {
            animation.play();
            damage();
        }
    }

    private void damage(){
        CombatManager.getInstance().hitCombating(DAMAGE + random.nextInt(MAX_ABERRATION * 2) - MAX_ABERRATION);
    }

    @Override
    public Animation getAnimation() {
        return animation;
    }

    @Override
    public void exit() {

    }
}
