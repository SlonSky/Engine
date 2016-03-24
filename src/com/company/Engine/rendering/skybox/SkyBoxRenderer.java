package com.company.Engine.rendering.skybox;

import com.company.Engine.rendering.RenderingEngine;
import com.company.Engine.rendering.Transform;
import com.company.Engine.util.Quaternion;
import com.company.Engine.util.Vector3f;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Slon on 24.03.2016.
 */
public class SkyBoxRenderer {

    private SkyBoxShader shader;

    public SkyBoxRenderer(){
        shader = new SkyBoxShader();
    }

    public void render(SkyBox skyBox, RenderingEngine engine){
        shader.bind();
        // todo: old cull face
        glCullFace(GL_FRONT);
        glDepthFunc(GL_LEQUAL);

        shader.bindCubeMap(skyBox);
        //todo camera component!!
        shader.updateUniforms(
                new Transform(
                        engine.getMainCamera().getPos(),
                        new Quaternion(0,0,0,1),
                        new Vector3f(1000,1000,1000)), null, engine);
        skyBox.getBox().draw();

        glCullFace(GL_BACK);
        glDepthFunc(GL_LESS);

    }

}
