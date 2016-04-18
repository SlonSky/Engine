package com.company.Engine.core;

import com.company.Engine.util.Vector3f;

/**
 * Created by Slon on 21.03.2016.
 */
public class MoveControl extends GameComponent{
    private static final Vector3f Y_AXIS = new Vector3f(0,1,0);

    private int forward;
    private int backward;
    private int left;
    private int right;
    private int jump;
    private float speed;
    private boolean shoot;

    private Vector3f translation;

    public MoveControl(int forward, int backward, int left, int right, int jump, float speed) {
        this.forward = forward;
        this.backward = backward;
        this.left = left;
        this.right = right;
        this.speed = speed;
    }

    public void input(){
        float movAmt = (float)(speed * Time.getDelta());
        Vector3f rightVec =  getTransform().getRotation().getRight().normalized();
        if(Input.getKey(forward)){
            translation.set(translation.add(getTranslation(rightVec.cross(Y_AXIS), movAmt)));
        }
        if(Input.getKey(backward)){
            translation.set(translation.add(getTranslation(rightVec.cross(Y_AXIS), -movAmt)));
        }
        if(Input.getKey(right)){
            translation.set(translation.add(getTranslation(rightVec, movAmt)));
        }
        if(Input.getKey(left)){
            translation.set(translation.add(getTranslation(rightVec, -movAmt)));
        }
        if(Input.getKeyDown(jump)){
            translation.set(translation.add(getTranslation(Y_AXIS, 9*movAmt)));
        }


        // template!
        if(Input.getKey(Input.KEY_U)){
            translation.set(translation.add(getTranslation(Y_AXIS, 3*movAmt)));
        }

        if(Input.getKey(Input.KEY_J)){
            translation.set(translation.add(getTranslation(Y_AXIS, -movAmt)));
        }

    }

    @Override
    public void update() {
        super.update();

        move(translation);
        translation = new Vector3f(0,0,0);
    }

    private void move(Vector3f dir, float amt){
        getTransform().setPosition(getTransform().getPosition().add(dir.mul(amt)));
    }

    private void move(Vector3f translation){
        getTransform().setPosition(getTransform().getPosition().add(translation));
    }

    private Vector3f getTranslation(Vector3f dir, float amt){
        return (dir.mul(amt));
    }

    public Vector3f getTranslation() {
        return translation;
    }

    public void setTranslation(Vector3f translation) {
        this.translation = translation;
    }

    @Override
    public void setParent(GameObject parent) {
        super.setParent(parent);
        translation = getTransform().getPosition();

    }
}
