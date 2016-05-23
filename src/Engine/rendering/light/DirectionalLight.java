package Engine.rendering.light;

import Engine.util.Vector3f;

/**
 * Created by Slon on 13.02.2016.
 */
public class DirectionalLight extends Light{
    private Vector3f direction;

    public DirectionalLight(Vector3f color, float intensity, Vector3f direction) {
        super(color, intensity);
        setShader(DirectionalShader.getInstance());
        this.direction = direction;
    }

    public Vector3f getDirection() {
        return direction;
    }

    public void setDirection(Vector3f direction) {
        this.direction = direction;
    }
}
