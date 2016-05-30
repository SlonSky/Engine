package Game.entities;

import Engine.rendering.Transform;
import Engine.rendering.particles.Particle;
import Engine.rendering.particles.ParticleTexture;
import Engine.util.Quaternion;
import Engine.util.Vector3f;

/**
 * Created by Slon on 30.05.2016.
 */
public class Blood extends ParticleSystem {


    private Vector3f dir;

    public Blood(ParticleTexture texture) {
        super(texture, 40, new Vector3f(0,0,0), 0.1f, 0.5f, false);
    }

    @Override
    void emitParticle(Vector3f position) {

        Vector3f d = new Vector3f(dir.getX()+(float)Math.random()*2-1, dir.getY()+(float)Math.random()*2-1, dir.getZ()+(float)Math.random()*2-1).normalized().mul(0.08f);
        new Particle(new Transform(position, new Quaternion(0,0,0,1), new Vector3f(1,1,1)), texture, d, gravity, liveLength, addLighting);
    }

    public void setDir(Vector3f dir) {
        this.dir = dir.normalized();
    }
}
