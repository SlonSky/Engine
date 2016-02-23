package com.company.Engine.components;

import com.company.Engine.core.Input;
import com.company.Engine.core.Window;
import com.company.Engine.util.Vector2f;
import com.company.Engine.util.Vector3f;

/**
 * Created by Slon on 11.02.2016.
 */
public class FreeLook {
    public static final Vector3f Y_AXIS = new Vector3f(0,1,0);

    private int mouseUnlockKey;
    private int mouseLockKey;

    private float sensitivity;
    private boolean mouseLocked;
    private Vector2f centerPos;

    public FreeLook(int mouseUnlockKey, int mouseLockKey, float sensitivity) {
        this.mouseUnlockKey = mouseUnlockKey;
        this.mouseLockKey = mouseLockKey;
        this.sensitivity = sensitivity;
        mouseLocked = false;
        centerPos = new Vector2f(Window.getWidth()/2, Window.getHeight()/2);
    }


    public void input(Vector3f forward, Vector3f up) {
        if (Input.getKey(mouseUnlockKey)) {
            Input.setCursor(true);
            mouseLocked = false;
        }
        if (Input.getMouse(mouseLockKey)) {
            Input.setCursor(false);
            Input.setMousePosition(centerPos);
            mouseLocked = true;
        }

        if (mouseLocked) {
            Vector2f deltaPos = Input.getMousePosition().sub(centerPos);
            boolean rotX = deltaPos.getY() != 0;
            boolean rotY = deltaPos.getX() != 0;

            if (rotX) {
                rotateX(forward, up, -deltaPos.getY() * sensitivity);
            }
            if (rotY) {
                rotateY(forward, up, deltaPos.getX() * sensitivity);
            }
            if (rotX || rotY) {
                Input.setMousePosition(centerPos);
            }
        }
    }

    private void rotateY(Vector3f forward, Vector3f up, float angle){
        Vector3f hAxis = Y_AXIS.cross(forward).normalized();
        forward = forward.rotate(angle, Y_AXIS).normalized();
        up = forward.cross(hAxis).normalized();
    }

    private void rotateX(Vector3f forward, Vector3f up, float angle){
        Vector3f hAxis = Y_AXIS.cross(forward).normalized();
        forward = forward.rotate(angle, hAxis).normalized();
        up = forward.cross(hAxis).normalized();
    }
}
