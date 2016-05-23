package Engine.windows;

import Engine.core.CoreEngine;
import Engine.core.Input;
import Game.games.WindowedGame;

/**
 * Created by Slon on 08.05.2016.
 */

// todo: WindowManager class containing all UIFrames, providing all set<smFrame>()'s
    // in constructor - init from file?
public class WindowManager {

    private CoreEngine engine;

    private UIState oldState = UIState.LOGO;

    public WindowManager(CoreEngine engine) {
        this.engine = engine;
    }

    public void alert(UIState state){
        if(state == null){
            return;
        }
        switch (state){

            case LOGO:
                Input.setCursor(false);
                ((WindowedGame) engine.getGame()).setLogo();
                oldState = UIState.LOGO;
                break;
            case MAIN_MENU:
                if(oldState == UIState.LOGO){
                    ((WindowedGame) engine.getGame()).playMenuTheme();
                }
                ((WindowedGame) engine.getGame()).setMainMenu();
                Input.setCursor(true);
                oldState = UIState.MAIN_MENU;
                break;
            case GAME:
                oldState = UIState.GAME;
                break;
            case CHOOSE_LEVEL:
                ((WindowedGame) engine.getGame()).setLevelChoose();
                oldState = UIState.CHOOSE_LEVEL;
                break;
            case SETTINGS:
                ((WindowedGame) engine.getGame()).setSettings();
                oldState = UIState.SETTINGS;
                break;
            case CREDITS:
                ((WindowedGame) engine.getGame()).setCredits();
                oldState = UIState.CREDITS;
                break;
            case EXIT:
                engine.stop();
                break;
        }
    }
}
