package Game.gun;

import Engine.audio.Sound;
import Engine.audio.Source;
import Engine.core.Time;
import Engine.rendering.animation.Animation;
import Engine.util.Quaternion;
import Engine.util.Vector3f;
import Game.entities.CombatManager;
import Game.player.*;

import java.util.Random;

/**
 * Created by Slon on 22.05.2016.
 */

public class Shooting implements GunState {

    private static final double SHOT_PERIOD = Math.PI * 20;
    private static final float RECOIL_ANGLE = 2;
    private static final float RECOIL_OFFSET = -0.01f;
    private static final float SHOT_TIME = 0.1f;

    private static final int DAMAGE = 15;
    private static final int MAX_ABERRATION = 6;

    // todo: init sounds from loader
    private static Sound shooting = new Sound("res/sound/fire.wav");
    private static Sound shootingEnded = new Sound("res/sound/fired.wav");
    private static Sound dryFire = new Sound("res/sound/dryfire.wav");

    private Source source;
    private Animation animation;
    private Controllable controllable;
    private Equipment equipment;

    private Random random;

    private float increment;
    private float time;

    public Shooting(Source source, Animation animation, Controllable controllable, Equipment equipment) {
        this.source = source;
        this.animation = animation;
        this.controllable = controllable;
        this.equipment = equipment;
        random = new Random();
    }

    @Override
    public void enter() {
        source.stop();
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
        animation.setOffset(new Vector3f(0, 0, 0));
        animation.setRotationOffset(new Quaternion(0, 0, 0, 1));

        if(equipment.getBulletsInMagazine() == 0){
            return;
        }

        source.setLooping(false);
        source.play(shootingEnded.getBufferId());

        if(time != 0){
            equipment.setBulletsInMagazine(equipment.getBulletsInMagazine() - 1);
        }
//        controllable.setShooting(false);

    }

    private void recoil(){
        if(equipment.getBulletsInMagazine() == 0) {
            return;
        }
        increment += Time.getDelta();
        // offset
        float kickout = (float) (Math.sin(increment * SHOT_PERIOD));
        animation.setOffset(controllable.getLookAt().add(kickout).normalized().mul(RECOIL_OFFSET));

        // rot
        animation.setRotationOffset(new Quaternion(controllable.getLeft(),
                (float) Math.toRadians(RECOIL_ANGLE * (Math.abs(Math.sin(increment * SHOT_PERIOD)) - 1) / 2)));
    }

    private void sound(){
            if(equipment.getBulletsInMagazine() != 0) {
                source.setLooping(true);
                source.play(shooting.getBufferId(), true);
            } else {
                source.setLooping(false);
                source.play(dryFire.getBufferId(), true);
            }
    }

    private void fireBullet(){
        damage();
        if(equipment.getBulletsInMagazine() != 0) {
            time += Time.getDelta();
            if (time >= SHOT_TIME) {
                equipment.setBulletsInMagazine(equipment.getBulletsInMagazine() - 1);
                time = 0;
            }
        } else {
            time = 0;
        }
//        System.out.println(equipment.getBulletsInMagazine());
    }

    private void damage(){
        CombatManager.getInstance().hitOpponent(DAMAGE + random.nextInt(MAX_ABERRATION * 2) - MAX_ABERRATION, equipment.getShotRay());
    }
}
