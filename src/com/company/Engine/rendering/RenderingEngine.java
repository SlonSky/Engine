package com.company.Engine.rendering;

/**
 * Created by Slon on 09.02.2016.
 */

import com.company.Engine.core.Window;
import com.company.Engine.rendering.light.AmbientShader;
import com.company.Engine.rendering.light.Light;
import com.company.Engine.rendering.light.PointLight;
import com.company.Engine.rendering.light.SpotLight;
import com.company.Engine.util.Matrix4f;
import com.company.Engine.util.Vector3f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL30.*;

public class RenderingEngine {

    private ArrayList<Light> lights;
    private Shader ambient;
    private Camera mainCamera;
    private Light activeLight;

    // temporary!
//    private ShadowMapFBO shadowMapFBO;
//    private Shader shadow;
    //////////////

    public RenderingEngine(){
        lights = new ArrayList<>();
        glClearColor(0.5f, 0, 0.5f, 1);
        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_TEXTURE_2D);

//        glEnable(GL_SCISSOR_TEST);
//        glScissor(10, 10, Window.getWidth() - 20, Window.getHeight() - 20);

        ambient = new AmbientShader(new Vector3f(0.3f, 0.3f, 0.3f));

//        shadow = new ShadowShader();
//        shadowMapFBO = new ShadowMapFBO();
    }


    public void render(ArrayList<Entity> entities) {

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        drawEntities(entities, ambient);


        glEnable(GL_BLEND);
        glBlendFunc(GL_ONE, GL_ONE);
        glDepthMask(false);
        glDepthFunc(GL_EQUAL);

        for(Light light: lights) {
            activeLight = light;
            drawEntities(entities, light.getShader());
        }

        glDepthFunc(GL_LESS);
        glDepthMask(true);
        glDisable(GL_BLEND);

    }

//    public void shadowMapPass(List<Entity> entities, SpotLight light){
//        shadowMapFBO.bindForWriting();
//
//        glClear(GL_DEPTH_BUFFER_BIT);
//
//        Camera mainCam = mainCamera;
////        Transform.setProjection(70, Window.getWidth(), Window.getHeight(), 0.1f, 1000); // new
//
//        mainCamera = new Camera();
//        mainCamera.setPos(light.getPosition());
//        mainCamera.setForward(light.getDirection());
//
//        shadow.bind();
//        shadow.updateUniforms(entities.get(1).getTransform(), entities.get(1).getMaterial(), this);
//        entities.get(1).getMesh().draw();
//
//
//        glBindFramebuffer(GL_FRAMEBUFFER, 0);
//
//        // TODO: oldCam / oldPersProj variables!
//
//        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
//        mainCamera = mainCam;
//        shadowMapFBO.bindForReading(GL_TEXTURE0);
//
//        shadow.bind();
//        shadow.updateUniforms(entities.get(0).getTransform(), entities.get(0).getMaterial(), this);
//        entities.get(0).getMesh().draw();
//    }




    private void drawEntities(List<Entity> entities, Shader shader){
        for (Entity e : entities) {
            shader.bind();
            shader.updateUniforms(e.getTransform(), e.getMaterial(), this);
            e.getMesh().draw();
        }
    }

    public ArrayList<Light> getLights() {
        return lights;
    }

    public void addLight(Light light){
        lights.add(light);
    }

    public void setMainCamera(Camera mainCamera) {
        this.mainCamera = mainCamera;
    }

    public Camera getMainCamera() {
        return mainCamera;
    }

    public Light getActiveLight() {
        return activeLight;
    }

    public Shader getAmbient() {
        return ambient;
    }
}
