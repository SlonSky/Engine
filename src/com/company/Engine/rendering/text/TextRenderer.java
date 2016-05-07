package com.company.Engine.rendering.text;

import com.company.Engine.rendering.RenderingEngine;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

/**
 * Created by Slon on 27.03.2016.
 */
public class TextRenderer {
    private TextShader shader;

    public TextRenderer(){
        shader = new TextShader();
    }

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

    public void render(Text text, RenderingEngine engine){
        glDisable(GL_DEPTH_TEST);
        shader.bind();
        shader.updateFont(text);
        shader.updateUniforms(text.getTransform(), null, engine);
        text.draw();
        glEnable(GL_DEPTH_TEST);
    }
}
