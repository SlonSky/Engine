package Game.games;

import Engine.core.Input;
import Engine.physics.Collider;
import Engine.rendering.Camera;
import Engine.rendering.Graphic;
import Engine.rendering.RenderingEngine;
import Engine.rendering.Transform;
import Engine.rendering.animation.AnimMesh;
import Engine.rendering.animation.Animation;
import Engine.rendering.guis.GUITexture;
import Engine.rendering.light.DirectionalLight;
import Engine.rendering.light.Light;
import Engine.rendering.meshManagment.Material;
import Engine.rendering.meshManagment.Mesh;
import Engine.rendering.meshManagment.Texture;
import Engine.rendering.particles.Particle;
import Engine.rendering.particles.ParticleMaster;
import Engine.rendering.particles.ParticleTexture;
import Engine.rendering.skybox.SkyBox;
import Engine.rendering.text.Font;
import Engine.rendering.text.Text;
import Engine.util.Quaternion;
import Engine.util.Vector2f;
import Engine.util.Vector3f;
import Engine.windows.UIState;
import Engine.windows.WindowManager;
import Game.GameObject;
import Game.Level;
import Game.enemy.Enemy;
import Game.entities.Decoration;
import Game.entities.Fire;
import Game.player.Player;
import Game.Game;
import Game.GameplayManager;
import Game.Initializer;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Slon on 29.05.2016.
 */
public class Play implements GameState {

    private Game game;
    private Camera camera;
    private Level level;
    private WindowManager manager;

    private boolean pause;
    private boolean gameOver;
    private boolean levelEnded;

    public Play(WindowManager manager, Game game) {
        this.manager = manager;
        this.game = game;
        this.pause = false;
    }



    public void init(){

        System.out.println("Loading...");
        double start = System.currentTimeMillis();

        camera = new Camera(new Vector3f(0,0,0),new Quaternion(0,0,0,1));
        game.setCamera(camera);
        camera.setPos(new Vector3f(0, 1, 0));

        level = Initializer.getInstance().loadLevel(camera);
        double finish = System.currentTimeMillis();

        System.out.println((finish - start) / 1000 + "s");
    }

    @Override
    public void enter() {
        init();

    }

    @Override
    public void update() {
        GameplayManager.getInstance().update();
        gameOver = checkGameOver();
        levelEnded = checkLevelEnded();
        checkPause();
        if(pause || gameOver || levelEnded){
            manager.update();
        } else {
            level.input();
            level.update();
            ParticleMaster.update(camera);
        }
    }

    @Override
    public void render(RenderingEngine engine) {
        engine.render(level);
        if(pause || gameOver || levelEnded){
            engine.render(manager.getMainFrame(), true);
        }

    }

    public void checkPause(){
        if(Input.getKeyDown(Input.KEY_ESCAPE) && !checkGameOver() && !checkLevelEnded()){
            pause = !pause;
            if(pause){
                manager.alert(UIState.PAUSE);
            } else {
                manager.alert(UIState.GAME);
            }
        }
        if(manager.getOldState() == UIState.GAME){
            pause = manager.isPaused();
        }
    }

    public boolean checkGameOver(){
        boolean res = GameplayManager.getInstance().isGameOver();
        if(res){
            manager.alert(UIState.GAME_OVER);
        }
        return res;
    }

    public boolean checkLevelEnded(){
        boolean res = GameplayManager.getInstance().isLevelEnded();
        if(res){
            manager.alert(UIState.LEVEL_ENDED);
        }
        return res;
    }

    @Override
    public void exit() {
        level.destroy();
    }
}
