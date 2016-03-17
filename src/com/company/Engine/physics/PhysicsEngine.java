package com.company.Engine.physics;

import com.company.Engine.rendering.Box;
import com.company.Engine.util.Vector3f;
import com.company.Game.Game;
import com.company.Game.objects.GameObject;

import java.util.List;

/**
 * Created by Slon on 16.03.2016.
 * TODO: PhysicObject class?
 * TODO: collision vector/ move vector
 */
public class PhysicsEngine {


    /**
     *
     * @param box
     * @param colliders
     * @return collision vector
     */
    public static Vector3f checkIntersection(GameObject box, List<GameObject> colliders){
        for(GameObject b: colliders){
            Vector3f collisionVec = b.getColider().getCenter().sub(box.getColider().getCenter());
            if(Math.abs(b.getColider().getCenter().getX() - box.getColider().getCenter().getX()) < (box.getColider().getSize() + b.getColider().getSize())
                    && Math.abs(b.getColider().getCenter().getY() - box.getColider().getCenter().getY()) < (box.getColider().getSize() + b.getColider().getSize())
                    && Math.abs(b.getColider().getCenter().getZ() - box.getColider().getCenter().getZ()) < (box.getColider().getSize() + b.getColider().getSize())
                    && !box.equals(b)){
                return collisionVec.normalized();
            }
        }
        return new Vector3f(0,0,0);
    }

}
