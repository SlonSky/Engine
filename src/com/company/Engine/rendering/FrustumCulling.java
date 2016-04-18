package com.company.Engine.rendering;

import com.company.Engine.util.Plane;
import com.company.Engine.util.Vector3f;
import com.company.Engine.core.GameComponent;

/**
 * Created by Slon on 21.03.2016.
 */
public class FrustumCulling extends GameComponent {
//    private Graphic bound = new Graphic(new Mesh("bound.obj"), new Material(new Texture("bound.png"), 0,0));

    private Box cullingBox;

    public FrustumCulling(Box cullingBox) {
        this.cullingBox = cullingBox;
//        bound.setParent(getParent());
    }

    public void update(){
        cullingBox.setPosition(getTransform().getPosition());
    }

    public void render(Shader shader, RenderingEngine renderingEngine){

//        bound.render(shader, renderingEngine);
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

}
