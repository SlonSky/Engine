package Engine.rendering.skybox;

import Engine.rendering.RenderingEngine;
import Engine.rendering.Shader;
import Engine.rendering.Transform;
import Engine.rendering.meshManagment.Mesh;
import Engine.util.Quaternion;
import Engine.util.Vector3f;

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
