package Game.entities;

import Engine.core.Time;
import Engine.rendering.Transform;
import Engine.rendering.particles.Particle;
import Engine.rendering.particles.ParticleTexture;
import Engine.util.Quaternion;
import Engine.util.Vector3f;

/**
 * Created by Slon on 29.05.2016.
 */
public abstract class ParticleSystem {

    protected float pps;
    protected Vector3f speed;
    protected float gravity;
    protected float liveLength;

    protected ParticleTexture texture;
    protected boolean addLighting;

    public ParticleSystem(ParticleTexture texture, float pps, Vector3f speed, float gravity, float liveLength, boolean addLighting) {
        this.pps = pps;
        this.speed = speed;
        this.gravity = gravity;
        this.liveLength = liveLength;
        this.texture = texture;
        this.addLighting = addLighting;
    }

    public void generateParticles(Vector3f position){
        float particlesToCreate = pps * (float) Time.getDelta();
        int count = (int)Math.floor(particlesToCreate);
        float partialParticle = particlesToCreate % 1;
        for(int i = 0; i < count; i++){
            emitParticle(position);
        }
        if(Math.random() < partialParticle){
            emitParticle(position);
        }
    }

    abstract void emitParticle(Vector3f position);

}

