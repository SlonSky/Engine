package Game.entities;

import Engine.rendering.*;
import Engine.rendering.particles.ParticleTexture;
import Engine.util.Vector3f;
import Game.GameObject;

/**
 * Created by Slon on 31.05.2016.
 */
public class FireObject extends GameObject {
    private static final float FIRE_GRAVITY = -0.0001f;
    private static final float FIRE_SPEED = 4;

    private Fire fire;
//    private FrustumCulling frustumCulling;

    public FireObject(Transform transform, ParticleTexture texture) {
        super(transform);
        fire = new Fire(texture, FIRE_GRAVITY, FIRE_SPEED, true);
//        frustumCulling = new FrustumCulling(new Box(cullingSize, transform));
    }


    @Override
    public void update() {
        super.update();
        fire.generateParticles(getTransform().getPosition());
    }

}
