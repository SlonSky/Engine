package Engine.rendering.guis;

import Engine.rendering.RenderingEngine;
import Engine.rendering.meshManagment.Mesh;
import Engine.util.*;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Slon on 11.04.2016.
 */
public class GUIRenderer {

    private Mesh quad;
    private GUIShader shader;


    public GUIRenderer(){
        Vertex[] v = {
                new Vertex(new Vector3f(-1, 1, 0)),
                new Vertex(new Vector3f(-1, -1, 0)),
                new Vertex(new Vector3f(1, 1, 0)),
                new Vertex(new Vector3f(1, -1, 0))
        };
        int[] i = {
                0,2,1, 1,2,3
        };
        quad = new Mesh(v, i);
        shader = new GUIShader();
    }

    public void render(ArrayList<GUITexture> guis, RenderingEngine engine){
        glDisable(GL_DEPTH_TEST);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        for(GUITexture gui: guis){
            shader.bind();
            gui.getTexture().bind();
            shader.updateUniforms(gui.getTransform(), null, engine);
            shader.setMask(gui.getMask());
            quad.draw();
        }

        glEnable(GL_DEPTH_TEST);
        glDisable(GL_BLEND);
    }

    public void render(GUITexture gui, RenderingEngine engine){

        if(!gui.isHidden()) {
            glDisable(GL_DEPTH_TEST);
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

            shader.bind();
            gui.getTexture().bind();
            shader.updateUniforms(gui.getTransform(), null, engine);
            shader.setMask(gui.getMask());
            quad.draw();
            glEnable(GL_DEPTH_TEST);
            glDisable(GL_BLEND);
        }
    }
}
