package com.company.Engine.rendering;

/**
 * Created by Slon on 17.02.2016.
 */
import static org.lwjgl.opengl.GL20.*;
public class ShadowShader extends Shader {

    public ShadowShader(){
        super();
        addVertexShader("shadowVertex.txt");
        addFragmentShader("shadowFragment.txt");
        compileShader();

        addUniform("WVP");
        addUniform("shadowMap");
    }

    @Override
    public void updateUniforms(Transform transformation, Material material, RenderingEngine renderingEngine) {
        setUniform("WVP", transformation.getProjectedTransformation());
        setUniform("shadowMap", 0);
    }
}