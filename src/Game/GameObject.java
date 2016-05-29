package Game;

import Engine.rendering.RenderingEngine;
import Engine.rendering.Shader;
import Engine.rendering.Transform;

import java.util.ArrayList;

/**
 * Created by Slon on 21.03.2016.
 */
public abstract class GameObject {
    private Transform transform;
    private ArrayList<GameComponent> components;

    protected boolean remove = false;

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

    public boolean isToRemove() {
        return remove;
    }

    public void remove(){};
}
