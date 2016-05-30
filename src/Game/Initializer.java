package Game;

import Engine.audio.Sound;
import Engine.audio.Source;
import Engine.core.Window;
import Engine.physics.Collider;
import Engine.rendering.Camera;
import Engine.rendering.Graphic;
import Engine.rendering.Transform;
import Engine.rendering.animation.AnimMesh;
import Engine.rendering.animation.Animation;
import Engine.rendering.guis.GUITexture;
import Engine.rendering.light.DirectionalLight;
import Engine.rendering.light.Light;
import Engine.rendering.meshManagment.Material;
import Engine.rendering.meshManagment.Mesh;
import Engine.rendering.meshManagment.Texture;
import Engine.rendering.skybox.SkyBox;
import Engine.rendering.text.Font;
import Engine.rendering.text.Text;
import Engine.util.Quaternion;
import Engine.util.Vector2f;
import Engine.util.Vector3f;
import Game.enemy.Enemy;
import Game.entities.Decoration;
import Game.player.Player;
import com.sun.deploy.util.IcoEncoder;
import com.sun.javaws.jnl.IconDesc;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.ImageIOImageData;
import sun.awt.IconInfo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Slon on 22.05.2016.
 *
 * Initializes game resources and settings from res.ini and settings.ini files
 */
public class Initializer {

    private static final String SETTINGS_FILEPATH = "res/settings.ini";
    private static final String LEVELS_LOCATION = "res/levels/";

    public static final String PLAYER_STEP1 = "playerStep1";
    public static final String PLAYER_STEP2 = "playerStep2";
    public static final String PLAYER_DYING = "playerDying";

    public static final String GUN_SHOOTING = "gunShooting";
    public static final String GUN_SHOOTING_END = "gunShootingEnd";
    public static final String GUN_DRY_FIRE = "gunDryFire";
    public static final String GUN_RELOAD = "gunReload";

    public static final String ZM_ATTACK = "zmAttack";
    public static final String ZM_ROAR = "zmRoar";
    public static final String ZM_DYING = "zmDying";


    private static Initializer instance = new Initializer();

    private static HashMap<String, Sound> sounds;

    private int levelToLoad;
    // Level...
    // static keys
    // static sounds
    // ...
    private Initializer(){
        // open file
        // init static settings
        // ...
    }

    public void initSettings(String settings){

    }


    // todo: init(int levelNumber){}
    // todo : temp!/////////////////////////
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

    public void initSettings(){

    }

    //todo: temmp
    public Level loadLevel(Camera camera){
        // read file
        // create Level instance


        // main initialization

        loadSounds();
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
        player = new Player(camera, new Transform(new Vector3f(0f, 5, 0), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                hands,
                new Collider(new Vector3f(2,2f,2f), new Vector3f(0,0,0)));
        objects.add(player);

// todo: singleton for config initialization


//        fire =  new Fire(new ParticleTexture(new Texture("fire.png"), 8), 5, 5, 5);

        AnimMesh animMesh = new AnimMesh("zm_fast", "idle", new Material(new Texture("zomby_light.png"), 1, 4), 106, 30);
        idle = new AnimMesh("zm_fast", "idle", new Material(new Texture("zomby_light.png"), 1, 4), 106, 30);

        chase =new AnimMesh("zm_fast", "chase", new Material(new Texture("zomby_light.png"), 1, 4), 31, 40);
        attack = new AnimMesh("zm_fast", "attack", new Material(new Texture("zomby_light.png"), 1, 4), 29, 40);
        dying = new AnimMesh("zm_fast", "dying", new Material(new Texture("zomby_light.png"), 1, 4), 38, 30);

        objects.add(new Enemy(new Transform(new Vector3f(10,0,10), new Quaternion(0,0,0,1), new Vector3f(1,1,1)),
                new Collider(new Vector3f(2,2,2)),
                new Vector3f(1,2 ,1), new Vector3f(0, 1, 0), new Animation(0, 106, animMesh),
                new Animation(0, 31, chase), new Animation(0, 29, attack), new Animation(0, 38, dying)));

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
                new Vector3f(11, 11, 11)));

//        objects.add(new Decoration(
//                new Transform(new Vector3f(40, 5, -10), new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(-90)), new Vector3f(1,1,1)),
//                new Graphic(building, buildingMaterial),
//                new Vector3f(11, 11, 11),
//                new Vector3f(11, 11, 11)));


        lights.add(new DirectionalLight(new Vector3f(0.6f, 0.4f, 0.3f), 0.5f, new Vector3f(-0.8f, -0.15f, -0.57f)));
        Level level = new Level(new SkyBox("1"), objects, lights);

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

        return level;
    }

    // todo: from file
    private void loadSounds(){
        sounds = new HashMap<>(20);
        sounds.put(PLAYER_STEP1, new Sound("res/sound/steps1.wav"));
        sounds.put(PLAYER_STEP2, new Sound("res/sound/steps.wav"));
        sounds.put(PLAYER_DYING, new Sound("res/sound/playerDying.wav"));

        sounds.put(GUN_SHOOTING, new Sound("res/sound/fire.wav"));
        sounds.put(GUN_SHOOTING_END, new Sound("res/sound/fired.wav"));
        sounds.put(GUN_DRY_FIRE, new Sound("res/sound/dryfire.wav"));
        sounds.put(GUN_RELOAD, new Sound("res/sound/reload.ogg"));


        sounds.put(ZM_ATTACK, new Sound("res/sound/zmAttack.wav"));
        sounds.put(ZM_ROAR, new Sound("res/sound/zmRoar.wav"));
        sounds.put(ZM_DYING, new Sound("res/sound/zmDying.wav"));


    }

    public void setLevelToLoad(int levelToLoad) {
        this.levelToLoad = levelToLoad;
    }

    public static Initializer getInstance() {
        return instance;
    }

    public Sound getSound(String name){
        return sounds.get(name);
    }
}
