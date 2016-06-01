package Engine.physics;

import Engine.rendering.meshManagment.Material;
import Engine.util.Vector3f;

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


    public Vector3f checkIntersection(Collider colliding, Vector3f movementVector){
        Vector3f pos = colliding.getPosition().add(movementVector);
        for(Collider collider: colliders){
            Vector3f collisionVec = collider.getPosition().sub(pos);
            if(!collider.equals(colliding)
                    && Math.abs(pos.getX() - collider.getPosition().getX()) < (colliding.getSizeX() + collider.getSizeX())
                    && Math.abs(pos.getY() - collider.getPosition().getY()) < (colliding.getSizeY() + collider.getSizeY())
                    && Math.abs(pos.getZ() - collider.getPosition().getZ()) < (colliding.getSizeZ() + collider.getSizeZ())
                    ){
                movementVector = responseAABBCollision(movementVector, collisionVec, collider.getSize().add(colliding.getSize()));
            }
        }
        return movementVector;
    }


    private Vector3f responseAABBCollision(Vector3f movementVector, Vector3f collisionVec, Vector3f sizeAmt){
        Vector3f normal = calcMovementCorrector(collisionVec, sizeAmt, movementVector);
        return movementVector.mul(normal);
    }


    public void removeCollider(Collider collider){
        colliders.remove(collider);
    }

    private Vector3f calcNormal(Vector3f colVec, Vector3f size){

        colVec = colVec.div(size).normalized();
        float xEntry = Math.abs(colVec.getX());
        float yEntry = Math.abs(colVec.getY());
        float zEntry = Math.abs(colVec.getZ());


        // face collision
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

    private Vector3f calcMovementCorrector(Vector3f colVec, Vector3f size, Vector3f moveVec){

        // edge collision
        if(size.mul(new Vector3f(1,0,1)).length() - colVec.mul(new Vector3f(1,0,1)).length() < 0.3){

            Vector3f n = calcNormal(colVec, size).mul(new Vector3f(1, 0, 1));
            float dot = colVec.mul(new Vector3f(1, 0, 1)).dot(n);
            return new Vector3f(n.getX() == 0 ? dot: 0, 1, n.getZ() == 0 ? dot: 0);
        }

        colVec = colVec.div(size).normalized();
        float xEntry = Math.abs(colVec.getX());
        float yEntry = Math.abs(colVec.getY());
        float zEntry = Math.abs(colVec.getZ());

        if(xEntry > yEntry && xEntry > zEntry){
            return new Vector3f(0, 1, 1);
        }
        if(yEntry > xEntry && yEntry > zEntry){
            return new Vector3f(1, 0, 1);
        }
        if(zEntry > xEntry && zEntry > yEntry){
            return new Vector3f(1,1,0);
        }

        return new Vector3f(1,1,1);
    }



}
