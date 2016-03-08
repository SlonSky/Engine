package com.company.Engine.util;

/**
 * Created by Slon on 07.03.2016.
 */
public class Plane {
    private float a;
    private float b;
    private float c;
    private float d;

    public Plane(float a, float b, float c, float d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public float length(){
        return (float)Math.sqrt(a * a + b * b + c * c);
    }

    public Plane normalized(){
        float length = length();
        return new Plane(a/length, b/length, c/length, d/length);
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public float getC() {
        return c;
    }

    public void setC(float c) {
        this.c = c;
    }

    public float getD() {
        return d;
    }

    public void setD(float d) {
        this.d = d;
    }
}
