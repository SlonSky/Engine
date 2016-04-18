package com.company.Engine.rendering.guis;

import com.company.Engine.rendering.RenderingEngine;
import com.company.Engine.rendering.Shader;
import com.company.Engine.rendering.Transform;
import com.company.Engine.rendering.meshManagment.Material;

/**
 * Created by Slon on 11.04.2016.
 */
public class GUIShader extends Shader{

    public GUIShader(){
        super();
        addVertexShader("guiVertex.txt");
        addFragmentShader("guiFragment.txt");
        compileShader();

        addUniform("transform");
    }

    @Override
    public void updateUniforms(Transform transformation, Material material, RenderingEngine renderingEngine) {
        setUniform("transform", transformation.getTransformation());
    }
}
