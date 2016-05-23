package Game.entities;

import Game.GameObject;
import Engine.core.Input;
import Engine.rendering.Camera;
import Engine.rendering.Transform;
import Game.FreeLook;
import Game.FreeMove;

/**
 * Created by Slon on 03.04.2016.
 */
public class Director extends GameObject {

    private Camera camera;

    public Director(Camera camera, Transform transform) {
        super(transform);
        this.camera = camera;
        addComponent(new FreeLook(0, Input.KEY_ESCAPE, 0.07f));
        addComponent(new FreeMove(Input.KEY_W, Input.KEY_S, Input.KEY_A, Input.KEY_D, 1));
    }

    @Override
    public void update() {
        super.update();
        camera.setPos(getTransform().getPosition());
        camera.setRot(getTransform().getRotation());
    }
}
