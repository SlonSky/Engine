package com.company.Engine.rendering.text;

import com.company.Engine.rendering.RenderingEngine;
import com.company.Engine.rendering.Shader;
import com.company.Engine.rendering.Transform;
import com.company.Engine.rendering.meshManagment.Material;

/**
 * Created by Slon on 27.03.2016.
 */
public class TextShader extends Shader {

    public TextShader(){
        super();
        addVertexShader("textVertex.txt");
        addFragmentShader("textFragment.txt");
        compileShader();

        addUniform("WVP");
        addUniform("color");

    }

    public void updateFont(Text text){
        text.getFont().getAtlas().bind();
        setUniform("color", text.getColor());
    }

    @Override
    public void updateUniforms(Transform transformation, Material material, RenderingEngine renderingEngine) {
        setUniform("WVP", transformation.getTransformation());
    }
}
