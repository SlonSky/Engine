package Game.gun;

import Engine.audio.Sound;
import Game.player.*;

/**
 * Created by Slon on 22.05.2016.
 */
public class Reloading implements GunState {

    // todo: you know...
    private static Sound reload = new Sound("res/sound/reload.ogg");

    private Audible audible;
    private Animable animable;
    private Controllable controllable;
    private Equipment equipment;

    public Reloading(Audible audible, Animable animable, Controllable controllable, Equipment equipment) {
        this.audible = audible;
        this.animable = animable;
        this.controllable = controllable;
        this.equipment = equipment;
    }

    @Override
    public void enter() {
        audible.getSource().setLooping(false);
        audible.getSource().stop();
        playAnimation();
        sound();
    }

    @Override
    public void update() {
        if(!animable.getAnimation().isPlaying()){
            controllable.setReloading(false);
            chargeMagazine();
        }
    }

    @Override
    public void exit() {
        animable.getAnimation().stop();
    }

    public void chargeMagazine(){
        int bulletsNeed = equipment.getMagazineCapacity() - equipment.getBulletsInMagazine();
        int bullets = equipment.getBulletsAmount()-bulletsNeed;
        equipment.setBulletsInMagazine(bullets > 0 ? bulletsNeed : equipment.getBulletsAmount());
        equipment.setBulletsAmount(bullets > 0 ? bullets : 0);
    }

    private void playAnimation(){
        animable.getAnimation().play();
    }

    private void sound(){
        audible.getSource().play(reload.getBufferId(), true);
    }
}
