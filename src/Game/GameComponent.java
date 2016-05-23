package Game;

import Engine.rendering.RenderingEngine;
import Engine.rendering.Shader;
import Engine.rendering.Transform;

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
