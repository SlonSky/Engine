package com.company.Engine.windows;

import com.company.Engine.core.CoreEngine;
import com.company.Engine.core.Input;
import com.company.Game.WindowedGame;

/**
 * Created by Slon on 08.05.2016.
 */
public class WindowListener {

    private CoreEngine engine;

    private UIState oldState = UIState.LOGO;

    public WindowListener(CoreEngine engine) {
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
//                engine.getGame().start(level2)
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
                oldState = UIState.CREDITS;
                break;
            case EXIT:
                engine.stop();
                break;
        }
    }
}
