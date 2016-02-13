package com.company.forEngine;


import com.company.forEngine.core.Input;
import com.company.forEngine.core.Window;
import com.company.forEngine.rendering.*;
import com.company.forEngine.util.Vector2f;
import com.company.forEngine.util.Vector3f;
import com.company.forEngine.util.Vertex;

import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Slon on 09.02.2016.
 */
public class Game {

    private Camera camera;
    private Transform transform;
    private Material material;
    private Mesh mesh;
    ArrayList<Entity> entities;

    public void init(){
//        try {
//            AL.create(); // openAL context
//            int buffer = alGenBuffers();
//            WaveData data = WaveData.create(new BufferedInputStream(new FileInputStream("res/Armo01.wav")));
//
//            alBufferData(buffer, data.format, data.data, data.samplerate);
//            data.dispose();
//            source = alGenSources();
//            alSourcei(source, AL_BUFFER, buffer);
//            alSource3f(source, AL_POSITION, 0, 0, 0);
//            alSource3f(source, AL_VELOCITY, 0, 0, 0);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.exit(-1);
//        }



        Vertex[] vertices = new Vertex[]{
                new Vertex(new Vector3f(-50, 0 ,50), new Vector2f(0, 1), new Vector3f(0, 1, 0)),
                new Vertex(new Vector3f(50, 0, 50), new Vector2f(1, 1), new Vector3f(0, 1, 0)),
                new Vertex(new Vector3f(50,0,  -50), new Vector2f(1, 0), new Vector3f(0, 1, 0)),
                new Vertex(new Vector3f(-50, 0, -50), new Vector2f(0, 0), new Vector3f(0, 1, 0))
        };

        int[] indices = new int[]{
               0,1,2, 0,2,3
        };
        Mesh plane = new Mesh(vertices, indices);
        mesh = new Mesh("valid.obj");
        transform = new Transform();
        camera = new Camera();
        material = new Material(new Texture("1.png"), new Vector3f(1,1,1));
        Transform.setProjection(70, Window.getWidth(), Window.getHeight(), 0.01f, 1000f);
        Transform.setCamera(camera);

        entities = new ArrayList<>();
        entities.add(new Entity(mesh, new Transform(new Vector3f(0,0.5f,0), new Vector3f(0,0,0), new Vector3f(1,1,1)), material));
        entities.add(new Entity(mesh, transform, material));
        entities.add(new Entity(plane, new Transform(new Vector3f(0,0,0), new Vector3f(0,0,0), new Vector3f(1,1,1)), material));




    }

    public void render(RenderingEngine renderingEngine){
        renderingEngine.render(entities, camera);

    }

    float d = 0.0f;
    public void update(){
        d += 1f;
        float a = (float)Math.sin(d);
        transform.setPosition(new Vector3f(4, 2, 3));
        transform.setRotation(new Vector3f(d, d, 0));
    }

    public void input(){
        if(Input.getKey(Input.KEY_Q)){
            System.exit(0);
        }
        camera.input();
    }
}
