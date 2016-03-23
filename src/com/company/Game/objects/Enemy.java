package com.company.Game.objects;

import com.company.Engine.core.Input;
import com.company.Engine.rendering.*;
import com.company.Engine.util.Vector3f;
import com.company.Game.components.*;

/**
 * Created by Slon on 15.03.2016.
 */
public class Enemy extends GameObject{

    private Graphic graphic;
//    private AI ai;
    private FrustumCulling culling;

    public Enemy(Transform transform, Graphic graphic, Vector3f cullingSize) {
        super(transform);

        this.graphic = graphic;
        this.culling = new FrustumCulling(new Box(cullingSize, transform));

        addComponent(new AIMove());
        addComponent(graphic);
        addComponent(culling);
    }

    public void render(Shader shader, RenderingEngine renderingEngine){
        if(culling.inFrustum(renderingEngine.getFrustum())){
            graphic.render(shader, renderingEngine);
        }
    }
}
