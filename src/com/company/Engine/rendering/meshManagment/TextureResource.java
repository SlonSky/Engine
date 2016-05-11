package com.company.Engine.rendering.meshManagment;

import com.company.Engine.util.Vector2f;

/**
 * Created by Slon on 21.03.2016.
 */
public class TextureResource {
    private int id;
    private Vector2f size;

    public TextureResource(int id, Vector2f size) {
        this.id = id;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vector2f getSize() {
        return size;
    }
}
