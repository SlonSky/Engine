package Game.gun;

import Engine.audio.Source;
import Engine.util.Quaternion;
import Engine.util.Vector3f;
import Game.GameObject;
import Engine.rendering.Transform;
import Engine.rendering.animation.Animation;
import Game.player.Controllable;

/**
 * Created by Slon on 19.04.2016.
 */
public class Gun extends GameObject implements Equipment {

    private static final float SHOT_DISTANCE = 20;

    private Controllable controllable;

    private Ray shotRay;

    private Animation hands;
    private Source audio;

    private GunState state;
    private GunState idling;
    private GunState shooting;
    private GunState reloading;

    // todo: init from loader
    private int capacity = 300;
    private int bulletsInMagazine = 300;
    private int bulletsAmount = 900;

    public Gun(Transform transform, Animation animation, Controllable control) {
        super(transform);

        hands = animation;
        audio = new Source();

        controllable = control;

        addComponent(hands);
        addComponent(audio);

        idling = new Idling();
        shooting = new Shooting(audio, animation, controllable, this);
        reloading = new Reloading(audio, animation, controllable, this);
        state = idling;

        shotRay = new Ray(new Vector3f(0,0,0), new Vector3f(0,0,0));
    }

    @Override
    public void update() {
        super.update();
        if(controllable.isShooting() && !controllable.isReloading()){
            changeState(shooting);
            makeShotRay();
        } else if(controllable.isReloading()){
            changeState(reloading);
        } else {
            changeState(idling);
        }
        state.update();
    }

    private void changeState(GunState newState){
        if(state == newState){
            return;
        }
        state.exit();
        state = newState;
        state.enter();
    }

    private void makeShotRay(){
        shotRay.start = getTransform().getPosition();
        shotRay.end = shotRay.start.add(controllable.getLookAt().mul(SHOT_DISTANCE));
    }

    @Override
    public int getMagazineCapacity() {
        return capacity;
    }

    @Override
    public int getBulletsInMagazine() {
        return bulletsInMagazine;
    }

    @Override
    public int getBulletsAmount() {
        return bulletsAmount;
    }

    @Override
    public void setBulletsAmount(int bulletsAmount) {
        this.bulletsAmount = bulletsAmount;
    }

    @Override
    public Ray getShotRay() {
        return shotRay;
    }

    @Override
    public void setBulletsInMagazine(int amount) {
        bulletsInMagazine = amount;
    }
}
