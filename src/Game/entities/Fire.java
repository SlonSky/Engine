package Game.entities;

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

    private static final float BURN_SPEED = 0.01f;
    private static final float DENSITY = 5;

    private float height;
    private float length;
    private float width;

    public Fire(ParticleTexture texture, float height, float length, float width) {
        super(texture, DENSITY, new Vector3f(0.01f, 0.01f, 0.01f), -0.0001f, 4, true);
        this.height = height;
        this.length = length;
        this.width = width;
    }

    @Override
    void emitParticle(Vector3f position) {
        float dirX = (float) Math.random() * 2f - 1f;
        float dirZ = (float) Math.random() * 2f - 1f;
        Vector3f velocity = new Vector3f(dirX, 0, dirZ);
        velocity = velocity.normalized();
        velocity = velocity.mul(new Vector3f(0.0015f, 0.001f, 0.0015f));
        new Particle(new Transform(position, new Quaternion(0,0,0,1), new Vector3f(1,1,1)), texture,
                velocity, gravity, liveLength, addLighting);
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }
}
