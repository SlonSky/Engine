package Editor.LevelEditor;

import Engine.core.*;
import Engine.rendering.Camera;
import Game.Game;
import Game.GameObject;
import Game.entities.Decoration;
import Engine.rendering.Transform;
import Engine.rendering.light.Light;
import Engine.rendering.meshManagment.Material;
import Engine.rendering.meshManagment.Mesh;
import Engine.rendering.meshManagment.Texture;
import Engine.rendering.skybox.SkyBox;
import Engine.util.Quaternion;
import Engine.util.Vector3f;
import Engine.rendering.Graphic;
import Game.entities.Director;
import Game.Game;
import Game.GameObject;
import Game.Level;
import Game.entities.Decoration;
import Game.player.Player;

import java.util.ArrayList;

/**
 * Created by Slon on 08.04.2016.
 */
public class EditingGame extends Game {

    private SkyBox skyBox;
    private ArrayList<GameObject> objects;
    private ArrayList<Light> lights;


    @Override
    public void init() {

        skyBox = new SkyBox("TEST");
        objects = new ArrayList<>();
        lights = new ArrayList<>();

        Camera camera = new Camera(new Vector3f(0,0,0), new Quaternion(0,0,0,1));
        setCamera(camera);
        objects.add(new Director(camera, new Transform(camera.getPos(), camera.getRot(), new Vector3f(1, 1, 1))));


        level = new Level(skyBox, objects, lights);

        // start console
        Thread console = new Thread(new Runnable() {
            @Override
            public void run() {
                new EditorConsole(engine);
            }
        });
        console.start();
    }


    @Override
    public void input() {
        super.input();
        handleControl();
    }

    @Override
    public void update() {
        super.update();
        if(Input.getMouseDown(0)){
            System.out.println("down");
        }
        if (Input.getMouseUp(0)){
            System.out.println("up");
        }if( Input.getKeyUp(Input.KEY_T)){
            System.out.println("shqqqqq");
        }
        doTemp();
    }

    public void doTemp(){

        if(!EditorConsole.p.equals(new Vector3f(0,0,0)) && !EditorConsole.name.equals("")) {
//            level.getObjects().add(
//                    new Decoration(new Transform(EditorConsole.p, new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(-90)), new Vector3f(3, 3, 3)),
//                    new Graphic(new Mesh(EditorConsole.name+".obj"), new Material(new Texture(EditorConsole.name+".png"))),
//                    new Vector3f(3, 3, 3),
//                    new Vector3f(3,3, 3)));

            EditorConsole.p = new Vector3f(0,0,0);
            EditorConsole.name = "";
        }
    }

    public void handleControl(){
        if(Input.getKey(Input.KEY_Q)){
            System.out.println("GoodBye!");
            System.exit(1);
        }
    }

//    public void saveLevel(){
//
//        try {
//            FileWriter out = new FileWriter(new File("level"));
//            for(GameObject object: objects){
//                out.write();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
}
