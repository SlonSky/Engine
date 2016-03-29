package com.company.Engine.rendering.text;

import com.company.Editor.EditorWindow;
import com.company.Engine.rendering.RenderingEngine;
import com.company.Engine.rendering.Transform;
import com.company.Engine.util.Quaternion;
import com.company.Engine.util.Vector3f;
import org.lwjgl.opengl.GL11;

import java.awt.*;

/**
 * Created by Slon on 27.03.2016.
 */
public class TextRenderer {
    private TextShader shader;

    public TextRenderer(){
        shader = new TextShader();
    }

    public void render(Text text, RenderingEngine engine){

        GL11.glDisable(GL11.GL_DEPTH_TEST);

        shader.bind();
        shader.updateFont(text);
        Vector3f forward = engine.getMainCamera().getForward();
        Vector3f right = engine.getMainCamera().getRight();
        Vector3f up = engine.getMainCamera().getUp();
        shader.updateUniforms(
                new Transform(
                        engine.getMainCamera().getPos()
                        .add(forward.normalized().mul(33))
                        .add(right.normalized().mul(text.getPos().getX()))
                        .add(up.normalized().mul(text.getPos().getY())),
//                        )).add(new Vector3f(text.getPos().getX(), text.getPos().getY(), 0).mul(forward.normalized())),

                        engine.getMainCamera().getRot(),
                        new Vector3f(text.getSize(), text.getSize(), 1)), null, engine);
        text.draw();

        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }
}
