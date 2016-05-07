package com.company.Game;

import com.company.Engine.core.*;
import com.company.Engine.rendering.*;
import com.company.Engine.rendering.light.Light;
import com.company.Engine.rendering.meshManagment.Material;
import com.company.Engine.rendering.meshManagment.Mesh;
import com.company.Engine.rendering.meshManagment.Texture;
import com.company.Engine.rendering.skybox.SkyBox;
import com.company.Engine.util.Quaternion;
import com.company.Engine.util.Vector3f;
import com.company.Game.entities.Decoration;
import com.company.Game.entities.Player;

import java.util.ArrayList;

/**
 * Created by Slon on 12.04.2016.
 */
public class UserGame extends Game {

    private enum GAME_STATE{
        MAIN_MENU,
        LEVEL_MENU,
        LOADING,
        SETTINGS_MENU,
        TITLE,
        GAME,
        PAUSE,
        EXIT
    }

    private GAME_STATE state;

    // private ArrayList<Menu> menus;





    @Override
    public void init() {

        /**
         * Camera/Transform initializing (todo: are we need it?)
         */
        Camera camera = new Camera(new Vector3f(0,0,0), new Quaternion(0,0,0,1));
        setCamera(camera);

        /**
         *  GUI initialization:
         *  - main menu
         *  - level menu
         *  - loading menu
         *  - settings menu
         *  - title
         */

        /**
         * Some other initializing
         * -...
         */
        loadLevel("prologue.level");
    }

    public void loadLevel(String level){

        // read level
        // load level
        // loading gui controlling


        // TEMPORARY!!!
        SkyBox skyBox = new SkyBox("2");
        ArrayList<GameObject> objects = new ArrayList<>();
        ArrayList<Light> lights = new ArrayList<>();
        Player player = new Player(camera, new Transform(new Vector3f(0f, 10, 0f), camera.getRot(), new Vector3f(1,1,1)),
                new Graphic(new Mesh("AK1.obj"), new Material(new Texture("ak.png"))),
                new Vector3f(0.3f,0.7f,0.3f));
        objects.add(player);
        Decoration d = new Decoration(
                new Transform(new Vector3f(20, 5, -10), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(-90)), new Vector3f(1,1,1)),
                new Graphic(new Mesh("building.obj"), new Material(new Texture("building.png"), 0, 8)),
                new Vector3f(5, 5, 5),
                new Vector3f(5, 5, 5));
        objects.add(d);

        this.level = new Level(skyBox, objects, lights);
        state = GAME_STATE.GAME;
    }

    private void userInput(){
        if(Input.getKey(Input.KEY_Q)){
            state = GAME_STATE.EXIT;
        }
    }

    @Override
    public void render(RenderingEngine renderingEngine) {

        switch (state){
            case GAME:
                 renderingEngine.render(level);
                 break;
            case PAUSE:
                renderingEngine.render(level);
                // renderingEngine.render(pauseMenu);
                break;
            case LOADING:
                // renderingEngine.render(loading);
                break;
            case MAIN_MENU:
                // renderingEngine.render(mainMenu);
                break;
            case LEVEL_MENU:
                // renderingEngine.render(levelMenu);
                break;
            case SETTINGS_MENU:
                // renderingEngine.render(levelMenu);
                break;
            case TITLE:
                // renderingEngine.render(title);
                break;
            case EXIT:
                engine.stop();
                break;
        }
    }

    @Override
    public void update() {
        switch (state){
            case GAME:
                level.update();
                break;
            case PAUSE:
                // update(pauseMenu);
                break;
            case LOADING:
                //
                break;
            case MAIN_MENU:
                // mainMenu.update
                break;
            case LEVEL_MENU:
                //
                break;
            case SETTINGS_MENU:
                //
                break;
            case TITLE:
                //
                break;
        }
    }

    @Override
    public void input() {

        userInput();

        switch (state){
            case GAME:
                level.input();
                break;
            case PAUSE:
                // input(pauseMenu);`
                break;
            case LOADING:
                //
                break;
            case MAIN_MENU:
                // mainMenu.input()
                break;
            case LEVEL_MENU:
                //
                break;
            case SETTINGS_MENU:
                //
                break;
            case TITLE:
                //
                break;
        }
    }
}
