package Game.games;


import Engine.rendering.RenderingEngine;
import Engine.windows.WindowManager;

/**
 * Created by Slon on 29.05.2016.
 */
public class Menu implements GameState{

    private WindowManager manager;

    public Menu(WindowManager manager) {
        this.manager = manager;
    }

    @Override
    public void enter() {

    }

    @Override
    public void update() {
        manager.update();

    }

    @Override
    public void render(RenderingEngine engine) {
        engine.render(manager.getMainFrame(), false);
    }

    @Override
    public void exit() {

    }
}
