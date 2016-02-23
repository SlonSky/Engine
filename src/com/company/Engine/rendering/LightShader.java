//package com.company.forEngine.rendering;
//
//import com.company.Engine.rendering.light.DirectionalLight;
//import com.company.Engine.util.Vector3f;
//import static org.lwjgl.opengl.GL11.*;
//
///**
// * Created by Slon on 13.02.2016.
// */
//public class LightShader extends Shader {
//
//    private Vector3f ambient;
//    private DirectionalLight directionalLight;
//
//    public LightShader(){
//        super();
//        addVertexShader("lightVertex.txt");
//        addFragmentShader("lightFragment.txt");
//        compileShader();
//
//        addUniform("color");
//
//        addUniform("gWVP");
//        addUniform("gWorld");
//
//        addUniform("ambient");
//        addUniform("gDirectionalLight.light.color");
//        addUniform("gDirectionalLight.light.intensity");
//        addUniform("gDirectionalLight.direction");
//
//        addUniform("eyePosition");
//        addUniform("specularIntensity");
//        addUniform("specularPower");
//
//        ambient = new Vector3f(0.3f, 0.3f, 0.3f);
//        directionalLight = new DirectionalLight(new Vector3f(1,1,1), 0.4f, new Vector3f(0, -1, 1));
//    }
//
//
//    @Override
//    public void updateUniforms(Transform transformation, Camera camera, Material material) {
//        if(material.getTexture() != null){
//            material.getTexture().bind();
//        } else {
//            glBindTexture(GL_TEXTURE_2D, 0);
//        }
//        setUniform("color", material.getColor());
//        setUniform("gWVP", transformation.getProjectedTransformation());
//        setUniform("gWorld", transformation.getTransformation());
//        setUniform("ambient", ambient);
//        setUniform("gDirectionalLight.light.color", directionalLight.getColor());
//        setUniform("gDirectionalLight.light.intensity", directionalLight.getIntensity());
//        setUniform("gDirectionalLight.direction", directionalLight.getDirection());
//        setUniform("eyePosition", camera.getPos());
//        setUniform("specularIntensity", material.getSpecularIntensity());
//        setUniform("specularPower", material.getSpecularPower());
//    }
//}
