package com.company.Engine.rendering.meshManagment;

/**
 * Created by Slon on 13.02.2016.
 */
public class Material {
    private Texture texture;
    private float specularIntensity;
    private float specularPower;

    public Material(Texture texture){
        this(texture, 8, 32);
    }

    public Material(Texture texture, float specularIntensity, float specularPower) {
        this.texture = texture;
        this.specularIntensity = specularIntensity;
        this.specularPower = specularPower;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }


    public float getSpecularIntensity() {
        return specularIntensity;
    }

    public void setSpecularIntensity(float specularIntensity) {
        this.specularIntensity = specularIntensity;
    }

    public float getSpecularPower() {
        return specularPower;
    }

    public void setSpecularPower(float specularPower) {
        this.specularPower = specularPower;
    }
}
