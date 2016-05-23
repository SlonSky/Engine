package Game.gun;

import Engine.audio.Source;
import Game.GameObject;
import Engine.rendering.Transform;
import Engine.rendering.animation.Animation;
import Game.player.Animable;
import Game.player.Audible;
import Game.player.Controllable;

/**
 * Created by Slon on 19.04.2016.
 */
public class Gun extends GameObject implements Audible, Animable, Equipment {

    private Controllable controllable;

    private Animation hands;
    private Source audio;

    private GunState state;
    private GunState idling;
    private GunState shooting;
    private GunState reloading;

    // todo: init from loader
    private int capacity = 30;
    private int bulletsInMagazine = 30;
    private int bulletsAmount = 90;

    public Gun(Transform transform, Animation animation, Controllable control) {
        super(transform);

        hands = animation;
        audio = new Source();

        controllable = control;

        addComponent(hands);
        addComponent(audio);

        idling = new Idling();
        shooting = new Shooting(this, this, controllable, this);
        reloading = new Reloading(this, this, controllable, this);
        state = idling;
    }

    @Override
    public void update() {
        super.update();
        if(controllable.isShooting()){
            changeState(shooting);
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

    @Override
    public Source getSource() {
        return audio;
    }

    @Override
    public Animation getAnimation() {
        return hands;
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
    public void setBulletsInMagazine(int amount) {
        bulletsInMagazine = amount;
    }
}
