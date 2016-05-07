package com.company.Game.entities;

import com.company.Editor.LevelEditor.SyncEditor;
import com.company.Engine.ai.AIMove;
import com.company.Engine.audio.AudioEngine;
import com.company.Engine.audio.Sound;
import com.company.Engine.audio.Source;
import com.company.Engine.core.*;
import com.company.Engine.physics.Collider;
import com.company.Engine.rendering.*;
import com.company.Engine.util.Vector3f;
import org.lwjgl.Sys;

/**
 * Created by Slon on 21.03.2016.
 */
public class Player extends GameObject {

    private Camera camera;

    private Graphic graphic;
    private Collider collider;
    private MoveControl moveControl;
    private LookControl lookControl;

    // todo: better state machine

    private Source audio;
    private Sound jump;
    private Sound die;


    // temp
    private GraphicBound collideBound;

    public Player(Camera camera, Transform transform, Graphic graphic, Vector3f collideSize, Vector3f colliderOffset) {
        super(transform);

        this.camera = camera;
        this.graphic = graphic;
        this.collider = new Collider(collideSize, colliderOffset);
        this.moveControl = new MoveControl(Input.KEY_W, Input.KEY_S, Input.KEY_A, Input.KEY_D, Input.KEY_SPACE, 10);
        this.lookControl = new LookControl(0, Input.KEY_ESCAPE,  0.5f);

        this.audio = new Source();
        this.jump = new Sound();
        this.die = new Sound();

        addComponent(graphic);
        addComponent(collider);
        addComponent(moveControl);
        addComponent(lookControl);

        // temp
        collideBound = new GraphicBound(collideSize.add(0.0f),colliderOffset, new Vector3f(1, 0, 0), true);
        addComponent(collideBound);

    }
    public Player(Camera camera, Transform transform, Graphic graphic,  Vector3f colliderOffset) {
        this(camera, transform, graphic, colliderOffset, new Vector3f(0,0,0));

    }
    @Override
    public void input() {
        super.input();

    }

    // todo: player actions functions!
    public void update(){

        move();
        super.update();
        handleCamera();
        sound();
        aiControl(); // todo: player observer!

    }
    public void render(Shader shader, RenderingEngine renderingEngine){
            graphic.render(shader, renderingEngine);

            // temp
            collideBound.render(shader, renderingEngine);
    }

    private void move(){
        /**
         * MOVEMENT
         */

        // gravity
        moveControl.setTranslation(moveControl.getTranslation().sub(new Vector3f(0, (float) Time.getDelta() * 8, 0)));
        moveControl.setTranslation(collider.solveCollision(moveControl.getTranslation()));
        if(getTransform().getPosition().getY() < 1){
            getTransform().setPosition(new Vector3f(getTransform().getPosition().getX(), 1, getTransform().getPosition().getZ()));
        }
        handleCamera();
    }

    private void sound(){
        AudioEngine.setListenerData(getTransform().getPosition());
        //temp!
        if(moveControl.isJump()){
            audio.play(jump.getBufferId(), true);
        }
    }

    private void aiControl(){
        AIMove.moveTo = getTransform().getPosition();
    }

    private void handleCamera(){

        // abc temp!
        camera.setPos(getTransform().getPosition()
                .sub(new Vector3f((float) SyncEditor.a / 10f,(float) SyncEditor.b / 10f, (float) SyncEditor.c / 10f))
//                        .rotate(getTransform().getRotation()
//                                        .mul(new Quaternion(camera.getLeft(), (float) Math.toRadians((float) SyncEditor.x / 10f)))
//                                        .mul(new Quaternion(camera.getUp(), (float) Math.toRadians((float) SyncEditor.y / 10f)))
//                                        .mul(new Quaternion(camera.getForward(), (float) Math.toRadians((float) SyncEditor.z / 10f)))
                        );
        camera.setRot(
                getTransform().getRotation()
//                        .mul(new Quaternion(camera.getLeft(), (float) Math.toRadians((float) SyncEditor.x / 10f)))
//                        .mul(new Quaternion(camera.getUp(), (float) Math.toRadians((float) SyncEditor.y / 10f)))
//                        .mul(new Quaternion(camera.getForward(), (float) Math.toRadians((float) SyncEditor.z / 10f)))
        );
    }

    public void setJump(Sound jump) {
        this.jump = jump;
    }

    public void setDie(Sound die) {
        this.die = die;
    }
}
