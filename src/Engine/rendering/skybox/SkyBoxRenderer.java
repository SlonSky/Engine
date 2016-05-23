package Engine.rendering.skybox;

import Engine.rendering.RenderingEngine;

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
        glCullFace(GL_FRONT);
        glDepthFunc(GL_LEQUAL);
        skyBox.render(shader, engine);
        glCullFace(GL_BACK);
        glDepthFunc(GL_LESS);

    }

}
