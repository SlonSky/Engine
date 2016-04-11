package com.company.Engine.physics;

import com.company.Engine.rendering.Box;
import com.company.Engine.rendering.Transform;
import com.company.Engine.util.Vector3f;
import com.company.Game.components.GameComponent;
import com.company.Game.objects.GameObject;

/**
 * Created by Slon on 15.03.2016.
 * todo: AABB
 */
public class Collider extends GameComponent {
    private PhysicsEngine engine = PhysicsEngine.getInstance();
    private Box bound;

    public Collider(Vector3f size){
        bound = new Box(size, new Transform());
    }

    public void setParent(GameObject parent){
        super.setParent(parent);
        bound.setTransform(getTransform());
        engine.addCollider(this);
    }

    public void update() {
        bound.setPosition(getTransform().getPosition());
    }

    public void solveCollision(){
        Vector3f oldPos = getPosition();
        getTransform().setPosition(oldPos.sub(engine.checkIntersection(this).mul(0.2f)));
    }

    public Vector3f solveCollision(Vector3f transl){
//        Vector3f oldPos = getPosition();
//        getTransform().setPosition(oldPos.sub(engine.checkIntersection(this).mul(0.2f)));
        return engine.checkIntersection(this, transl);
    }

    public Vector3f getPosition(){
        return getTransform().getPosition();
    }

    public float getSize() {
        return bound.getSize();
    }

}
