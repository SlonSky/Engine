package com.company.Engine.ai;

import com.company.Engine.physics.Collider;
import com.company.Engine.rendering.*;
import com.company.Engine.util.Vector3f;
import com.company.Engine.core.GameObject;

/**
 * Created by Slon on 15.03.2016.
 */
public class Enemy extends GameObject {

    private Graphic graphic;
    private AIMove ai;
    private FrustumCulling culling;

    private Collider collider;

    // temp
    private GraphicBound graphicBound;
    private GraphicBound colliderBound;

    public Enemy(Transform transform, Graphic graphic, Vector3f cullingSize) {
        super(transform);

        this.graphic = graphic;
        this.culling = new FrustumCulling(new Box(cullingSize, transform));
        this.ai = new AIMove();
        this.collider = new Collider(new Vector3f(3,3,3));

        addComponent(ai);
        addComponent(graphic);
        addComponent(culling);
        addComponent(collider);

        // temp
        graphicBound = new GraphicBound(cullingSize, new Vector3f(0, 1, 0));
        colliderBound = new GraphicBound(new Vector3f(3,3,3), new Vector3f(1,0,0), true);
        addComponent(graphicBound);
        addComponent(colliderBound);
    }

    @Override
    public void update() {
        super.update();
    }

    public void render(Shader shader, RenderingEngine renderingEngine){
        if(culling.inFrustum(renderingEngine.getFrustum())){
            graphic.render(shader, renderingEngine);

            // temp
            graphicBound.render(shader, renderingEngine);
            colliderBound.render(shader, renderingEngine);
        }
    }
}
