package Game.enemy;

import Editor.LevelEditor.SyncEditor;
import Engine.audio.Sound;
import Engine.audio.Source;
import Engine.physics.Collider;
import Engine.rendering.*;
import Engine.rendering.animation.Animation;
import Engine.rendering.meshManagment.Texture;
import Engine.rendering.particles.Particle;
import Engine.rendering.particles.ParticleTexture;
import Engine.util.Quaternion;
import Engine.util.Vector3f;
import Game.GameObject;
import Game.Level;
import Game.entities.CombatManager;
import Game.entities.Opponent;
import Game.gun.Ray;

/**
 * Created by Slon on 15.03.2016.
 */
public class Enemy extends GameObject implements Movable, Opponent{

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
//    private Animation attack;
    private Animation calm; // ?

    private Sound noise;
    private Sound strike;
    private Sound dieScream;
    private Sound hitScream;
    private Sound steps;

    private EnemyState state;
    private EnemyState idle;
    private EnemyState chase;
    private EnemyState attack;
    private EnemyState death;

    private final float health;
    private float currentHealth;

    public Enemy(Transform transform, Collider collider, Vector3f cullingSize, Vector3f cullingOffset,
                 Animation idleAnim, Animation chaseAnim, Animation attackAnim, Animation deathAnim) {
        super(transform);

        this.culling = new FrustumCulling(new Box(cullingSize, getTransform()), cullingOffset);
        this.ai = new AIMove();
        this.collider = collider;

        addComponent(ai);
        addComponent(culling);
        addComponent(collider);

        addComponent(idleAnim);
        addComponent(chaseAnim);
        addComponent(attackAnim);
        addComponent(deathAnim);

        idle = new Idle(audio, idleAnim);
        chase = new Chase(audio, chaseAnim, this);
        attack = new Attack(audio, attackAnim);
        death = new Death(audio, deathAnim);
        state = idle;

        health = 100;
        currentHealth = health;

        CombatManager.getInstance().addOpponent(this);

        // temp
        graphicBound = new GraphicBound(cullingSize, cullingOffset, new Vector3f(0, 1, 0), false);
        colliderBound = new GraphicBound(collider.getSize(), collider.getOffset(), new Vector3f(1,0,0), true);
        addComponent(graphicBound);
        addComponent(colliderBound);
    }

    private ParticleTexture p = new ParticleTexture(new Texture("fire.png"), 8);

    @Override
    public void update() {
        super.update();

        if(currentHealth <= 0){
            changeState(death);
            if(!state.getAnimation().isPlaying()){
                remove = true;
            }
        } else {
            if (ai.isInAttackArea()) {
                changeState(attack);
                ai.faceToPlayer();
            } else if (ai.isInChaseArea()) {
                changeState(chase);
                ai.faceToPlayer();
            } else {
                changeState(idle);
            }
        }
        state.update();
    }

    private void changeState(EnemyState newState){
        if(state != newState) {
            state.exit();
            state = newState;
            state.enter();
        }
    }


    @Override
    public void move(){
        getTransform().setPosition(getTransform().getPosition().add(collider.solveCollision(ai.getMovementVector())));
    }

    public void render(Shader shader, RenderingEngine renderingEngine){
        if(culling.inFrustum(renderingEngine.getFrustum())){
            state.getAnimation().render(shader, renderingEngine);

            // temp
            graphicBound.render(shader, renderingEngine);
            colliderBound.render(shader, renderingEngine);
        }
    }

    @Override
    public Collider getCollider() {
        return collider;
    }

    @Override
    public void getDamage(float damage) {
        currentHealth -= damage;
        System.out.println("Zm " + currentHealth);
    }

    @Override
    public void remove() {
        collider.remove();
        CombatManager.getInstance().removeOpponent(this);
    }
}
