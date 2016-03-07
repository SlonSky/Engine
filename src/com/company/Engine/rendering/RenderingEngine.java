package com.company.Engine.rendering;

/**
 * Created by Slon on 09.02.2016.
 */

import com.company.Engine.core.CoreEngine;
import com.company.Engine.core.Window;
import com.company.Engine.rendering.light.AmbientShader;
import com.company.Engine.rendering.light.Light;
import com.company.Engine.rendering.light.PointLight;
import com.company.Engine.rendering.light.SpotLight;
import com.company.Engine.util.Matrix4f;
import com.company.Engine.util.Vector3f;
import com.company.Game.objects.Level;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL30.*;

public class RenderingEngine {
    private CoreEngine core;

    private Camera mainCamera;
    private Light activeLight;

    private LevelRenderer levelRenderer;

    public RenderingEngine(CoreEngine core){
        this.core = core;
        levelRenderer = new LevelRenderer();

        glClearColor(1.5f, 1, 1.5f, 1);
        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_TEXTURE_2D);

    }


    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        levelRenderer.render(core.getGame().getLevel(), this);



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
