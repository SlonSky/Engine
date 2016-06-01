package Game.entities;

import Editor.LevelEditor.SyncEditor;
import Engine.rendering.Transform;
import Engine.rendering.particles.Particle;
import Engine.rendering.particles.ParticleTexture;
import Engine.util.Quaternion;
import Engine.util.Vector3f;
import Game.GameObject;

/**
 * Created by Slon on 29.05.2016.
 */
public class Fire extends ParticleSystem {

    private static final Vector3f BURN_SPEED = new Vector3f(0.0015f, 0.005f, 0.0015f);
    private static final float DENSITY = 5;

    public Fire(ParticleTexture texture, float gravity, float liveLength, boolean fire) {
        super(texture, DENSITY, new Vector3f(0,0,0), gravity, liveLength, fire);
    }

    @Override
    void emitParticle(Vector3f position) {
        float dirX = (float) Math.random() * 2f - 1f;
        float dirZ = (float) Math.random() * 2f - 1f;
        Vector3f velocity = new Vector3f(dirX, 0, dirZ);
        velocity = velocity.normalized();
        velocity = velocity.mul(BURN_SPEED);
        new Particle(new Transform(position, new Quaternion(0,0,0,1), new Vector3f(1,1,1)), texture,
                velocity, gravity, liveLength, addLighting);
    }
}
