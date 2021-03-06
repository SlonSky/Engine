package Engine.rendering;

import Engine.util.Matrix4f;
import Engine.util.Quaternion;
import Engine.util.Vector3f;

/**
 * Created by Slon on 10.02.2016.
 */
public class Transform {
    private static Camera camera;
    private static final float FRUSTUM_BACKWARD = 3;

    private static float zNear;
    private static float zFar;
    private static float width;
    private static float height;
    private static float fov;

    private Vector3f position;
    private Quaternion rotation;
    private Vector3f scale;

    private static Matrix4f projectionMatrix;

    public Transform(Vector3f position, Quaternion rotation, Vector3f scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public Transform(){
        this(new Vector3f(0,0,0), new Quaternion(0,0,0,1), new Vector3f(1,1,1));
    }

    public Matrix4f getTransformation(){
        Matrix4f translationMatrix = new Matrix4f().initTranslation(position.getX(), position.getY(), position.getZ());
        Matrix4f rotationMatrix = rotation.toRotationMatrix();
        Matrix4f scaleMatrix = new Matrix4f().initScale(scale.getX(), scale.getY(), scale.getZ());
        return translationMatrix.mul(rotationMatrix.mul(scaleMatrix));
    }

    public Matrix4f getProjectedTransformation(){
        Matrix4f transformationMatrix = getTransformation();
//        Matrix4f cameraRotation = new Matrix4f().initRotation(camera.getForward(), camera.getUp(), camera.getRight());
        Matrix4f cameraRotation = camera.getRot().conjugate().toRotationMatrix();
        Matrix4f cameraTranslation = new Matrix4f().initTranslation(-camera.getPos().getX(), -camera.getPos().getY(), -camera.getPos().getZ());

        return projectionMatrix.mul(cameraRotation.mul(cameraTranslation.mul(transformationMatrix)));
    }

    public Matrix4f getProjectedBillBoardTransformation(){
        Matrix4f transformationMatrix = getTransformation();
        Matrix4f cameraRotation = camera.getRot().conjugate().toRotationMatrix();
        Matrix4f cameraTranslation = new Matrix4f().initTranslation(-camera.getPos().getX(), -camera.getPos().getY(), -camera.getPos().getZ());

        Matrix4f mv = cameraRotation.mul(cameraTranslation.mul(transformationMatrix));
        mv.set(0,0, 1);
        mv.set(0,1, 0);
        mv.set(0,2, 0);
        mv.set(1,0, 0);
        mv.set(1,1, 1);
        mv.set(1,2, 0);
        mv.set(2,0, 0);
        mv.set(2,1, 0);
        mv.set(2,2, 1);

        return projectionMatrix.mul(mv);

    }

    public static Matrix4f getProjectedModelView(){
        Matrix4f id = new Matrix4f().initIdentity();
        Matrix4f cameraRotation = new Matrix4f().initRotation(camera.getForward(), camera.getUp());
        Vector3f camPos = camera.getPos().sub(camera.getForward().normalized().mul(FRUSTUM_BACKWARD));
        Matrix4f cameraTranslation = new Matrix4f().initTranslation(-camPos.getX(), -camPos.getY(), -camPos.getZ());
        return projectionMatrix.mul((cameraRotation.mul(cameraTranslation).mul(id)));
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

    public static void setProjection(float fov, float width, float height, float zNear, float zFar){
        Transform.fov = fov;
        Transform.width = width;
        Transform.height = height;
        Transform.zNear = zNear;
        Transform.zFar = zFar;
        projectionMatrix = new Matrix4f().initPerspectiveProjection(fov, width, height, zNear, zFar);
    }

    public void rotate(Quaternion rotation){
        Quaternion oldRot = this.rotation;
        this.rotation = oldRot.mul(rotation).normalized();
    }

    public Quaternion getLookAtRotation(Vector3f point, Vector3f up) {
        return new Quaternion(new Matrix4f().initRotation(point.sub(position).normalized(), up));
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Quaternion getRotation() {
        return rotation;
    }

    public void setRotation(Quaternion rotation) {
        this.rotation = rotation;
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }

    public Transform clone(){
        return new Transform(
                new Vector3f(position.getX(), position.getY(), position.getZ()),
                new Quaternion(rotation.getX(), rotation.getY(), rotation.getZ(), rotation.getW()),
                new Vector3f(scale.getX(), scale.getY(), scale.getZ()));
    }
}
