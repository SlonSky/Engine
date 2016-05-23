package Engine.rendering.guis;

import Engine.rendering.RenderingEngine;
import Engine.rendering.Shader;
import Engine.rendering.Transform;
import Engine.rendering.meshManagment.Material;
import Engine.util.Vector4f;

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
