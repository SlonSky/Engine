package com.company.Engine.physics;

import com.company.Engine.util.Vector3f;

import java.util.ArrayList;

/**
 * Created by Slon on 16.03.2016.
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

//    public Vector3f checkIntersection(Collider colliding){
//        for(Collider collider: colliders){
//            Vector3f collisionVec = collider.getPosition().sub(colliding.getPosition());
//            if(!collider.equals(colliding)
//                    && Math.abs(colliding.getPosition().getX() - collider.getPosition().getX()) < (colliding.getSize() + collider.getSize())
//                    && Math.abs(colliding.getPosition().getY() - collider.getPosition().getY()) < (colliding.getSize() + collider.getSize())
//                    && Math.abs(colliding.getPosition().getZ() - collider.getPosition().getZ()) < (colliding.getSize() + collider.getSize())
//                    ){
//                return collisionVec.normalized();
//            }
//        }
//        return new Vector3f(0,0,0);
//    }

//    public Vector3f checkIntersection(Collider colliding, Vector3f transl){
//        Vector3f pos = colliding.getPosition().add(transl);
//        for(Collider collider: colliders){
//            Vector3f collisionVec = pos.sub(collider.getPosition());
//            if(!collider.equals(colliding)
//                    && Math.abs(pos.getX() - collider.getPosition().getX()) < (colliding.getSize() + collider.getSize())
//                    && Math.abs(pos.getY() - collider.getPosition().getY()) < (colliding.getSize() + collider.getSize())
//                    && Math.abs(pos.getZ() - collider.getPosition().getZ()) < (colliding.getSize() + collider.getSize())
//                    ){
//                System.out.println("block!");
//
//                // normal calc:
////                return transl.mul(transl.normalized().mul(collisionVec.normalized()));
//                return transl.mul(-0.1f);
//            }
//        }
//        return transl;
//    }

    public Vector3f checkIntersection(Collider colliding, Vector3f transl){
        Vector3f pos = colliding.getPosition().add(transl);
        for(Collider collider: colliders){
            Vector3f collisionVec = collider.getPosition().sub(pos);
            if(!collider.equals(colliding)
                    && Math.abs(pos.getX() - collider.getPosition().getX()) < (colliding.getSizeX() + collider.getSizeX())
                    && Math.abs(pos.getY() - collider.getPosition().getY()) < (colliding.getSizeY() + collider.getSizeY())
                    && Math.abs(pos.getZ() - collider.getPosition().getZ()) < (colliding.getSizeZ() + collider.getSizeZ())
                    ){
//                System.out.println("block!");
                transl = responseAABBCollision(transl, collisionVec);
            }
        }
        return transl;
    }


    private Vector3f responseAABBCollision(Vector3f translation, Vector3f collisionVec){
        Vector3f normal = calcNormal(collisionVec);
        if(normal.max() > 0) {
            return translation.sub(translation.mul(normal));
        } else
            return translation.add(translation.mul(normal));
    }

    private Vector3f calcNormal(Vector3f colVec){
        float xEntry = Math.abs(colVec.getX());
        float yEntry = Math.abs(colVec.getY());
        float zEntry = Math.abs(colVec.getZ());

        if(xEntry > yEntry && xEntry > zEntry){
            return new Vector3f((colVec.getX() > 0) ? 1 : -1, 0, 0);
        }
        if(yEntry > xEntry && yEntry > zEntry){
            return new Vector3f(0, (colVec.getY() > 0) ? 1 : -1, 0);
        }
        if(zEntry > xEntry && zEntry > yEntry){
            return new Vector3f(0, 0, (colVec.getZ() > 0) ? 1 : -1);
        }
        return new Vector3f(0,0,0);


    }

}
