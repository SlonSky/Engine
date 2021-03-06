package Game;

import Engine.core.CoreEngine;
import Engine.core.Window;
import Engine.rendering.Camera;
import Engine.rendering.RenderingEngine;
import Engine.rendering.Transform;

/**
 * Created by Slon on 06.04.2016.
 */
public abstract class Game {
    protected CoreEngine engine;
    protected Camera camera;
    protected Level level;

    public abstract void init();

    public void input(){}
    public void update(){}
    public void render(RenderingEngine renderingEngine){}

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

    public void destroy(){}
}
