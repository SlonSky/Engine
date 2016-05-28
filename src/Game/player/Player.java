package Game.player;

import Editor.LevelEditor.SyncEditor;
import Engine.rendering.meshManagment.Texture;
import Engine.rendering.particles.Particle;
import Engine.rendering.particles.ParticleMaster;
import Engine.rendering.particles.ParticleSystem;
import Engine.rendering.particles.ParticleTexture;
import Engine.util.Quaternion;
import Game.enemy.AIMove;
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
public class Player extends GameObject implements Controllable, Livable{
    public static final float GRAVITY = 10;
    private static final float FLOOR_LEVEL = 1;


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

    private Vector3f velocity;

    ParticleTexture r = new ParticleTexture(new Texture("particleAtlas.png"), 4);
    ParticleTexture fire = new ParticleTexture(new Texture("fire.png"), 8);

    private ParticleSystem f = new ParticleSystem(fire,10, 0.0001f, -0.001f, 1f);
    private ParticleSystem particleSystem = new ParticleSystem(r,30, 0.01f,0.001f, 4f);


    // temp
    private GraphicBound collideBound;



    // todo: load from file:
    // public Player(Cam..){
    //     Animation hands = Initializer.get("hands");
    //     ...
    // todo: Initializer first reads file, then stores it in hashmap : [name][values] - like in file
    public Player(Camera camera, Transform transform, Animation hands, Collider collider) {
        super(transform);

        velocity = new Vector3f(0,0,0);

        this.camera = camera;
        this.collider = collider;

        // todo: read from settings.ini
        this.keyBoardControl = new KeyBoardControl(Input.KEY_W, Input.KEY_S, Input.KEY_A, Input.KEY_D, Input.KEY_R, Input.KEY_SPACE, 10);
        this.mouseControl = new MouseControl(0, 0.5f);

        this.audio = new Source();

        addComponent(collider);
        addComponent(keyBoardControl);
        addComponent(mouseControl);
        health = 100;
        currentHealth = health;

        gun = new Gun(getTransform(), hands, this);

        walking = new Walking(audio, hands, this);
        standing = new Standing();
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
//        System.out.println(collider.checkRayIntersection(new Vector3f(0, 3, 0), new Vector3f(0, 0, 20)));

        f.generateParticles(new Vector3f(0, 0, 5));
        if(Input.getKey(Input.KEY_Y)){


            System.out.println("h");
                    new Particle(new Transform(getTransform().getPosition().add(getTransform().getRotation().getForward().mul(1)),
                            new Quaternion(0, 0, 0, 1), new Vector3f(1, 1, 1)), fire, new Vector3f(0, 0, 0.001f), -0.001f, 1f);
        }

        particleSystem.generateParticles(new Vector3f(0,3,0));
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
        handleGravity();
        velocity = collider.solveCollision(keyBoardControl.getMovementVector());
        getTransform().setPosition(getTransform().getPosition().add(velocity));
        checkFloor();
    }

    private void handleGravity(){
        keyBoardControl.setMovementVector(keyBoardControl.getMovementVector().sub(new Vector3f(0, (float) Time.getDelta() * GRAVITY, 0)));
    }

    private void checkFloor(){
        if(getTransform().getPosition().getY() < FLOOR_LEVEL){
            getTransform().setPosition(new Vector3f(getTransform().getPosition().getX(), FLOOR_LEVEL, getTransform().getPosition().getZ()));
        }
    }

    private void handleCamera(){

        // abc temp!
        camera.setPos(getTransform().getPosition()
                .sub(new Vector3f((float) SyncEditor.a / 100f, (float) SyncEditor.b / 100f, (float) SyncEditor.c / 100f))
        );
        camera.setRot(getTransform().getRotation());
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

    @Override
    public void getDamage(int damage) {

    }
}
