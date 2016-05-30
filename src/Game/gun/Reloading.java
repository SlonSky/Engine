package Game.gun;

import Engine.audio.Sound;
import Engine.audio.Source;
import Engine.rendering.animation.Animation;
import Game.Initializer;
import Game.player.*;

/**
 * Created by Slon on 22.05.2016.
 */
public class Reloading implements GunState {

    private Sound reload;

    private Source source;
    private Animation animation;

    private Controllable controllable;
    private Equipment equipment;

    public Reloading(Source source, Animation animation, Controllable controllable, Equipment equipment) {
        this.source = source;
        this.animation = animation;
        this.controllable = controllable;
        this.equipment = equipment;
        reload = Initializer.getInstance().getSound(Initializer.GUN_RELOAD);
    }

    @Override
    public void enter() {
        source.setLooping(false);
        source.stop();
        playAnimation();
        sound();
    }

    @Override
    public void update() {
        if(!animation.isPlaying()){
            chargeMagazine();
            controllable.setReloading(false);
        }
    }

    @Override
    public void exit() {
        animation.stop();
    }

    public void chargeMagazine(){
        int bulletsNeed = equipment.getMagazineCapacity() - equipment.getBulletsInMagazine();
        int bulletsRemain = equipment.getBulletsAmount()-bulletsNeed;
        equipment.setBulletsInMagazine(bulletsRemain > 0 ? bulletsNeed : equipment.getBulletsAmount());
        equipment.setBulletsAmount(bulletsRemain > 0 ? bulletsRemain : 0);
    }

    private void playAnimation(){
        animation.play();
    }

    private void sound() {
        source.play(reload.getBufferId(), true);
    }
}
