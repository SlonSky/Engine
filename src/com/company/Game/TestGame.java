package com.company.Game;


import com.company.Engine.ai.Enemy;
import com.company.Engine.audio.*;
import com.company.Engine.core.*;
import com.company.Engine.rendering.*;
import com.company.Engine.rendering.guis.GUITexture;
import com.company.Engine.rendering.light.*;
import com.company.Engine.rendering.meshManagment.Material;
import com.company.Engine.rendering.meshManagment.Mesh;
import com.company.Engine.rendering.meshManagment.Texture;
import com.company.Engine.rendering.skybox.SkyBox;
import com.company.Engine.rendering.text.Font;
import com.company.Engine.rendering.text.Text;
import com.company.Engine.util.Quaternion;
import com.company.Engine.util.Vector2f;
import com.company.Engine.util.Vector3f;
import com.company.Engine.util.Vertex;
import com.company.Engine.rendering.Graphic;

import java.util.*;

/**
 * Created by Slon on 09.02.2016.
 */
public class TestGame extends Game {


//    private CoreEngine engine;
//    private Camera camera;

    private Transform transform;
    private Material material;
    private Mesh mesh;

    private  Random r;

    private Player player;
    private Decoration d;

    Text text;
    Font font;

//    private Level level;
    ArrayList<GameObject> objects;

    public void init(){

        System.out.println("Loading...");
        double start = System.currentTimeMillis();

        // main initialization
        camera = new Camera(new Vector3f(0,0,0),new Quaternion(0,0,0,1));
        setCamera(camera);

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


        camera.setPos(new Vector3f(0, 1, 0));

        Mesh road = new Mesh("road.obj");
        Material roadMaterial = new Material(new Texture("road.png"));
////
//        Mesh block = new Mesh("block.obj");
//        Material blockMaterial = new Material(new Texture("block.png"));
////
        Mesh fence = new Mesh("fence.obj");
        Material fenceMaterial = new Material(new Texture("fence.png"), 2, 10);

        Mesh building = new Mesh("building.obj");
        Material buildingMaterial = new Material(new Texture("building.png"), 0, 8);

//        Mesh building2 = new Mesh("building2.obj");
//        Material building2Material = new Material(new Texture("building2.png"), 0, 8);

        Mesh zombie = new Mesh("za.obj");
        Material zombieMaterial = new Material(new Texture("zomby_light.png"), 2, 20);

        for(int i =0; i<4; i++) {
            for (int j = 0; j < 15; j++) {

                objects.add(new Enemy(new Transform(new Vector3f(j *  r.nextInt(4)-2, 0, i * 10 +r.nextInt(5)), new Quaternion(new Vector3f(0, 1, 0),(float)Math.toRadians(r.nextInt(180))), new Vector3f(3, 3, 3)),
                        new Graphic(zombie, zombieMaterial),
                        new Vector3f(1.794f / 2, 0.581f / 2,  1.055f/ 2)));

//                objects.add(new Decoration(
//                        new Transform(new Vector3f(i*10+ 2, 1.6f, j * 2.032f * 2 ), new Quaternion(new Vector3f(0, 1, 0),(float)Math.toRadians(0)), new Vector3f(1, 1, 1)),
//                        new Graphic(fence, fenceMaterial),
//                        new Vector3f(0.133f / 2, 3.259f / 2, 4.032f / 2),
//                        new Vector3f(0.133f / 2, 3.259f / 2, 4.032f / 2)
//                ));
                objects.add(new Decoration(
                        new Transform(new Vector3f(i*10, 0, j * 3.422f*2), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(0)).mul(new Quaternion(new Vector3f(0, 0, 1), (float)Math.toRadians(180))), new Vector3f(1, 1, 1)),
                        new Graphic(road, roadMaterial),
                        new Vector3f(2.2f, 0.088f, 3.422f),
//                        new Vector3f(2.2f, 0.088f, 3.422f)
                        new Vector3f(3.4f, 3.4f, 3.4f)
                ));

            }
        }
//        objects.add(new GameObject(new Entity(building2, new Transform(new Vector3f(25, -0.5f, -3), new Quaternion(new Vector3f(0, 1, 0),(float)Math.toRadians(-90)), new Vector3f(1,1,1)), building2Material), new Vector3f(7.321f/2, 5.96f/2, 10.968f/2)));
//        objects.add(new GameObject(new Entity(building2, new Transform(new Vector3f(35, -0.5f, -3), new Quaternion(new Vector3f(0, 1, 0),(float)Math.toRadians(-90)), new Vector3f(1,1,1)), building2Material), new Vector3f(7.321f/2, 5.96f/2, 10.968f/2)));
//        objects.add(new GameObject(new Entity(building2, new Transform(new Vector3f(45, -0.5f, -3), new Quaternion(new Vector3f(0, 1, 0),(float)Math.toRadians(-90)), new Vector3f(1,1,1)), building2Material), new Vector3f(7.321f/2, 5.96f/2, 10.968f/2)));

        lights.add(new DirectionalLight(new Vector3f(0.5f, 0.3f, 0), 0.2f, new Vector3f(1f, -1, 1)));
        lights.add(new PointLight(new Vector3f(25.5f, 3, -2f), new Vector3f(1, 0,0), 0.2f, new Attenuation(1,0,0)));
        lights.add(new PointLight(new Vector3f(35.5f, 3, -2f), new Vector3f(1, 0,0), 0.2f, new Attenuation(1,0,0)));
        lights.add(new PointLight(new Vector3f(45.5f, 3, -2f), new Vector3f(1, 1,1), 0.2f, new Attenuation(1,0,0)));
        lights.add(new PointLight(new Vector3f(55.5f, 3, -2f), new Vector3f(1, 1,1), 0.2f, new Attenuation(1,0,0)));


        player = new Player(camera, new Transform(new Vector3f(0f, 10, 0f), camera.getRot(), new Vector3f(1,1,1)),
                new Graphic(new Mesh("AK1.obj"), new Material(new Texture("ak.png"))),
                new Vector3f(0.3f,0.7f,0.3f));
        objects.add(player);


        d = new Decoration(
                new Transform(new Vector3f(20, 5, -10), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(-90)), new Vector3f(1,1,1)),
                new Graphic(building, buildingMaterial),
                new Vector3f(5, 5, 5),
                new Vector3f(5, 5, 5));

        objects.add(d);
        level = new Level(new SkyBox("1"), objects, lights);

        font = new Font("rus.png", "rus.fnt");
        text = new Text(0.8f, 0.8f, 0.15f, "", font, new Vector3f(1, 0,0));
        level.addText(text);

        level.addGUI(new GUITexture(new Texture("health.png"), new Vector2f(0.85f, -0.75f), new Vector2f(0.25f, 0.25f)));
        level.addGUI(new GUITexture(new Texture("minimap.png"), new Vector2f(-0.75f, 0.70f),new Vector2f( 0.2f, 0.2f)));
        level.addGUI(new GUITexture(new Texture("logo.png"), new Vector2f(-0.75f, -0.70f),new Vector2f( 0.2f, 0.2f)));
        level.addGUI(new GUITexture(new Texture("cross.png"), new Vector2f(0, 0),new Vector2f( 0.2f, 0.2f)));
//
//        level.addGUI(new GUITexture(new Texture("health.png"), new Vector2f(1, 1), new Vector2f(1f, 1f)));


//        Listener listener = new Listener(new Vector3f(0,0,0), new Vector3f(0,0,1));
//        Sound sound = new Sound(new Vector3f(20, 20, 0), 1, new WAVSoundData("fx/theme.wav"));
//        sound.play();


//        try {
//            Audio ogg = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("res/sound/theme1.ogg"));;
//
//            ogg.playAsSoundEffect(1, 1, true);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        SoundStore.get().poll(0);

        AudioEngine.setListenerData(new Vector3f(0, 0, 0));
        int buffer = AudioEngine.loadSound("res/sound/fx/shot.wav");
        Source        source = new Source();

        source.setLooping(true);
//        source.play(buffer);


        Source source1 = new Source();
        source1.setLooping(true);
        source1.setPitch(5);
//        source1.play(buffer);

        Source source2 = new Source();
        int v = AudioEngine.loadSound("res/sound/theme1.ogg");
        System.out.println(v);
        source2.play(v);
//        source.delete();
//        AudioEngine.dispose();


        double finish = System.currentTimeMillis();
        System.out.println((finish - start) / 1000 + "s");
    }
    public void render(RenderingEngine renderingEngine){
        renderingEngine.render(level);
    }
float k =0;
    public void update(){
        level.update();
//        k+= (float)Time.getDelta();
//        level.getText().get(0).setPos(new Vector2f((float)SyncEditor.y/100f,(float)SyncEditor.z/100f));
//        level.getText().get(0).setSize((float)SyncEditor.x/100f);
//        System.out.println(level.getText().get(0).getSize());
        level.getText().get(0).edit(Integer.toString(engine.getFPS()));

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
