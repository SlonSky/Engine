package Game.games;


import Engine.audio.Sound;
import Engine.audio.Source;
import Engine.core.*;
import Engine.physics.Collider;
import Engine.rendering.*;
import Engine.rendering.animation.AnimMesh;
import Engine.rendering.animation.Animation;
import Engine.rendering.guis.GUITexture;
import Engine.rendering.light.*;
import Engine.rendering.meshManagment.Material;
import Engine.rendering.meshManagment.Mesh;
import Engine.rendering.meshManagment.Texture;
import Engine.rendering.particles.ParticleMaster;
import Engine.rendering.particles.ParticleTexture;
import Engine.rendering.skybox.SkyBox;
import Engine.rendering.text.Font;
import Engine.rendering.text.Text;
import Engine.util.Quaternion;
import Engine.util.Vector2f;
import Engine.util.Vector3f;
import Game.Game;
import Game.GameObject;
import Game.GameplayManager;
import Game.Level;
import Game.enemy.Enemy;
import Game.entities.*;
import Game.player.Player;
import Game.Initializer;
import Game.trigger.Finish;
import Game.trigger.Trigger;
import Game.trigger.ZMSpawn;
import org.newdawn.slick.particles.Particle;

import javax.xml.stream.XMLEventReader;
import java.util.*;

/**
 * Created by Slon on 09.02.2016.
 */
public class TestGame extends Game {


//    private Transform transform;
//    private Material material;
//    private Mesh mesh;


    private Player player;
    private Decoration d;

    Text text;
    Font font;

    ArrayList<GameObject> objects;

        AnimMesh idle ;
        AnimMesh chase;
    AnimMesh attack;
    AnimMesh dying;

    private Random r;


    public void init(){

        System.out.println("Loading...");
        double start = System.currentTimeMillis();

        // main initialization
        camera = new Camera(new Vector3f(0,0,0),new Quaternion(0,0,0,1));
        setCamera(camera);
        Initializer.getInstance().loadLevel(camera);
        camera.setPos(new Vector3f(0, 1, 0));
        objects = new ArrayList<>();
        ArrayList<Light> lights = new ArrayList<>();

        r = new Random();
//
//        lights.add(new DirectionalLight(new Vector3f(0.5f, 0.3f, 0), 0.2f, new Vector3f(1f, -1, 1)));
//        lights.add(new PointLight(new Vector3f(25.5f, 3, -2f), new Vector3f(1, 0,0), 0.2f, new Attenuation(1,0,0)));
//        lights.add(new PointLight(new Vector3f(35.5f, 3, -2f), new Vector3f(1, 0,0), 0.2f, new Attenuation(1,0,0)));
//        lights.add(new PointLight(new Vector3f(45.5f, 3, -2f), new Vector3f(1, 1,1), 0.2f, new Attenuation(1,0,0)));
//        lights.add(new PointLight(new Vector3f(55.5f, 3, -2f), new Vector3f(1, 1,1), 0.2f, new Attenuation(1,0,0)));





        Animation hands = new Animation(0, 100, new AnimMesh("ak2", "untitled", new Material(new Texture("ak.png"), 2, 8), 100, 30));
        player = new Player(camera, new Transform(new Vector3f(-30f, 5, -55), camera.getRot(), new Vector3f(1,1,1)),
                hands,
                new Collider(new Vector3f(2,2f,2f), new Vector3f(0,-1.5f,0)));
        objects.add(player);
//        objects.add(new Director(camera, new Transform(new Vector3f(0f, 5, 0), camera.getRot(), new Vector3f(1,1,1))));

// todo: singleton for config initialization


        // Concrete floor
        objects.add(new Decoration(new Transform(new Vector3f(-0.2686f,-0.16f,13.4492f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new Graphic(new Mesh("floor.obj"), new Material(new Texture("concrete.png"), 0.7f, 1))));
        //***********************

        // Road
        Mesh roadDefault = new Mesh("roadDefault.obj");
        Material roadDefaultMaterial = new Material(new Texture("roadDefault.png"), 0.8f, 1);
        Vector3f roadDefaultCullingSize = new Vector3f(6,0.6f,6);
        Vector3f roadDefaultCullingOffset = new Vector3f(0,0,0);

        Mesh roadCross = new Mesh("roadCross.obj");
        Material roadCrossMaterial = new Material(new Texture("roadDefault.png"), 0.8f, 1);
        Vector3f roadCrossCullingSize = new Vector3f(6,0.6f,9);
        Vector3f roadCrossCullingOffset = new Vector3f(0,0,0);

        Mesh roadEnd = new Mesh("roadEnd.obj");
        Material roadEndMaterial = new Material(new Texture("roadDefault.png"), 0.8f, 1);
        Vector3f roadEndCullingSize = new Vector3f(6,0.6f,3);
        Vector3f roadEndCullingOffset = new Vector3f(0,0,0);

        Vector3f roadStart = new Vector3f(-2.374f, 0, -69.54f);
        float incr = 6;
        for(int i=0; i < 30; i++){
            if(i == 18){
                continue;
            }
            objects.add(new Decoration(new Transform(roadStart.add(new Vector3f(0, 0,incr*i)),new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                    new Graphic(roadDefault, roadDefaultMaterial), roadDefaultCullingSize));
            if(i > 18){
                objects.add(new Decoration(new Transform(roadStart.add(new Vector3f(30, 0,incr*i)),new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                        new Graphic(roadDefault, roadDefaultMaterial), roadDefaultCullingSize));
            }
        }
        objects.add(new Decoration(new Transform(new Vector3f(-4.09f, 0, 38.45f),new Quaternion(new Vector3f(0,1,0), (float)Math.toRadians(-90)), new Vector3f(1,1,1)),
                new Graphic(roadEnd, roadEndMaterial), roadEndCullingSize));
        objects.add(new Decoration(new Transform(new Vector3f(0.4f, 0, 38.445f),new Quaternion(new Vector3f(0,1,0), (float)Math.toRadians(-90)), new Vector3f(1,1,1)),
                new Graphic(roadCross, roadCrossMaterial), roadCrossCullingSize));

        roadStart = new Vector3f(9.61f, 0, 38.446f);
        for(int i=0; i < 6; i++){
            if(i == 3){
                continue;
            }
            objects.add(new Decoration(new Transform(roadStart.add(new Vector3f(i*incr, 0,0)),new Quaternion(new Vector3f(0,1,0), (float)Math.toRadians(-90)), new Vector3f(1,1,1)),
                    new Graphic(roadDefault, roadDefaultMaterial), roadDefaultCullingSize));
        }
        objects.add(new Decoration(new Transform(new Vector3f(27.61412f, 0, 36.721f),new Quaternion(new Vector3f(0,1,0), (float)Math.toRadians(180)), new Vector3f(1,1,1)),
                new Graphic(roadEnd, roadEndMaterial), roadEndCullingSize));
        objects.add(new Decoration(new Transform(new Vector3f(27.59949f, 0, 41.2316f),new Quaternion(new Vector3f(0,1,0), (float)Math.toRadians(180)), new Vector3f(1,1,1)),
                new Graphic(roadCross, roadCrossMaterial), roadCrossCullingSize));
        //*******************************************************


        // Buildings

        Mesh building = new Mesh("building.obj");
        Material buildingMaterial = new Material(new Texture("building.png"), 1, 3);
        Material buildingWhiteMaterial = new Material(new Texture("buildingWhite.png"), 1, 3);

        objects.add(new Decoration(new Transform(new Vector3f(-33.74f, 4.83957f, -46.285f), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(90)), new Vector3f(1,1,1)),
                new Graphic(building, buildingMaterial), new Vector3f(11, 11, 11), new Vector3f(11, 11, 11), new Vector3f(0,0,0)));
        objects.add(new Decoration(new Transform(new Vector3f(-24.264f, 4.83957f, -46.285f), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(90)), new Vector3f(1,1,1)),
                new Graphic(building, buildingMaterial), new Vector3f(11, 11, 11), new Vector3f(11, 11, 11), new Vector3f(0,0,0)));
        objects.add(new Decoration(new Transform(new Vector3f(-14.772f, 4.83957f, -46.285f), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(90)), new Vector3f(1,1,1)),
                new Graphic(building, buildingMaterial), new Vector3f(11, 11, 11), new Vector3f(11, 11, 11), new Vector3f(0,0,0)));

        objects.add(new Decoration(new Transform(new Vector3f(-29.21f, 4.83957f, 24.18f), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(-90)), new Vector3f(1,1,1)),
                new Graphic(building, buildingMaterial), new Vector3f(11, 11, 11), new Vector3f(11, 11, 11), new Vector3f(0,0,0)));
        objects.add(new Decoration(new Transform(new Vector3f(-19.18f, 4.83957f, 24.18f), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(-90)), new Vector3f(1,1,1)),
                new Graphic(building, buildingMaterial), new Vector3f(11, 11, 11), new Vector3f(11, 11, 11), new Vector3f(0,0,0)));
        objects.add(new Decoration(new Transform(new Vector3f(-10.23398f, 4.83957f, 24.18f), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(-90)), new Vector3f(1,1,1)),
                new Graphic(building, buildingMaterial), new Vector3f(11, 11, 11), new Vector3f(11, 11, 11), new Vector3f(0,0,0)));

        objects.add(new Decoration(new Transform(new Vector3f(4.65732f, 4.83957f, -19.0414f), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(180)), new Vector3f(1,1,1)),
                new Graphic(building, buildingMaterial), new Vector3f(11, 11, 11), new Vector3f(11, 11, 11), new Vector3f(0,0,0)));
        objects.add(new Decoration(new Transform(new Vector3f(4.65732f, 4.83957f, -9.549f), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(180)), new Vector3f(1,1,1)),
                new Graphic(building, buildingMaterial), new Vector3f(11, 11, 11), new Vector3f(11, 11, 11), new Vector3f(0,0,0)));
        objects.add(new Decoration(new Transform(new Vector3f(4.65732f, 4.83957f, -0.06f), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(180)), new Vector3f(1,1,1)),
                new Graphic(building, buildingMaterial), new Vector3f(11, 11, 11), new Vector3f(11, 11, 11), new Vector3f(0,0,0)));


        objects.add(new Decoration(new Transform(new Vector3f(6.53646f, 4.83957f, -57.3327f), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(180)), new Vector3f(1,1,1)),
                new Graphic(building, buildingWhiteMaterial), new Vector3f(11, 11, 11), new Vector3f(11, 11, 11), new Vector3f(0,0,0)));
        objects.add(new Decoration(new Transform(new Vector3f(6.53646f, 4.83957f, -47.3327f), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(180)), new Vector3f(1,1,1)),
                new Graphic(building, buildingWhiteMaterial), new Vector3f(11, 11, 11), new Vector3f(11, 11, 11), new Vector3f(0,0,0)));
        objects.add(new Decoration(new Transform(new Vector3f(6.53646f, 4.83957f, -37.3327f), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(180)), new Vector3f(1,1,1)),
                new Graphic(building, buildingWhiteMaterial), new Vector3f(11, 11, 11), new Vector3f(11, 11, 11), new Vector3f(0,0,0)));

        objects.add(new Decoration(new Transform(new Vector3f(17.5756f, 4.83957f, 50.29884f), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(180)), new Vector3f(1,1,1)),
                new Graphic(building, buildingWhiteMaterial), new Vector3f(11, 11, 11), new Vector3f(11, 11, 11), new Vector3f(0,0,0)));
        objects.add(new Decoration(new Transform(new Vector3f(8.14582f, 4.83957f, 50.29884f), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(180)), new Vector3f(1,1,1)),
                new Graphic(building, buildingWhiteMaterial), new Vector3f(11, 11, 11), new Vector3f(11, 11, 11), new Vector3f(0,0,0)));

        objects.add(new Decoration(new Transform(new Vector3f(8.1f, 4.83957f, 19.96f), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(180)), new Vector3f(1,1,1)),
                new Graphic(building, buildingWhiteMaterial), new Vector3f(11, 11, 11), new Vector3f(11, 11, 11), new Vector3f(0,0,0)));
        objects.add(new Decoration(new Transform(new Vector3f(8.1f, 4.83957f, 29.392f), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(180)), new Vector3f(1,1,1)),
                new Graphic(building, buildingWhiteMaterial), new Vector3f(11, 11, 11), new Vector3f(11, 11, 11), new Vector3f(0,0,0)));
        //*******************************

        // Walls
        Mesh wall = new Mesh("wall.obj");
        Material wallMaterial = new Material(new Texture("wall.png"), 2, 20);

        roadStart = new Vector3f(-35.34f, 5, -66.277f);
        incr = 10.57f;
        for(int i = 0; i < 8; i++) {
            objects.add(new Decoration(
                    new Transform(roadStart.add(new Vector3f(i*incr,0,0)), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(0)), new Vector3f(1, 3.036f, 1)),
                    new Graphic(wall, wallMaterial),
                    new Vector3f(12.618f, 11.963f, 0.186f),
                    new Vector3f(12f, 11.963f, 0.186f), new Vector3f(0, 0, 0)));

        }
        roadStart = new Vector3f(-35.34f, 5, 92.4f);
        incr = 10.57f;
        for(int i = 0; i < 8; i++) {
//            if(i == 6){
//                continue;
//            }
            objects.add(new Decoration(
                    new Transform(roadStart.add(new Vector3f(i*incr,0,0)), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(0)), new Vector3f(1, 3.036f, 1)),
                    new Graphic(wall, wallMaterial),
                    new Vector3f(12.618f, 11.963f, 0.186f),
                    new Vector3f(12f, 11.963f, 0.186f), new Vector3f(0, 0, 0)));

        }
        roadStart = new Vector3f(-40.195f, 5, -61.29f);
        incr = 10.57f;
        for(int i = 0; i < 15; i++) {
            objects.add(new Decoration(
                    new Transform(roadStart.add(new Vector3f(0,0,i*incr)), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(90)), new Vector3f(1, 3.036f, 1)),
                    new Graphic(wall, wallMaterial),
                     new Vector3f(12f, 11.963f,0.186f ),
                    new Vector3f(0.186f, 11.963f,  12f), new Vector3f(0, 0, 0)));
            objects.add(new Decoration(
                    new Transform(roadStart.add(new Vector3f(80,0,i*incr)), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(90)), new Vector3f(1, 3.036f, 1)),
                    new Graphic(wall, wallMaterial),
                    new Vector3f(10f, 9.963f,0.186f ),
                    new Vector3f(0.186f, 11.963f, 10f), new Vector3f(0, 0, 0)));

        }
        //***********************************************

        // field
        Mesh field = new Mesh("field.obj");
        Material fieldMaterial = new Material(new Texture("field.png"), 0.8f, 1);
        objects.add(new Decoration(
                new Transform(new Vector3f(-22.08f, 0, -10.72f), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(0)), new Vector3f(1, 1, 1)),
                new Graphic(field, fieldMaterial)));
        //********

        Mesh ruins1 = new Mesh("ruins1.obj");
        Material ruins1Material = new Material(new Texture("ruins1.png"), 0.8f, 1);

        objects.add(new Decoration(
                new Transform(new Vector3f(-8.47f,0,44.93f), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(0)), new Vector3f(1, 1, 1)),
                new Graphic(ruins1, ruins1Material),
                new Vector3f(5.35f, 5.35f,5.35f ),
                new Vector3f(5.35f, 5.35f, 5.35f), new Vector3f(0, 0, 0)));

        objects.add(new Decoration(
                new Transform(new Vector3f(-14.58f,0,44.93f), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(0)), new Vector3f(1, 1, 1)),
                new Graphic(ruins1, ruins1Material),
                new Vector3f(5.35f, 5.35f,5.35f ),
                new Vector3f(5.35f, 5.35f, 5.35f), new Vector3f(0, 0, 0)));

///////////////////////////////////////////////////////

        Mesh tank = new Mesh("tank.obj");
        Material tankMaterial = new Material(new Texture("tank.png"), 2, 15);

        objects.add(new Decoration(
                new Transform(new Vector3f(0, 0.8f, 86.818f), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(180)), new Vector3f(1, 1, 1)),
                new Graphic(tank, tankMaterial),
                new Vector3f(3.851f, 3.851f,  8.321f),
                new Vector3f(3.851f, 3.851f,  8.321f), new Vector3f(0f, 0, 0)
        ));
        objects.add(new Decoration(
                new Transform(new Vector3f(-5, 0.8f, 86.818f), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(180)), new Vector3f(1, 1, 1)),
                new Graphic(tank, tankMaterial),
                new Vector3f(3.851f, 3.851f,  8.321f),
                new Vector3f(3.851f, 3.851f,  8.321f), new Vector3f(0f, 0, 0)
        ));
//////////////////////////////////////////////////////
        Mesh tent = new Mesh("tent.obj");
        Material tentMaterial = new Material(new Texture("tent.png"), 1, 15);
        objects.add(new Decoration(
                new Transform(new Vector3f(-22.95f, 0f, 81.98f), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(0)), new Vector3f(1, 1, 1)),
                new Graphic(tent, tentMaterial),
                new Vector3f(9.313f, 4.761f, 8.358f),
                new Vector3f(9.313f, 4.761f, 8.358f), new Vector3f(0f, 0, 0)
        ));
        objects.add(new Decoration(
                new Transform(new Vector3f(10.4f, 0f, 81.98f), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(0)), new Vector3f(1, 1, 1)),
                new Graphic(tent, tentMaterial),
                new Vector3f(9.313f, 4.761f, 8.358f),
                new Vector3f(9.313f, 4.761f, 8.358f), new Vector3f(0f, 0, 0)
        ));

        Mesh car = new Mesh("car.obj");
        Material carMaterial = new Material(new Texture("car.png"), 2, 10);
        objects.add(new Decoration(
                new Transform(new Vector3f(27.43f, 0f, 84.93f), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(0)), new Vector3f(1, 1, 1)),
                new Graphic(car, carMaterial),
                new Vector3f(2.881f, 4.047f, 7.037f),
                new Vector3f(2.881f, 4.047f, 7.037f), new Vector3f(0f, 0, 0)
        ));

        Mesh garbage = new Mesh("garbage.obj");
        Material garbageMaterial = new Material(new Texture("garbage.png"), 2, 12);
        objects.add(new Decoration(
                new Transform(new Vector3f(12.71f, 0, 0.31f), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(0)), new Vector3f(3, 3, 3)),
                new Graphic(garbage, garbageMaterial),
                new Vector3f(2, 2.95f, 3.346f),
                new Vector3f(2, 2.95f, 3.346f), new Vector3f(0f, 0, 0)
        ));
        objects.add(new Decoration(
                new Transform(new Vector3f(12.71f, 0, -4.31f), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(0)), new Vector3f(3, 3, 3)),
                new Graphic(garbage, garbageMaterial),
                new Vector3f(2, 2.95f, 3.346f),
                new Vector3f(2, 2.95f, 3.346f), new Vector3f(0f, 0, 0)
        ));
        objects.add(new Decoration(
                new Transform(new Vector3f(12.71f, 0, -8.31f), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(0)), new Vector3f(3, 3, 3)),
                new Graphic(garbage, garbageMaterial),
                new Vector3f(2, 2.95f, 3.346f),
                new Vector3f(2, 2.95f, 3.346f), new Vector3f(0f, 0, 0)
        ));

        Mesh ambulance = new Mesh("ambulance.obj");
        Material ambulanceMaterial = new Material(new Texture("ambulance.png"), 1, 9);
        objects.add(new Decoration(
                new Transform(new Vector3f(22.5f, 0.0f, -3.28f), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(0)), new Vector3f(1, 1, 1)),
                new Graphic(ambulance, ambulanceMaterial),
                new Vector3f(3.646f, 4.355f, 10f),
                new Vector3f(3.646f, 4.355f, 10f), new Vector3f(3.5f, 0, 0)
        ));

        Mesh oldCar = new Mesh("oldCar.obj");
        Material oldCareMaterial = new Material(new Texture("oldCar.png"), 1, 1);
        objects.add(new Decoration(
                new Transform(new Vector3f(30, 0.0f, -32), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(180)), new Vector3f(1, 1, 1)),
                new Graphic(oldCar, oldCareMaterial),
                new Vector3f(3.535f, 4f, 6.414f),
                new Vector3f(3.535f, 4f, 6.414f), new Vector3f(0f, 0, 0)
        ));
        objects.add(new Decoration(
                new Transform(new Vector3f(26, 0.0f, 20), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(180)), new Vector3f(1, 1, 1)),
                new Graphic(oldCar, oldCareMaterial),
                new Vector3f(3.535f, 4f, 6.414f),
                new Vector3f(3.535f, 4f, 6.414f), new Vector3f(0f, 0, 0)
        ));

        Mesh block = new Mesh("block.obj");
        Material blockMaterial = new Material(new Texture("block.png"), 1, 3);
        objects.add(new Decoration(
                new Transform(new Vector3f(-3.52f, 0f, 47.159f ), new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(0)), new Vector3f(1, 1, 1)),
                new Graphic(block, blockMaterial),
                new Vector3f(2.862f, 0.96f, 0.85f),
                new Vector3f(2.862f, 0.96f, 0.85f), new Vector3f(0, 0, 0)

        ));
        objects.add(new Decoration(
                new Transform(new Vector3f(-1.11f, 0f, 45.268f ), new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(0)), new Vector3f(1, 1, 1)),
                new Graphic(block, blockMaterial),
                new Vector3f(2.862f, 0.96f, 0.85f),
                new Vector3f(2.862f, 0.96f, 0.85f), new Vector3f(0, 0, 0)

        ));

        // *************BIG FLAME******************************
//        objects.add(new FireObject(
//                new Transform(new Vector3f(0,0.1f,-18.5f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
//                new ParticleTexture(new Texture("fire.png"), 8)));
//        objects.add(new SmokeObject(
//                new Transform(new Vector3f(0,0.1f,-18.51f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
//                new ParticleTexture(new Texture("smoke.png"), 8)));

        // car fire
        objects.add(new FireObject(
                new Transform(new Vector3f(25.7f,1.198f,18.5f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new ParticleTexture(new Texture("fire.png"), 8)));
        objects.add(new SmokeObject(
                new Transform(new Vector3f(25.7f,1.298f,18.5f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new ParticleTexture(new Texture("smoke.png"), 8)));

        objects.add(new FireObject(
                new Transform(new Vector3f(25.7f,1.298f,18.0f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new ParticleTexture(new Texture("fire.png"), 8)));
        objects.add(new SmokeObject(
                new Transform(new Vector3f(25.7f,1.298f,18.0f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new ParticleTexture(new Texture("smoke.png"), 8)));

        objects.add(new FireObject(
                new Transform(new Vector3f(26.2f,1.198f,18.5f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new ParticleTexture(new Texture("fire.png"), 8)));
        objects.add(new SmokeObject(
                new Transform(new Vector3f(26.2f,1.298f,18.5f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new ParticleTexture(new Texture("smoke.png"), 8)));

        objects.add(new FireObject(
                new Transform(new Vector3f(26.2f,1.298f,18.0f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new ParticleTexture(new Texture("fire.png"), 8)));
        objects.add(new SmokeObject(
                new Transform(new Vector3f(26.2f,1.298f,18.0f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new ParticleTexture(new Texture("smoke.png"), 8)));

// car 2
        objects.add(new FireObject(
                new Transform(new Vector3f(29.92f,1.198f,-34.13f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new ParticleTexture(new Texture("fire.png"), 8)));
        objects.add(new SmokeObject(
                new Transform(new Vector3f(29.92f,1.298f,-34.13f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new ParticleTexture(new Texture("smoke.png"), 8)));

        objects.add(new FireObject(
                new Transform(new Vector3f(29.92f,1.298f,-33.63f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new ParticleTexture(new Texture("fire.png"), 8)));
        objects.add(new SmokeObject(
                new Transform(new Vector3f(29.92f,1.298f,-33.63f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new ParticleTexture(new Texture("smoke.png"), 8)));

        objects.add(new FireObject(
                new Transform(new Vector3f(29.42f,1.198f,-34.13f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new ParticleTexture(new Texture("fire.png"), 8)));
        objects.add(new SmokeObject(
                new Transform(new Vector3f(29.42f,1.298f,-34.13f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new ParticleTexture(new Texture("smoke.png"), 8)));

        objects.add(new FireObject(
                new Transform(new Vector3f(29.42f,1.298f,-33.63f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new ParticleTexture(new Texture("fire.png"), 8)));
        objects.add(new SmokeObject(
                new Transform(new Vector3f(29.42f,1.298f,-33.63f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new ParticleTexture(new Texture("smoke.png"), 8)));

        /////

        // build fire
        objects.add(new FireObject(
                new Transform(new Vector3f(-7.93f,0.3f,43.75f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new ParticleTexture(new Texture("fire.png"), 8)));
        objects.add(new SmokeObject(
                new Transform(new Vector3f(-7.93f,0.3f,43.75f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new ParticleTexture(new Texture("smoke.png"), 8)));
        objects.add(new FireObject(
                new Transform(new Vector3f(-7.43f,0.8f,43.75f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new ParticleTexture(new Texture("fire.png"), 8)));
        objects.add(new SmokeObject(
                new Transform(new Vector3f(-7.43f,0.8f,43.75f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new ParticleTexture(new Texture("smoke.png"), 8)));
        objects.add(new FireObject(
                new Transform(new Vector3f(-6.93f,0.8f,43.75f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new ParticleTexture(new Texture("fire.png"), 8)));
        objects.add(new SmokeObject(
                new Transform(new Vector3f(-6.93f,0.8f,43.75f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new ParticleTexture(new Texture("smoke.png"), 8)));
        objects.add(new FireObject(
                new Transform(new Vector3f(-8.43f,0.8f,43.75f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new ParticleTexture(new Texture("fire.png"), 8)));
        objects.add(new SmokeObject(
                new Transform(new Vector3f(-8.43f,0.8f,43.75f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new ParticleTexture(new Texture("smoke.png"), 8)));
        objects.add(new FireObject(
                new Transform(new Vector3f(-8.93f,0.6f,43.75f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new ParticleTexture(new Texture("fire.png"), 8)));
        objects.add(new SmokeObject(
                new Transform(new Vector3f(-8.93f,0.3f,43.75f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new ParticleTexture(new Texture("smoke.png"), 8)));
        /////////////////////

        //************************************************************************

        // Enemies

        AnimMesh animMesh = new AnimMesh("zm_fast", "idle", new Material(new Texture("zomby_light.png"), 1, 4), 106, 30);
        idle = new AnimMesh("zm_fast", "idle", new Material(new Texture("zomby_light.png"), 1, 4), 106, 30);

        chase = new AnimMesh("zm_fast", "chase", new Material(new Texture("zomby_light.png"), 1, 4), 31, 40);
        attack = new AnimMesh("zm_fast", "attack", new Material(new Texture("zomby_light.png"), 1, 4), 29, 40);
        dying = new AnimMesh("zm_fast", "dying", new Material(new Texture("zomby_light.png"), 1, 4), 38, 30);
// ***************************************

        // Triggers

        ArrayList<Enemy> e  = new ArrayList<>();
        e.add(new Enemy(new Transform(new Vector3f(-9,0,-35), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new Collider(new Vector3f(2,4,2)),
                new Vector3f(1,2 ,1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));

        e.add(new Enemy(new Transform(new Vector3f(-15,0,-34), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new Collider(new Vector3f(2,4,2)),
                new Vector3f(1,2 ,1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));

        e.add(new Enemy(new Transform(new Vector3f(-22,0,-17), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new Collider(new Vector3f(2,4,2)),
                new Vector3f(1,2 ,1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));
        e.add(new Enemy(new Transform(new Vector3f(-33,0,-24), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new Collider(new Vector3f(2,4,2)),
                new Vector3f(1,2 ,1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));
        e.add(new Enemy(new Transform(new Vector3f(-31,0,-4), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new Collider(new Vector3f(2,4,2)),
                new Vector3f(1,2 ,1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));
        e.add(new Enemy(new Transform(new Vector3f(-18,0,-4), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new Collider(new Vector3f(2,4,2)),
                new Vector3f(1,2 ,1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));
        e.add(new Enemy(new Transform(new Vector3f(-32, 0, -3), new Quaternion(0, 0, 0, 1), new Vector3f(1, 1, 1)),
                new Collider(new Vector3f(2, 4, 2)),
                new Vector3f(1, 2, 1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));
        GameplayManager.getInstance().addTrigger(new Trigger(5, new Vector3f(-8, 0, -55), new ZMSpawn(e)));
//********************************
        e  = new ArrayList<>();
        e.add(new Enemy(new Transform(new Vector3f(15.8f,0,-16.62f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new Collider(new Vector3f(2,4,2)),
                new Vector3f(1,2 ,1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));

        e.add(new Enemy(new Transform(new Vector3f(-17,0,-39), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new Collider(new Vector3f(2,4,2)),
                new Vector3f(1,2 ,1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));

        e.add(new Enemy(new Transform(new Vector3f(-17,0,-52), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new Collider(new Vector3f(2,4,2)),
                new Vector3f(1,2 ,1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));
        e.add(new Enemy(new Transform(new Vector3f(25,0,-45), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new Collider(new Vector3f(2,4,2)),
                new Vector3f(1,2 ,1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));
        e.add(new Enemy(new Transform(new Vector3f(26,0,-18), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new Collider(new Vector3f(2,4,2)),
                new Vector3f(1,2 ,1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));
        e.add(new Enemy(new Transform(new Vector3f(28,0,-3), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new Collider(new Vector3f(2,4,2)),
                new Vector3f(1,2 ,1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));
        e.add(new Enemy(new Transform(new Vector3f(35, 0, -31), new Quaternion(0, 0, 0, 1), new Vector3f(1, 1, 1)),
                new Collider(new Vector3f(2, 4, 2)),
                new Vector3f(1, 2, 1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));
        GameplayManager.getInstance().addTrigger(new Trigger(5, new Vector3f(7, 0, -29), new ZMSpawn(e)));
        ///////////////////////////////////////////
        e  = new ArrayList<>();
        e.add(new Enemy(new Transform(new Vector3f(-30f,0,32f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new Collider(new Vector3f(2,4,2)),
                new Vector3f(1,2 ,1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));

        e.add(new Enemy(new Transform(new Vector3f(-16,0,33), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new Collider(new Vector3f(2,4,2)),
                new Vector3f(1,2 ,1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));

        e.add(new Enemy(new Transform(new Vector3f(-27,0,47), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new Collider(new Vector3f(2,4,2)),
                new Vector3f(1,2 ,1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));
        e.add(new Enemy(new Transform(new Vector3f(-24,0,41), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new Collider(new Vector3f(2,4,2)),
                new Vector3f(1,2 ,1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));
        e.add(new Enemy(new Transform(new Vector3f(5,0,39), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new Collider(new Vector3f(2,4,2)),
                new Vector3f(1,2 ,1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));
        e.add(new Enemy(new Transform(new Vector3f(10,0,42), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new Collider(new Vector3f(2,4,2)),
                new Vector3f(1,2 ,1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));
        e.add(new Enemy(new Transform(new Vector3f(-15, 0, 49), new Quaternion(0, 0, 0, 1), new Vector3f(1, 1, 1)),
                new Collider(new Vector3f(2, 4, 2)),
                new Vector3f(1, 2, 1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));
        GameplayManager.getInstance().addTrigger(new Trigger(5, new Vector3f(-2, 0, 17), new ZMSpawn(e)));
//////////////////////////////
        e  = new ArrayList<>();
        e.add(new Enemy(new Transform(new Vector3f(22,0,59f), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new Collider(new Vector3f(2,4,2)),
                new Vector3f(1,2 ,1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));

        e.add(new Enemy(new Transform(new Vector3f(32,0,52), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new Collider(new Vector3f(2,4,2)),
                new Vector3f(1,2 ,1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));

        e.add(new Enemy(new Transform(new Vector3f(19,0,71), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new Collider(new Vector3f(2,4,2)),
                new Vector3f(1,2 ,1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));
        e.add(new Enemy(new Transform(new Vector3f(32,0,72), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new Collider(new Vector3f(2,4,2)),
                new Vector3f(1,2 ,1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));
        e.add(new Enemy(new Transform(new Vector3f(4,0,76), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new Collider(new Vector3f(2,4,2)),
                new Vector3f(1,2 ,1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));
        e.add(new Enemy(new Transform(new Vector3f(10,0,42), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new Collider(new Vector3f(2,4,2)),
                new Vector3f(1,2 ,1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));
        e.add(new Enemy(new Transform(new Vector3f(6, 0, 72), new Quaternion(0, 0, 0, 1), new Vector3f(1, 1, 1)),
                new Collider(new Vector3f(2, 4, 2)),
                new Vector3f(1, 2, 1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));
        GameplayManager.getInstance().addTrigger(new Trigger(5, new Vector3f(17, 0, 41), new ZMSpawn(e)));
        GameplayManager.getInstance().addTrigger(new Trigger(4, new Vector3f(27, 0, 85), new Finish()));
//////////////////////////////
//        objects.add(new Enemy(new Transform(new Vector3f(10,0,10), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
//                new Collider(new Vector3f(2,2,2)),
//                new Vector3f(1,2 ,1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
//                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));


//        objects.add(new Decoration(
//                new Transform(new Vector3f(30, 5, -10), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(-90)), new Vector3f(1,1,1)),
//                new Graphic(building, buildingMaterial),
//                new Vector3f(11, 11, 11),
//                new Vector3f(11, 11, 11),
//                new Vector3f(0,0,0)));

        lights.add(new DirectionalLight(new Vector3f(0.6f, 0.4f, 0.3f), 0.5f, new Vector3f(-0.8f, -0.15f, -0.57f)));
        level = new Level(new SkyBox("1"), objects, lights);

        font = new Font("rus.png", "rus.fnt");
        text = new Text(-0.4f, 0.8f, 0.15f, "Post Mortem", font, new Vector3f(1, 0,0));
        level.addText(text);

        level.addGUI(new GUITexture(new Texture("cross.png"), new Vector2f(0, 0),new Vector2f(0.1f, 0.1f)));
//        level.addGUI(new GUITexture(new Texture("health.png"), new Vector2f(1, 1), new Vector2f(1f, 1f)));

        level.setLevelTheme(new Sound("res/sound/motherfucker.ogg"));
        Source background = new Source();
        background.play(level.playTheme());

        double finish = System.currentTimeMillis();
        System.out.println((finish - start) / 1000 + "s");
    }
    public void render(RenderingEngine renderingEngine){
        renderingEngine.render(level);
    }
float k =0;
    public void update(){
        level.update();
        GameplayManager.getInstance().update(player);
//fire.generateParticles(new Vector3f(0,0,0));
//        fire.generateParticles(new Vector3f(0, 0, -20));

        ParticleMaster.update(engine.getRenderingEngine().getMainCamera());
//        k+= (float)Time.getDelta();
//        level.getText().get(0).setPos(new Vector2f((float)SyncEditor.y/100f,(float)SyncEditor.z/100f));
//        level.getText().get(0).setSize((float)SyncEditor.x/100f);
//        System.out.println(level.getText().get(0).getSize());
//        level.getText().clear();

//        level.getText().get(0).edit(Integer.toString(engine.getFPS()));

//        level.addText( new Text(0.8f, 0.8f, 0.15f, "text", font, new Vector3f(1, 0,0)));


//        level.getGuis().get(0).setPos(new Vector2f((float)SyncEditor.y/100f, (float)SyncEditor.z/100f));
//        level.getGuis().get(0).setScale(new Vector2f((float)SyncEditor.x/100f, (float)SyncEditor.x/100f));


//        level.setText(new Text(SyncEditor.y, SyncEditor.z, SyncEditor.x,
//                Integer.toString(engine.getFPS()),
////                "Some text from me",
//                font, new Vector3f(1,1,0)));
    }

    int i = 0;
    public void input(){
        if(Input.getKey(Input.KEY_Q)){
            engine.stop();
//            System.exit(0);
        }
        level.input();


    }

    public void setEngine(CoreEngine engine){
        this.engine = engine;
    }

    public Level getLevel() {
        return level;
    }


}
