package com.company.Engine.rendering;

import com.company.Engine.components.FreeLook;
import com.company.Engine.core.Input;
import com.company.Engine.core.Time;
import com.company.Engine.core.Window;
import com.company.Engine.util.Vector2f;
import com.company.Engine.util.Vector3f;

/**
 * Created by Slon on 11.02.2016.
 */
public class Camera {
    public static final Vector3f Y_AXIS = new Vector3f(0,1,0);

    private Vector3f pos;
    private Vector3f forward;
    private Vector3f up;

    private FreeLook freeLookComponent;

    public Camera(Vector3f pos, Vector3f forward, Vector3f up) {
        this.pos = pos;
        this.forward = forward;
        this.up = up;
        freeLookComponent = new FreeLook(Input.KEY_ESCAPE, 0, 0.5f);
    }

    public Camera(){
        this(new Vector3f(0,0,0), new Vector3f(0,0,1), new Vector3f(0,1,0));
    }

    boolean mouseLocked = false;
    Vector2f centerPos = new Vector2f(Window.getWidth()/2, Window.getHeight()/2);
    public void input(){

        float movAmt = (float)(10 * Time.getDelta());
        if(Input.getKey(Input.KEY_W)){
            move(getForward(), movAmt);
        }
        if(Input.getKey(Input.KEY_S)){
            move(getForward(), -movAmt);
        }
        if(Input.getKey(Input.KEY_D)){
            move(getRight(), movAmt);
        }
        if(Input.getKey(Input.KEY_A)){
            move(getLeft(), movAmt);
        }

//        TODO: object/component system!
//        freeLookComponent.input(forward, up);
//        System.out.println(forward + " " + up);

        float sensitivity = 0.5f;
        if(Input.getKey(Input.KEY_ESCAPE)){
            Input.setCursor(true);
            mouseLocked = false;
        }
        if(Input.getMouse(0)){
            Input.setCursor(false);
            Input.setMousePosition(centerPos);
            mouseLocked = true;
        }

        if(mouseLocked){
            Vector2f deltaPos = Input.getMousePosition().sub(centerPos);
            boolean rotX = deltaPos.getY() != 0;
            boolean rotY = deltaPos.getX() != 0;

            if(rotX){
                rotateX(-deltaPos.getY() * sensitivity);
            }
            if(rotY){
                rotateY(deltaPos.getX() * sensitivity);
            }
            if(rotX || rotY){
                Input.setMousePosition(centerPos);
            }
        }
    }

    private void move(Vector3f dir, float amt){
        pos = pos.add(dir.mul(amt));
    }

    private void rotateY(float angle){
        Vector3f hAxis = Y_AXIS.cross(forward).normalized();
        forward = forward.rotate(angle, Y_AXIS).normalized();
        up = forward.cross(hAxis).normalized();
    }

    private void rotateX(float angle){
        Vector3f hAxis = Y_AXIS.cross(forward).normalized();
        forward = forward.rotate(angle, hAxis).normalized();
        up = forward.cross(hAxis).normalized();
    }

    public Vector3f getLeft(){
        return forward.cross(up).normalized();
    }

    public Vector3f getRight(){
        return up.cross(forward).normalized();
    }

    public Vector3f getPos() {
        return pos;
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
    }

    public Vector3f getForward() {
        return forward;
    }

    public void setForward(Vector3f forward) {
        this.forward = forward;
    }

    public Vector3f getUp() {
        return up;
    }

    public void setUp(Vector3f up) {
        this.up = up;
    }
}
