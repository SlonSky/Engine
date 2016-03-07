package com.company.Engine.rendering;

import com.company.Engine.util.Matrix4f;
import com.company.Engine.util.Vector3f;

/**
 * Created by Slon on 10.02.2016.
 */
public class Transform {
    private static Camera camera;

    private static boolean orthographic = false;

    private static float zNear;
    private static float zFar;
    private static float width;
    private static float height;
    private static float fov;

    private Vector3f position;
    private Vector3f rotation;
    private Vector3f scale;

    private static Matrix4f projectionMatrix;

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
        Matrix4f cameraRotation = new Matrix4f().initCamera(camera.getForward(), camera.getUp());
        Matrix4f cameraTranslation = new Matrix4f().initTranslation(-camera.getPos().getX(), -camera.getPos().getY(), -camera.getPos().getZ());

        return projectionMatrix.mul(cameraRotation.mul(cameraTranslation.mul(transformationMatrix)));
    }

    public static Matrix4f getProjectedModelView(){
        Matrix4f id = new Matrix4f().initIdentity();
        Matrix4f cameraRotation = new Matrix4f().initCamera(camera.getForward(), camera.getUp());
        Matrix4f cameraTranslation = new Matrix4f().initTranslation(-camera.getPos().getX(), -camera.getPos().getY(), -camera.getPos().getZ());
        return projectionMatrix.mul((cameraRotation.mul(cameraTranslation)).mul(id));
//        Matrix4f orient = new Matrix4f().initCamOrientation(camera.getForward(), camera.getUp());
//        Matrix4f transl = new Matrix4f().initCamTrans(camera.getPos());
//        return projectionMatrix.mul((orient.mul(transl)).mul(new Matrix4f()));
    }

    public static Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    public static Camera getCamera() {
        return camera;
    }

    public static void setCamera(Camera camera) {
        Transform.camera = camera;
    }

    public static void setOrthographicProjection(float left, float right, float bottom, float top, float near, float far){
        projectionMatrix = new Matrix4f().initOrthographic(left, right, bottom, top, near, far);
    }

    public static void backToPerspective(){
        projectionMatrix = new Matrix4f().initPerspectiveProjection(fov, width, height, zNear, zFar);
    }

    public static void setProjection(float fov, float width, float height, float zNear, float zFar){
        Transform.fov = fov;
        Transform.width = width;
        Transform.height = height;
        Transform.zNear = zNear;
        Transform.zFar = zFar;
        projectionMatrix = new Matrix4f().initPerspectiveProjection(fov, width, height, zNear, zFar);
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

    public void increaseRot(Vector3f amt){
        rotation = rotation.add(amt);
    }

    public static boolean isOrthographic() {
        return orthographic;
    }

    public static void setOrthographic(boolean orthographic) {
        Transform.orthographic = orthographic;
    }
}
