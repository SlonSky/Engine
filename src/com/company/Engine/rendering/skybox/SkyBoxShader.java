package com.company.Engine.rendering.skybox;

import com.company.Engine.rendering.RenderingEngine;
import com.company.Engine.rendering.Shader;
import com.company.Engine.rendering.Transform;
import com.company.Engine.rendering.meshManagment.Material;
import com.company.Engine.util.Quaternion;
import com.company.Engine.util.Vector3f;

/**
 * Created by Slon on 24.03.2016.
 */
public class SkyBoxShader extends Shader {


    public SkyBoxShader(){
        super();

        addVertexShader("skyBoxVertex.txt");
        addFragmentShader("skyBoxFragment.txt");
        compileShader();

        addUniform("WVP");
    }

    @Override
    public void updateUniforms(Transform transformation, Material material, RenderingEngine renderingEngine) {
        transformation.rotate(new Quaternion(new Vector3f(0,1,0), (float)Math.toRadians(10)));
        setUniform("WVP", transformation.getProjectedTransformation());
    }
}
