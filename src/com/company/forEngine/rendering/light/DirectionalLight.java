package com.company.forEngine.rendering.light;

import com.company.forEngine.rendering.LightShader;
import com.company.forEngine.util.Vector2f;
import com.company.forEngine.util.Vector3f;

/**
 * Created by Slon on 13.02.2016.
 */
public class DirectionalLight extends Light{
    private Vector3f direction;

    public DirectionalLight(Vector3f color, float intensity, Vector3f direction) {
        super(color, intensity);
        this.direction = direction;
    }

    public Vector3f getDirection() {
        return direction;
    }

    public void setDirection(Vector3f direction) {
        this.direction = direction;
    }
}
