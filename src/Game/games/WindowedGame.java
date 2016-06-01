package Game.games;

import Engine.audio.Sound;
import Engine.audio.Source;
import Engine.core.Input;
import Engine.rendering.Camera;
import Engine.rendering.RenderingEngine;
import Engine.rendering.text.Font;
import Engine.util.Quaternion;
import Engine.util.Vector2f;
import Engine.util.Vector3f;
import Engine.windows.*;
import Game.Game;
import Game.GameObject;
import Game.entities.Decoration;
import Game.player.Player;


/**
 * Created by Slon on 27.04.2016.
 */
public class WindowedGame extends Game{

    private WindowManager windowManager;

    private Source gameBackground;

    private GameState state;
    private GameState menu;
    private GameState play;

    // todo: system.ini text file with init params (editable in settings)
    // todo in logo: [LICENSE] content is using in educational purposes
    // todo: init gui in other thread while logo

    @Override
    public void init() {

        camera = new Camera(new Vector3f(0,0,0),new Quaternion(0,0,0,1));
        setCamera(camera);

        gameBackground = new Source(true);

        windowManager = new WindowManager(this, gameBackground);

        menu = new Menu(windowManager);
        play = new Play(windowManager, this);


        state = menu;

    }

    @Override
    public void render(RenderingEngine renderingEngine) {
        state.render(renderingEngine);
    }

    @Override
    public void update() {
        state.update();
    }

    private void changeState(GameState newState){
        if(state != newState){
            state.exit();
            state = newState;
            state.enter();
        }
    }

    public void setPlay(){
        changeState(play);
    }

    public void setMenu(){
        changeState(menu);
    }

    public Source getGameBackground() {
        return gameBackground;
    }

    public void exit(){
        engine.stop();
    }
}
