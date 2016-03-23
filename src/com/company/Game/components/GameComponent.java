package com.company.Game.components;

import com.company.Engine.rendering.RenderingEngine;
import com.company.Engine.rendering.Shader;
import com.company.Engine.rendering.Transform;
import com.company.Game.objects.GameObject;

/**
 * Created by Slon on 21.03.2016.
 */
public abstract class GameComponent {
    private GameObject parent;

    public void input(){}
    public void update(){}
    public void render(Shader shader, RenderingEngine renderingEngine){}


    public Transform getTransform(){
        return parent.getTransform();
    }

    public GameObject getParent() {
        return parent;
    }

    public void setParent(GameObject parent) {
        this.parent = parent;
    }
}
