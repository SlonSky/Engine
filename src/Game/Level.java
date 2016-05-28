package Game;

import Engine.rendering.guis.GUITexture;
import Engine.rendering.light.Light;
import Engine.rendering.particles.Particle;
import Engine.rendering.particles.ParticleMaster;
import Engine.rendering.skybox.SkyBox;
import Engine.rendering.text.Text;
import Game.GameObject;

import java.util.ArrayList;

/**
 * Created by Slon on 07.03.2016.
 */
public class Level {

    // todo: triggers, respawn point
    private ArrayList<GameObject> objects;
    private ArrayList<Light> lights;
    private SkyBox skyBox;
    private ArrayList<Text> text;
    private ArrayList<GUITexture> guis;


    public Level(SkyBox skyBox, ArrayList<GameObject> objects, ArrayList<Light> lights) {
        this.skyBox = skyBox;
        this.objects = objects;
        this.lights = lights;
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
        for(GameObject object: objects){
            object.update();
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

    public ArrayList<GameObject> getObjects() {
        return objects;
    }

    public ArrayList<Light> getLights() {
        return lights;
    }

    public void addText(Text text){
        this.text.add(text);
    }

    public ArrayList<Text> getText() {
        return text;
    }

    public void addGUI(GUITexture gui){
        guis.add(gui);
    }

    public ArrayList<GUITexture> getGuis() {
        return guis;
    }


}
