package com.company.Engine.rendering;

import com.company.Engine.util.Vector3f;
import com.company.Engine.util.Vertex;

/**
 * Created by Slon on 07.03.2016.
 */
public class Cube {
    private float length;
    private Vector3f center;
    private Vector3f[] points;

    public Cube(float length, Transform transform){
        points = new Vector3f[8];

        float r = length/2;
        center = transform.getPosition();

        // bottom face
        points[0] = center.add(new Vector3f(-r, -r, r));
        points[1] = center.add(new Vector3f(r, -r, r));
        points[2] = center.add(new Vector3f(r, -r, -r));
        points[3] = center.add(new Vector3f(-r, -r, -r));

        // top face
        points[4] = center.add(new Vector3f(-r, r, r));
        points[5] = center.add(new Vector3f(r, r, r));
        points[6] = center.add(new Vector3f(r, r, -r));
        points[7] = center.add(new Vector3f(-r, r, -r));



    }

    public float getLength() {
        return length;
    }

    public Vector3f getCenter() {
        return center;
    }

    public Vector3f[] getPoints() {
        return points;
    }

    public boolean isCulling(){

        return false;
    }
}
