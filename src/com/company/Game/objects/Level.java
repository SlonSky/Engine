package com.company.Game.objects;

import com.company.Engine.rendering.RenderingEngine;
import com.company.Engine.rendering.light.Light;
import com.company.Engine.rendering.skybox.SkyBox;
import com.company.Engine.rendering.text.Font;
import com.company.Engine.rendering.text.Text;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Slon on 07.03.2016.
 */
public class Level {

    // todo: triggers, respawn point
    private ArrayList<GameObject> objects;
    private ArrayList<Light> lights;
    private SkyBox skyBox;

    private Text text;
    private Font font;

    public Level(SkyBox skyBox, ArrayList<GameObject> objects, ArrayList<Light> lights) {
        this.skyBox = skyBox;
        this.objects = objects;
        this.lights = lights;
    }

    public void update(){
        for(GameObject object: objects){
            object.update();
        }
    }

    public void input(){
        for(GameObject object: objects){
            object.input();
        }
    }
    public SkyBox getSkyBox() {
        return skyBox;
    }

    public ArrayList<GameObject> getObjects() {
        return objects;
    }

    public ArrayList<Light> getLights() {
        return lights;
    }

    public Text getText() {
        return text;
    }

    public Font getFont() {
        return font;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public void setFont(Font font) {
        this.font = font;
    }
}
