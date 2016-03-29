package com.company.Game.components;

import com.company.Engine.core.Time;
import com.company.Engine.util.Quaternion;
import com.company.Engine.util.Vector3f;

import java.util.Random;

/**
 * Created by Slon on 22.03.2016.
 */
public class AIMove extends GameComponent {

    private Random r = new Random();
    private float speed = r.nextInt(10);
    public void update(){
        getTransform().setPosition(getTransform().getPosition().add(speed*(float)Time.getDelta()));
    }
}
