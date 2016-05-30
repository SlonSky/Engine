package Engine.windows;

import Game.Game;
import Engine.audio.Sound;
import Engine.audio.Source;
import Engine.core.CoreEngine;
import Engine.core.Input;
import Engine.rendering.text.Font;
import Engine.util.Vector2f;
import Engine.util.Vector3f;
import Game.games.WindowedGame;

/**
 * Created by Slon on 08.05.2016.
 */

// todo: WindowManager class containing all UIFrames, providing all set<smFrame>()'s
    // in constructor - init from file?
public class WindowManager {

//    private CoreEngine engine;

    private WindowedGame game;

    private UIState oldState = UIState.LOGO;

    private UIFrame mainFrame;
    private UIFrame logo;
    private UIFrame mainMenu;
    private UIFrame settings;
    private UIFrame loading;
    private UIFrame pause;
    private UIFrame levelChoose;
    private UIFrame titles;

    private Source gameBackground;
    private Sound menuTheme;

    private Vector2f mouseCoord;
    private boolean paused;

    public WindowManager(WindowedGame game, Source gameBackground) {
//        this.engine = engine;
        this.game = game;
        this.gameBackground = gameBackground;

        Font font = new Font("rus.png", "rus.fnt");
        UIFrame.setFont(font);
        UIFrame.setUiSource();
        UIFrame.setClickSound("res/sound/buttonclick.wav");
        UIFrame.setHoverSound("res/sound/menu_focus.wav");

        initLogo();
        initMainMenu();
        initLoading();
        initPause();
        initLevelChoose();
        initSettings();
        initTitles();


        menuTheme = new Sound("res/sound/mainMenuTheme.ogg");
        alert(UIState.LOGO);
    }

    public void alert(UIState state){
        if(state == null){
            return;
        }
        switch (state){

            case LOGO:
                Input.setCursor(false);
                mainFrame = logo;
                oldState = UIState.LOGO;
                playLogo();
                break;
            case MAIN_MENU:
                if(oldState == UIState.LOGO){
                    playMenuTheme();
                } else if(oldState == UIState.PAUSE){
                    playMenuTheme();
                    game.setMenu();
                }
                mainFrame = mainMenu;
                Input.setCursor(true);
                oldState = UIState.MAIN_MENU;
                break;
            case LOADING:
                if(oldState == UIState.LOADING){
                    game.setPlay();
                    alert(UIState.GAME);
                } else {
                    Input.setCursor(false);
                    mainFrame = loading;
                    oldState = UIState.LOADING;
                }

                break;
            case PAUSE:
                mainFrame = pause;
                Input.setCursor(true);
                mouseCoord = Input.getMousePosition();
                paused = true;
                oldState = UIState.PAUSE;
                break;
            case GAME:
                if(oldState == UIState.PAUSE){
                    Input.setMousePosition(mouseCoord);
                }
                paused = false;
                Input.setCursor(false);
                oldState = UIState.GAME;
                break;
            case CHOOSE_LEVEL:
                mainFrame = levelChoose;
                oldState = UIState.CHOOSE_LEVEL;
                break;
            case SETTINGS:
                mainFrame = settings;
                oldState = UIState.SETTINGS;
                break;
            case CREDITS:
                mainFrame = titles;
                oldState = UIState.CREDITS;
                break;
            case EXIT:
                // todo: game.exit();
                break;
        }
    }

    public void update(){
        if(oldState == UIState.LOADING){
            alert(UIState.LOADING);
        }
        mainFrame.update();
    }

    public void initLogo(){
        logo = new UIFrame();
        BlinkImage intro = new BlinkImage(new Vector2f(0,0), new Vector2f(0.8f,0.8f), "intro.png", true, 2.5);
        intro.setActState(UIState.MAIN_MENU);
        logo.addComponent(intro);
        logo.setManager(this);
    }

    public void initMainMenu(){
        mainMenu = new UIFrame();

        Image background = new Image(new Vector2f(0,0), new Vector2f(1,1), "back.png", false);

        Label title = new Label(new Vector2f(-0.85f, 0.3f), 0.5f, "Post Mortem", new Vector3f(0.6f, 0.2f, 0.3f));

        Button newGame = new Button(new Vector2f(-0.76f,0), 0.1f, "New game", new Vector3f(0.7f,0,0), new Vector3f(0.9f,0,0), new Vector3f(0.9f,0.2f,0));
        Button level = new Button(new Vector2f(-0.76f,-0.2f), 0.1f, "Choose level", new Vector3f(0.7f,0,0), new Vector3f(0.9f,0,0), new Vector3f(0.9f,0.2f,0));
        Button settings = new Button(new Vector2f(-0.76f,-0.4f), 0.1f, "Settings", new Vector3f(0.7f,0,0), new Vector3f(0.9f,0,0), new Vector3f(0.9f,0.2f,0));
        Button titles = new Button(new Vector2f(-0.76f,-0.6f), 0.1f, "Credits", new Vector3f(0.7f,0,0), new Vector3f(0.9f,0,0), new Vector3f(0.9f,0.2f,0));
        Button exit = new Button(new Vector2f(-0.76f,-0.8f), 0.1f, "Exit", new Vector3f(0.7f,0,0), new Vector3f(0.9f,0,0), new Vector3f(0.9f,0.2f,0));

        newGame.setStateOnClick(UIState.LOADING);
        titles.setStateOnClick(UIState.CREDITS);
        settings.setStateOnClick(UIState.SETTINGS);
        level.setStateOnClick(UIState.CHOOSE_LEVEL);
        exit.setStateOnClick(UIState.EXIT);

        mainMenu.addComponent(background);
        mainMenu.addComponent(title);
        mainMenu.addComponent(newGame);
        mainMenu.addComponent(level);
        mainMenu.addComponent(settings);
        mainMenu.addComponent(titles);
        mainMenu.addComponent(exit);

        mainMenu.setManager(this);
    }

    public void initLoading(){
        loading = new UIFrame();

        Image background = new Image(new Vector2f(0,0), new Vector2f(1,1), "loading.png", false);

        loading.addComponent(background);
        loading.setManager(this);
    }

    public void initPause(){
        pause = new UIFrame();


        Menu menu = new Menu(new Vector2f(0.6f,-0.6f), new Vector2f(0.3f, 0.3f), "pause.png");
        Button resume = new Button(new Vector2f(0.40f,-0.55f), 0.1f, "Resume", new Vector3f(0.7f,0,0), new Vector3f(0.9f,0,0), new Vector3f(0.9f,0.2f,0));
        Button mainMenu = new Button(new Vector2f(0.40f,-0.70f), 0.1f, "Main menu", new Vector3f(0.7f,0,0), new Vector3f(0.9f,0,0), new Vector3f(0.9f,0.2f,0));
        Button exit = new Button(new Vector2f(0.40f,-0.85f), 0.1f, "Exit", new Vector3f(0.7f,0,0), new Vector3f(0.9f,0,0), new Vector3f(0.9f,0.2f,0));

        resume.setStateOnClick(UIState.GAME);
        mainMenu.setStateOnClick(UIState.MAIN_MENU);
        exit.setStateOnClick(UIState.EXIT);

        menu.addButton(resume);
        menu.addButton(mainMenu);
        menu.addButton(exit);

        pause.addComponent(menu);

        pause.setManager(this);

    }

    public void initLevelChoose(){
        levelChoose = new UIFrame();

        Image background = new Image(new Vector2f(0,0), new Vector2f(1,1), "levelBack.png", false);
        Image layer = new Image(new Vector2f(0,0), new Vector2f(1,1), "levelLayer.png", false);

        LevelIcon initio = new LevelIcon(new Vector2f(-0.5f,0), new Vector2f(0.4f,0.4f), "an initio.png", "locked.png", true, false, 1);
        LevelIcon jasta = new LevelIcon(new Vector2f(0,0), new Vector2f(0.4f,0.4f), "alea jasta est.png", "locked.png", true, false, 2);
        LevelIcon hominus = new LevelIcon(new Vector2f(0.5f,0), new Vector2f(0.4f,0.4f), "homines.png", "locked.png", true, false, 3);

        LevelIcon initio1 = new LevelIcon(new Vector2f(0.5f,-1f), new Vector2f(0.4f,0.4f), "an initio.png", "locked.png", true, true, 4);
        LevelIcon jasta1 = new LevelIcon(new Vector2f(-0.5f,-1f), new Vector2f(0.4f,0.4f), "alea jasta est.png", "locked.png", true, true, 5);
        LevelIcon hominus1 = new LevelIcon(new Vector2f(0,-1f), new Vector2f(0.4f,0.4f), "homines.png", "locked.png", true, true, 6);


        LevelIcon initio2 = new LevelIcon(new Vector2f(0, -2f), new Vector2f(0.4f,0.4f), "an initio.png", "locked.png", true, true, 7);
        LevelIcon jasta2 = new LevelIcon(new Vector2f(0.5f,-2f), new Vector2f(0.4f,0.4f), "alea jasta est.png", "locked.png", true, true, 8);
        LevelIcon hominus2 = new LevelIcon(new Vector2f(-0.5f,-2f), new Vector2f(0.4f,0.4f), "homines.png", "locked.png", true, true, 9);

        RollPanel rollPanel = new RollPanel(-0.1f, UIComponent.Y_AXIS, -0.3f, 2f);

        rollPanel.addComponent(initio);
        rollPanel.addComponent(jasta);
        rollPanel.addComponent(hominus);

        rollPanel.addComponent(jasta1);
        rollPanel.addComponent(hominus1);
        rollPanel.addComponent(initio1);

        rollPanel.addComponent(hominus2);
        rollPanel.addComponent(initio2);
        rollPanel.addComponent(jasta2);

        Button back = new Button(new Vector2f(0.8f, -0.8f), 0.1f, "back", new Vector3f(0.7f,0,0), new Vector3f(0.9f,0,0), new Vector3f(0.9f,0.2f,0));
        back.setStateOnClick(UIState.MAIN_MENU);

        levelChoose.addComponent(background);

        levelChoose.addComponent(rollPanel);

        levelChoose.addComponent(layer);
        levelChoose.addComponent(back);

        levelChoose.setManager(this);
        levelChoose.setKeyAct(Input.KEY_ESCAPE, UIState.MAIN_MENU);
    }

    public void initSettings(){
        settings = new UIFrame();

        Image background = new Image(new Vector2f(0,0), new Vector2f(1,1), "settingsBack.png", false);
        Image layer = new Image(new Vector2f(0,0), new Vector2f(1,1), "settingsLayer.png", false);

        RollPanel rollPanel = new RollPanel(-0.1f, UIComponent.Y_AXIS, -0.2f, 0.8f);

        Label soundTitle = new Label(new Vector2f(-0.85f, 0.2f), 0.2f, "Sound", new Vector3f(0.6f, 0.2f, 0.3f));
        Label ambient = new Label(new Vector2f(-0.76f, 0f), 0.1f, "Ambient", new Vector3f(0.7f,0,0));
        Label effects = new Label(new Vector2f(-0.76f, -0.2f), 0.1f, "Effects", new Vector3f(0.7f,0,0));


        //todo: remove it
        Label graphicsTitle = new Label(new Vector2f(-0.85f, -0.5f), 0.2f, "Graphics", new Vector3f(0.6f, 0.2f, 0.3f));
        Label vSync = new Label(new Vector2f(-0.76f, -0.7f), 0.1f, "Vertical sync.", new Vector3f(0.7f,0,0));

        Label gameTitle = new Label(new Vector2f(-0.85f, -1f), 0.2f, "Game", new Vector3f(0.6f, 0.2f, 0.3f));
        Label difficult = new Label(new Vector2f(-0.76f, -1.2f), 0.1f, "Difficult", new Vector3f(0.7f,0,0));

        rollPanel.addComponent(soundTitle);
        rollPanel.addComponent(ambient);
        rollPanel.addComponent(effects);
        rollPanel.addComponent(graphicsTitle);
        rollPanel.addComponent(vSync);
        rollPanel.addComponent(gameTitle);
        rollPanel.addComponent(difficult);

        ScrollBar ambientBar = new ScrollBar(new Vector2f(0.55f,0.05f), "bar.png", "scroll.png", 0, 100, 20);
        ScrollBar effectBar = new ScrollBar(new Vector2f(0.55f,-0.15f), "bar.png", "scroll.png", 0, 100, 20);
        CheckBox checkBox = new CheckBox(new Vector2f(0.55f, -0.65f), "checkBack.png", "checkMark.png", true);
        ScrollBar difficultBar = new ScrollBar(new Vector2f(0.55f,-1.15f), "bar.png", "scroll.png", 1, 3, 20);
        rollPanel.addComponent(ambientBar);
        rollPanel.addComponent(effectBar);
        rollPanel.addComponent(checkBox);
        rollPanel.addComponent(difficultBar);


        Button back = new Button(new Vector2f(0.8f, -0.8f), 0.1f, "back", new Vector3f(0.7f,0,0), new Vector3f(0.9f,0,0), new Vector3f(0.9f,0.2f,0));
        back.setStateOnClick(UIState.MAIN_MENU);

        settings.addComponent(background);

        settings.addComponent(rollPanel);

        settings.addComponent(layer);

        settings.addComponent(back);

        settings.setManager(this);
        settings.setKeyAct(Input.KEY_ESCAPE, UIState.MAIN_MENU);
    }

    public void initTitles(){
        titles = new UIFrame();
        // ...
        // todo: auto rollable panel(speed..) - extends rollpanel?
        RollPanel panel = new RollPanel(-0.1f, UIComponent.Y_AXIS, -1.2f, 0.8f);

        panel.addComponent(new Label(new Vector2f(-0.6f, 0.2f), 0.2f, "Post Mortem", new Vector3f(0.6f, 0.2f, 0.3f)));
        panel.addComponent(new Label(new Vector2f(-0.6f, 0f), 0.1f, "videogame", new Vector3f(0.7f,0,0)));

        panel.addComponent(new Label(new Vector2f(-0.6f, -0.2f), 0.2f, "Director", new Vector3f(0.6f, 0.2f, 0.3f)));
        panel.addComponent(new Label(new Vector2f(-0.6f, -0.4f), 0.1f, "Slonsky", new Vector3f(0.7f,0,0)));

        titles.addComponent(panel);

        titles.setManager(this);
        titles.setKeyAct(Input.KEY_ESCAPE, UIState.MAIN_MENU);
    }

    public void playMenuTheme(){
        gameBackground.setLooping(true);
        gameBackground.play(menuTheme.getBufferId());
    }

    public void playLogo(){
        Sound theme = new Sound("res/sound/logo.wav");
        gameBackground.play(theme.getBufferId());
    }

    public UIFrame getMainFrame() {
        return mainFrame;
    }

    public UIState getOldState() {
        return oldState;
    }

    public boolean isPaused() {
        return paused;
    }
}
