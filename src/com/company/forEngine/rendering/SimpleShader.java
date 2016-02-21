package com.company.forEngine.rendering;

import com.company.forEngine.util.Matrix4f;

/**
 * Created by Slon on 10.02.2016.
 */
public class SimpleShader extends Shader {
    public SimpleShader(){
        super();
        addVertexShader("simpleVertex.txt");
        addFragmentShader("simpleFragment.txt");
        compileShader();
        addUniform("transformation");
    }

    @Override
    public void updateUniforms(Transform transformation, Camera camera, Material material) {
        material.getTexture().bind();
        setUniform("transformation", transformation.getProjectedTransformation());
    }
}
