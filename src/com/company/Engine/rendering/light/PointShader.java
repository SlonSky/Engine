package com.company.Engine.rendering.light;

import com.company.Engine.rendering.meshManagment.Material;
import com.company.Engine.rendering.RenderingEngine;
import com.company.Engine.rendering.Shader;
import com.company.Engine.rendering.Transform;

/**
 * Created by Slon on 15.02.2016.
 */
public class PointShader extends Shader {
    public static final PointShader instance = new PointShader();

    private PointShader(){
        super();
        addVertexShader("pointVertex.txt");
        addFragmentShader("pointFragment.txt");
        compileShader();

        addUniform("WVP");
        addUniform("world");

        addUniform("pointLight.light.color");
        addUniform("pointLight.light.intensity");

        addUniform("pointLight.attenuation.constant");
        addUniform("pointLight.attenuation.linear");
        addUniform("pointLight.attenuation.exponent");

        addUniform("pointLight.position");
        addUniform("pointLight.range");

        addUniform("eyePosition");
        addUniform("specularIntensity");
        addUniform("specularPower");
    }

    @Override
    public void updateUniforms(Transform transformation, Material material, RenderingEngine renderingEngine) {
        prepareTexture(material);
        setUniform("WVP", transformation.getProjectedTransformation());
        setUniform("world", transformation.getTransformation());

        setUniform("pointLight", (PointLight)renderingEngine.getActiveLight());

        setUniform("eyePosition", renderingEngine.getMainCamera().getPos());
        setUniform("specularIntensity", material.getSpecularIntensity());
        setUniform("specularPower", material.getSpecularPower());
    }

    public static PointShader getInstance() {
        return instance;
    }
}
