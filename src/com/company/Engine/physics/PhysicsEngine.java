package com.company.Engine.physics;

import com.company.Engine.util.Vector3f;

import java.util.ArrayList;

/**
 * Created by Slon on 16.03.2016.
 * TODO: PhysicObject class?
 * TODO: collision vector/ move vector
 */
public class PhysicsEngine {
    public static final PhysicsEngine instance = new PhysicsEngine();

    private ArrayList<Collider> colliders;

    private PhysicsEngine() {
        colliders = new ArrayList<>();
    }

    public static PhysicsEngine getInstance() {
        return instance;
    }

    public void addCollider(Collider collider){
        colliders.add(collider);
    }

    public Vector3f checkIntersection(Collider colliding){
        for(Collider collider: colliders){
            Vector3f collisionVec = collider.getPosition().sub(colliding.getPosition());
            if(!collider.equals(colliding)
                    && Math.abs(colliding.getPosition().getX() - collider.getPosition().getX()) < (colliding.getSize() + collider.getSize())
                    && Math.abs(colliding.getPosition().getY() - collider.getPosition().getY()) < (colliding.getSize() + collider.getSize())
                    && Math.abs(colliding.getPosition().getZ() - collider.getPosition().getZ()) < (colliding.getSize() + collider.getSize())
                    ){
                return collisionVec.normalized();
            }
        }
        return new Vector3f(0,0,0);
    }

    public Vector3f checkIntersection(Collider colliding, Vector3f transl){
        Vector3f pos = colliding.getPosition().add(transl);
        for(Collider collider: colliders){
            Vector3f collisionVec = pos.sub(collider.getPosition());
            if(!collider.equals(colliding)
                    && Math.abs(pos.getX() - collider.getPosition().getX()) < (colliding.getSize() + collider.getSize())
                    && Math.abs(pos.getY() - collider.getPosition().getY()) < (colliding.getSize() + collider.getSize())
                    && Math.abs(pos.getZ() - collider.getPosition().getZ()) < (colliding.getSize() + collider.getSize())
                    ){
                System.out.println("block!");

                // normal calc:
//                return transl.mul(transl.normalized().mul(collisionVec.normalized()));
                return transl.mul(-0.1f);
            }
        }
        return transl;
    }

}
