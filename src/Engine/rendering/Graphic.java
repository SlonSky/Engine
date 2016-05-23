package Engine.rendering;

import Engine.rendering.meshManagment.Material;
import Engine.rendering.meshManagment.Mesh;
import Game.GameComponent;

/**
 * Created by Slon on 21.03.2016.
 */
public class Graphic extends GameComponent {
    private Mesh mesh;
    private Material material;

    public Graphic(Mesh mesh, Material material) {
        this.mesh = mesh;
        this.material = material;
    }

    public void render(Shader shader, RenderingEngine renderingEngine){
        shader.bind();
        shader.updateUniforms(getTransform(), material, renderingEngine);
        mesh.draw();
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
