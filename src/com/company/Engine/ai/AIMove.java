package com.company.Engine.ai;

import com.company.Engine.core.Time;
import com.company.Engine.util.Vector3f;
import com.company.Engine.core.GameComponent;

import java.util.Random;

/**
 * Created by Slon on 22.03.2016.
 */
public class AIMove extends GameComponent {
    /**
     * todo:
     *  ai observer
     *  level grid - graph for pathfinding
     *  A*
     *  ***************
     *  **ooooooooo**o*
     *  **ooooooo***oo*
     *  **ooo**oo***oo*
     *  **ooo**oo***oo*
     *  **ooooooo***oo*
     *  **ooooooo***oo*
     *  *******ooooooo*
     *  *******ooooooo*
     *  ***************
     *  keep all paths
     *
     *  attack/sleep distance
     *
     */

    // temp!
    public static Vector3f moveTo = new Vector3f(0,0,0);

    private Random r = new Random();
    private float speed = r.nextInt(10);
    public void update(){
        if(getTransform().getPosition().sub(moveTo).abs().length() > 3) {
            getTransform().setPosition(getTransform().getPosition().add(moveTo.sub(getTransform().getPosition()).normalized().mul(new Vector3f(speed * (float) Time.getDelta(), 0, speed * (float) Time.getDelta()))));
        }
    }
}
