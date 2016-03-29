package com.company.Engine.rendering.text;

/**
 * Created by Slon on 27.03.2016.
 */
public class AtlasChar {
    private int id;
    private float x;
    private float y;
    private float width;
    private float height;
    private float xOffset;
    private float yOffset;
    private float xAdvance;

    public AtlasChar(int id, float x, float y, float width, float height, float xOffset, float yOffset, float xAdvance) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.xAdvance = xAdvance;
    }

    public int getId() {
        return id;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getxOffset() {
        return xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public float getxAdvance() {
        return xAdvance;
    }

    public String toString(){
        return "id="+id+" x="+x+" y="+y+" width="+width+" height="+height+" xOffset="+xOffset+" y="+yOffset+" xAdvance="+xAdvance+"\n";
    }
}
