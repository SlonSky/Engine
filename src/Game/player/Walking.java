package Game.player;

import Engine.audio.Sound;
import Engine.core.Time;
import Engine.util.Vector3f;

import java.util.Random;

/**
 * Created by Slon on 22.05.2016.
 */
public class Walking implements PlayerState {

    private static final double WALKING_PERIOD = 2*Math.PI;
    private static final float WALKING_AMPLITUDE = 0.02f;

    // todo: the same thing...
    private static Sound step1 = new Sound("res/sound/steps1.wav");
    private static Sound step2 = new Sound("res/sound/steps.wav");

    private Audible audible;
    private Animable animable;
    private Controllable controllable;

    private float increment;

    public Walking(Audible audible, Animable animable, Controllable controllable) {
        this.audible = audible;
        this.animable = animable;
        this.controllable = controllable;
    }

    @Override
    public void enter() {
        increment = 0;
        audible.getSource().setLooping(true);
    }


    @Override
    public void update() {
        walk();
        sound();
    }

    private void walk(){
        increment += Time.getDelta();
        animable.getAnimation().setOffset(controllable.getLookAt().normalized()
                .mul(WALKING_AMPLITUDE * (float) Math.sin(increment * WALKING_PERIOD)));
    }


    private Random r = new Random();
    private void sound(){
        if(r.nextInt(2) == 0) {
            audible.getSource().play(step1.getBufferId(), true);
        } else {
            audible.getSource().play(step2.getBufferId(), true);
        }
    }

    @Override
    public void exit() {
        animable.getAnimation().setOffset(new Vector3f(0,0,0));
        audible.getSource().setLooping(false);
    }
}
