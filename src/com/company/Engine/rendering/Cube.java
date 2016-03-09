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

    public Cube(Vector3f dimensions, Transform transform){
        points = new Vector3f[8];

        float x = dimensions.getX();
        float y = dimensions.getY();
        float z = dimensions.getZ();

        center = transform.getPosition();

        // bottom face
        points[0] = center.add(new Vector3f(-x, -y, z));
        points[1] = center.add(new Vector3f(x, -y, z));
        points[2] = center.add(new Vector3f(x, -y, -z));
        points[3] = center.add(new Vector3f(-x, -y, -z));

        // top face
        points[4] = center.add(new Vector3f(-x, y, z));
        points[5] = center.add(new Vector3f(x, y, z));
        points[6] = center.add(new Vector3f(x, y, -z));
        points[7] = center.add(new Vector3f(-x, y, -z));



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
