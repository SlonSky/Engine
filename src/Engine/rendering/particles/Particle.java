package Engine.rendering.particles;

import Engine.core.Time;
import Engine.rendering.Camera;
import Engine.rendering.Transform;
import Engine.util.Vector2f;
import Engine.util.Vector3f;
import Game.player.Player;

/**
 * Created by Slon on 27.05.2016.
 */
public class Particle {
    private Transform transform;
    private Vector3f velocity;
    private float gravityEffect;
    private float lifeLength;

    private float elapsedTime = 0;
    private float distance;

    private ParticleTexture texture;

    private Vector2f texOffset1 = new Vector2f(0,0);
    private Vector2f texOffset2 = new Vector2f(0,0);
    private float blend;

    public Particle(Transform transform, ParticleTexture texture, Vector3f velocity, float gravityEffect, float lifeLength) {
        this.transform = transform;
        this.velocity = velocity;
        this.gravityEffect = gravityEffect;
        this.lifeLength = lifeLength;
        this.texture = texture;
        ParticleMaster.addParticle(this);
    }

    public Transform getTransform() {
        return transform;
    }

    public boolean update(Camera camera){
        velocity.setY(velocity.getY() - Player.GRAVITY * gravityEffect * (float) Time.getDelta());
        Vector3f change = new Vector3f(velocity.getX(), velocity.getY(), velocity.getZ());
        change.mul(transform.getScale());
        transform.setPosition(transform.getPosition().add(change));
        distance = camera.getPos().sub(transform.getPosition()).length();

        updateTextureCoordInfo();
        elapsedTime += Time.getDelta();

        return  elapsedTime < lifeLength;
    }

    private void updateTextureCoordInfo(){
        float lifeFactor = elapsedTime / lifeLength;
        int stageCount = texture.getNumberOfRows() * texture.getNumberOfRows();
        float atlasProgression = lifeFactor * stageCount;
        int index1 = (int) Math.floor(atlasProgression);
        int index2 = index1 < stageCount - 1 ? index1 + 1 : index1;
        this.blend = atlasProgression % 1;
        texOffset1 = getTexOffset(index1);
        texOffset2 = getTexOffset(index2);
//
        System.out.println(texOffset1 + " " + texOffset2);
    }

    private Vector2f getTexOffset(int index){
        int column = index % texture.getNumberOfRows();
        int row = index / texture.getNumberOfRows();
//        System.out.println(row);
        return new Vector2f((float) column / texture.getNumberOfRows(),
                (float)row / texture.getNumberOfRows());
    }

    public ParticleTexture getTexture() {
        return texture;
    }

    public Vector2f getTexOffset1() {
        return texOffset1;
    }

    public Vector2f getTexOffset2() {
        return texOffset2;
    }

    public float getBlend() {
        return blend;
    }

    public float getDistance() {
        return distance;
    }
}
