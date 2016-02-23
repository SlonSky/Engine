package com.company.Game;


import com.company.Engine.core.CoreEngine;
import com.company.Engine.core.Input;
import com.company.Engine.core.Time;
import com.company.Engine.core.Window;
import com.company.Engine.rendering.*;
import com.company.Engine.rendering.light.Attenuation;
import com.company.Engine.rendering.light.DirectionalLight;
import com.company.Engine.rendering.light.PointLight;
import com.company.Engine.rendering.light.SpotLight;
import com.company.Engine.util.Vector2f;
import com.company.Engine.util.Vector3f;
import com.company.Engine.util.Vertex;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Slon on 09.02.2016.
 */

/**
 * Warning! Dangerous architecture!
 * TODO: Object/Component system (maybe)
 * TODO: Scene graph
 */

public class Game {

    private CoreEngine engine;

    private Camera camera;
    private Transform transform;
    private Material material;
    private Mesh mesh;
    ArrayList<Entity> entities;

    private  Random r;

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

        camera = new Camera();
        engine.getRenderingEngine().setMainCamera(camera);


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


        material = new Material(new Texture("1.png"), 1, 3);
        Transform.setProjection(70, Window.getWidth(), Window.getHeight(), 0.01f, 1000f);
        Transform.setCamera(camera);

        entities = new ArrayList<>();
//        entities.add(new Entity(mesh, new Transform(new Vector3f(0,0.5f,0), new Vector3f(0,0,0), new Vector3f(1,1,1)), material));
//        entities.add(new Entity(mesh, transform, material));
//        entities.add(new Entity(plane, new Transform(new Vector3f(0,0,0), new Vector3f(0,0,0), new Vector3f(1,1,1)), material));
//
////Cube test
       r = new Random();
//        for(int i = 0; i < 300; i++){
//            entities.add(new Entity(mesh, new Transform(new Vector3f(r.nextInt(50),r.nextInt(50),r.nextInt(50)), new Vector3f(r.nextInt(50),r.nextInt(50),r.nextInt(50)), new Vector3f(1,1,1)), material));
//        }
//        entities.add(new Entity(new Mesh("m1.obj"), new Transform(new Vector3f(-30, 2, -10), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)),
////                new Material(null, new Vector3f(1,0,0))
//                material
//        ));
//
//        entities.add(new Entity(new Mesh("punk3.obj"), new Transform(new Vector3f(-40, 0, 10), new Vector3f(0, 90, 0), new Vector3f(1, 1, 1)),
//                new Material(new Texture("2.png"))));
//
//        entities.add(new Entity(new Mesh("room.obj"), new Transform(new Vector3f(0,0,0), new Vector3f(0,0,0), new Vector3f(1,1,1)), material));
//        entities.add(new Entity(new Mesh("room1.obj"), new Transform(new Vector3f(0,0,0), new Vector3f(0,0,0), new Vector3f(1,1,1)), new Material(new Texture("room.png"), 1, 0)));

        entities.add(new Entity(new Mesh("floor.obj"), new Transform(new Vector3f(0,0,0), new Vector3f(0,0,0), new Vector3f(1,1,1)), new Material(new Texture("floor.png"), 1, 0)));
        entities.add(new Entity(new Mesh("walls.obj"), new Transform(new Vector3f(0,0,0), new Vector3f(0,0,0), new Vector3f(1,1,1)), new Material(new Texture("wall.png"), 1, 0)));
        entities.add(new Entity(new Mesh("up.obj"), new Transform(new Vector3f(0,0,0), new Vector3f(0,0,0), new Vector3f(1,1,1)), new Material(new Texture("up.png"), 1, 0)));

        entities.add(new Entity(new Mesh("table.obj"), new Transform(new Vector3f(4, -0.1f, 1), new Vector3f(0, 0, 0), new Vector3f(0.4f, 0.4f, 0.4f)), new Material(new Texture("wood.png"))));
//        entities.add(new Entity(new Mesh("lamp.obj"), new Transform(new Vector3f(1.5f, 2.9f, 1.5f), new Vector3f(0, 0, 0), new Vector3f(0.5f, 0.5f, 0.5f)), new Material(new Texture("lamp.png"), 1, 8)));
        entities.add(new Entity(new Mesh("bed.obj"), new Transform(new Vector3f(4,0,4), new Vector3f(0,30,0), new Vector3f(0.75f, 0.75f, 0.75f)), new Material(new Texture("bed.png"), 1, 0)));

        camera.setPos(new Vector3f(0, 1, 0));
        camera.setForward(new Vector3f(1, 0, 1));

//        engine.getRenderingEngine().addLight(new DirectionalLight(new Vector3f(1, 1,1), 0.8f, new Vector3f(1, -1, -1)));
        engine.getRenderingEngine().addLight(new PointLight(new Vector3f(1.5f, 2.9f, 1.5f), new Vector3f(1, 1,1), 0.7f, new Attenuation(0,0,0.5f)));
//        engine.getRenderingEngine().addLight(new SpotLight(new Vector3f(-40, 5, 20), new Vector3f(0, -1, -1), new Vector3f(1, 1,1), 10.9f, new Attenuation(0,0,0.5f), 0.4f ));

    }
    public void render(RenderingEngine renderingEngine){
        renderingEngine.render(entities);

    }

    float d = 0.0f;
    public void update(){
        d += Time.getDelta()/10;
        float a = 3 * (float)Math.sin(d);
        transform.setPosition(new Vector3f(4, 2, 3));
        transform.setRotation(new Vector3f(d, d, 0));
//        ((PointLight)engine.getRenderingEngine().getLights().get(0)).setPosition(new Vector3f(a, 3, 1.5f));
    }

    public void input(){
        if(Input.getKey(Input.KEY_Q)){
            System.exit(0);
        }
        camera.input();
    }

    public void setEngine(CoreEngine engine){
        this.engine = engine;
    }
}
