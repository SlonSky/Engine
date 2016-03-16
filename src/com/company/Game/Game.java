package com.company.Game;


import com.company.Engine.core.CoreEngine;
import com.company.Engine.core.Input;
import com.company.Engine.core.Time;
import com.company.Engine.core.Window;
import com.company.Engine.physics.PhysicsEngine;
import com.company.Engine.rendering.*;
import com.company.Engine.rendering.light.*;
import com.company.Engine.util.Quaternion;
import com.company.Engine.util.Vector2f;
import com.company.Engine.util.Vector3f;
import com.company.Engine.util.Vertex;
import com.company.Game.objects.GameObject;
import com.company.Game.objects.Level;
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

/**
 * TODO: refactor GameObject and Entity class (change transform location)
 */

public class Game {

    private CoreEngine engine;
    private Camera camera;

    private Transform transform;
    private Material material;
    private Mesh mesh;

    private  Random r;

    private Player player;
    private Level level;
    ArrayList<GameObject> objects;

    public void init(){

        // main initialization
        camera = new Camera(new Vector3f(0,0,0),new Quaternion(0,0,0,1));
        engine.getRenderingEngine().setMainCamera(camera);
        Transform.setProjection(70, Window.getWidth(), Window.getHeight(), 0.01f, 1000f);
        Transform.setCamera(camera);

        // stuff
        transform = new Transform();
        r = new Random();

        objects = new ArrayList<>();
        ArrayList<Light> lights = new ArrayList<>();

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
        mesh = new Mesh("bound.obj");
        material = new Material(new Texture("1.png"), 1, 3);

/***********************************************************/


/*************************************************************************************
* SCENE: MULTICUBE
*/
//        entities.add(new Entity(mesh, new Transform(new Vector3f(0,0.5f,0), new Vector3f(0,0,0), new Vector3f(1,1,1)), material));
//        entities.add(new Entity(mesh, transform, material));
//        entities.add(new Entity(plane, new Transform(new Vector3f(0,0,0), new Vector3f(0,0,0), new Vector3f(1,1,1)), material));
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
//            objects.add(new GameObject(new Entity(r, new Transform(new Vector3f(i * 40, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)), rm)));
//        }
//        for(int i = 0; i < 30; i++){
//            objects.add(new GameObject(new Entity(b, new Transform(new Vector3f(i * 20, 1, 5), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)), mb)));
//            objects.add(new GameObject(new Entity(t, new Transform(new Vector3f(i * 20 + 2, 0, 2), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)), tm)));
//        }
//        for(int i = 0; i < 30; i++){
//            objects.add(new GameObject(new Entity(b, new Transform(new Vector3f(i * 20, 1, -5), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)), mb)));
//        }
//
//        for(int i = 0; i < 30; i++){
//            objects.add(new GameObject(new Entity(b, new Transform(new Vector3f(i * 20, 1, 20), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)), mb)));
//            objects.add(new GameObject(new Entity(t, new Transform(new Vector3f(i * 20 + 2, 0, 22), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)), tm)));
//        }
//        for(int i = 0; i < 30; i++){
//            objects.add(new GameObject(new Entity(b, new Transform(new Vector3f(i * 20, 1, 15), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)), mb)));
//        }
//
//        for(int i = 0; i < 30; i++){
//            objects.add(new GameObject(new Entity(b, new Transform(new Vector3f(i * 20, 1, 40), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)), mb)));
//            objects.add(new GameObject(new Entity(t, new Transform(new Vector3f(i * 20 + 2, 0, 42), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)), tm)));
//        }
//        for(int i = 0; i < 30; i++){
//            objects.add(new GameObject(new Entity(b, new Transform(new Vector3f(i * 20, 1, 35), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)), mb)));
//        }
//
//        Mesh l = new Mesh("1_LIGHTER.obj");
//        Material lm = new Material(new Texture("1_LIGHTER.png"));
//        for(int i = 0; i < 10; i++){
//            objects.add(new GameObject(new Entity(l, new Transform(new Vector3f(i * 40, 1, 2), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)), lm)));
//            lights.add(new PointLight(new Vector3f(i*40, 3.7f, 0.7f),/* new Vector3f(0, -1, 0),*/ new Vector3f(1, 1,1), 10.9f, new Attenuation(0,0,0.5f) /*, 0.4f*/ ));
//        }
/*******************************************************************************/
//        player = new Player(camera, new Mesh("punk3.obj"), new Material(new Texture("2.png")));
//        entities.add(player);

//        engine.getRenderingEngine().addLight(new DirectionalLight(new Vector3f(1, 0.6f, 0.6f), 0.8f, new Vector3f(1, -1, 0)));
//        engine.getRenderingEngine().addLight(new PointLight(new Vector3f(10.5f, 3, 1.5f), new Vector3f(1, 1,1), 0.2f, new Attenuation(1,0,0)));
//        engine.getRenderingEngine().addLight(new SpotLight(new Vector3f(-2, 5.1f, 3), new Vector3f(0, -1, -1), new Vector3f(1, 1,1), 10.9f, new Attenuation(0,0,0.5f), 0.4f ));
//        engine.getRenderingEngine().addLight(new SpotLight(new Vector3f(4, 3, 4), new Vector3f(0, -1, 0), new Vector3f(1, 1,1), 10.9f, new Attenuation(0,0,0.5f), 0.4f ));


        camera.setPos(new Vector3f(0, 1, 0));
//        camera.setForward(new Vector3f(1, 0, 1));
/**
 * Test cube scene
 */
//        objects.add(new GameObject(new Entity(mesh, transform, material)));
//        objects.add(new GameObject(new Entity(plane,new Transform(new Vector3f(0,0,0), new Vector3f(0,0,0), new Vector3f(1,1,1)), material)));

//        int n = 100_000;
//        int size = 5000;
//        for(int i = 0; i < n; i++){
//            objects.add(new GameObject(new Entity(mesh,
//                    new Transform(new Vector3f(r.nextInt(size),r.nextInt(size),r.nextInt(size)),
//                            new Vector3f(r.nextInt(50),r.nextInt(50),r.nextInt(50)), new Vector3f(2.1f,2.1f,2.1f)), material)));
//        }

//        lights.add(new PointLight(new Vector3f(10.5f, 3, 1.5f), new Vector3f(1, 1,1), 0.2f, new Attenuation(1,0,0)));
//        lights.add(new PointLight(new Vector3f(20.5f, 3, 10.5f), new Vector3f(0, 1,1), 0.2f, new Attenuation(1,0,0)));
//        lights.add(new PointLight(new Vector3f(-10.5f, 3, -21.5f), new Vector3f(1, 0,0), 0.2f, new Attenuation(1,0,0)));
//        lights.add(new PointLight(new Vector3f(40.5f, 5, 13.5f), new Vector3f(0, 1,0), 0.2f, new Attenuation(1,0,0)));

        Mesh road = new Mesh("road.obj");
        Material roadMaterial = new Material(new Texture("road.png"));

        Mesh block = new Mesh("block.obj");
        Material blockMaterial = new Material(new Texture("block.png"));

        Mesh fence = new Mesh("fence.obj");
        Material fenceMaterial = new Material(new Texture("fence.png"), 2, 10);

        Mesh building = new Mesh("building.obj");
        Material buildingMaterial = new Material(new Texture("building.png"), 0, 8);

        Mesh building2 = new Mesh("building2.obj");
        Material building2Material = new Material(new Texture("building2.png"), 0, 8);

        Mesh zombie = new Mesh("zombie.obj");
        Material zombieMaterial = new Material(new Texture("zombie.png"), 1, 8);

        objects.add(new GameObject(new Entity(building, new Transform(new Vector3f(50, 5, -10), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(-90)), new Vector3f(1,1,1)), buildingMaterial), new Vector3f(10.539f/2, 10.539f/2, 10.451f/2)));
        objects.add(new GameObject(new Entity(building, new Transform(new Vector3f(-15, 5, -20), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(-90)), new Vector3f(1,1,1)), buildingMaterial), new Vector3f(10.539f/2, 10.539f/2, 10.451f/2)));
        objects.add(new GameObject(new Entity(building, new Transform(new Vector3f(5, 5, -10), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(-90)), new Vector3f(1,1,1)), buildingMaterial), new Vector3f(10.539f/2, 10.539f/2, 10.451f/2)));

        objects.add(new GameObject(new Entity(building, new Transform(new Vector3f(5, 5, -10), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(-90)), new Vector3f(1,1,1)), buildingMaterial), new Vector3f(10.539f/2, 10.539f/2, 10.451f/2)));

//        objects.add(new GameObject(new Entity(block, new Transform(new Vector3f(0, 0, 0), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(-37)), new Vector3f(1,1,1)), blockMaterial), new Vector3f(2.862f/2, 0.963f/2, 0.858f/2)));
//        objects.add(new GameObject(new Entity(block, new Transform(new Vector3f(3, 0, 0), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(64)), new Vector3f(1,1,1)), blockMaterial), new Vector3f(2.862f/2, 0.963f/2, 0.858f/2)));
//        objects.add(new GameObject(new Entity(block, new Transform(new Vector3f(1.5f, 0, 1), new Vector3f(0,-37,0), new Vector3f(1,1,1)), blockMaterial), new Vector3f(2.862f/2, 0.963f/2, 0.858f/2)));

//        for(int j =0; j<4; j++) {
//            for (int i = 0; i < 15; i++) {
//
//                objects.add(new GameObject(new Entity(zombie, new Transform(new Vector3f(i * r.nextInt(5), 0, j * 10 + r.nextInt(4)-2), new Quaternion(new Vector3f(0, 1, 0),(float)Math.toRadians(r.nextInt(180))), new Vector3f(1, 1, 1)), zombieMaterial), new Vector3f(1.794f / 2, 0.581f / 2,  1.055f/ 2)));
//                objects.add(new GameObject(new Entity(zombie, new Transform(new Vector3f(i * r.nextInt(5), 0, j * 10 + r.nextInt(4)-2), new Quaternion(new Vector3f(0, 1, 0),(float)Math.toRadians(r.nextInt(180))), new Vector3f(1, 1, 1)), zombieMaterial), new Vector3f(1.794f / 2, 0.581f / 2,  1.055f/ 2)));
//                objects.add(new GameObject(new Entity(zombie, new Transform(new Vector3f(i * r.nextInt(5), 0, j * 10 + r.nextInt(4)-2), new Quaternion(new Vector3f(0, 1, 0),(float)Math.toRadians(r.nextInt(180))), new Vector3f(1, 1, 1)), zombieMaterial), new Vector3f(1.794f / 2, 0.581f / 2,  1.055f/ 2)));
//                objects.add(new GameObject(new Entity(zombie, new Transform(new Vector3f(i * r.nextInt(5), 0, j * 10 + r.nextInt(4)-2), new Quaternion(new Vector3f(0, 1, 0),(float)Math.toRadians(r.nextInt(180))), new Vector3f(1, 1, 1)), zombieMaterial), new Vector3f(1.794f / 2, 0.581f / 2,  1.055f/ 2)));
//
//                objects.add(new GameObject(new Entity(fence, new Transform(new Vector3f(i * 2.032f * 2, 0, j*10 + 1), new Quaternion(new Vector3f(0, 1, 0),(float)Math.toRadians(90)), new Vector3f(1, 1, 1)), fenceMaterial), new Vector3f(4.032f / 2, 3.259f / 2, 0.133f / 2)));
//                objects.add(new GameObject(new Entity(road, new Transform(new Vector3f(i * 3.422f, 0, j*10), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(90)).mul(new Quaternion(new Vector3f(0, 0, 1), (float)Math.toRadians(180))), new Vector3f(1, 1, 1)), roadMaterial), new Vector3f(3.422f / 2, 0.088f / 2, 2.2f / 2)));
//            }
//        }
//
//
//        objects.add(new GameObject(new Entity(building2, new Transform(new Vector3f(25, -0.5f, -3), new Quaternion(new Vector3f(0, 1, 0),(float)Math.toRadians(-90)), new Vector3f(1,1,1)), building2Material), new Vector3f(7.321f/2, 5.96f/2, 10.968f/2)));
//        objects.add(new GameObject(new Entity(building2, new Transform(new Vector3f(35, -0.5f, -3), new Quaternion(new Vector3f(0, 1, 0),(float)Math.toRadians(-90)), new Vector3f(1,1,1)), building2Material), new Vector3f(7.321f/2, 5.96f/2, 10.968f/2)));
//        objects.add(new GameObject(new Entity(building2, new Transform(new Vector3f(45, -0.5f, -3), new Quaternion(new Vector3f(0, 1, 0),(float)Math.toRadians(-90)), new Vector3f(1,1,1)), building2Material), new Vector3f(7.321f/2, 5.96f/2, 10.968f/2)));

//        lights.add(new DirectionalLight(new Vector3f(0.3f, 0.3f, 0), 0.2f, new Vector3f(0, -1, -1)));
//        lights.add(new PointLight(new Vector3f(25.5f, 3, -2f), new Vector3f(1, 0,0), 0.2f, new Attenuation(1,0,0)));
//        lights.add(new PointLight(new Vector3f(35.5f, 3, -2f), new Vector3f(1, 0,0), 0.2f, new Attenuation(1,0,0)));
//        lights.add(new PointLight(new Vector3f(45.5f, 3, -2f), new Vector3f(1, 1,1), 0.2f, new Attenuation(1,0,0)));
//        lights.add(new PointLight(new Vector3f(55.5f, 3, -2f), new Vector3f(1, 1,1), 0.2f, new Attenuation(1,0,0)));


        level = new Level(objects, lights);

        player = new Player(camera, new Entity(new Mesh("hands.obj"), new Transform(camera.getPos(), camera.getRot(), new Vector3f(1,1,1)),material));
        objects.add(player.getBody());
    }
    public void render(RenderingEngine renderingEngine){
        renderingEngine.render();
    }

    public void update(){
        player.update();
    }

    int i = 0;
    public void input(){
        if(Input.getKey(Input.KEY_Q)){
            System.exit(0);
        }
        player.input(PhysicsEngine.checkIntersection(player.getBody(), objects));
//        camera.input();

        if(Input.getKey(Input.KEY_C)){
            i += 1;
//            for(GameObject g: objects) {
            GameObject g = objects.get(0);
                g.getEntity().getTransform().setPosition(new Vector3f(0,0,0));
                g.getCullingCube().moveTo(new Vector3f(0, 0, 0));
                g.getBound().getTransform().setPosition(new Vector3f(0,0,0));
//            }
        }
    }

    public void setEngine(CoreEngine engine){
        this.engine = engine;
    }

    public Level getLevel() {
        return level;
    }
}
