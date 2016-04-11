package com.company.Game.objects;

import com.company.Editor.LevelEditor.EditorWindow;
import com.company.Engine.core.Input;
import com.company.Engine.physics.Collider;
import com.company.Engine.rendering.*;
import com.company.Engine.util.Vector3f;
import com.company.Game.components.*;

/**
 * Created by Slon on 21.03.2016.
 */
public class Player extends GameObject{
    private Camera camera;

    private Graphic graphic;
    private Collider collider;
    private MoveControl moveControl;


    // temp
    private GraphicBound collideBound;

    public Player(Camera camera, Transform transform, Graphic graphic, Vector3f collideSize) {
        super(transform);

        this.camera = camera;
        this.graphic = graphic;
        this.collider = new Collider(collideSize);
        this.moveControl = new MoveControl(Input.KEY_W, Input.KEY_S, Input.KEY_A, Input.KEY_D, 10);

        addComponent(graphic);
        addComponent(collider);
        addComponent(moveControl);
        addComponent(new LookControl(0, Input.KEY_ESCAPE,  0.5f));

        // temp
        collideBound = new GraphicBound(collideSize.add(0.0f), new Vector3f(1, 0, 0), true);
        addComponent(collideBound);
    }

    public void update(){

//        moveControl.setTranslation(moveControl.getTranslation().sub(new Vector3f(0, (float)Time.getDelta()*10, 0)));
        moveControl.setTranslation(collider.solveCollision(moveControl.getTranslation()));
        super.update();

        // gravity
//        collider.solveCollision();

//        moveControl.move(collider.solveCollision(moveControl.getMoveVector()));
        handleCamera();


    }

    public void render(Shader shader, RenderingEngine renderingEngine){
            graphic.render(shader, renderingEngine);

            // temp
            collideBound.render(shader, renderingEngine);
    }

    private void handleCamera(){

        // abc temp!
        camera.setPos(getTransform().getPosition()
                .sub(new Vector3f((float)EditorWindow.a / 10f,(float) EditorWindow.b / 10f, (float)EditorWindow.c / 10f)
                        .rotate(getTransform().getRotation()
//                                        .mul(new Quaternion(camera.getLeft(), (float) Math.toRadians((float) EditorWindow.x / 10f)))
//                                        .mul(new Quaternion(camera.getUp(), (float) Math.toRadians((float) EditorWindow.y / 10f)))
//                                        .mul(new Quaternion(camera.getForward(), (float) Math.toRadians((float) EditorWindow.z / 10f)))
                        )));
        camera.setRot(
                getTransform().getRotation()
//                        .mul(new Quaternion(camera.getLeft(), (float) Math.toRadians((float) EditorWindow.x / 10f)))
//                        .mul(new Quaternion(camera.getUp(), (float) Math.toRadians((float) EditorWindow.y / 10f)))
//                        .mul(new Quaternion(camera.getForward(), (float) Math.toRadians((float) EditorWindow.z / 10f)))
        );


    }


}
