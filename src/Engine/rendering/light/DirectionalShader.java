package Engine.rendering.light;

import Engine.rendering.*;
import Engine.rendering.meshManagment.Material;

/**
 * Created by Slon on 15.02.2016.
 */
public class DirectionalShader extends Shader {

    public static final DirectionalShader instance = new DirectionalShader();

    private DirectionalShader(){
        super();
        addVertexShader("directionalVertex.txt");
        addFragmentShader("directionalFragment.txt");
        compileShader();

        addUniform("WVP");
        addUniform("world");

        addUniform("directionalLight.light.color");
        addUniform("directionalLight.light.intensity");
        addUniform("directionalLight.direction");

        addUniform("eyePosition");
        addUniform("specularIntensity");
        addUniform("specularPower");
    }

    @Override
    public void updateUniforms(Transform transformation, Material material, RenderingEngine renderingEngine) {
        prepareTexture(material);
        setUniform("WVP", transformation.getProjectedTransformation());
        setUniform("world", transformation.getTransformation());

        setUniform("directionalLight", (DirectionalLight)renderingEngine.getActiveLight());

        setUniform("eyePosition", renderingEngine.getMainCamera().getPos());
        setUniform("specularIntensity", material.getSpecularIntensity());
        setUniform("specularPower", material.getSpecularPower());
    }

    public static DirectionalShader getInstance() {
        return instance;
    }
}
