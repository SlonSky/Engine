package Game.enemy;

import Engine.audio.Sound;
import Engine.audio.Source;
import Engine.rendering.animation.Animation;
import Game.Initializer;
import Game.entities.CombatManager;

import java.util.Random;

/**
 * Created by Slon on 26.05.2016.
 */
public class Attack implements EnemyState {
    private static final int DAMAGE = 10;
    private static final int MAX_ABERRATION = 4;

    private Sound attack;

    private Source source;
    private Animation animation;
    private Random random;

    public Attack(Source source, Animation animation) {
        this.source = source;
        this.animation = animation;
        this.random = new Random();
        attack = Initializer.getInstance().getSound(Initializer.ZM_ATTACK);
    }

    @Override
    public void enter() {
        damage();
        source.play(attack.getBufferId());
    }

    @Override
    public void update() {
        if(!animation.isPlaying()) {
            source.play(attack.getBufferId());
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
