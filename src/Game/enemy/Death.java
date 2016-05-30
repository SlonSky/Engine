package Game.enemy;

import Engine.audio.Sound;
import Engine.audio.Source;
import Engine.core.Time;
import Engine.rendering.animation.Animation;
import Game.Initializer;

/**
 * Created by Slon on 26.05.2016.
 */
public class Death implements EnemyState {

    private Sound deathScream;

    private Source source;
    private Animation animation;

    public Death(Source source, Animation animation) {
        this.source = source;
        this.animation = animation;
        deathScream = Initializer.getInstance().getSound(Initializer.ZM_DYING);
    }

    @Override
    public void enter() {
        source.play(deathScream.getBufferId());
        animation.play();
    }

    @Override
    public void update() {
    }

    @Override
    public void exit() {

    }

    @Override
    public Animation getAnimation() {
        return animation;
    }
}
