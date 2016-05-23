package Engine.rendering.light;

import Engine.util.Vector3f;

/**
 * Created by Slon on 15.02.2016.
 */
public class SpotLight extends PointLight {
    private Vector3f direction;
    private float cutoff;

    public SpotLight(Vector3f position, Vector3f direction, Vector3f color, float intensity, Attenuation attenuation, float cutoff) {
        super(position, color, intensity, attenuation);
        setShader(SpotShader.getInstance());
        this.cutoff = cutoff;
        this.direction = direction;
    }

    public Vector3f getDirection() {
        return direction;
    }

    public void setDirection(Vector3f direction) {
        this.direction = direction;
    }

    public float getCutoff() {
        return cutoff;
    }

    public void setCutoff(float cutoff) {
        this.cutoff = cutoff;
    }
}
