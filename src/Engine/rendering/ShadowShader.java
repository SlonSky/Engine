//package Engine.rendering;
//
///**
// * Created by Slon on 17.02.2016.
// */
//import static org.lwjgl.opengl.GL11.*;
//
//import static org.lwjgl.opengl.GL20.*;
//public class ShadowShader extends Shader {
//int map;
//    public ShadowShader(){
//        super();
//        addVertexShader("shadowVertex.txt");
//        addFragmentShader("shadowFragment.txt");
//        compileShader();
//
//        addUniform("WVP");
//        addUniform("shadowMap");
//    }
//
//    public void setShadowMap(int map){
//       this.map = map;
//    }
//
//    @Override
//    public void updateUniforms(Transform transformation, Material material, RenderingEngine renderingEngine) {
//        setUniform("WVP", transformation.getProjectedTransformation());
//        setUniform("shadowMap", map);
//
//        prepareTexture(material);
//    }
//}
