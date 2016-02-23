package com.company.Engine.rendering;

/**
 * Created by Slon on 10.02.2016.
 */

import com.company.Engine.rendering.objLoader.IndexedModel;
import com.company.Engine.rendering.objLoader.OBJModel;
import com.company.Engine.util.Utils;
import com.company.Engine.util.Vertex;
import org.lwjgl.opengl.GL15;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class Mesh {
    private int vbo;
    private int ibo;
    private int size;

    public Mesh(Vertex[] vertices, int[] indices){
        initMeshData();
        addVertices(vertices, indices);
//        size = 0;

    }

    public Mesh(String filename){
        initMeshData();
        loadMesh(filename);
    }

    private void initMeshData(){
        vbo = glGenBuffers();
        ibo = glGenBuffers();
    }

    private void addVertices(Vertex[] vertices, int[] indices){
        size = indices.length;
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        GL15.glBufferData(GL_ARRAY_BUFFER, Utils.createFlippedBuffer(vertices), GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, Utils.createFlippedBuffer(indices), GL_STATIC_DRAW);
    }

    public void draw(){
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, Vertex.SIZE * 4, 12);
        glVertexAttribPointer(2, 3, GL_FLOAT, false, Vertex.SIZE * 4, 20);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glDrawElements(GL_TRIANGLES, size, GL_UNSIGNED_INT, 0);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
    }

    private Mesh loadMesh(String fileName)
    {
        String[] splitArray = fileName.split("\\.");
        String ext = splitArray[splitArray.length - 1];

        if(!ext.equals("obj"))
        {
            System.err.println("Error: File format not supported for mesh data: " + ext);
            new Exception().printStackTrace();
            System.exit(1);
        }
        OBJModel test = new OBJModel("./res/models/" + fileName);
        IndexedModel model = test.toIndexModel();
        model.calcNormals();

        ArrayList<Vertex> vertices = new ArrayList<>();
        for(int i = 0; i < model.getPositions().size(); i++){
            vertices.add(new Vertex(
                    model.getPositions().get(i),
                    model.getTexCoords().get(i),
                    model.getNormals().get(i)));
        }

        Vertex[] vertexData = new Vertex[vertices.size()];
        vertices.toArray(vertexData);

        Integer[] indexData = new Integer[model.getIndices().size()];
        model.getIndices().toArray(indexData);

        addVertices(vertexData, Utils.toIntArray(indexData));

        return null;
    }

}
