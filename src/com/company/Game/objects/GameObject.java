package com.company.Game.objects;

import com.company.Engine.rendering.*;
import com.company.Engine.util.Quaternion;
import com.company.Engine.util.Vector3f;

/**
 * Created by Slon on 25.02.2016.
 */
public class GameObject {

    private static Mesh boundMesh = new Mesh("bound.obj");
    private static Material boundMat = new Material(new Texture("bound.png"), 0,0);

    private Entity entity;
    private Box cullingCube;
    private Box collider;
    private boolean culling;

    public Entity bound;


    public GameObject(Entity entity, Vector3f dimensions) {
        this(entity, dimensions, true);
    }

    public GameObject(Entity entity, Vector3f dimensions, boolean culling) {
        this.entity = entity;
        this.culling = culling;
        cullingCube = new Box(dimensions, entity.getTransform());
        bound = new Entity(boundMesh, new Transform(entity.getTransform().getPosition(), new Quaternion(0, 0, 0, 1), dimensions), boundMat);

        // temporary!
//        collider = new Box(new Vector3f(cullingCube.getSize(),cullingCube.getSize(),cullingCube.getSize()), entity.getTransform());
        collider = cullingCube;
    }

    public Entity getEntity() {
        return entity;
    }

    public Box getCullingCube() {
        return cullingCube;
    }

    public Entity getBound() {
        return bound;
    }

    public boolean isCulling() {
        return culling;
    }

    public Box getColider() {
        return collider;
    }
}
