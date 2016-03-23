package com.company.Engine.rendering;

import com.company.Engine.rendering.light.AmbientShader;
import com.company.Engine.rendering.light.Light;
import com.company.Engine.util.Matrix4f;
import com.company.Engine.util.Plane;
import com.company.Engine.util.Vector3f;
import com.company.Game.objects.Decoration;
import com.company.Game.objects.GameObject;
import com.company.Game.objects.Level;
import com.company.Game.objects.Player;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Slon on 07.03.2016.
 * TODO: light renderable/non-light renderable entities
 */


public class LevelRenderer {

    private Shader ambient;

    public LevelRenderer(){
        ambient = new AmbientShader(new Vector3f(0.8f, 0.8f, 0.8f));
    }

    public void render(Level level, RenderingEngine engine){

        ArrayList<GameObject> objects = level.getObjects();
        ArrayList<Light> lights = level.getLights();

        for(GameObject object: objects){
            object.render(ambient, engine);
        }

        // blending other light types rendering
        glEnable(GL_BLEND);
        glBlendFunc(GL_ONE, GL_ONE);
        glDepthMask(false);
        glDepthFunc(GL_EQUAL);

        for (Light light: lights) {
            engine.setActiveLight(light);
            for(GameObject object: objects){
                object.render(light.getShader(), engine);
            }
        }

        glDepthFunc(GL_LESS);
        glDepthMask(true);
        glDisable(GL_BLEND);
    }

//    public void render(ArrayList<GameObject> objects, RenderingEngine engine){
////        player.render(ambient, engine);
////        d.render(ambient, engine);
//    }


//    private void drawObjects(List<GameObject> objects, Shader shader, RenderingEngine engine){
//        Plane[] frustum = calcFrustum();
//        int b = 0;
//        for (GameObject object : objects) {
//            //if(!object.isCulling() || objectInFrustum(frustum, object.getCullingCube().getPoints())){
////                b++;
//                Entity e = object.getEntity();
//                shader.bind();
//                shader.updateUniforms(e.getTransform(), e.getMaterial(), engine);
//                e.getMesh().draw();
//
//                shader.bind();
//                shader.updateUniforms(object.bound.getTransform(), object.bound.getMaterial(), engine);
//                object.bound.getMesh().draw(); // todo
//            }
//        }
//        System.err.println(b);
//    }

//    private Plane[] calcFrustum(){
//        Matrix4f clip = Transform.getProjectedModelView();
//
//        Plane[] frustum = new Plane[6];
//
//        // right plane
//        frustum[0] = new Plane(
//                clip.get(3, 0) - clip.get(0,0),
//                clip.get(3, 1) - clip.get(0,1),
//                clip.get(3, 2) - clip.get(0,2),
//                clip.get(3, 3) - clip.get(0,3)).normalized();
//
//        // left plane
//        frustum[1] = new Plane(
//                clip.get(3, 0) + clip.get(0,0),
//                clip.get(3, 1) + clip.get(0,1),
//                clip.get(3, 2) + clip.get(0,2),
//                clip.get(3, 3) + clip.get(0,3)).normalized();
//
//        // bottom plane
//        frustum[2] = new Plane(
//                clip.get(3, 0) + clip.get(1,0),
//                clip.get(3, 1) + clip.get(1,1),
//                clip.get(3, 2) + clip.get(1,2),
//                clip.get(3, 3) + clip.get(1,3)).normalized();
//
//        // top plane
//        frustum[3] = new Plane(
//                clip.get(3, 0) - clip.get(1,0),
//                clip.get(3, 1) - clip.get(1,1),
//                clip.get(3, 2) - clip.get(1,2),
//                clip.get(3, 3) - clip.get(1,3)).normalized();
//
//        // near plane
//        frustum[4] = new Plane(
//                clip.get(3, 0) - clip.get(2,0),
//                clip.get(3, 1) - clip.get(2,1),
//                clip.get(3, 2) - clip.get(2,2),
//                clip.get(3, 3) - clip.get(2,3)).normalized();
//
//        // far plane
//        frustum[5] = new Plane(
//                clip.get(3, 0) + clip.get(2,0),
//                clip.get(3, 1) + clip.get(2,1),
//                clip.get(3, 2) + clip.get(2,2),
//                clip.get(3, 3) + clip.get(2,3)).normalized();
//
//        return frustum;
//    }

//    private boolean pointInFrustum(Plane[] frustum, Vector3f point){
//        for(int i = 0; i < 6; i++){
//            if(frustum[i].getA() * point.getX() + frustum[i].getB() * point.getY() + frustum[i].getC() * point.getZ() + frustum[i].getD() <= 0){
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private boolean objectInFrustum(Plane[] frustum, Vector3f[] points){
//        for (Vector3f point : points) {
//            if (pointInFrustum(frustum, point)) {
//                return true;
//            }
//        }
//        return false;
//    }

}

