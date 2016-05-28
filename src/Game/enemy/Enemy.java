package Game.enemy;

import Engine.audio.Sound;
import Engine.audio.Source;
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

    private Source audio;

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

    private EnemyState state;
    private EnemyState idle;
    private EnemyState chase;

    public Enemy(Transform transform, Collider collider, Vector3f cullingSize, Vector3f cullingOffset,
                 Animation idleAnim, Animation chaseAnim, Animation attackAnim, Animation hitAnim, Animation deathAnim) {
        super(transform);

        this.culling = new FrustumCulling(new Box(cullingSize, getTransform()), cullingOffset);
        this.ai = new AIMove();
        this.collider = collider;

        addComponent(ai);
        addComponent(culling);
        addComponent(collider);

        addComponent(idleAnim);
        addComponent(chaseAnim);

        idle = new Idle(audio, idleAnim);
        chase = new Chase(audio, chaseAnim);
        state = chase;

        // temp
        graphicBound = new GraphicBound(cullingSize, cullingOffset, new Vector3f(0, 1, 0), false);
        colliderBound = new GraphicBound(collider.getSize(), collider.getOffset(), new Vector3f(1,0,0), true);
        addComponent(graphicBound);
        addComponent(colliderBound);
    }
    @Override
    public void update() {
        super.update();


        state.update();

    }

    private void changeState(EnemyState newState){
        if(state != newState) {
            state.exit();
            state = newState;
            state.enter();
        }
    }

    private void move(){
//        ai.setTranslation(collider.solveCollision(ai.getTranslation()));
//        ai.move();
        getTransform().setPosition(getTransform().getPosition().add(collider.solveCollision(ai.getMovementVector())));
    }

    public void render(Shader shader, RenderingEngine renderingEngine){
        if(culling.inFrustum(renderingEngine.getFrustum())){
//            animation.render(shader, renderingEngine);
            state.getAnimation().render(shader, renderingEngine);
            // temp
            graphicBound.render(shader, renderingEngine);
            colliderBound.render(shader, renderingEngine);
        }
    }
}
