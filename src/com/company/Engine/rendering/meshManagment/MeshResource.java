package com.company.Engine.rendering.meshManagment;

import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;

/**
 * Created by Slon on 10.03.2016.
 */
public class MeshResource {

    private int vbo;
    private int ibo;
    private int size;

    public MeshResource(int size) {
        vbo = glGenBuffers();
        ibo = glGenBuffers();
        this.size = size;
    }

    public int getVbo() {
        return vbo;
    }

    public int getIbo() {
        return ibo;
    }

    public int getSize() {
        return size;
    }

    public void destroy(){
        glDeleteBuffers(vbo);
        glDeleteBuffers(ibo);
    }
}
