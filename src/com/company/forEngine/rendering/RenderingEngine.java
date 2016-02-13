package com.company.forEngine.rendering;

/**
 * Created by Slon on 09.02.2016.
 */

import com.company.forEngine.rendering.light.DirectionalLight;
import com.company.forEngine.rendering.light.Light;
import com.company.forEngine.util.Vector3f;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class RenderingEngine {

//    private ArrayList<Light> lights;
    private Shader shader;
    public RenderingEngine(){
//        lights = new ArrayList<>();
        glClearColor(0.5f, 0, 0.5f, 1);
        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_TEXTURE_2D);

        shader = new LightShader();

    }


    public void render(ArrayList<Entity> entities, Camera camera){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        for(Entity e: entities){
            shader.bind();
            shader.updateUniforms(e.getTransform(), camera, e.getMaterial());
            e.getMesh().draw();
        }

//        glEnable(GL_BLEND);
//        glBlendFunc(GL_ONE, GL_ONE);
//        glDepthMask(false);
//        glDepthFunc(GL_EQUAL);
//        for (Light light: lights){
//            light.getShader().bind();
//            light.getShader().updateUniforms(transform, camera, material);
//            mesh.draw();
//        }
//        glDepthFunc(GL_LESS);
//        glDepthMask(true);
//        glDisable(GL_BLEND);
        // draw


    }

//    public void addLight(Light light){
//        lights.add(light);
//    }

    public Shader getShader() {
        return shader;
    }
}
