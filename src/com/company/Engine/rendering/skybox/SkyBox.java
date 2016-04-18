package com.company.Engine.rendering.skybox;

import com.company.Engine.core.Time;
import com.company.Engine.rendering.RenderingEngine;
import com.company.Engine.rendering.Shader;
import com.company.Engine.rendering.Transform;
import com.company.Engine.rendering.meshManagment.Mesh;
import com.company.Engine.util.Quaternion;
import com.company.Engine.util.Vector3f;

/**
 * Created by Slon on 24.03.2016.
 */
public class SkyBox {

    private static final float SKY_BOX_SIZE = 100000.0f;

    private Mesh box;
    private CubeMapTexture texture;
    public SkyBox(String textureName) {
        this.box = new Mesh("bound.obj");
        this.texture = new CubeMapTexture(textureName);
    }

    public CubeMapTexture getTexture() {
        return texture;
    }

    public Mesh getBox() {
        return box;
    }

    public void render(Shader shader, RenderingEngine engine){
        shader.bind();
        texture.bind();
        shader.updateUniforms(new Transform(engine.getMainCamera().getPos(), new Quaternion(0, 0, 0, 1),
                new Vector3f(SKY_BOX_SIZE, SKY_BOX_SIZE, SKY_BOX_SIZE)), null, engine);
        box.draw();
    }
}
