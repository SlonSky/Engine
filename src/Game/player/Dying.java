package Game.player;

import Engine.audio.Sound;
import Engine.audio.Source;
import Engine.core.Time;
import Engine.util.Quaternion;
import Engine.util.Vector3f;
import Game.Initializer;

/**
 * Created by Slon on 22.05.2016.
 */
public class Dying implements PlayerState {

    private static final float FALL_SPEED = 3;


    private Sound dyingScream;

    private Source source;
    private Controllable controllable;

    public Dying(Source source, Controllable controllable) {
        this.source = source;
        this.controllable = controllable;
        dyingScream = Initializer.getInstance().getSound(Initializer.PLAYER_DYING);
    }

    @Override
    public void enter() {
        source.play(dyingScream.getBufferId());
    }

    @Override
    public void update() {
        controllable.getTransform().setRotation(controllable.getTransform().getRotation().nlerp(Player.LYING_ROT, (float) Time.getDelta() * FALL_SPEED, true));
    }

    @Override
    public void exit() {

    }
}
