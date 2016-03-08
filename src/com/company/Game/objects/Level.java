package com.company.Game.objects;

import com.company.Engine.rendering.RenderingEngine;
import com.company.Engine.rendering.light.Light;

import java.util.ArrayList;

/**
 * Created by Slon on 07.03.2016.
 */
public class Level {
    private ArrayList<GameObject> objects;
    private ArrayList<Light> lights;

    public Level(ArrayList<GameObject> objects, ArrayList<Light> lights) {
        this.objects = objects;
        this.lights = lights;
    }

    public ArrayList<GameObject> getObjects() {
        return objects;
    }

    public ArrayList<Light> getLights() {
        return lights;
    }
}
