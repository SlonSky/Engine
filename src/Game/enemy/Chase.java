package Game.enemy;

import Engine.audio.Sound;
import Engine.audio.Source;
import Engine.rendering.animation.Animation;
import Game.Initializer;

/**
 * Created by Slon on 26.05.2016.
 */
public class Chase implements EnemyState {


    private Sound roar;

    private Source source;
    private Animation animation;

    private Movable movable;

    public Chase(Source source, Animation animation, Movable movable) {
        this.source = source;
        this.animation = animation;
        this.movable = movable;
        roar = Initializer.getInstance().getSound(Initializer.ZM_ROAR);
    }

    @Override
    public void enter() {

    }

    @Override
    public void update() {
        if(!animation.isPlaying()) {
            source.play(roar.getBufferId());
            animation.play();
        }
        movable.move();
    }

    @Override
    public void exit() {

    }

    @Override
    public Animation getAnimation() {
        return animation;
    }
}
