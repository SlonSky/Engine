package Engine.rendering;

import Engine.util.Vector3f;

/**
 * Created by Slon on 07.03.2016.
 */
public class Box {
    private Transform transform;
    private Vector3f dimensions;
    private Vector3f[] points;

    private Vector3f offset = new Vector3f(0,0,0);

    public Box(Vector3f dimensions, Transform transform){
        points = new Vector3f[8];
        this.dimensions = dimensions.div(2);
        this.transform = transform;
        initBounds();
    }

    private void initBounds(){
        float x = dimensions.getX();
        float y = dimensions.getY();
        float z = dimensions.getZ();

        Vector3f center = transform.getPosition().add(offset);

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

//    public void moveTo(Vector3f position){
//        transform.setPosition(position);
//        initBounds();
//    }

    public void setPosition(Vector3f position){
        transform.setPosition(position);
        initBounds();
    }

    public Vector3f getMin(){
        return points[3];
    }

    public Vector3f getMax(){
        return points[5];
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public Vector3f[] getPoints() {
        return points;
    }

    public Vector3f getCenter(){
        return transform.getPosition();
    }

//    public float getSize(){
//        return dimensions.max();
//    }

    // temp?
    public Vector3f getSize(){return dimensions;}

    public Transform getTransform() {
        return transform;
    }

    public boolean isCulling(){
        return false;
    }

    public Vector3f getOffset() {
        return offset;
    }

    public void setOffset(Vector3f offset) {
        this.offset = offset;
    }
}
