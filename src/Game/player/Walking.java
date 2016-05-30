package Game.player;

import Engine.audio.Sound;
import Engine.audio.Source;
import Engine.core.Time;
import Engine.rendering.animation.Animation;
import Engine.util.Vector3f;
import Game.Initializer;

import java.util.Random;

/**
 * Created by Slon on 22.05.2016.
 */
public class Walking implements PlayerState {

    private static final double WALKING_PERIOD = 2*Math.PI;
    private static final float WALKING_AMPLITUDE = 0.02f;

    private Sound step1 = Initializer.getInstance().getSound(Initializer.PLAYER_STEP1); // new Sound("res/sound/steps1.wav");
    private Sound step2 = Initializer.getInstance().getSound(Initializer.PLAYER_STEP2);//new Sound("res/sound/steps.wav");

    private Source source;
    private Animation animation;
    private Controllable controllable;

    private float increment;

    public Walking(Source source, Animation animation, Controllable controllable) {
        this.source = source;
        this.animation = animation;
        this.controllable = controllable;
    }

    @Override
    public void enter() {
        increment = 0;
        source.setLooping(true);
    }


    @Override
    public void update() {
        walk();
        sound();
    }

    private void walk(){
        increment += Time.getDelta();
        animation.setOffset(controllable.getLookAt().normalized()
                .mul(WALKING_AMPLITUDE * (float) Math.sin(increment * WALKING_PERIOD)));
    }


    private Random r = new Random();
    private void sound(){
        if(r.nextInt(2) == 0) {
            source.play(step1.getBufferId(), true);
        } else {
            source.play(step2.getBufferId(), true);
        }
    }

    @Override
    public void exit() {
        animation.setOffset(new Vector3f(0,0,0));
        source.setLooping(false);
    }
}
