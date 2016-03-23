package com.company.Game.objects;

import com.company.Engine.core.Input;
import com.company.Engine.physics.Collider;
import com.company.Engine.rendering.*;
import com.company.Engine.util.Vector3f;
import com.company.Game.components.FrustumCulling;
import com.company.Game.components.Graphic;
import com.company.Game.components.LookControl;
import com.company.Game.components.MoveControl;

/**
 * Created by Slon on 21.03.2016.
 */
public class Player extends GameObject{
    private Camera camera;

    private Graphic graphic;
    private FrustumCulling culling;
    private Collider collider;

    public Player(Camera camera, Transform transform, Graphic graphic, Vector3f cullingSize, Vector3f collideSize) {
        super(transform);

        this.camera = camera;
        this.graphic = graphic;
        this.culling = new FrustumCulling(new Box(cullingSize, transform));
        this.collider = new Collider(collideSize);

        addComponent(graphic);
        addComponent(culling);
        addComponent(collider);
        addComponent(new MoveControl(Input.KEY_W, Input.KEY_S, Input.KEY_A, Input.KEY_D, 10));
        addComponent(new LookControl(0, Input.KEY_ESCAPE,  0.5f));
    }

    public void update(){
        super.update();
        collider.solveCollision();
        handleCamera();
    }

    public void render(Shader shader, RenderingEngine renderingEngine){
        if(culling.inFrustum(renderingEngine.getFrustum())){
            graphic.render(shader, renderingEngine);
        }
    }

    private void handleCamera(){
        camera.setPos(getTransform().getPosition().sub(new Vector3f(0, -2, 3).rotate(getTransform().getRotation())));
        camera.setRot(getTransform().getRotation());
    }


}
