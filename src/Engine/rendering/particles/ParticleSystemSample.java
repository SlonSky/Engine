package Engine.rendering.particles;

import Engine.core.Time;
import Engine.rendering.Transform;
import Engine.util.Quaternion;
import Engine.util.Vector3f;

/**
 * Created by Slon on 27.05.2016.
 */
public class ParticleSystemSample {

    private float pps;
    private float speed;
    private float gravity;
    private float liveLength;

    private ParticleTexture texture;
    private boolean addLighting;

    public ParticleSystemSample(ParticleTexture texture, float pps, float speed, float gravity, float liveLength, boolean addLIghing) {
        this.pps = pps;
        this.speed = speed;
        this.gravity = gravity;
        this.liveLength = liveLength;
        this.texture = texture;
        this.addLighting = addLIghing;
    }

    public void generateParticles(Vector3f position){
        float particlesToCreate = pps * (float)Time.getDelta();
        int count = (int)Math.floor(particlesToCreate);
        float partialParticle = particlesToCreate % 1;
        for(int i = 0; i < count; i++){
            emitParticle(position);
        }
        if(Math.random() < partialParticle){
            emitParticle(position);
        }
    }

    private void emitParticle(Vector3f position){
        float dirX = (float) Math.random() * 2f - 1f;
        float dirZ = (float) Math.random() * 2f - 1f;
        Vector3f velocity = new Vector3f(dirX, 0, dirZ);
        velocity = velocity.normalized();
        velocity = velocity.mul(speed);
        new Particle(new Transform(position, new Quaternion(0,0,0,1), new Vector3f(1,1,1)), texture,
                velocity, gravity, liveLength, addLighting);
    }


}
