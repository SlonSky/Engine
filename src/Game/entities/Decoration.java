package Game.entities;

import Engine.physics.Collider;
import Engine.rendering.*;
import Engine.util.Vector3f;
import Game.GameObject;

/**
 * Created by Slon on 21.03.2016.
 * TODO: renderable interface
 */
public class Decoration extends GameObject {
    private Graphic graphic;
    private FrustumCulling culling;
    private Collider collider;


    // temp
    private GraphicBound cullingBound;
    private GraphicBound collideBound;

    // todo: get collider in counstr
    public Decoration(Transform transform, Graphic graphic, Vector3f cullingSize, Vector3f collideSize, Vector3f colliderOffset) {
        super(transform);
        this.graphic = graphic;
        this.culling = new FrustumCulling(new Box(cullingSize, getTransform()));
        this.collider = new Collider(collideSize, colliderOffset);

        addComponent(graphic);
        addComponent(culling);
        addComponent(collider);

        // temp
        cullingBound = new GraphicBound(cullingSize, new Vector3f(0, 1, 0));
        addComponent(cullingBound);
        collideBound = new GraphicBound(collideSize.add(0.1f), colliderOffset, new Vector3f(1, 0, 0), true);
        addComponent(collideBound);
    }


    public Decoration(Transform transform, Graphic graphic, Vector3f cullingSize){
        super(transform);
        this.graphic = graphic;
        this.culling = new FrustumCulling(new Box(cullingSize, getTransform()));

        addComponent(graphic);
        addComponent(culling);

        // temp
        cullingBound = new GraphicBound(cullingSize, new Vector3f(0, 1, 0));
        addComponent(cullingBound);
    }

    public Decoration(Transform transform, Graphic graphic){
        super(transform);
        this.graphic = graphic;


        addComponent(graphic);
    }

    public void render(Shader shader, RenderingEngine renderingEngine){
        if(culling == null){
            graphic.render(shader, renderingEngine);
        } else {
            if (culling.inFrustum(renderingEngine.getFrustum())) {
                graphic.render(shader, renderingEngine);

                // temp
//                cullingBound.render(shader, renderingEngine);

//                if(collideBound!= null)
//                collideBound.render(shader, renderingEngine);
            }
        }
    }
}
