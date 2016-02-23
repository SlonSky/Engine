package com.company.Engine.rendering.light;

import com.company.Engine.util.Vector3f;

/**
 * Created by Slon on 15.02.2016.
 */
public class PointLight extends Light {
    private static final float COLOR_DEPTH = 256;

    private Vector3f position;
    private Attenuation attenuation;
    float range;

    public PointLight(Vector3f position, Vector3f color, float intensity, Attenuation attenuation) {
        super(color, intensity);
        setShader(PointShader.getInstance());

        this.position = position;
        this.attenuation = attenuation;

        float exp = attenuation.getExponent();
        float lin = attenuation.getLinear();
        float cons = attenuation.getConstant() - COLOR_DEPTH * getIntensity() * getColor().max();
        this.range = (float)(-lin + Math.sqrt(lin * lin - 4 * exp * cons) / (2 * exp));
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Attenuation getAttenuation() {
        return attenuation;
    }

    public void setAttenuation(Attenuation attenuation) {
        this.attenuation = attenuation;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }
}
