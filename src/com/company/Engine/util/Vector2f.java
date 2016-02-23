package com.company.Engine.util;

/**
 * Created by Slon on 11.02.2016.
 */
public class Vector2f {
    private float x;
    private float y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float dot(Vector2f r){
        return x * r.getX() + y * r.getY();
    }

    public float length(){
        return (float)Math.sqrt(x * x + y * y);
    }

    public Vector2f normalized(){
        float length = length();
        return new Vector2f(x / length, y / length);
    }

    public Vector2f add(Vector2f r){
        return new Vector2f(x + r.getX(), y + r.getY());
    }

    public Vector2f add(float r){
        return new Vector2f(x + r, y + r);
    }

    public Vector2f sub(Vector2f r){
        return new Vector2f(x - r.getX(), y - r.getY());
    }

    public Vector2f sub(float r){
        return new Vector2f(x - r, y - r);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean equals(Object v){
        return x == ((Vector2f)v).getX() && y == ((Vector2f)v).getY();
    }
}
