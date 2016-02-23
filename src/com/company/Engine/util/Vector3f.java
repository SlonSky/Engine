package com.company.Engine.util;

/**
 * Created by Slon on 10.02.2016.
 */
public class Vector3f {
    private float x;
    private float y;
    private float z;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float dot(Vector3f r){
        return x * r.getX() + y * r.getY() + z * r.getZ();
    }

    public Vector3f cross(Vector3f r){
        return new Vector3f(
                y * r.getZ() - z * r.getY(),
                z * r.getX() - x * r.getZ(),
                x * r.getY() - y * r.getX()
        );
    }

    public float max(){
        return Math.max(x, Math.max(y , z));
    }

    public float length(){
        return (float)Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3f normalized(){
        float length = length();
        return new Vector3f(x / length, y / length, z / length);
    }

    public Vector3f rotate(float angle, Vector3f axis){
        float sinHafAngle = (float)Math.sin(Math.toRadians(angle/2));
        float cosHafAngle = (float)Math.cos(Math.toRadians(angle / 2));

        float rX = axis.getX() * sinHafAngle;
        float rY = axis.getY() * sinHafAngle;
        float rZ = axis.getZ() * sinHafAngle;
        float rW = cosHafAngle;

        Quaternion rotation = new Quaternion(rX, rY, rZ, rW);
        Quaternion conjugate = rotation.conjugate();
        Quaternion w = rotation.mul(this).mul(conjugate);
        return new Vector3f(w.getX(), w.getY(), w.getZ());
    }

    public Vector3f add(Vector3f r){
        return new Vector3f(x + r.getX(), y + r.getY(), z + r.getZ());
    }

    public Vector3f add(float r){
        return new Vector3f(x + r, y + r, z + r);
    }

    public Vector3f sub(Vector3f r){
        return new Vector3f(x - r.getX(), y - r.getY(), z - r.getZ());
    }

    public Vector3f sub(float r){
        return new Vector3f(x - r, y - r, z - r);
    }

    public Vector3f mul(Vector3f r){
        return new Vector3f(x * r.getX(), y * r.getY(), z * r.getZ());
    }

    public Vector3f mul(float r){
        return new Vector3f(x * r, y * r, z * r);
    }

    public Vector3f div(Vector3f r){
        return new Vector3f(x / r.getX(), y / r.getY(), z / r.getZ());
    }

    public Vector3f div(float r){
        return new Vector3f(x / r, y / r, z / r);
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

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void set(Vector3f r){
        this.z = r.getZ();
        this.y = r.getY();
        this.x = r.getX();
    }

    @Override
    public String toString(){
        return "(" + x + " " + y + " " + z + ")";
    }

    public boolean equals(Object v){
        return x == ((Vector3f)v).getX() && y == ((Vector3f)v).getY() && z == ((Vector3f)v).getZ();
    }
}
