package com.company.Engine.rendering.light;

import com.company.Engine.rendering.meshManagment.Material;
import com.company.Engine.rendering.RenderingEngine;
import com.company.Engine.rendering.Shader;
import com.company.Engine.rendering.Transform;

/**
 * Created by Slon on 15.02.2016.
 */
public class SpotShader extends Shader {
    public static final SpotShader instance = new SpotShader();

    private SpotShader(){
        super();
        addVertexShader("spotVertex.txt");
        addFragmentShader("spotFragment.txt");
        compileShader();

        addUniform("WVP");
        addUniform("world");

        addUniform("spotLight.pointLight.light.color");
        addUniform("spotLight.pointLight.light.intensity");
        addUniform("spotLight.pointLight.attenuation.constant");
        addUniform("spotLight.pointLight.attenuation.linear");
        addUniform("spotLight.pointLight.attenuation.exponent");

        addUniform("spotLight.pointLight.position");
        addUniform("spotLight.pointLight.range");
        addUniform("spotLight.cutoff");
        addUniform("spotLight.direction");

        addUniform("eyePosition");
        addUniform("specularIntensity");
        addUniform("specularPower");
    }

    @Override
    public void updateUniforms(Transform transformation, Material material, RenderingEngine renderingEngine) {
        prepareTexture(material);
        setUniform("WVP", transformation.getProjectedTransformation());
        setUniform("world", transformation.getTransformation());

        setUniform("spotLight", (SpotLight)renderingEngine.getActiveLight());

        setUniform("eyePosition", renderingEngine.getMainCamera().getPos());
        setUniform("specularIntensity", material.getSpecularIntensity());
        setUniform("specularPower", material.getSpecularPower());
    }

    public static SpotShader getInstance() {
        return instance;
    }
}
