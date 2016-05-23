package Game.player;

import Editor.LevelEditor.SyncEditor;
import Engine.ai.AIMove;
import Engine.audio.Source;
import Engine.core.*;
import Engine.physics.Collider;
import Engine.rendering.*;
import Engine.rendering.animation.Animation;
import Engine.util.Vector3f;
import Game.GameObject;
import Game.KeyBoardControl;
import Game.MouseControl;
import Game.gun.Gun;

/**
 * Created by Slon on 21.03.2016.
 */
public class Player extends GameObject implements Audible, Controllable, Livable{

    private Camera camera;
    private Gun gun;

    private Collider collider;
    private KeyBoardControl keyBoardControl;
    private MouseControl mouseControl;

    private Source audio;

    private PlayerState state;
    private PlayerState walking;
    private PlayerState standing;
    private PlayerState dying;

    private int health;
    private int currentHealth;


    // temp
    private GraphicBound collideBound;


    // todo: update objects
    public Player(Camera camera, Transform transform, Animation hands, Collider collider) {
        super(transform);

        this.camera = camera;
        this.collider = collider;

        // todo: read from settings.ini
        this.keyBoardControl = new KeyBoardControl(Input.KEY_W, Input.KEY_S, Input.KEY_A, Input.KEY_D, Input.KEY_SPACE, 10);
        this.mouseControl = new MouseControl(0, 0.5f);

        this.audio = new Source();

        addComponent(collider);
        addComponent(keyBoardControl);
        addComponent(mouseControl);


        health = 100;
        currentHealth = health;

        gun = new Gun(getTransform(), hands, this);

        walking = new Walking(this, gun, this);
        standing = new Standing(this, gun);
        dying = new Dying();
        state = standing;

        // temp
        collideBound = new GraphicBound(collider.getSize(), collider.getOffset(), new Vector3f(1, 0, 0), true);
        addComponent(collideBound);
    }

    @Override
    public void input() {
        super.input();
    }

    private void updateGun(){
        gun.getTransform().setPosition(getTransform().getPosition());
        gun.getTransform().setRotation(getTransform().getRotation());
        gun.update();
    }

    public void update(){

        if(isMoving()){
            changeState(walking);
        } else {
            changeState(standing);
        }
        state.update();
        move();
        super.update();
        updateGun();
        handleCamera();

        AIMove.moveTo = getTransform().getPosition();

    }
    public void render(Shader shader, RenderingEngine renderingEngine){

            gun.render(shader, renderingEngine);
            // temp
            collideBound.render(shader, renderingEngine);
    }

    private void changeState(PlayerState newState){
        if(state != newState) {
            state.exit();
            state = newState;
            newState.enter();
        }
    }

    // todo: movable?
    private void move(){

        // gravity
        keyBoardControl.setTranslation(keyBoardControl.getTranslation().sub(new Vector3f(0, (float) Time.getDelta() * 8, 0)));

        // moving
        keyBoardControl.setTranslation(collider.solveCollision(keyBoardControl.getTranslation()));

        // todo: improve by floor plane collider, then clear
        // floor check
        if(getTransform().getPosition().getY() < 1){
            getTransform().setPosition(new Vector3f(getTransform().getPosition().getX(), 1, getTransform().getPosition().getZ()));
        }
        ////
    }

    private void handleCamera(){

        // abc temp!
        camera.setPos(getTransform().getPosition()
                .sub(new Vector3f((float) SyncEditor.a / 100f, (float) SyncEditor.b / 100f, (float) SyncEditor.c / 100f))
        );
        camera.setRot(getTransform().getRotation());
    }

    @Override
    public Source getSource() {
        return audio;
    }

    @Override
    public boolean isMoving() {
        return keyBoardControl.isMoving();
    }

    @Override
    public boolean isShooting() {
        return mouseControl.isShooting();
    }

    @Override
    public boolean isReloading() {
        return keyBoardControl.isReloading();
    }

    @Override
    public boolean isOnLand() {
        return false;
    }

    @Override
    public void setShooting(boolean shooting) {
    }

    @Override
    public void setReloading(boolean reloading) {
        keyBoardControl.setReloading(reloading);
    }

    @Override
    public Vector3f getLookAt() {
        return camera.getForward();
    }

    @Override
    public Vector3f getLeft() {
        return camera.getLeft();
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getCurrentHealth() {
        return currentHealth;
    }

    @Override
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }
}
