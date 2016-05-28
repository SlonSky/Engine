package Engine.rendering;

import Engine.util.Plane;
import Engine.util.Vector3f;
import Game.GameComponent;

/**
 * Created by Slon on 21.03.2016.
 */
public class FrustumCulling extends GameComponent {

    private Box cullingBox;
    private Vector3f offset = new Vector3f(0,0,0);

    public FrustumCulling(Box cullingBox) {
        this.cullingBox = cullingBox;

    }

    public FrustumCulling(Box cullingBox, Vector3f offset) {
        this.cullingBox = cullingBox;
        this.offset = offset;
    }

    public void update(){
        cullingBox.setPosition(getTransform().getPosition());
    }

    private boolean pointInFrustum(Plane[] frustum, Vector3f point){
        for(int i = 0; i < 6; i++){
            if(frustum[i].getA() * point.getX() + frustum[i].getB() * point.getY() + frustum[i].getC() * point.getZ() + frustum[i].getD() <= 0){
                return false;
            }
        }
        return true;
    }

    public boolean inFrustum(Plane[] frustum){
        for (Vector3f point : cullingBox.getPoints()) {
            if (pointInFrustum(frustum, point)) {
                return true;
            }
        }
        return false;
    }

    public Vector3f getOffset() {
        return offset;
    }

    public void setOffset(Vector3f offset) {
        this.offset = offset;
    }

    public Vector3f getSize(){
        return cullingBox.getSize();
    }

}
