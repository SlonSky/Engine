package com.company.Game.objects;

import com.company.Engine.core.Input;
import com.company.Engine.rendering.Camera;
import com.company.Engine.rendering.Transform;
import com.company.Game.components.FreeLook;
import com.company.Game.components.FreeMove;
import com.company.Game.components.LookControl;
import com.company.Game.components.MoveControl;

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
