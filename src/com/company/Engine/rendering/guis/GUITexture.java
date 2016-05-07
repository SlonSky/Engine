package com.company.Engine.rendering.guis;

import com.company.Engine.rendering.Transform;
import com.company.Engine.rendering.meshManagment.Texture;
import com.company.Engine.util.*;
import org.lwjgl.opengl.Display;

/**
 * Created by Slon on 11.04.2016.
 */
public class GUITexture {
    public static final float ASPECT_RATIO = (float)Display.getWidth()/(float)Display.getHeight();
    private Texture texture;
    private Transform transform;

    public GUITexture(Texture texture, Vector2f pos, Vector2f scale) {
        this.texture = texture;
        transform = new Transform();
        transform.setPosition(new Vector3f(pos.getX(), pos.getY(), 0));
        transform.setScale(new Vector3f(scale.getX() / ASPECT_RATIO, scale.getY(), 1));

//        setScale(new Vector2f(scale.getX()*ASPECT_RATIO, scale.getY()/ASPECT_RATIO));
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setPos(Vector2f pos) {
        transform.setPosition(new Vector3f(pos.getX(), pos.getY(), 0));
    }

    public void setScale(Vector2f scale) {
        transform.setScale(new Vector3f(scale.getX()/ ASPECT_RATIO, scale.getY(), 1));
    }

    public Transform getTransform() {
        return transform;
    }
}
