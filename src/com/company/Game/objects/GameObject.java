package com.company.Game.objects;

import com.company.Engine.rendering.RenderingEngine;
import com.company.Engine.rendering.Shader;
import com.company.Engine.rendering.Transform;
import com.company.Game.components.GameComponent;

import java.util.ArrayList;

/**
 * Created by Slon on 21.03.2016.
 */
public abstract class GameObject {
    private Transform transform;
    private ArrayList<GameComponent> components;

    public GameObject(Transform transform){
        this.transform = transform;
        components = new ArrayList<>();
    }

    public void input(){
        for (GameComponent component: components){
            component.input();
        }
    }

    public void update(){
        for (GameComponent component: components){
            component.update();
        }
    }

    public void render(Shader shader, RenderingEngine renderingEngine){
        for (GameComponent component: components){
            component.render(shader, renderingEngine);
        }
    }

    public void addComponent(GameComponent component){
        components.add(component);
        component.setParent(this);
    }

    public Transform getTransform() {
        return transform;
    }
}
