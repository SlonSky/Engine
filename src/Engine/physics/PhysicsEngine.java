package Engine.physics;

import Engine.rendering.meshManagment.Material;
import Engine.util.Vector3f;

import java.util.ArrayList;

/**
 * Created by Slon on 16.03.2016.
 */

// todo: plane-box ontersection
    // todo: collision plane map (plane for static objects, aabb for dynamic)
public class PhysicsEngine {
    public static final PhysicsEngine instance = new PhysicsEngine();

    // todo: update in core
    // / todo: handle moving and colliding objects

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

    public Vector3f checkIntersection(Collider colliding, Vector3f movementVector){
        Vector3f pos = colliding.getPosition().add(movementVector);
        for(Collider collider: colliders){
            Vector3f collisionVec = collider.getPosition().sub(pos);
            if(!collider.equals(colliding)
                    && Math.abs(pos.getX() - collider.getPosition().getX()) < (colliding.getSizeX() + collider.getSizeX())
                    && Math.abs(pos.getY() - collider.getPosition().getY()) < (colliding.getSizeY() + collider.getSizeY())
                    && Math.abs(pos.getZ() - collider.getPosition().getZ()) < (colliding.getSizeZ() + collider.getSizeZ())
                    ){
//                System.out.println("block!");
                movementVector = responseAABBCollision(movementVector, collisionVec, collider.getSize().add(colliding.getSize()));
            }
        }
        return movementVector;
    }


    private Vector3f responseAABBCollision(Vector3f movementVector, Vector3f collisionVec, Vector3f sizeAmt){
        Vector3f normal = calcMovementCorrector(collisionVec, sizeAmt, movementVector);
//        System.out.println(normal);
//        if(normal.max() > 0) {
//            return movementVector.sub(movementVector.mul(normal));
//        }
//        return movementVector.add(movementVector.mul(normal));

//
//
//        System.out.println(calcNormal(movementVector));
//
        return movementVector.mul(normal);
    }



    private Vector3f calcNormal(Vector3f colVec, Vector3f size){

        colVec = colVec.div(size).normalized();
        float xEntry = Math.abs(colVec.getX());
        float yEntry = Math.abs(colVec.getY());
        float zEntry = Math.abs(colVec.getZ());



        System.out.println(
                        colVec.normalized().dot(new Vector3f(1, 0, 1).normalized())
//                + " " + colVec.normalized().dot(new Vector3f(0, 1, 0).normalized())
//                + " " + colVec.normalized().dot(new Vector3f(0, 0, 1).normalized())
        );

//        if(colVec.mul(new Vector3f(1,0,1)).normalized().dot(new Vector3f(1, 0, 1).normalized()) > 0.9){
//            return new Vector3f(1, 0, 1).normalized();
//        }
//        if(colVec.mul(new Vector3f(1,0,1)).normalized().dot(new Vector3f(-1, 0, -1).normalized()) > 0.9){
//            return new Vector3f(-1, 0, -1).normalized();
//        }
//        if(colVec.mul(new Vector3f(1,0,1)).normalized().dot(new Vector3f(-1, 0, 1).normalized()) > 0.9){
//            return new Vector3f(-1, 0, 1).normalized();
//        }
//        if(colVec.mul(new Vector3f(1,0,1)).normalized().dot(new Vector3f(1, 0, -1).normalized()) > 0.9){
//            return new Vector3f(1, 0, -1).normalized();
//        }
//        if(xEntry == yEntry){
//            System.out.println("here1");
//            return new Vector3f((colVec.getX() > 0) ? 1 : -1, (colVec.getY() > 0) ? 1 : -1, 0).normalized();
//        }
//        if(xEntry == zEntry){
//            System.out.println("here2");
//            return new Vector3f((colVec.getX() > 0) ? 1 : -1, 0, (colVec.getZ() > 0) ? 1 : -1).normalized();
//        }
//        if(yEntry == zEntry){
//            System.out.println("here3");
//            return new Vector3f(0, (colVec.getY() > 0) ? 1 : -1, (colVec.getZ() > 0) ? 1 : -1).normalized();
//        }

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




//        System.out.println(size.mul(new Vector3f(1, 0, 1)).length() - colVec.mul(new Vector3f(1, 0, 1)).length());

        //
//        System.out.println("n: " + calcNormal(colVec, size));

        // edge collision
        if(size.mul(new Vector3f(1,0,1)).length() - colVec.mul(new Vector3f(1,0,1)).length() < 0.3){
//            System.out.println(size.mul(new Vector3f(1, 0, 1)).length() - colVec.mul(new Vector3f(1, 0, 1)).length());

            Vector3f n = calcNormal(colVec, size).mul(new Vector3f(1, 0, 1));
            float dot = colVec.mul(new Vector3f(1, 0, 1)).dot(n);
            return new Vector3f(n.getX() == 0 ? dot: 0, 1, n.getZ() == 0 ? dot: 0);
        }


        colVec = colVec.div(size).normalized();
        float xEntry = Math.abs(colVec.getX());
        float yEntry = Math.abs(colVec.getY());
        float zEntry = Math.abs(colVec.getZ());

//        float dot = colVec.mul(new Vector3f(1,0,1)).normalized().dot(new Vector3f(1, 0, 1).normalized());
//        if(dot <= -1){
//            return new Vector3f(dot, 1, dot).normalized();
//        }



//        if(Math.abs(xEntry - yEntry) < 0.01){
//            return new Vector3f(0, 0, 1);
//        }
//        if(Math.abs(xEntry - zEntry) < 0.01){
//            return new Vector3f(0, 1, 0);
//        }
//        if(Math.abs(yEntry - zEntry) < 0.01){
//            return new Vector3f(1, 0, 0);
//        }

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
