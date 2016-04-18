package com.company.Engine.core;

import com.company.Engine.rendering.Camera;
import com.company.Engine.rendering.RenderingEngine;
import com.company.Engine.rendering.Transform;

/**
 * Created by Slon on 06.04.2016.
 */
public abstract class Game {
    protected CoreEngine engine;
    protected Camera camera;
    protected Level level;

    public abstract void init();

    public void input(){
        level.input();
    }

    public void update(){
        level.update();
    }


    public void render(RenderingEngine renderingEngine){  renderingEngine.render(level);  }

    public void setEngine(CoreEngine engine) {
        this.engine = engine;
    }

    public CoreEngine getEngine() {
        return engine;
    }

    public void setCamera(Camera camera){
        engine.getRenderingEngine().setMainCamera(camera);
        this.camera = camera;
        Transform.setProjection(70, Window.getWidth(), Window.getHeight(), 0.01f, 1000f);
        Transform.setCamera(camera);
    }

    public void destroy(){
        level.destroy();
    }
}
