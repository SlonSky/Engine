package com.company.Engine.rendering;

import com.company.Engine.rendering.light.AmbientShader;
import com.company.Engine.rendering.light.Light;
import com.company.Engine.util.Vector3f;
import com.company.Engine.core.GameObject;
import com.company.Engine.core.Level;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Slon on 07.03.2016.
 * TODO: light renderable/non-light renderable entities
 */


public class LevelRenderer {

    private Shader ambient;
    public LevelRenderer(){
        ambient = new AmbientShader(new Vector3f(0.8f, 0.8f, 0.8f));
    }

    public void render(Level level, RenderingEngine engine){

        ArrayList<GameObject> objects = level.getObjects();
        ArrayList<Light> lights = level.getLights();

        for(GameObject object: objects){
            object.render(ambient, engine);
        }

        // blending other light types rendering
        glEnable(GL_BLEND);
        glBlendFunc(GL_ONE, GL_ONE);
        glDepthMask(false);
        glDepthFunc(GL_EQUAL);

        for (Light light: lights) {
            engine.setActiveLight(light);
            for(GameObject object: objects){

                // todo: if it's light-renderable object
                object.render(light.getShader(), engine);
            }
        }

        glDepthFunc(GL_LESS);
        glDepthMask(true);
        glDisable(GL_BLEND);
    }

}

