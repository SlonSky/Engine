package Game.player;

import Engine.rendering.Transform;
import Engine.util.Vector3f;

/**
 * Created by Slon on 22.05.2016.
 */
public interface Controllable {
    boolean isMoving();
    boolean isShooting();
    boolean isReloading();
    void setReloading(boolean reloading);

    Transform getTransform();
    Vector3f getLookAt();
    Vector3f getLeft();
}
