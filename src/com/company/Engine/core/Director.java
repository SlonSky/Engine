package com.company.Engine.core;

import com.company.Engine.core.GameObject;
import com.company.Engine.core.Input;
import com.company.Engine.rendering.Camera;
import com.company.Engine.rendering.Transform;
import com.company.Engine.core.FreeLook;
import com.company.Engine.core.FreeMove;

/**
 * Created by Slon on 03.04.2016.
 */
public class Director extends GameObject {

    private Camera camera;

    public Director(Camera camera, Transform transform) {
        super(transform);
        this.camera = camera;
        addComponent(new FreeLook(0, Input.KEY_ESCAPE, 0.5f));
        addComponent(new FreeMove(Input.KEY_W, Input.KEY_S, Input.KEY_A, Input.KEY_D, 10));
    }

    @Override
    public void update() {
        super.update();
        camera.setPos(getTransform().getPosition());
        camera.setRot(getTransform().getRotation());
    }
}
