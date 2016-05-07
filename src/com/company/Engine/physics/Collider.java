package com.company.Engine.physics;

import com.company.Engine.rendering.Box;
import com.company.Engine.rendering.Transform;
import com.company.Engine.util.Vector3f;
import com.company.Engine.core.GameComponent;
import com.company.Engine.core.GameObject;

/**
 * Created by Slon on 15.03.2016.
 * todo: broad/wide phases AABB/OBB
 */
public class Collider extends GameComponent {
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

    public Vector3f solveCollision(Vector3f transl){
//        Vector3f oldPos = getPosition();
//        getTransform().setPosition(oldPos.sub(engine.checkIntersection(this).mul(0.2f)));
        return engine.checkIntersection(this, transl);
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

}
