package Engine.rendering.animation;

import Engine.rendering.RenderingEngine;
import Engine.rendering.Shader;
import Engine.rendering.meshManagment.Material;
import Engine.rendering.meshManagment.Mesh;

import java.util.ArrayList;

/**
 * Created by Slon on 21.05.2016.
 */
public class AnimMesh {
    private ArrayList<Mesh> frames;
    private Material material;
    private int fps;

    public AnimMesh(String packageName, String fileName, Material material, int framesAmt, int fps){
        frames = new ArrayList<>();
        this.material = material;
        this.fps = fps;
        loadMeshes(packageName, fileName, framesAmt);
    }

    public Mesh getFrame(int framePos){
        return frames.get(framePos);
    }

    private void loadMeshes(String packName, String fileName, int framesAmt){
        for(int i = 1; i < framesAmt+1; i++){
                frames.add(new Mesh(packName + "/" + fileName + "_000" +
                        ((i < 10) ? "0" : "") +
                        ((i < 100) ? "0" : "") + i + ".obj"
                ));
//            // [todo] temp!
//            System.out.print(i + " ");
        }System.out.println();
    }

    public int getFps() {
        return fps;
    }

    public Material getMaterial() {
        return material;
    }
}
