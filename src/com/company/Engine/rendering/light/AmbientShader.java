package com.company.Engine.rendering.light;

import com.company.Editor.LevelEditor.EditorWindow;
import com.company.Engine.rendering.meshManagment.Material;
import com.company.Engine.rendering.RenderingEngine;
import com.company.Engine.rendering.Shader;
import com.company.Engine.rendering.Transform;
import com.company.Engine.util.Vector3f;

/**
 * Created by Slon on 15.02.2016.
 */
public class AmbientShader extends Shader {

    private Vector3f ambientIntensity;

    public AmbientShader(Vector3f ambientIntensity){
        super();
        addVertexShader("ambientVertex.txt");
        addFragmentShader("ambientFragment.txt");
        compileShader();

        this.ambientIntensity = ambientIntensity;

        addUniform("WVP");
        addUniform("ambient");
        addUniform("color");
    }

    @Override
    public void updateUniforms(Transform transformation, Material material, RenderingEngine renderingEngine) {
        prepareTexture(material);
        setUniform("color", material.getColor());

            setUniform("WVP", transformation.getProjectedTransformation());
        setUniform("ambient", ambientIntensity);
    }

    public Vector3f getAmbientIntensity() {
        return ambientIntensity;
    }

    public void setAmbientIntensity(Vector3f ambientIntensity) {
        this.ambientIntensity = ambientIntensity;
    }
}
