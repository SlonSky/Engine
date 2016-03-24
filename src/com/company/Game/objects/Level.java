package com.company.Game.objects;

import com.company.Engine.rendering.RenderingEngine;
import com.company.Engine.rendering.light.Light;
import com.company.Engine.rendering.skybox.SkyBox;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Slon on 07.03.2016.
 */
public class Level {
    private ArrayList<GameObject> objects;
    private ArrayList<Light> lights;
    private SkyBox skyBox;

    public Level(SkyBox skyBox, ArrayList<GameObject> objects, ArrayList<Light> lights) {
        this.skyBox = skyBox;
        this.objects = objects;
        this.lights = lights;
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
}
