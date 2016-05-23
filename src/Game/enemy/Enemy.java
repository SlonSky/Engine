package Game.enemy;

import Engine.ai.AIMove;
import Engine.audio.Sound;
import Engine.core.Time;
import Engine.physics.Collider;
import Engine.rendering.*;
import Engine.rendering.animation.Animation;
import Engine.util.Vector3f;
import Game.GameObject;

/**
 * Created by Slon on 15.03.2016.
 */
public class Enemy extends GameObject {

    private AIMove ai;
    private FrustumCulling culling;

    private Collider collider;

    // temp
    private GraphicBound graphicBound;
    private GraphicBound colliderBound;

    private Animation animation;

    private Animation walk;
    private Animation hit;
    private Animation die;
    private Animation attack;
    private Animation calm; // ?

    private Sound noise;
    private Sound strike;
    private Sound dieScream;
    private Sound hitScream;
    private Sound steps;

    public Enemy(Transform transform, Animation animation, Vector3f cullingSize) {
        super(transform);

        this.animation = animation;
        this.culling = new FrustumCulling(new Box(cullingSize, transform));
        this.ai = new AIMove();
        this.collider = new Collider(new Vector3f(2,2,2));

        addComponent(ai);
        addComponent(culling);
        addComponent(collider);
        addComponent(this.animation);


        // temp
        graphicBound = new GraphicBound(cullingSize, new Vector3f(0, 1, 0));
        colliderBound = new GraphicBound(new Vector3f(2,2,2), new Vector3f(1,0,0), true);
        addComponent(graphicBound);
        addComponent(colliderBound);
    }
    @Override
    public void update() {
        super.update();
        move();

    }

    private void move(){
        ai.setTranslation(collider.solveCollision(ai.getTranslation()));
        ai.move();
    }

    public void render(Shader shader, RenderingEngine renderingEngine){
        if(culling.inFrustum(renderingEngine.getFrustum())){
            animation.render(shader, renderingEngine);

            // temp
            graphicBound.render(shader, renderingEngine);
            colliderBound.render(shader, renderingEngine);
        }
    }
}
