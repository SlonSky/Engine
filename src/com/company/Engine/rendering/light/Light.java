package com.company.Engine.rendering.light;

import com.company.Engine.rendering.Shader;
import com.company.Engine.util.Vector3f;

/**
 * Created by Slon on 13.02.2016.
 */
public abstract class Light {
    private Vector3f color;
    private float intensity;
    private Shader shader;

    public Light(Vector3f color, float intensity) {
        this.color = color;
        this.intensity = intensity;
    }

    public void bindShader(){
        shader.bind();
    }

// todo ?????


    public Vector3f getColor() {
        return color;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }

    public float getIntensity() {
        return intensity;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }

    public Shader getShader() {
        return shader;
    }

    public void setShader(Shader shader) {
        this.shader = shader;
    }
}
