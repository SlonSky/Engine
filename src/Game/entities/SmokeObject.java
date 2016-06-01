package Game.entities;

import Engine.rendering.Transform;
import Engine.rendering.particles.ParticleTexture;
import Game.GameObject;

/**
 * Created by Slon on 31.05.2016.
 */
public class SmokeObject extends GameObject{

    private static final float SMOKE_GRAVITY = -0.0001f;
    private static final float SMOKE_SPEED = 8;

    private Fire smoke;

    public SmokeObject(Transform transform, ParticleTexture texture) {
        super(transform);
        smoke = new Fire(texture, SMOKE_GRAVITY, SMOKE_SPEED, false);
    }

    @Override
    public void update() {
        super.update();
        smoke.generateParticles(getTransform().getPosition());
    }
}
