package com.company.forEngine.rendering;

import com.company.forEngine.rendering.light.DirectionalLight;
import com.company.forEngine.util.Vector3f;

/**
 * Created by Slon on 13.02.2016.
 */
public class LightShader extends Shader {

    private Vector3f ambient;
    private DirectionalLight directionalLight;

    public LightShader(){
        super();
        addVertexShader("lightVertex.txt");
        addFragmentShader("lightFragment.txt");
        compileShader();

        addUniform("color");

        addUniform("gWVP");
        addUniform("gWorld");

        addUniform("ambient");
        addUniform("gDirectionalLight.light.color");
        addUniform("gDirectionalLight.light.intensity");
        addUniform("gDirectionalLight.direction");

        addUniform("eyePosition");
        addUniform("specularIntensity");
        addUniform("specularPower");

        ambient = new Vector3f(0.5f, 0.5f, 0.5f);
        directionalLight = new DirectionalLight(new Vector3f(1,1,1), 1, new Vector3f(0, -10, 10));
    }

    @Override
    public void updateUniforms(Transform transformation, Camera camera, Material material) {
        material.getTexture().bind();
        setUniform("color", material.getColor());
        setUniform("gWVP", transformation.getProjectedTransformation());
        setUniform("gWorld", transformation.getTransformation());
        setUniform("ambient", ambient);
        setUniform("gDirectionalLight.light.color", directionalLight.getColor());
        setUniform("gDirectionalLight.light.intensity", directionalLight.getIntensity());
        setUniform("gDirectionalLight.direction", directionalLight.getDirection());
        setUniform("eyePosition", camera.getPos());
        setUniform("specularIntensity", material.getSpecularIntensity());
        setUniform("specularPower", material.getSpecularPower());
    }
}
