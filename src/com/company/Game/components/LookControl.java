package com.company.Game.components;

import com.company.Engine.core.Input;
import com.company.Engine.core.Window;
import com.company.Engine.util.Quaternion;
import com.company.Engine.util.Vector2f;
import com.company.Engine.util.Vector3f;

/**
 * Created by Slon on 21.03.2016.
 */
public class LookControl extends GameComponent{
    private static final Vector3f Y_AXIS = new Vector3f(0,1,0);
    private static final Vector2f CENTER_POS = new Vector2f(Window.getWidth()/2, Window.getHeight()/2);

    private int lockKey;
    private int unlockKey;

    private float sensitivity;
    private boolean mouseLocked = false;

    public LookControl(int lockKey, int unlockKey, float sensitivity) {
        this.lockKey = lockKey;
        this.unlockKey = unlockKey;
        this.sensitivity = sensitivity;
    }

    public void input(){
        if(Input.getKey(unlockKey)){
            Input.setCursor(true);
            mouseLocked = false;
        }
        if(Input.getMouse(lockKey)){
            Input.setCursor(false);
            Input.setMousePosition(CENTER_POS);
            mouseLocked = true;
        }

        if(mouseLocked){
            Vector2f deltaPos = Input.getMousePosition().sub(CENTER_POS);
            boolean rotX = deltaPos.getY() != 0;
            boolean rotY = deltaPos.getX() != 0;

            if(rotX){
                rotateX(-deltaPos.getY() * sensitivity);
            }
            if(rotY){
                rotateY(deltaPos.getX() * sensitivity);
            }
            if(rotX || rotY){
                Input.setMousePosition(CENTER_POS);
            }
        }
    }

    public boolean isMouseLocked() {
        return mouseLocked;
    }

    public void setMouseLocked(boolean mouseLocked) {
        this.mouseLocked = mouseLocked;
    }

    private void rotateY(float angle){
        getTransform().setRotation(
                (new Quaternion(Y_AXIS, (float) Math.toRadians(angle))).mul(getTransform().getRotation()).normalized());
    }

    private void rotateX(float angle){
        getTransform().setRotation(
                (new Quaternion(getTransform().getRotation().getRight(),
                        (float) Math.toRadians(angle))).mul(getTransform().getRotation()).normalized());
    }

}
