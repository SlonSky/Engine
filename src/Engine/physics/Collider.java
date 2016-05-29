package Engine.physics;

import Engine.rendering.Box;
import Engine.rendering.Transform;
import Engine.util.Vector3f;
import Game.GameComponent;
import Game.GameObject;
import Game.gun.Ray;

/**
 * Created by Slon on 15.03.2016.
 */
public class Collider extends GameComponent{
    private PhysicsEngine engine = PhysicsEngine.getInstance();
    private Box bound;
    private Vector3f offset;

    private float fLow;
    private float fHigh;
    private float fLFraction;

    public Collider(Vector3f size){
//        bound = new Box(size, new Transform());
        this(size, new Vector3f(0,0,0));
    }
    public Collider(Vector3f size, Vector3f offset){
        this.offset = offset;
        bound = new Box(size, new Transform());
        bound.setPosition(bound.getCenter().add(offset));
    }

    public void setParent(GameObject parent){
        super.setParent(parent);
        engine.addCollider(this);
    }

    public void update() {
        bound.setPosition(getTransform().getPosition().add(offset));
    }

//    public void solveCollision(){
//        Vector3f oldPos = getPosition();
//        getTransform().setPosition(oldPos.sub(engine.checkIntersection(this).mul(0.2f)));
//    }

    public Vector3f solveCollision(Vector3f movementVector){
//        Vector3f oldPos = getPosition();
//        getTransform().setPosition(oldPos.sub(engine.checkIntersection(this).mul(0.2f)));
        return engine.checkIntersection(this, movementVector);
    }

    public boolean checkRayIntersection(Ray shotRay, Vector3f intersectionVec){
        fLow = 0f;
        fHigh = 1f;

        if(clipLine(0, shotRay) && clipLine(1, shotRay) && clipLine(2, shotRay)) {
            Vector3f b = shotRay.end.sub(shotRay.start);
            intersectionVec.set(shotRay.start.add(b.mul(fLow)));
            fLFraction = fLow;
            return true;
        }
        return false;
    }

    private boolean clipLine(int axis, Ray ray){
        float fDimLow = 0f;
        float fDimHigh = 0f;

        switch (axis){
            case 0:
                fDimLow = (bound.getMin().getX() - ray.start.getX())/(ray.end.getX() - ray.start.getX());
                fDimHigh = (bound.getMax().getX() - ray.start.getX())/(ray.end.getX() - ray.start.getX());
                break;
            case 1:
                fDimLow = (bound.getMin().getY() - ray.start.getY())/(ray.end.getY() - ray.start.getY());
                fDimHigh = (bound.getMax().getY() - ray.start.getY())/(ray.end.getY() - ray.start.getY());
                break;
            case 2:
                fDimLow = (bound.getMin().getZ() - ray.start.getZ())/(ray.end.getZ() - ray.start.getZ());
                fDimHigh = (bound.getMax().getZ() - ray.start.getZ())/(ray.end.getZ() - ray.start.getZ());
                break;
        }

        if(fDimHigh < fDimLow){
            float temp = fDimHigh;
            fDimHigh = fDimLow;
            fDimLow = temp;
        }

        if(fDimHigh < fLow){
            return false;
        }
        if(fDimLow > fHigh){
            return false;
        }

        fLow = Math.max(fDimLow, fLow);
        fHigh = Math.max(fDimHigh, fHigh);

        return fLow <= fHigh;
    }

    public void remove(){
        engine.removeCollider(this);
    }

    public Vector3f getPosition(){
        return getTransform().getPosition().add(offset);
    }

    public float getSizeX() {
        return bound.getSize().getX();
    }

    public float getSizeY() {
        return bound.getSize().getY();
    }

    public float getSizeZ() {
        return bound.getSize().getZ();
    }

    public Vector3f getSize(){
        return bound.getSize();
    }

    public Vector3f getOffset() {
        return offset;
    }

    public float getfLFraction() {
        return fLFraction;
    }
}
