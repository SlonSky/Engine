package Engine.rendering.animation;

import Game.GameComponent;
import Engine.core.Time;
import Engine.rendering.RenderingEngine;
import Engine.rendering.Shader;
import Engine.rendering.Transform;
import Engine.util.Quaternion;
import Engine.util.Vector3f;

import java.util.ArrayList;

/**
 * Created by Slon on 21.05.2016.
 */
public class Animation extends GameComponent{
    private ArrayList<AnimMesh> meshes;
    private int startFrame;
    private int finishFrame;
    private int currentFrame;
    private int frames;
    private Vector3f offset;
    private Quaternion rotationOffset;

    private double time;
    private double animLength;

    public Animation(int startFrame, int finishFrame, AnimMesh mesh){
        meshes = new ArrayList<>();
        meshes.add(mesh);
        offset = new Vector3f(0,0,0);
        rotationOffset = new Quaternion(0,0,0,1);
        this.startFrame = startFrame;
        this.finishFrame = finishFrame;
        this.frames = finishFrame - startFrame - 1;
        animLength = (float)(frames-1) / (float)mesh.getFps();
        time = animLength * 2;
    }

    @Override
    public void update() {
        if(isPlaying()) {
            time += Time.getDelta();
            currentFrame = (int) (frames * time / animLength);
        } else {
            currentFrame = startFrame;
        }
    }

    public void play(){
        if(!isPlaying()) {
            time = 0;
        }
    }

    public void stop(){
        currentFrame = startFrame;
        time = animLength*2;
    }

    public boolean isPlaying(){
        return time < animLength;
    }

    @Override
    public void render(Shader shader, RenderingEngine renderingEngine) {

        Transform transform = getTransform().clone();
        transform.setPosition(transform.getPosition().add(offset));
        transform.rotate(rotationOffset);

        for(AnimMesh animMesh: meshes){
            shader.bind();
            shader.updateUniforms(transform, animMesh.getMaterial(), renderingEngine);
            animMesh.getFrame(currentFrame).draw();
        }
    }

    public void addAnimMesh(AnimMesh mesh){
        meshes.add(mesh);
    }

    public Vector3f getOffset() {
        return offset;
    }

    public void setOffset(Vector3f offset) {
        this.offset = offset;
    }

    public Quaternion getRotationOffset() {
        return rotationOffset;
    }

    public void setRotationOffset(Quaternion rotationOffset) {
        this.rotationOffset = rotationOffset;
    }
}
