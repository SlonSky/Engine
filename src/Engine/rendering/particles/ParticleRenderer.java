package Engine.rendering.particles;

import Engine.rendering.RenderingEngine;
import Engine.rendering.meshManagment.Mesh;
import Engine.util.Vector3f;
import Engine.util.Vertex;

import java.util.ArrayList;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;

/**
 * Created by Slon on 27.05.2016.
 */
public class ParticleRenderer {

    private Mesh quad;
    private ParticleShader shader;

    public ParticleRenderer(){
        Vertex[] v = {
                new Vertex(new Vector3f(-0.5f, 0.5f, 0)),
                new Vertex(new Vector3f(-0.5f, -0.5f, 0)),
                new Vertex(new Vector3f(0.5f, 0.5f, 0)),
                new Vertex(new Vector3f(0.5f, -0.5f, 0))
        };
        int[] i = {
                0,2,1, 1,2,3
        };
        quad = new Mesh(v, i);
        shader = new ParticleShader();
    }



    public void render(HashMap<ParticleTexture, ArrayList<Particle>> particles, RenderingEngine engine){
        glEnable(GL_BLEND);
        glDepthMask(false);
        for(ParticleTexture texture: particles.keySet()) {
            // bind
            texture.getTexture().bind();
            for (Particle particle : particles.get(texture)) {

                if(particle.isAddLighting()){
                    glBlendFunc(GL_SRC_ALPHA, GL_ONE);
                } else {
                    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
                }

                shader.bind();
                shader.loadParticleInfo(particle.getTexOffset1(), particle.getTexOffset2(), texture.getNumberOfRows(), particle.getBlend(), particle.isBillBoard());
                shader.updateUniforms(particle.getTransform(), null, engine);
                quad.draw();
            }
        }
        glDisable(GL_BLEND);
        glDepthMask(true);
    }


}
