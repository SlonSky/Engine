package Engine.rendering;

import Engine.rendering.light.AmbientShader;
import Engine.rendering.light.Light;
import Engine.util.Vector3f;
import Game.GameObject;
import Game.Level;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Slon on 07.03.2016.
 */


public class LevelRenderer {

    private AmbientShader ambient;
    public LevelRenderer(Vector3f ambient){
        this.ambient = new AmbientShader(ambient);
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
                object.render(light.getShader(), engine);
            }
        }


        glDepthFunc(GL_LESS);
        glDepthMask(true);
        glDisable(GL_BLEND);

    }

    public void setAmbient(Vector3f ambient){
        this.ambient.setAmbientIntensity(ambient);
        System.out.println(ambient);
    }

}

