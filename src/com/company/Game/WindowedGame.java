package com.company.Game;

import com.company.Engine.audio.Sound;
import com.company.Engine.audio.Source;
import com.company.Engine.core.Game;
import com.company.Engine.rendering.Camera;
import com.company.Engine.rendering.RenderingEngine;
import com.company.Engine.rendering.text.Font;
import com.company.Engine.util.Quaternion;
import com.company.Engine.util.Vector2f;
import com.company.Engine.util.Vector3f;
import com.company.Engine.windows.*;

/**
 * Created by Slon on 27.04.2016.
 */
public class WindowedGame extends Game{

    private UIFrame mainFrame;
    private WindowListener listener;

    private UIFrame logo;
    private UIFrame mainMenu;
    private UIFrame settings;
    private UIFrame loading;
    private UIFrame levelChoose;
//    private UIFrame


    private Source gameBackground;
    private Sound menuTheme;

    private GameState state;

    // todo: system.ini text file with init params (editable in settings)

    @Override
    public void init() {

        camera = new Camera(new Vector3f(0,0,0),new Quaternion(0,0,0,1));
        setCamera(camera);



        listener = new WindowListener(engine);
        Font font = new Font("rus.png", "rus.fnt");
        UIFrame.setFont(font);
        UIFrame.setUiSource();
        UIFrame.setClickSound("res/sound/buttonclick.wav");
        UIFrame.setHoverSound("res/sound/menu_focus.wav");
        initLogo();
        initMainMenu();
        initLevelChoose();
        initSettings();

        gameBackground = new Source();
        menuTheme = new Sound("res/sound/mainMenuTheme.ogg");
        state = GameState.MENU;

        listener.alert(UIState.LOGO);

        // todo in logo: [LICENSE] content is using in educational purposes
        // todo: init gui in other thread while logo

//        mainFrame = initLogo();



    }

    @Override
    public void render(RenderingEngine renderingEngine) {
        switch (state){
            case GAME:
                renderingEngine.render(level);
                break;
            case MENU:
                renderingEngine.render(mainFrame);
                break;
            case PAUSE:
                renderingEngine.render(level);
                renderingEngine.render(mainFrame);
                break;
        }
    }

    @Override
    public void update() {
        switch (state){
            case GAME:
                level.update();
                break;
            case MENU:
                mainFrame.update();
                break;
            case PAUSE:
                mainFrame.update();
                break;
        }
    }

    public void playMenuTheme(){
        gameBackground.setLooping(true);
        gameBackground.play(menuTheme.getBufferId());
    }

    public void playLogo(){
        Sound theme = new Sound("res/sound/logo.wav");
        gameBackground.play(theme.getBufferId());
    }

    public void initLogo(){
        logo = new UIFrame();
        BlinkImage intro = new BlinkImage(new Vector2f(0,0), new Vector2f(0.8f,0.8f), "intro.png", true, 2.5);
        intro.setActState(UIState.MAIN_MENU);
        logo.addComponent(intro);
        logo.setListener(listener);
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

        mainMenu.setListener(listener);
    }

    public void initLevelChoose(){
        levelChoose = new UIFrame();

        Image background = new Image(new Vector2f(0,0), new Vector2f(1,1), "levelBack.png", false);
        Image layer = new Image(new Vector2f(0,0), new Vector2f(1,1), "levelLayer.png", false);

        Icon initio = new Icon(new Vector2f(-0.5f,0), new Vector2f(0.4f,0.4f), "an initio.png", true);
        Icon jasta = new Icon(new Vector2f(0,0), new Vector2f(0.4f,0.4f), "alea jasta est.png", true);
        Icon hominus = new Icon(new Vector2f(0.5f,0), new Vector2f(0.4f,0.4f), "homines.png", true);

        Icon initio1 = new Icon(new Vector2f(0.5f,-1f), new Vector2f(0.4f,0.4f), "an initio.png", true);
        Icon jasta1 = new Icon(new Vector2f(-0.5f,-1f), new Vector2f(0.4f,0.4f), "alea jasta est.png", true);
        Icon hominus1 = new Icon(new Vector2f(0,-1f), new Vector2f(0.4f,0.4f), "homines.png", true);

        Icon initio2 = new Icon(new Vector2f(0, -2f), new Vector2f(0.4f,0.4f), "an initio.png", true);
        Icon jasta2 = new Icon(new Vector2f(0.5f,-2f), new Vector2f(0.4f,0.4f), "alea jasta est.png", true);
        Icon hominus2 = new Icon(new Vector2f(-0.5f,-2f), new Vector2f(0.4f,0.4f), "homines.png", true);

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

        levelChoose.setListener(listener);
    }

    public void initSettings(){
        settings = new UIFrame();

        Image background = new Image(new Vector2f(0,0), new Vector2f(1,1), "settingsBack.png", false);
        Image layer = new Image(new Vector2f(0,0), new Vector2f(1,1), "settingsLayer.png", false);

        RollPanel rollPanel = new RollPanel(-0.1f, UIComponent.Y_AXIS, -0.2f, 0.8f);

        Label soundTitle = new Label(new Vector2f(-0.85f, 0.2f), 0.2f, "Sound", new Vector3f(0.6f, 0.2f, 0.3f));
        Label ambient = new Label(new Vector2f(-0.76f, 0f), 0.1f, "Ambient", new Vector3f(0.7f,0,0));
        Label effects = new Label(new Vector2f(-0.76f, -0.2f), 0.1f, "Effects", new Vector3f(0.7f,0,0));

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

        settings.setListener(listener);
    }

    public void setLogo(){
        mainFrame = logo;
        playLogo();
    }

    public void setMainMenu(){
        mainFrame = mainMenu;
    }

    public void setLevelChoose(){
        mainFrame = levelChoose;
    }

    public void setSettings(){
        mainFrame = settings;
    }

}
