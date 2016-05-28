package Engine.rendering;

/**
 * Created by Slon on 09.02.2016.
 */

import Engine.core.CoreEngine;
import Engine.rendering.guis.GUIRenderer;
import Engine.rendering.guis.GUITexture;
import Engine.rendering.light.Light;
import Engine.rendering.particles.Particle;
import Engine.rendering.particles.ParticleMaster;
import Engine.rendering.particles.ParticleRenderer;
import Engine.rendering.skybox.SkyBoxRenderer;
import Engine.rendering.text.Text;
import Engine.rendering.text.TextRenderer;
import Engine.util.Matrix4f;
import Engine.util.Plane;
import Engine.util.Vector3f;
import Game.Level;
import Engine.windows.UIComponent;
import Engine.windows.UIFrame;

import static org.lwjgl.opengl.GL11.*;

public class RenderingEngine {
    private CoreEngine core;

    private Camera mainCamera;
    private Light activeLight;
    private Plane[] frustum;

    private LevelRenderer levelRenderer;

    // todo move to level renderer?
    private SkyBoxRenderer skyBoxRenderer;
    private TextRenderer textRenderer;
    private GUIRenderer guiRenderer;

    public RenderingEngine(CoreEngine core){
        this.core = core;
        levelRenderer = new LevelRenderer(new Vector3f(0.9f, 0.9f, 0.95f));
        skyBoxRenderer = new SkyBoxRenderer();

        textRenderer = new TextRenderer();
        guiRenderer = new GUIRenderer();

        ParticleMaster.init();

        glClearColor(1.5f, 1, 1.5f, 1);
        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_TEXTURE_2D);

    }




    public void render(Level level) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        frustum = calcFrustum();

        skyBoxRenderer.render(level.getSkyBox(), this);
        levelRenderer.render(level, this);
        ParticleMaster.render(this);
        guiRenderer.render(level.getGuis(), this);
        textRenderer.render(level.getText(), this);

    }

    public void render(UIFrame frame){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        for(UIComponent component: frame.getComponents()){
            GUITexture gui = component.getGui();
            Text text = component.getText();
            if(gui != null) {
                guiRenderer.render(component.getGui(), this);
            }
            if(text != null) {
                textRenderer.render(component.getText(), this);
            }
        }
    }

    private Plane[] calcFrustum(){
        Matrix4f clip = Transform.getProjectedModelView();

        Plane[] frustum = new Plane[6];

        // right plane
        frustum[0] = new Plane(
                clip.get(3, 0) - clip.get(0,0),
                clip.get(3, 1) - clip.get(0,1),
                clip.get(3, 2) - clip.get(0,2),
                clip.get(3, 3) - clip.get(0,3)).normalized();

        // left plane
        frustum[1] = new Plane(
                clip.get(3, 0) + clip.get(0,0),
                clip.get(3, 1) + clip.get(0,1),
                clip.get(3, 2) + clip.get(0,2),
                clip.get(3, 3) + clip.get(0,3)).normalized();

        // bottom plane
        frustum[2] = new Plane(
                clip.get(3, 0) + clip.get(1,0),
                clip.get(3, 1) + clip.get(1,1),
                clip.get(3, 2) + clip.get(1,2),
                clip.get(3, 3) + clip.get(1,3)).normalized();

        // top plane
        frustum[3] = new Plane(
                clip.get(3, 0) - clip.get(1,0),
                clip.get(3, 1) - clip.get(1,1),
                clip.get(3, 2) - clip.get(1,2),
                clip.get(3, 3) - clip.get(1,3)).normalized();

        // near plane
        frustum[4] = new Plane(
                clip.get(3, 0) - clip.get(2,0),
                clip.get(3, 1) - clip.get(2,1),
                clip.get(3, 2) - clip.get(2,2),
                clip.get(3, 3) - clip.get(2,3)).normalized();

        // far plane
        frustum[5] = new Plane(
                clip.get(3, 0) + clip.get(2,0),
                clip.get(3, 1) + clip.get(2,1),
                clip.get(3, 2) + clip.get(2,2),
                clip.get(3, 3) + clip.get(2,3)).normalized();

        return frustum;
    }

    public Plane[] getFrustum() {
        return frustum;
    }

    public void setActiveLight(Light activeLight) {
        this.activeLight = activeLight;
    }

    public void setMainCamera(Camera mainCamera) {
        this.mainCamera = mainCamera;
    }

    public Camera getMainCamera() {
        return mainCamera;
    }

    public Light getActiveLight() {
        return activeLight;
    }

}
