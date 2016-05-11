package com.company.Engine.rendering.guis;

import com.company.Engine.rendering.RenderingEngine;
import com.company.Engine.rendering.Shader;
import com.company.Engine.rendering.Transform;
import com.company.Engine.rendering.meshManagment.Material;
import com.company.Engine.util.Vector4f;

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
        addUniform("mask");
    }


    public void setMask(Vector4f mask){
        setUniform("mask", mask);
    }

    @Override
    public void updateUniforms(Transform transformation, Material material, RenderingEngine renderingEngine) {
        setUniform("transform", transformation.getTransformation());
    }
}
