package Game.enemy;

import Engine.audio.Source;
import Engine.rendering.animation.Animation;

import java.util.Random;

/**
 * Created by Slon on 26.05.2016.
 */
public class Idle implements EnemyState{

    //  store here animation too;
    private Source source;
    private Animation animation;
    private Random random;

    public Idle(Source source, Animation animation) {
        this.source = source;
        this.animation = animation;
        this.random = new Random();
    }

    @Override
    public void enter() {
    }

    @Override
    public void update() {
        if(!animation.isPlaying()) {
            animation.play();
        }
    }

    @Override
    public Animation getAnimation() {
        return animation;
    }

    @Override
    public void exit() {

    }
}
