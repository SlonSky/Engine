package Engine.rendering.text;

import Engine.rendering.RenderingEngine;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

/**
 * Created by Slon on 27.03.2016.<br>
 * <code>TextRenderer</code> - specified render util for {@link Text} rendering.
 */
public class TextRenderer {
    private TextShader shader;

    public TextRenderer(){
        shader = new TextShader();
    }

    /**
     * Render list of {@link Text}
     * @param textList list of {@link Text} items
     * @param engine main {@link RenderingEngine}
     */
    public void render(ArrayList<Text> textList, RenderingEngine engine){
        glDisable(GL_DEPTH_TEST);
        for(Text text: textList) {
            shader.bind();
            shader.updateFont(text);
            shader.updateUniforms(text.getTransform(), null, engine);
            text.draw();
        }
        glEnable(GL_DEPTH_TEST);
    }

    /**
     * Render {@link Text} item
     * @param text {@link Text} item
     * @param engine main {@link RenderingEngine}
     */
    public void render(Text text, RenderingEngine engine){
        glDisable(GL_DEPTH_TEST);
        shader.bind();
        shader.updateFont(text);
        shader.updateUniforms(text.getTransform(), null, engine);
        text.draw();
        glEnable(GL_DEPTH_TEST);
    }
}
