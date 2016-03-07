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
import com.company.Game.objects.Player;

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

    private Player player;

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
        transform = new Transform();

        Transform.setProjection(70, Window.getWidth(), Window.getHeight(), 0.01f, 1000f);
        Transform.setCamera(camera);

        r = new Random();
        entities = new ArrayList<>();


/*****************************************************
* Data for scenes (test)
*****************************************************/

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
        material = new Material(new Texture("1.png"), 1, 3);

/***********************************************************/


/*************************************************************************************
* SCENE: MULTICUBE
*/
//        entities.add(new Entity(mesh, new Transform(new Vector3f(0,0.5f,0), new Vector3f(0,0,0), new Vector3f(1,1,1)), material));
        entities.add(new Entity(mesh, transform, material));
        entities.add(new Entity(plane, new Transform(new Vector3f(0,0,0), new Vector3f(0,0,0), new Vector3f(1,1,1)), material));
//        for(int i = 0; i < 500; i++){
//            entities.add(new Entity(mesh, new Transform(new Vector3f(r.nextInt(100),r.nextInt(100),r.nextInt(100)), new Vector3f(r.nextInt(50),r.nextInt(50),r.nextInt(50)), new Vector3f(1,1,1)), material));
//        }

//        entities.add(new Entity(new Mesh("m1.obj"), new Transform(new Vector3f(-30, 2, -10), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)),
////                new Material(null, new Vector3f(1,0,0))
//                material
//        ));

//        entities.add(new Entity(new Mesh("punk3.obj"), new Transform(new Vector3f(-40, 0, 10), new Vector3f(0, 90, 0), new Vector3f(1, 1, 1)),
//                new Material(new Texture("2.png"))));
/*****************************************************************************************/


/***************************************************************************
* SCENE: ROOM
*/

//        entities.add(new Entity(new Mesh("room.obj"), new Transform(new Vector3f(0,0,0), new Vector3f(0,0,0), new Vector3f(1,1,1)), material));
//        entities.add(new Entity(new Mesh("room1.obj"), new Transform(new Vector3f(0,0,0), new Vector3f(0,0,0), new Vector3f(1,1,1)), new Material(new Texture("room.png"), 1, 0)));

//        entities.add(new Entity(new Mesh("floor.obj"), new Transform(new Vector3f(0,0,0), new Vector3f(0,0,0), new Vector3f(1,1,1)), new Material(new Texture("floor.png"), 1, 0)));
//        entities.add(new Entity(new Mesh("walls.obj"), new Transform(new Vector3f(0,0,0), new Vector3f(0,0,0), new Vector3f(1,1,1)), new Material(new Texture("wall.png"), 1, 0)));
//        entities.add(new Entity(new Mesh("up.obj"), new Transform(new Vector3f(0,0,0), new Vector3f(0,0,0), new Vector3f(1,1,1)), new Material(new Texture("up.png"), 1, 0)));

//        entities.add(new Entity(new Mesh("table.obj"), new Transform(new Vector3f(4, -0.1f, 1), new Vector3f(0, 0, 0), new Vector3f(0.4f, 0.4f, 0.4f)), new Material(new Texture("wood.png"))));
//        entities.add(new Entity(new Mesh("lamp.obj"), new Transform(new Vector3f(1.5f, 2.9f, 1.5f), new Vector3f(0, 0, 0), new Vector3f(0.5f, 0.5f, 0.5f)), new Material(new Texture("lamp.png"), 1, 8)));
//        entities.add(new Entity(new Mesh("bed.obj"), new Transform(new Vector3f(4, 0, 4), new Vector3f(0, 30, 0), new Vector3f(0.75f, 0.75f, 0.75f)), new Material(new Texture("bed.png"), 1, 0)));
/********************************************************************************/


/**************************************************************************************************
* SCENE: LITTLE STUPID TOWN
*/
//        Mesh b = new Mesh("1_BUILDING.obj");
//        Material mb = new Material(new Texture("1_BUILIDING.png"));
//
//        Mesh r = new Mesh("1_ROAD.obj");
//        Material rm = new Material(new Texture("1_ASPHALT.png"));
//
//        Mesh t = new Mesh("1_TRASH.obj");
//        Material tm =  new Material(new Texture("1_TRASH.png"));
//        for(int i = 0; i < 10; i++){
//            entities.add(new Entity(r, new Transform(new Vector3f(i * 40, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)), rm));
//        }
//        for(int i = 0; i < 30; i++){
//            entities.add(new Entity(b, new Transform(new Vector3f(i * 20, 1, 5), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)), mb));
//            entities.add(new Entity(t, new Transform(new Vector3f(i * 20 + 2, 0, 2), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)), tm));
//        }
//        for(int i = 0; i < 30; i++){
//            entities.add(new Entity(b, new Transform(new Vector3f(i * 20, 1, -5), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)), mb));
//        }
//
//        for(int i = 0; i < 30; i++){
//            entities.add(new Entity(b, new Transform(new Vector3f(i * 20, 1, 20), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)), mb));
//            entities.add(new Entity(t, new Transform(new Vector3f(i * 20 + 2, 0, 22), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)), tm));
//        }
//        for(int i = 0; i < 30; i++){
//            entities.add(new Entity(b, new Transform(new Vector3f(i * 20, 1, 15), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)), mb));
//        }
//
//        for(int i = 0; i < 30; i++){
//            entities.add(new Entity(b, new Transform(new Vector3f(i * 20, 1, 40), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)), mb));
//            entities.add(new Entity(t, new Transform(new Vector3f(i * 20 + 2, 0, 42), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)), tm));
//        }
//        for(int i = 0; i < 30; i++){
//            entities.add(new Entity(b, new Transform(new Vector3f(i * 20, 1, 35), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)), mb));
//        }
//
//        Mesh l = new Mesh("1_LIGHTER.obj");
//        Material lm = new Material(new Texture("1_LIGHTER.png"));
//        for(int i = 0; i < 10; i++){
//            entities.add(new Entity(l, new Transform(new Vector3f(i * 40, 1, 2), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)), lm));
//            engine.getRenderingEngine().addLight(new PointLight(new Vector3f(i*40, 3.7f, 0.7f),/* new Vector3f(0, -1, 0),*/ new Vector3f(1, 1,1), 10.9f, new Attenuation(0,0,0.5f) /*, 0.4f*/ ));
//        }
/*******************************************************************************/



        camera.setPos(new Vector3f(0, 1, 0));
        camera.setForward(new Vector3f(1, 0, 1));

//        player = new Player(camera, new Mesh("punk3.obj"), new Material(new Texture("2.png")));
//        entities.add(player);

//        engine.getRenderingEngine().addLight(new DirectionalLight(new Vector3f(1, 0.6f, 0.6f), 0.8f, new Vector3f(1, -1, 0)));
//        engine.getRenderingEngine().addLight(new PointLight(new Vector3f(10.5f, 3, 1.5f), new Vector3f(1, 1,1), 0.2f, new Attenuation(1,0,0)));
//        engine.getRenderingEngine().addLight(new SpotLight(new Vector3f(-2, 5.1f, 3), new Vector3f(0, -1, -1), new Vector3f(1, 1,1), 10.9f, new Attenuation(0,0,0.5f), 0.4f ));
        engine.getRenderingEngine().addLight(new SpotLight(new Vector3f(4, 3, 4), new Vector3f(0, -1, 0), new Vector3f(1, 1,1), 10.9f, new Attenuation(0,0,0.5f), 0.4f ));

    }
    public void render(RenderingEngine renderingEngine){
        renderingEngine.render(entities);

    }

    float d = 0.0f;
    public void update(){
        d += Time.getDelta()/10;
        float a = 3 * (float)Math.sin(d);
//        player.update();
        transform.setPosition(new Vector3f(4, 2, 3));
//        transform.setRotation(new Vector3f(d, d, 0));

//        ((SpotLight)engine.getRenderingEngine().getLights().get(0)).setPosition(engine.getRenderingEngine().getMainCamera().getPos());
//        ((SpotLight)engine.getRenderingEngine().getLights().get(0)).setDirection(engine.getRenderingEngine().getMainCamera().getForward());
    }

    public void input(){
        if(Input.getKey(Input.KEY_Q)){
            System.exit(0);
        }
//        player.input();
        camera.input();
    }

    public void setEngine(CoreEngine engine){
        this.engine = engine;
    }
}
