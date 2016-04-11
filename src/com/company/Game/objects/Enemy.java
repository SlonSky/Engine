package com.company.Game.objects;

import com.company.Engine.core.Input;
import com.company.Engine.rendering.*;
import com.company.Engine.rendering.meshManagment.Material;
import com.company.Engine.rendering.meshManagment.Mesh;
import com.company.Engine.rendering.meshManagment.Texture;
import com.company.Engine.util.Vector3f;
import com.company.Game.components.*;

/**
 * Created by Slon on 15.03.2016.
 */
public class Enemy extends GameObject{

    private Graphic graphic;
    private AIMove ai;
    private FrustumCulling culling;

    // temp
    private GraphicBound graphicBound;

    public Enemy(Transform transform, Graphic graphic, Vector3f cullingSize) {
        super(transform);

        this.graphic = graphic;
        this.culling = new FrustumCulling(new Box(cullingSize, transform));
        this.ai = new AIMove();

//        addComponent(ai);
        addComponent(graphic);
        addComponent(culling);

        // temp
        graphicBound = new GraphicBound(cullingSize, new Vector3f(0, 1, 0));
        addComponent(graphicBound);
    }

    public void render(Shader shader, RenderingEngine renderingEngine){
        if(culling.inFrustum(renderingEngine.getFrustum())){
            graphic.render(shader, renderingEngine);

            // temp
            graphicBound.render(shader, renderingEngine);
        }
    }
}
