package Engine.physics;

import Engine.rendering.Box;
import Engine.rendering.Transform;
import Engine.util.Vector3f;
import Game.GameComponent;
import Game.GameObject;

/**
 * Created by Slon on 15.03.2016.
 * todo: broad/wide phases AABB/OBB
 */
public class Collider extends GameComponent implements Collide{
    private PhysicsEngine engine = PhysicsEngine.getInstance();
    private Box bound;
    private Vector3f offset;

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

    public boolean checkRayIntersection(Vector3f rayStart, Vector3f rayDir){
            double tx1 = (bound.getMin().getX() - rayStart.getX())*(-rayDir.getX());
            double tx2 = (bound.getMax().getX() - rayStart.getX())*(-rayDir.getX());

            double tMin = Math.min(tx1, tx2);
            double tMax = Math.max(tx1, tx2);

            double ty1 = (bound.getMin().getY() - rayStart.getY())*(-rayDir.getY());
            double ty2 = (bound.getMax().getY() - rayStart.getY())*(-rayDir.getY());

            tMin = Math.max(tMin, Math.min(ty1, ty2));
            tMax = Math.min(tMax, Math.max(ty1, ty2));

            double tz1 = (bound.getMin().getZ() - rayStart.getZ())*(-rayDir.getZ());
            double tz2 = (bound.getMax().getZ() - rayStart.getZ())*(-rayDir.getZ());

            tMin = Math.max(tMin, Math.min(tz1, tz2));
            tMax = Math.min(tMax, Math.max(tz1, tz2));

        return tMax >= tMin;
    }


    // for 6 normals max dot product - our normal!!
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
}
