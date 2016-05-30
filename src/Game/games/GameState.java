package Game.games;

import Engine.rendering.RenderingEngine;

/**
 * Created by Slon on 29.05.2016.
 */
public interface GameState {
    void enter();
    void update();
    void render(RenderingEngine engine);
    void exit();
}
