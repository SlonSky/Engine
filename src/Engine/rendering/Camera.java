package Engine.rendering;

import Engine.util.Quaternion;
import Engine.util.Vector3f;

/**
 * Created by Slon on 11.02.2016.
 * TODO: make it component
 */
public class Camera {

    private Vector3f pos;
    private Quaternion rot;

    public Camera(Vector3f pos, Quaternion rot) {
        this.pos = pos;
        this.rot = rot;
    }

    public Quaternion getRot() {
        return rot;
    }

    public void setRot(Quaternion rot) {
        this.rot = rot;
    }

    public Vector3f getLeft(){
        return rot.getLeft().normalized();
    }

    public Vector3f getRight(){
        return rot.getRight().normalized();
    }

    public Vector3f getPos() {
        return pos;
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
    }

    public Vector3f getForward() {
        return rot.getForward();
    }

    public Vector3f getUp() {
        return rot.getUp();
    }
}
