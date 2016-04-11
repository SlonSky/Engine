package com.company.Engine.rendering;

/**
 * Created by Slon on 09.02.2016.
 */

import com.company.Engine.core.CoreEngine;
import com.company.Engine.rendering.gui.GUIRenderer;
import com.company.Engine.rendering.light.Light;
import com.company.Engine.rendering.skybox.SkyBoxRenderer;
import com.company.Engine.rendering.text.Text;
import com.company.Engine.rendering.text.TextRenderer;
import com.company.Engine.util.Matrix4f;
import com.company.Engine.util.Plane;
import com.company.Game.objects.Decoration;
import com.company.Game.objects.Level;
import com.company.Game.objects.Player;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import sun.font.TrueTypeFont;

import java.awt.*;
import java.awt.Font;

import static org.lwjgl.opengl.GL11.*;

public class RenderingEngine {
    private CoreEngine core;

    private Camera mainCamera;
    private Light activeLight;
    private Plane[] frustum;

    org.newdawn.slick.TrueTypeFont font;

    private LevelRenderer levelRenderer;

    // todo move to level renderer?
    private SkyBoxRenderer skyBoxRenderer;
    private TextRenderer textRenderer;
    private GUIRenderer guiRenderer;

    public RenderingEngine(CoreEngine core){
        this.core = core;
        levelRenderer = new LevelRenderer();
        skyBoxRenderer = new SkyBoxRenderer();

        textRenderer = new TextRenderer();
        guiRenderer = new GUIRenderer();

        font = new org.newdawn.slick.TrueTypeFont(new Font("Times New Roman", Font.BOLD, 92), true);

        glClearColor(1.5f, 1, 1.5f, 1);
        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_TEXTURE_2D);

    }

    public void render(Level level) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        frustum = calcFrustum();

        skyBoxRenderer.render(level.getSkyBox(), this);
        levelRenderer.render(level, this);
        textRenderer.render(level.getText(), this);

        guiRenderer.render(level.getGuis(), this);

    }

    private Plane[] calcFrustum(){
        Matrix4f clip = Transform.getProjectedModelView();

        Plane[] frustum = new Plane[6];

        // right plane
        frustum[0] = new Plane(
                clip.get(3, 0) - clip.get(0,0),
                clip.get(3, 1) - clip.get(0,1),
                clip.get(3, 2) - clip.get(0,2),
                clip.get(3, 3) - clip.get(0,3)).normalized();

        // left plane
        frustum[1] = new Plane(
                clip.get(3, 0) + clip.get(0,0),
                clip.get(3, 1) + clip.get(0,1),
                clip.get(3, 2) + clip.get(0,2),
                clip.get(3, 3) + clip.get(0,3)).normalized();

        // bottom plane
        frustum[2] = new Plane(
                clip.get(3, 0) + clip.get(1,0),
                clip.get(3, 1) + clip.get(1,1),
                clip.get(3, 2) + clip.get(1,2),
                clip.get(3, 3) + clip.get(1,3)).normalized();

        // top plane
        frustum[3] = new Plane(
                clip.get(3, 0) - clip.get(1,0),
                clip.get(3, 1) - clip.get(1,1),
                clip.get(3, 2) - clip.get(1,2),
                clip.get(3, 3) - clip.get(1,3)).normalized();

        // near plane
        frustum[4] = new Plane(
                clip.get(3, 0) - clip.get(2,0),
                clip.get(3, 1) - clip.get(2,1),
                clip.get(3, 2) - clip.get(2,2),
                clip.get(3, 3) - clip.get(2,3)).normalized();

        // far plane
        frustum[5] = new Plane(
                clip.get(3, 0) + clip.get(2,0),
                clip.get(3, 1) + clip.get(2,1),
                clip.get(3, 2) + clip.get(2,2),
                clip.get(3, 3) + clip.get(2,3)).normalized();

        return frustum;
    }

    public Plane[] getFrustum() {
        return frustum;
    }

    public void setActiveLight(Light activeLight) {
        this.activeLight = activeLight;
    }

    public void setMainCamera(Camera mainCamera) {
        this.mainCamera = mainCamera;
    }

    public Camera getMainCamera() {
        return mainCamera;
    }

    public Light getActiveLight() {
        return activeLight;
    }

}
