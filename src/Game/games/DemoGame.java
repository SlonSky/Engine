package Game.games;

import Engine.rendering.Camera;
import Engine.rendering.Graphic;
import Engine.rendering.RenderingEngine;
import Engine.rendering.Transform;
import Engine.rendering.guis.GUITexture;
import Engine.rendering.light.*;
import Engine.rendering.meshManagment.Material;
import Engine.rendering.meshManagment.Mesh;
import Engine.rendering.meshManagment.Texture;
import Engine.rendering.skybox.SkyBox;
import Engine.util.Quaternion;
import Engine.util.Vector2f;
import Engine.util.Vector3f;
import Game.Game;
import Game.Level;
import Game.GameObject;
import Game.entities.Decoration;

import java.util.ArrayList;

/**
 * Created by Slon on 23.04.2016.
 */
public class DemoGame extends Game{


    @Override
    public void init() {
        System.out.println("Loading...");
        double start = System.currentTimeMillis();

        Camera camera = new Camera(new Vector3f(0,0,0), new Quaternion(0,0,0,1));
        setCamera(camera);

        SkyBox skyBox = new SkyBox("city");
        ArrayList<GameObject> objects = new ArrayList<>();
        ArrayList<Light> lights = new ArrayList<>();

        Mesh road = new Mesh("road.obj");
        Material roadMaterial = new Material(new Texture("road.png"), 1, 10);
////
        Mesh block = new Mesh("block.obj");
        Material blockMaterial = new Material(new Texture("block.png"), 1, 10);
//////
        Mesh fence = new Mesh("fence.obj");
        Material fenceMaterial = new Material(new Texture("fence.png"), 4, 10);
//
        Mesh building = new Mesh("building.obj");
        Material buildingMaterial = new Material(new Texture("building.png"), 1, 8);
        Material buildingWhiteMaterial =new Material(new Texture("buildingWhite.png"), 1, 8);
//
        Mesh building2 = new Mesh("building2.obj");
        Material building2Material = new Material(new Texture("building2.png"), 1, 8);

        Mesh barrel = new Mesh("barrel.obj");
        Material barrelMaterial = new Material(new Texture("barrel.png"), 2, 32);

        Mesh concreteFloor = new Mesh("concreteFloor.obj");
        Material concreteFloorMaterial = new Material(new Texture("concrete.png"), 1, 8);

        Mesh cone2 = new Mesh("cone2.obj");
        Material cone2Material = new Material(new Texture("cone2.png"), 1, 14);

        Mesh brickWall = new Mesh("brickWall.obj");
        Material brickWallMaterial = new Material(new Texture("brickWall.png"), 1, 9);

        Mesh wall = new Mesh("wall.obj");
        Material wallMaterial = new Material(new Texture("wall.png"), 2, 20);

        Mesh tent = new Mesh("tent.obj");
        Material tentMaterial = new Material(new Texture("tent.png"), 1, 15);


        Mesh tank = new Mesh("tank.obj");
        Material tankMaterial = new Material(new Texture("tank.png"), 2, 15);

        Mesh ambulance = new Mesh("ambulance.obj");
        Material ambulanceMaterial = new Material(new Texture("ambulance.png"), 1, 9);

        Mesh car = new Mesh("car.obj");
        Material carMaterial = new Material(new Texture("car.png"), 2, 10);

        Mesh garbage = new Mesh("garbage.obj");
        Material garbageMaterial = new Material(new Texture("garbage.png"), 2, 12);

        Mesh lamp = new Mesh("lamp.obj");
        Material lampMaterial = new Material(new Texture("1_LIGHTER.png"), 2, 15);

        for(int i = 0; i < 8; i++) {
            objects.add(new Decoration(
                    new Transform(new Vector3f(3.43f, 0, i*6.75f), new Quaternion(new Vector3f(0,0,1),(float)Math.toRadians(180)), new Vector3f(1, 1, 1)),
                    new Graphic(road, roadMaterial),
                    new Vector3f(4.4f, 0.16f, 6.8f),
                    new Vector3f(6.8f, 6.8f, 6.8f), new Vector3f(0, -20.2f, 0)

            ));

            objects.add(new Decoration(
                    new Transform(new Vector3f(-0.96f, 0, i*6.75f), new Quaternion(new Vector3f(0,0,1),(float)Math.toRadians(180)), new Vector3f(1, 1, 1)),
                    new Graphic(road, roadMaterial),
                    new Vector3f(4.4f, 0.16f, 6.8f),
                    new Vector3f(6.8f, 6.8f, 6.8f), new Vector3f(0, -20.2f, 0)

            ));
    }
        objects.add(new Decoration(
                new Transform(new Vector3f(3.35f, -0.087f, -1.64f ), new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(330)), new Vector3f(1, 1, 1)),
                new Graphic(block, blockMaterial),
                new Vector3f(3.25f, 0.96f, 0.85f),
                new Vector3f(3.25f, 3.25f, 3.25f), new Vector3f(0, 0.5f, 0)

        ));

        objects.add(new Decoration(
                new Transform(new Vector3f(-0.87f, -0.087f, -1.82f ), new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(-209)), new Vector3f(1, 1, 1)),
                new Graphic(block, blockMaterial),
                new Vector3f(3.25f, 0.96f, 0.85f),
                new Vector3f(3.25f, 3.25f, 3.25f), new Vector3f(0, 0.5f, 0)

        ));
        objects.add(new Decoration(
                new Transform(new Vector3f(3.54f, -0.087f, 34.6f ), new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(-330)), new Vector3f(1, 1, 1)),
                new Graphic(block, blockMaterial),
                new Vector3f(3.25f, 0.96f, 0.85f),
                new Vector3f(3.25f, 3.25f, 3.25f), new Vector3f(0, 0.5f, 0)

        ));
        objects.add(new Decoration(
                new Transform(new Vector3f(3.11f, -0.087f, 36.51f ), new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(-6)), new Vector3f(1, 1, 1)),
                new Graphic(block, blockMaterial),
                new Vector3f(3.25f, 0.96f, 0.85f),
                new Vector3f(3.25f, 3.25f, 3.25f), new Vector3f(0, 0.5f, 0)

        ));

        objects.add(new Decoration(
                new Transform(new Vector3f(-0.62f, -0.087f, 33.9f ), new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(19)), new Vector3f(1, 1, 1)),
                new Graphic(block, blockMaterial),
                new Vector3f(3.25f, 0.96f, 0.85f),
                new Vector3f(3.25f, 3.25f, 3.25f), new Vector3f(0, 0.5f, 0)

        ));
        objects.add(new Decoration(
                new Transform(new Vector3f(-1.16f, -0.087f, 36.06f ), new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(-33)), new Vector3f(1, 1, 1)),
                new Graphic(block, blockMaterial),
                new Vector3f(3.25f, 0.96f, 0.85f),
                new Vector3f(3.25f, 3.25f, 3.25f), new Vector3f(0, 0.5f, 0)

        ));
        for(int i = 0; i < 3; i++){
            objects.add(new Decoration(
                    new Transform(new Vector3f(-3.27f, 1.779f, 9.83f + 4*i ), new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(180)), new Vector3f(1, 1, 1)),
                    new Graphic(fence, fenceMaterial),
                    new Vector3f(0.133f,2.359f, 4.032f ),
                    new Vector3f(4f, 4f, 4), new Vector3f(-1.2f, 0f, 0)

            ));
            objects.add(new Decoration(
                    new Transform(new Vector3f(10.4f, 4.84f, 1.84f + 9.5f*i ), new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(180)), new Vector3f(1, 1, 1)),
                    new Graphic(building, buildingMaterial),
                    new Vector3f(10, 10, 10),
                    new Vector3f(10, 10, 10), new Vector3f(0, 0f, 0)

            ));
        }
        for(int i = 0; i < 8; i++){
            objects.add(new Decoration(
                    new Transform(new Vector3f(23.9f - i*4.23f, 1.779f, 37.63f ), new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(90)), new Vector3f(1, 1, 1)),
                    new Graphic(fence, fenceMaterial),
                    new Vector3f(0.133f,2.359f, 4.032f ),
                    new Vector3f(4f, 4f, 4), new Vector3f(-1.2f, 0f, 0)

            ));
        }
        objects.add(new Decoration(
                new Transform(new Vector3f(-7.999f, 4.84f, 2.13f ), new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(0)), new Vector3f(1, 1, 1)),
                new Graphic(building, buildingWhiteMaterial),
                new Vector3f(10, 10, 10),
                new Vector3f(10, 10, 10), new Vector3f(0, 0f, 0)

        ));
        objects.add(new Decoration(
                new Transform(new Vector3f(-7.999f, 4.84f, 30.5f ), new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(0)), new Vector3f(1, 1, 1)),
                new Graphic(building, buildingWhiteMaterial),
                new Vector3f(10, 10, 10),
                new Vector3f(10, 10, 10), new Vector3f(0, 0f, 0)

        ));
        objects.add(new Decoration(
                new Transform(new Vector3f(-4.29f, -0.45f, 22.69f ), new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(0)), new Vector3f(1, 1, 1)),
                new Graphic(building2, building2Material),
                new Vector3f(11, 7, 6),
                new Vector3f(11, 11, 11), new Vector3f(-5, 0f, 0)

        ));

        objects.add(new Decoration(
                new Transform(new Vector3f(4.39f, 0f, 39.21f ), new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(-28)), new Vector3f(1, 1, 1)),
                new Graphic(barrel, barrelMaterial),
                new Vector3f(1.37f, 1.37f, 2.083f),
                new Vector3f(2.1f, 2.1f, 2.1f), new Vector3f(0, 0f, 0)

        ));

        objects.add(new Decoration(
                new Transform(new Vector3f(-0.93f, 0f, 41.37f ), new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(37)), new Vector3f(1, 1, 1)),
                new Graphic(barrel, barrelMaterial),
                new Vector3f(1.37f, 1.37f, 2.083f),
                new Vector3f(2.1f, 2.1f, 2.1f), new Vector3f(0, 0f, 0)

        ));

        objects.add(new Decoration(
                new Transform(new Vector3f(0, 0f, 39.21f ), new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(83)), new Vector3f(1, 1, 1)),
                new Graphic(barrel, barrelMaterial),
                new Vector3f(1.37f, 1.37f, 2.083f),
                new Vector3f(2.1f, 2.1f, 2.1f), new Vector3f(0, 0f, 0)

        ));

        objects.add(new Decoration(
                new Transform(new Vector3f(-1.91f, 0f, 39.21f ), new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(90)), new Vector3f(1, 1, 1)),
                new Graphic(barrel, barrelMaterial),
                new Vector3f(1.37f, 1.37f, 2.083f),
                new Vector3f(2.1f, 2.1f, 2.1f), new Vector3f(0, 0f, 0)

        ));

        objects.add(new Decoration(
                new Transform(new Vector3f(-1.91f, 0f, 39.21f ), new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(90)), new Vector3f(1, 1, 1)),
                new Graphic(barrel, barrelMaterial),
                new Vector3f(1.37f, 1.37f, 2.083f),
                new Vector3f(2.1f, 2.1f, 2.1f), new Vector3f(0, 0f, 0)

        ));

        for(int i = 0; i < 3; i++) {
            objects.add(new Decoration(
                    new Transform(new Vector3f(15.16f + i*20, -0.09f, 31.56f), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(0)), new Vector3f(1, 1, 1)),
                    new Graphic(concreteFloor, concreteFloorMaterial),
                    new Vector3f(20, 0f, 12f),
                    new Vector3f(2.1f, 2.1f, 2.1f), new Vector3f(0, -5f, 0)

            ));
        }
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 12; j++) {
                objects.add(new Decoration(
                        new Transform(new Vector3f(8 + j * 5, 0f, 27.92f + 5*i), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(0)), new Vector3f(1, 1, 1)),
                        new Graphic(concreteFloor, concreteFloorMaterial),
                        new Vector3f(5, 0f, 5),
                        new Vector3f(2.1f, 2.1f, 2.1f), new Vector3f(0, -5f, 0)

                ));
            }
        }
        objects.add(new Decoration(
                new Transform(new Vector3f(4.2f, 0.5f, 33.36f ), new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(0)), new Vector3f(1, 1, 1)),
                new Graphic(cone2, cone2Material),
                new Vector3f(0.526f, 0.512f, 0.589f),
                new Vector3f(2.1f, 2.1f, 2.1f), new Vector3f(0, -5f, 0)

        ));
        objects.add(new Decoration(
                new Transform(new Vector3f(2.139f, 0.5f, 33.11f ), new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(0)), new Vector3f(1, 1, 1)),
                new Graphic(cone2, cone2Material),
                new Vector3f(0.526f, 0.512f, 0.589f),
                new Vector3f(2.1f, 2.1f, 2.1f), new Vector3f(0, -5f, 0)

        ));
        objects.add(new Decoration(
                new Transform(new Vector3f(-0.29f, 0.5f, 32.6f ), new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(0)), new Vector3f(1, 1, 1)),
                new Graphic(cone2, cone2Material),
                new Vector3f(0.526f, 0.512f, 0.589f),
                new Vector3f(2.1f, 2.1f, 2.1f), new Vector3f(0, -5f, 0)

        ));
        objects.add(new Decoration(
                new Transform(new Vector3f(-2.25f, 0.3f, 33.49f ), new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(0)), new Vector3f(1, 1, 1)),
                new Graphic(cone2, cone2Material),
                new Vector3f(0.526f, 0.512f, 0.589f),
                new Vector3f(2.1f, 2.1f, 2.1f), new Vector3f(0, -5f, 0)

        ));

        for(int i = 0; i < 6; i++){
            objects.add(new Decoration(
                    new Transform(new Vector3f(-15.7f, 1.39f, 2.19f + 4*i), new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(90)), new Vector3f(1, 1, 1)),
                    new Graphic(brickWall, brickWallMaterial),
                    new Vector3f(4, 4, 0.1f),
                    new Vector3f(2.1f, 2.1f, 2.1f), new Vector3f(0, -5f, 0)
            ));
        }

        objects.add(new Decoration(
                new Transform(new Vector3f(26.51f, 1.88f, 42.38f ), new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(90)), new Vector3f(1, 1, 1)),
                new Graphic(wall, wallMaterial),
                new Vector3f(10.618f, 3.941f, 0.186f),
                new Vector3f(10f, 10f, 10f), new Vector3f(-4.5f, 0,0)
        ));
        for(int i = 0; i < 5; i++){
            objects.add(new Decoration(
                    new Transform(new Vector3f(11.3f + i*10f, 1.88f, 49.28f ), new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(0)), new Vector3f(1, 1, 1)),
                    new Graphic(wall, wallMaterial),
                    new Vector3f(10.618f, 3.941f, 0.186f),
                    new Vector3f(10f, 10f, 10f), new Vector3f(0, 0,4.5f)
            ));
        }

        for(int i = 0; i < 3; i++){
            objects.add(new Decoration(
                    new Transform(new Vector3f(20.4f + i*10f, 1.88f, 25.55f ), new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(0)), new Vector3f(1, 1, 1)),
                    new Graphic(wall, wallMaterial),
                    new Vector3f(10.618f, 3.941f, 0.186f),
                    new Vector3f(10f, 10f, 10f), new Vector3f(0, 0,-4.5f)
            ));
        }

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 5; j++) {

                objects.add(new Decoration(
                        new Transform(new Vector3f(-5.71f - j*5, 0f, 9.46f+i*5), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(90)), new Vector3f(1, 1, 1)),
                        new Graphic(concreteFloor, concreteFloorMaterial),
                        new Vector3f(5, 0f, 5),
                        new Vector3f(2.1f, 2.1f, 2.1f), new Vector3f(0, -5f, 0)

                ));

            }
        }

        objects.add(new Decoration(
                new Transform(new Vector3f(17.84f, 0f, 44f), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(45)), new Vector3f(1, 1, 1)),
                new Graphic(tent, tentMaterial),
                new Vector3f(9.313f, 4.761f, 8.358f),
                new Vector3f(10f, 10f, 10f), new Vector3f(0f, -20, 0)
        ));
        objects.add(new Decoration(
                new Transform(new Vector3f(-7.56f, 0f, 51f), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(-27)), new Vector3f(1.5f, 1.5f, 1.5f)),
                new Graphic(tent, tentMaterial),
                new Vector3f(9.313f, 4.761f, 8.358f),
                new Vector3f(10f, 10f, 10f), new Vector3f(0f, -20, 0)
        ));

        objects.add(new Decoration(
                new Transform(new Vector3f(51.35f, 0.8f, 40.94f), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(210)), new Vector3f(2f, 2f, 2f)),
                new Graphic(tank, tankMaterial),
                new Vector3f(7.5f, 17.23f, 5.06f ),
                new Vector3f(15, 15, 15), new Vector3f(0f, 0, 0)
        ));
        objects.add(new Decoration(
                new Transform(new Vector3f(3.28f, 0.0f, 49.5f), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(80)), new Vector3f(1, 1, 1)),
                new Graphic(ambulance, ambulanceMaterial),
                new Vector3f(5.5f, 5.8f, 14.35f),
                new Vector3f(15, 15, 15), new Vector3f(0f, -10, 0)
        ));
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 3; j++){
                objects.add(new Decoration(
                        new Transform(new Vector3f(-5.7f - j * 5, 0f, 37.78f + 5*i), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(0)), new Vector3f(1, 1, 1)),
                        new Graphic(concreteFloor, concreteFloorMaterial),
                        new Vector3f(5, 0f, 5),
                        new Vector3f(2.1f, 2.1f, 2.1f), new Vector3f(0, -5f, 0)

                ));
            }
        }
        for(int i = 0; i < 8; i++){
            objects.add(new Decoration(
                    new Transform(new Vector3f(-15.7f, 1.39f, 35.33f + 4*i), new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(90)), new Vector3f(1, 1, 1)),
                    new Graphic(brickWall, brickWallMaterial),
                    new Vector3f(4, 4, 0.1f),
                    new Vector3f(2.1f, 2.1f, 2.1f), new Vector3f(0, -5f, 0)
            ));
        }
        objects.add(new Decoration(
                new Transform(new Vector3f(48.33f, 0.0f, 28.61f), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(-16)), new Vector3f(1.5f, 1.5f, 1.5f)),
                new Graphic(car, carMaterial),
                new Vector3f(4, 2.8f, 9.84f),
                new Vector3f(10, 10, 10), new Vector3f(2f, 0, 0)
        ));
        objects.add(new Decoration(
                new Transform(new Vector3f(-11.75f, 0.0f, 10.3f), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(0)), new Vector3f(4.2f, 4.2f, 4.2f)),
                new Graphic(garbage, garbageMaterial),
                new Vector3f(2.5f, 2.9f, 3.98f),
                new Vector3f(10, 10, 10), new Vector3f(2f, -20, 0)
        ));

        objects.add(new Decoration(
                new Transform(new Vector3f(-2.6f, 0.0f, 32.06f), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(0)), new Vector3f(1, 1, 1)),
                new Graphic(lamp, lampMaterial),
                new Vector3f(0.13f, 2.78f, 0.148f),
                new Vector3f(10, 10, 10), new Vector3f(2f, -20, 0)
        ));
        objects.add(new Decoration(
                new Transform(new Vector3f(8.55f, 0.0f, 37.97f), new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(-90)), new Vector3f(1, 1, 1)),
                new Graphic(lamp, lampMaterial),
                new Vector3f(0.13f, 2.78f, 0.148f),
                new Vector3f(10, 10, 10), new Vector3f(2f, -20, 0)
        ));

        lights.add(new DirectionalLight(new Vector3f(0.5f, 0.37f, 0.18f), 0.5f, new Vector3f(-0.8f, -0.15f, -0.57f)));
//        lights.add(new PointLight(new Vector3f(-1.06f, 2.46f, 6.73f), new Vector3f(1,0.3f,0), 100.5f, new Attenuation(0, 0, 0.5f)));
//        lights.add(new PointLight(new Vector3f(-1.06f, 2.46f, 32.03f), new Vector3f(1,0.3f,0), 100.5f, new Attenuation(0, 0, 0.5f)));
//        lights.add(new PointLight(new Vector3f(8.58f, 2.46f, 39.53f), new Vector3f(1,0.3f,0.1f), 100.5f, new Attenuation(0, 0, 0.5f)));
//        lights.add(new PointLight(new Vector3f(17.85f, 1.72f, 40.75f), new Vector3f(1,0,0), 0.5f, new Attenuation(3, 1, 2)));

        level = new Level(skyBox, objects, lights);

        level.addGUI(new GUITexture(new Texture("cross.png"), new Vector2f(0, 0), new Vector2f(0.2f, 0.2f)));


        System.out.println((System.currentTimeMillis() - start) / 1000 + "s");
    }

    @Override
    public void render(RenderingEngine renderingEngine) {
        renderingEngine.render(level);
    }

    @Override
    public void update() {
        level.update();
    }

    @Override
    public void input() {
        level.input();
    }
}
