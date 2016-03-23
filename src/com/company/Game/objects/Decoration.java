package com.company.Game.objects;

import com.company.Engine.physics.Collider;
import com.company.Engine.rendering.*;
import com.company.Engine.util.Vector3f;
import com.company.Game.components.FrustumCulling;
import com.company.Game.components.Graphic;

/**
 * Created by Slon on 21.03.2016.
 * TODO: renderable interface
 */
public class Decoration extends GameObject{
    private Graphic graphic;
    private FrustumCulling culling;
    private Collider collider;

    // todo: remove components from input!
    public Decoration(Transform transform, Graphic graphic, Vector3f cullingSize, Vector3f collideSize) {
        super(transform);
        this.graphic = graphic;
        this.culling = new FrustumCulling(new Box(cullingSize, getTransform()));
        this.collider = new Collider(collideSize);

        addComponent(graphic);
        addComponent(culling);
        addComponent(collider);
    }

    public void render(Shader shader, RenderingEngine renderingEngine){
        if(culling.inFrustum(renderingEngine.getFrustum())) {
            graphic.render(shader, renderingEngine);
        }
    }
}
