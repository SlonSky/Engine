package com.company.Game.objects;

import com.company.Engine.rendering.*;
import com.company.Engine.util.Vector3f;

/**
 * Created by Slon on 16.02.2016.
 */
public class Player extends Entity{
    private Camera camera;
//    private Entity body;

    public Player(Camera camera, Mesh mesh, Material material) {
        super(mesh, new Transform(camera.getPos(), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)), material);
        this.camera = camera;
    }


    public void input(){
        camera.input();
    }

    public void update(){
        setTransform(new Transform(camera.getPos().sub(new Vector3f(-5, 5, -1)), new Vector3f(0, 180, 0).sub(camera.getForward()), new Vector3f(1, 1, 1)));
    }

}
