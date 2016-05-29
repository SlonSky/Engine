package Game.player;

import Engine.audio.Sound;
import Engine.audio.Source;
import Engine.core.Time;
import Engine.util.Quaternion;
import Engine.util.Vector3f;

/**
 * Created by Slon on 22.05.2016.
 */
public class Dying implements PlayerState {

    private static final float FALL_SPEED = 3;


    private Sound sound;

    private Source source;
    private Controllable controllable;

    public Dying(Source source, Controllable controllable) {
        this.source = source;
        this.controllable = controllable;
    }

    @Override
    public void enter() {

    }

    @Override
    public void update() {
        controllable.getTransform().setRotation(controllable.getTransform().getRotation().nlerp(Player.LYING_ROT, (float) Time.getDelta() * FALL_SPEED, true));
    }

    @Override
    public void exit() {

    }
}
