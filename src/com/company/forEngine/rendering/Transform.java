package com.company.forEngine.rendering;

import com.company.forEngine.util.Matrix4f;
import com.company.forEngine.util.Vector3f;

/**
 * Created by Slon on 10.02.2016.
 */
public class Transform {
    private static Camera camera;

    private static float zNear;
    private static float zFar;
    private static float width;
    private static float height;
    private static float fov;

    private Vector3f position;
    private Vector3f rotation;
    private Vector3f scale;

    public Transform(Vector3f position, Vector3f rotation, Vector3f scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public Transform(){
        this(new Vector3f(0,0,0), new Vector3f(0,0,0), new Vector3f(1,1,1));
    }

    public Matrix4f getTransformation(){
        Matrix4f translationMatrix = new Matrix4f().initTranslation(position.getX(), position.getY(), position.getZ());
        Matrix4f rotationMatrix = new Matrix4f().initRotation(rotation.getX(), rotation.getY(), rotation.getZ());
        Matrix4f scaleMatrix = new Matrix4f().initScale(scale.getX(), scale.getY(), scale.getZ());
        return translationMatrix.mul(rotationMatrix.mul(scaleMatrix));
    }

    public Matrix4f getProjectedTransformation(){
        Matrix4f transformationMatrix = getTransformation();
        Matrix4f projectionMatrix = new Matrix4f().initProjection(fov, width, height, zNear, zFar);
        Matrix4f cameraRotation = new Matrix4f().initCamera(camera.getForward(), camera.getUp());
        Matrix4f cameraTranslation = new Matrix4f().initTranslation(-camera.getPos().getX(), -camera.getPos().getY(), -camera.getPos().getZ());

        return projectionMatrix.mul(cameraRotation.mul(cameraTranslation.mul(transformationMatrix)));
    }

    public static Camera getCamera() {
        return camera;
    }

    public static void setCamera(Camera camera) {
        Transform.camera = camera;
    }

    public static void setProjection(float fov, float width, float height, float zNear, float zFar){
        Transform.fov = fov;
        Transform.width = width;
        Transform.height = height;
        Transform.zNear = zNear;
        Transform.zFar = zFar;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }
}
