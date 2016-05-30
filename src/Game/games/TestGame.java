package Game.games;


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
import Game.Level;
import Game.enemy.Enemy;
import Game.entities.Decoration;
import Game.entities.Fire;
import Game.player.Player;
import Game.Initializer;
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


private Fire fire;

    public void init(){

        // Initializer.init(windowManager);

//fire = new Fire(new ParticleTexture(new Texture("smoke.png"), 8), 1, 1, 1);
        System.out.println("Loading...");
        double start = System.currentTimeMillis();

        // main initialization
        camera = new Camera(new Vector3f(0,0,0),new Quaternion(0,0,0,1));
        setCamera(camera);

        camera.setPos(new Vector3f(0, 1, 0));
        Initializer.getInstance().loadLevel(camera);
        objects = new ArrayList<>();
        ArrayList<Light> lights = new ArrayList<>();

r = new Random();

//        Mesh road = new Mesh("road.obj");
//        Material roadMaterial = new Material(new Texture("road.png"));
////////
//        Mesh block = new Mesh("block.obj");
//        Material blockMaterial = new Material(new Texture("block.png"));
//////
//        Mesh fence = new Mesh("fence.obj");
//        Material fenceMaterial = new Material(new Texture("fence.png"), 2, 10);
////
//
//        Mesh building2 = new Mesh("building2.obj");
//        Material building2Material = new Material(new Texture("building2.png"), 0, 8);
//
//        lights.add(new DirectionalLight(new Vector3f(0.5f, 0.3f, 0), 0.2f, new Vector3f(1f, -1, 1)));
//        lights.add(new PointLight(new Vector3f(25.5f, 3, -2f), new Vector3f(1, 0,0), 0.2f, new Attenuation(1,0,0)));
//        lights.add(new PointLight(new Vector3f(35.5f, 3, -2f), new Vector3f(1, 0,0), 0.2f, new Attenuation(1,0,0)));
//        lights.add(new PointLight(new Vector3f(45.5f, 3, -2f), new Vector3f(1, 1,1), 0.2f, new Attenuation(1,0,0)));
//        lights.add(new PointLight(new Vector3f(55.5f, 3, -2f), new Vector3f(1, 1,1), 0.2f, new Attenuation(1,0,0)));


        Mesh building = new Mesh("building.obj");
        Material buildingMaterial = new Material(new Texture("building.png"), 1, 8);

        Animation hands = new Animation(0, 1, new AnimMesh("ak2", "untitled", new Material(new Texture("ak.png"), 2, 8), 1, 30));
        player = new Player(camera, new Transform(new Vector3f(0f, 5, 0), camera.getRot(), new Vector3f(1,1,1)),
                hands,
                new Collider(new Vector3f(2,2f,2f), new Vector3f(0,0,0)));
        objects.add(player);

// todo: singleton for config initialization


        fire =  new Fire(new ParticleTexture(new Texture("fire.png"), 8), 5, 5, 5);

        AnimMesh animMesh = new AnimMesh("zm_fast", "idle", new Material(new Texture("zomby_light.png"), 1, 4), 106, 30);
        idle = new AnimMesh("zm_fast", "idle", new Material(new Texture("zomby_light.png"), 1, 4), 106, 30);

        chase =new AnimMesh("zm_fast", "chase", new Material(new Texture("zomby_light.png"), 1, 4), 31, 40);
        attack = new AnimMesh("zm_fast", "attack", new Material(new Texture("zomby_light.png"), 1, 4), 29, 40);
        dying = new AnimMesh("zm_fast", "dying", new Material(new Texture("zomby_light.png"), 1, 4), 38, 30);

//        objects.add(new Enemy(new Transform(new Vector3f(10,0,10), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
//                new Collider(new Vector3f(2,2,2)),
//                new Vector3f(1,2 ,1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
//                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));

//        objects.add(new Enemy(new Transform(new Vector3f(40,0,0), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
//                new Animation(0, 1, animMesh), new Collider(new Vector3f(2,2,2)),
//                new Vector3f(1,2,1), new Vector3f(0, 1, 0)));
//
//        objects.add(new Enemy(new Transform(new Vector3f(0,0,50), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
//                new Animation(0, 1, animMesh), new Collider(new Vector3f(2,2,2)),
//                new Vector3f(1,2,1), new Vector3f(0, 1, 0)));


//
//
//        objects.add(new Enemy(new Transform(new Vector3f(5,0,0), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
//                new Material(new Texture("zomby_light.png")), "fast", new Vector3f(1,1,1)));



//        d = new Decoration(
//                new Transform(new Vector3f(20, 5, -10), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(-90)), new Vector3f(1,1,1)),
//                new Graphic(building, buildingMaterial),
//                new Vector3f(11, 11, 11),
//                new Vector3f(30, 11, 11));
//
//        objects.add(d);
        objects.add(new Decoration(
                new Transform(new Vector3f(30, 5, -10), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(-90)), new Vector3f(1,1,1)),
                new Graphic(building, buildingMaterial),
                new Vector3f(11, 11, 11),
                new Vector3f(11, 11, 11),
                new Vector3f(0,0,0)));

//        objects.add(new Decoration(
//                new Transform(new Vector3f(40, 5, -10), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(-90)), new Vector3f(1,1,1)),
//                new Graphic(building, buildingMaterial),
//                new Vector3f(11, 11, 11),
//                new Vector3f(11, 11, 11)));


        lights.add(new DirectionalLight(new Vector3f(0.6f, 0.4f, 0.3f), 0.5f, new Vector3f(-0.8f, -0.15f, -0.57f)));
        level = new Level(new SkyBox("1"), objects, lights);

        font = new Font("rus.png", "rus.fnt");
        text = new Text(0.8f, 0.8f, 0.15f, "text", font, new Vector3f(1, 0,0));
        level.addText(text);

//        level.addText(new Text(0, 0.0f, 0.10f, "small piece of text", font, new Vector3f(0, 0,0)));
//
//        level.addText(new Text(-0.9f, 0.0f, 0.10f, "small shit of text", font, new Vector3f(0, 1,0)));
//
//        level.addGUI(new GUITexture(new Texture("health.png"), new Vector2f(0.85f, -0.75f), new Vector2f(0.25f, 0.25f)));
//        level.addGUI(new GUITexture(new Texture("minimap.png"), new Vector2f(-0.75f, 0.70f),new Vector2f( 0.2f, 0.2f)));
//        level.addGUI(new GUITexture(new Texture("logo.png"), new Vector2f(-0.75f, -0.70f),new Vector2f( 0.2f, 0.2f)));
        level.addGUI(new GUITexture(new Texture("cross.png"), new Vector2f(0, 0),new Vector2f(0.2f, 0.2f)));
//
//        level.addGUI(new GUITexture(new Texture("health.png"), new Vector2f(1, 1), new Vector2f(1f, 1f)));



//        Source source2 = new Source();
//        int v = AudioEngine.loadSound("res/sound/theme1.ogg");
//        source2.play(v);

//        player.setJump(new Sound("res\\sound\\fx\\jump.wav"));

        double finish = System.currentTimeMillis();
        System.out.println((finish - start) / 1000 + "s");
    }
    public void render(RenderingEngine renderingEngine){
        renderingEngine.render(level);
    }
float k =0;
    public void update(){
        level.update();
fire.generateParticles(new Vector3f(0,0,0));
        fire.generateParticles(new Vector3f(0, 0, -10));

        if(Input.getKey(Input.KEY_O)){

            level.getObjects().add(new Enemy(new Transform(new Vector3f(r.nextInt(30)-15,0,r.nextInt(30)-15), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                    new Collider(new Vector3f(3,4,3)),
                    new Vector3f(1,2 ,1), new Vector3f(0, 1, 0), new Animation(0, 106, idle),
                    new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));
            k++;
            System.out.println(k);
        }

        ParticleMaster.update(engine.getRenderingEngine().getMainCamera());
//        k+= (float)Time.getDelta();
//        level.getText().get(0).setPos(new Vector2f((float)SyncEditor.y/100f,(float)SyncEditor.z/100f));
//        level.getText().get(0).setSize((float)SyncEditor.x/100f);
//        System.out.println(level.getText().get(0).getSize());
//        level.getText().clear();

        level.getText().get(0).edit(Integer.toString(engine.getFPS()));

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
