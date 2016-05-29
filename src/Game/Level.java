package Game;

import Engine.rendering.guis.GUITexture;
import Engine.rendering.light.Light;
import Engine.rendering.particles.Particle;
import Engine.rendering.particles.ParticleMaster;
import Engine.rendering.skybox.SkyBox;
import Engine.rendering.text.Text;
import Game.GameObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Slon on 07.03.2016.
 */
public class Level {

    // todo: triggers, respawn point
    private static ArrayList<GameObject> objects;
    private static ArrayList<Light> lights;
    private SkyBox skyBox;
    private static ArrayList<Text> text;
    private static ArrayList<GUITexture> guis;


    public Level(SkyBox skyBox, ArrayList<GameObject> objects, ArrayList<Light> lights) {
        this.skyBox = skyBox;
        Level.objects = objects;
        Level.lights = lights;
        text = new ArrayList<>();
        guis = new ArrayList<>();
    }

    public Level(SkyBox skyBox, ArrayList<GameObject> objects){
        this(skyBox, objects, new ArrayList<>());
    }

    public Level(SkyBox skyBox){
        this(skyBox, new ArrayList<>());
    }


    // todo: proper deleting
    public void destroy(){
        objects.clear();
        lights.clear();
        text.clear();
        guis.clear();
    }

    public void update(){
        Iterator<GameObject> it = objects.iterator();
//        for(GameObject object: objects){
//            object.update();
//        }
        while (it.hasNext()){
            GameObject object = it.next();
            object.update();
            if(object.isToRemove()){
                object.remove();
                it.remove();
            }
        }
    }

    public void input(){
        for(GameObject object: objects){
            object.input();
        }
    }
    public SkyBox getSkyBox() {
        return skyBox;
    }

    public static ArrayList<GameObject> getObjects() {
        return objects;
    }

    public static ArrayList<Light> getLights() {
        return lights;
    }

    public static void addText(Text text){
        Level.text.add(text);
    }

    public static ArrayList<Text> getText() {
        return text;
    }

    public static void addGUI(GUITexture gui){
        guis.add(gui);
    }

    public static ArrayList<GUITexture> getGuis() {
        return guis;
    }

}
