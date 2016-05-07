package com.company.Game.entities;

import com.company.Engine.physics.Collider;
import com.company.Engine.rendering.*;
import com.company.Engine.util.Vector3f;
import com.company.Engine.core.GameObject;

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

    // todo: remove components from input!
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
    public Decoration(Transform transform, Graphic graphic, Vector3f cullingSize, Vector3f collideSize) {
        super(transform);
        this.graphic = graphic;
        this.culling = new FrustumCulling(new Box(cullingSize, getTransform()));
        this.collider = new Collider(collideSize);

        addComponent(graphic);
        addComponent(culling);
        addComponent(collider);

        // temp
        cullingBound = new GraphicBound(cullingSize, new Vector3f(0, 1, 0));
        addComponent(cullingBound);
        collideBound = new GraphicBound(collideSize.add(0.1f), new Vector3f(1, 0, 0), true);
        addComponent(collideBound);
    }
    public void render(Shader shader, RenderingEngine renderingEngine){
        if(culling.inFrustum(renderingEngine.getFrustum())) {
            graphic.render(shader, renderingEngine);

            // temp
            cullingBound.render(shader, renderingEngine);
            collideBound.render(shader, renderingEngine);
        }
    }
}
