package com.company.Engine.rendering;

/**
 * Created by Slon on 10.02.2016.
 */

import com.company.Engine.rendering.light.*;
import com.company.Engine.rendering.meshManagment.Material;
import com.company.Engine.rendering.meshManagment.Texture;
import com.company.Engine.util.Matrix4f;
import com.company.Engine.util.Vector2f;
import com.company.Engine.util.Vector3f;
import com.company.Engine.util.Vector4f;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;
public abstract class Shader {
    private int program;
    private static HashMap<String, Integer> uniforms = new HashMap<>();

    public Shader(){
        program = glCreateProgram();
        if(program == 0){
            System.err.println("Could not create shader program");
            System.exit(-1);
        }
    }

    public void addVertexShader(String filename){
        addProgram(loadShader(filename), GL_VERTEX_SHADER);
    }

    public void addFragmentShader(String filename){
        addProgram(loadShader(filename), GL_FRAGMENT_SHADER);
    }

    public void addGeometryShader(String filename){
        addProgram(loadShader(filename), GL_GEOMETRY_SHADER);
    }

    public void bind(){
        glUseProgram(program);
    }

    public void unbind(){
        glUseProgram(0);
    }

    public void compileShader(){
        glLinkProgram(program);
        if(glGetProgrami(program, GL_LINK_STATUS) == 0){
            System.err.println(glGetProgramInfoLog(program, 1024));
            System.exit(-1);
        }
        glValidateProgram(program);
        if(glGetProgrami(program, GL_VALIDATE_STATUS) == 0){
            System.err.println(glGetProgramInfoLog(program, 1024));
            System.exit(-1);
        }
    }

    private void addProgram(String text, int type){
        int shader = glCreateShader(type);
        if(shader == 0){
            System.err.println("Shader creation failed: Could not find valid memory location when adding shader");
            System.exit(-1);
        }
        glShaderSource(shader, text);
        glCompileShader(shader);
        if(glGetShaderi(shader, GL_COMPILE_STATUS) == 0){
            System.err.println(glGetShaderInfoLog(shader, 1024));
            System.exit(-1);
        }
        glAttachShader(program, shader);
    }

    private static String loadShader(String filename){
        StringBuilder shaderSource = new StringBuilder();
        BufferedReader shaderReader;
        try {
            shaderReader = new BufferedReader(new FileReader("./res/shaders/" + filename));
            String line;
            while ((line = shaderReader.readLine()) != null){
                shaderSource.append(line).append("\n");
            }
            shaderReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shaderSource.toString();
    }

    public void addUniform(String uniform){
        int uniformLocation = glGetUniformLocation(program, uniform);
        if(uniformLocation == 0xFFFFFFFF){
            System.err.println("Error: Could not find uniform: " + uniform);
            new Exception().printStackTrace();
            System.exit(-1);
        }
        uniforms.put(uniform, uniformLocation);
    }

    public abstract void updateUniforms(Transform transformation, Material material, RenderingEngine renderingEngine);

    public void setUniform(String uniformName, float value) {
        glUniform1f(uniforms.get(uniformName), value);
    }

    public void setUniform(String uniformName, int value) {
        glUniform1i(uniforms.get(uniformName), value);
    }

    public void setUniform(String uniformName, Vector3f vector){
        glUniform3f(uniforms.get(uniformName), vector.getX(), vector.getY(), vector.getZ());
    }

    public void setUniform(String uniformName, Matrix4f matrix) {
        glUniformMatrix4(uniforms.get(uniformName), true, Vector2f.Utils.createFlippedBuffer(matrix));
    }

    public void setUniform(String uniformName, Light light){
        setUniform(uniformName + ".color", light.getColor());
        setUniform(uniformName + ".intensity", light.getIntensity());
    }

    public void setUniform(String uniformName, Attenuation attenuation){
        setUniform(uniformName + ".constant", attenuation.getConstant());
        setUniform(uniformName + ".linear", attenuation.getLinear());
        setUniform(uniformName + ".exponent", attenuation.getExponent());
    }

    public void setUniform(String uniformName, DirectionalLight light){
        setUniform(uniformName + ".light", (Light)light);
        setUniform(uniformName + ".direction", light.getDirection());
    }

    public void setUniform(String uniformName, PointLight pointLight){
        setUniform(uniformName + ".light", (Light)pointLight);
        setUniform(uniformName + ".attenuation", pointLight.getAttenuation());
        setUniform(uniformName + ".position", pointLight.getPosition());
        setUniform(uniformName + ".range", pointLight.getRange());
    }

    public void setUniform(String uniformName, SpotLight spotLight){
        setUniform(uniformName + ".pointLight", (PointLight)spotLight);
        setUniform(uniformName + ".direction", spotLight.getDirection());
        setUniform(uniformName + ".cutoff", spotLight.getCutoff());
    }

    public void setUniform(String uniformName, Vector4f vec){
        glUniform4f(uniforms.get(uniformName), vec.getX(), vec.getY(), vec.getZ(), vec.getW());
    }
    public void prepareTexture(Material material){
        if(material.getTexture() != null){
            material.getTexture().bind();
        } else {
            Texture.unbind();
        }
    }
}
