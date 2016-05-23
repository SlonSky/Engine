package Game.gun;

import Engine.audio.Sound;
import Engine.core.Time;
import Engine.util.Quaternion;
import Engine.util.Vector3f;
import Game.player.*;

/**
 * Created by Slon on 22.05.2016.
 */

// todo: click-click on empty magazine - Gunnable interface for bullets/damage/direction(ray)
public class Shooting implements GunState {

    private static final double SHOT_PERIOD = Math.PI * 20;
    private static final float RECOIL_ANGLE = 2;
    private static final float RECOIL_OFFSET = -0.01f;
    private static final float SHOT_TIME = 0.1f;

    // todo: init sounds from loader
    private static Sound shooting = new Sound("res/sound/fire.wav");
    private static Sound shootingEnded = new Sound("res/sound/fired.wav");
    private static Sound dryFire = new Sound("res/sound/dryfire.wav");

    private Audible audible;
    private Animable animable;
    private Controllable controllable;
    private Equipment equipment;

    private float increment;
    private float time;

    public Shooting(Audible audible, Animable animable, Controllable controllable, Equipment equipment) {
        this.audible = audible;
        this.animable = animable;
        this.controllable = controllable;
        this.equipment = equipment;
    }

    @Override
    public void enter() {
        audible.getSource().stop();
        increment = 0;
        time = 0;
    }

    @Override
    public void update() {
        sound();
        recoil();
        fireBullet();
    }

    @Override
    public void exit() {
        animable.getAnimation().setOffset(new Vector3f(0,0,0));
        animable.getAnimation().setRotationOffset(new Quaternion(0, 0, 0, 1));

        if(equipment.getBulletsInMagazine() + equipment.getBulletsAmount() == 0){
            return;
        }
        audible.getSource().setLooping(false);
        audible.getSource().play(shootingEnded.getBufferId());

        if(time != 0){
            equipment.setBulletsInMagazine(equipment.getBulletsInMagazine() - 1);
        }
        controllable.setShooting(false);

    }

    private void recoil(){
        if(equipment.getBulletsInMagazine() == 0) {
            return;
        }
        increment += Time.getDelta();
        // offset
        float kickout = (float) (Math.sin(increment * SHOT_PERIOD));
        animable.getAnimation().setOffset(controllable.getLookAt().add(kickout).normalized().mul(RECOIL_OFFSET));

        // rot
        animable.getAnimation().setRotationOffset(new Quaternion(controllable.getLeft(),
                (float) Math.toRadians(RECOIL_ANGLE * (Math.abs(Math.sin(increment * SHOT_PERIOD)) - 1) / 2)));
    }

    private void sound(){
            if(equipment.getBulletsInMagazine() != 0) {
                audible.getSource().setLooping(true);
                audible.getSource().play(shooting.getBufferId(), true);
            } else {
                audible.getSource().setLooping(false);
                audible.getSource().play(dryFire.getBufferId(), true);
            }
    }

    private void fireBullet(){
        if(equipment.getBulletsInMagazine() != 0) {
            time += Time.getDelta();
            if (time >= SHOT_TIME) {
                equipment.setBulletsInMagazine(equipment.getBulletsInMagazine() - 1);
                time = 0;
            }
        } else {
            time = 0;
            if(equipment.getBulletsAmount() > 0) {
                controllable.setReloading(true);
            }
        }
        System.out.println(equipment.getBulletsInMagazine());
    }
}
