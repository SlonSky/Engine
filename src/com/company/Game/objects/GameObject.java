package com.company.Game.objects;

import com.company.Engine.rendering.*;
import com.company.Engine.util.Vector3f;
import com.company.Engine.util.Vertex;

/**
 * Created by Slon on 25.02.2016.
 */
public class GameObject {
    private Entity entity;
    private static Mesh boundMesh= new Mesh("bound.obj");
    private static Material boundMat = new Material(new Texture("bound.png"), 0,0);
    private Cube cullingCube;

    public Entity bound ;
    private Vector3f dimensions;


    public GameObject(Entity entity, Vector3f dimensions) {
        this.entity = entity;
        cullingCube = new Cube(dimensions, entity.getTransform());
        bound = new Entity(boundMesh, new Transform(entity.getTransform().getPosition(), new Vector3f(0,0,0), dimensions), boundMat);
    }

    public Entity getEntity() {
        return entity;
    }

    public Cube getCullingCube() {
        return cullingCube;
    }
}
