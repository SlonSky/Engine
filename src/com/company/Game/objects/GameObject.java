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
   public Entity bound ;
    private Cube cullingCube;



    public GameObject(Entity entity) {
        this.entity = entity;
        cullingCube = new Cube(3, entity.getTransform());

//        Vertex[] ver = new Vertex[8];
//        Vector3f[] points = cullingCube.getPoints();
//        for(int i = 0; i < 8; i++){
//            ver[i] = new Vertex(points[i]);
//        }
//        int[] ind = new int[]{
//                0,3,1,
//                1,3,2,
//                2,6,5,
//                0,4,7,
//                0,7,3,
//                5,1,2,
//                3,7,6,
//                3,6,2,
//                5,0,1,
//                0,5,4,
//                7,4,5,
//                5,6,7
//        };
//        Mesh m = new Mesh(ver, ind);
        bound = new Entity(boundMesh, new Transform(entity.getTransform().getPosition(), new Vector3f(0,0,0), new Vector3f(2,2,2)), boundMat);
    }

    public Entity getEntity() {
        return entity;
    }

    public Cube getCullingCube() {
        return cullingCube;
    }
}
