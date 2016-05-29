package Game.gun;

import Engine.util.Vector3f;

/**
 * Created by Slon on 28.05.2016.
 */
public class Ray {
    public Vector3f start;
    public Vector3f end;

    public Ray(Vector3f start, Vector3f direction) {
        this.start = start;
        this.end = direction;
    }
}
