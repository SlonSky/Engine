package com.company.Game.components;

import com.company.Engine.core.Input;
import com.company.Engine.core.Time;
import com.company.Engine.util.Vector3f;

/**
 * Created by Slon on 21.03.2016.
 */
public class MoveControl extends GameComponent{
    private int forward;
    private int backward;
    private int left;
    private int right;
    private float speed;

    public MoveControl(int forward, int backward, int left, int right, float speed) {
        this.forward = forward;
        this.backward = backward;
        this.left = left;
        this.right = right;
        this.speed = speed;
    }

    public void input(){
        float movAmt = (float)(speed * Time.getDelta());
        if(Input.getKey(forward)){
            move(getTransform().getRotation().getForward().normalized(), movAmt);
        }
        if(Input.getKey(backward)){
            move(getTransform().getRotation().getForward().normalized(), -movAmt);
        }
        if(Input.getKey(right)){
            move(getTransform().getRotation().getRight().normalized(), movAmt);
        }
        if(Input.getKey(left)){
            move(getTransform().getRotation().getLeft().normalized(), movAmt);
        }
    }

    private void move(Vector3f dir, float amt){
        getTransform().setPosition(getTransform().getPosition().add(dir.mul(amt)));
    }
}
