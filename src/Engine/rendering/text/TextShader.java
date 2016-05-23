package Engine.rendering.text;

import Engine.rendering.RenderingEngine;
import Engine.rendering.Shader;
import Engine.rendering.Transform;
import Engine.rendering.meshManagment.Material;

/**
 * Created by Slon on 27.03.2016.<br>
 * <code>TextShader</code> represents shader for the
 * {@link Text} rendering.
 */
public class TextShader extends Shader {

    public TextShader(){
        super();
        addVertexShader("textVertex.txt");
        addFragmentShader("textFragment.txt");
        compileShader();

        addUniform("WVP");
        addUniform("color");

    }

    /**
     * Updates {@link Text} parameters such as
     * font atlas texture and color setting.
     * @param text {@link Text} to render
     */
    public void updateFont(Text text){
        text.getFont().getAtlas().bind();
        setUniform("color", text.getColor());
    }

    /**
     * Update shader uniform variables
     * @param transformation text transformation
     * @param material some material
     * @param renderingEngine main {@link RenderingEngine}
     */
    @Override
    public void updateUniforms(Transform transformation, Material material, RenderingEngine renderingEngine) {
        setUniform("WVP", transformation.getTransformation());
    }
}
