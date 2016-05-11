package com.company.Engine.rendering.meshManagment;

/**
 * Created by Slon on 10.02.2016.
 */

import com.company.Engine.rendering.objLoader.IndexedModel;
import com.company.Engine.rendering.objLoader.OBJModel;
import com.company.Engine.util.Vector2f;
import com.company.Engine.util.Vertex;

import java.util.ArrayList;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class Mesh {
    private static HashMap<String, MeshResource> loadedMeshes = new HashMap<>();

    private String filename;
    private MeshResource resource;

    public Mesh(Vertex[] vertices, int[] indices){
        addVertices(vertices, indices);
//        size = 0;
    }

    public Mesh(String filename){
        this.filename = filename;
        MeshResource oldRes = loadedMeshes.get(filename);
        if(oldRes == null) {
            loadMesh(filename);
            loadedMeshes.put(filename, resource);
        } else {
            resource = oldRes;
        }
    }

    private void addVertices(Vertex[] vertices, int[] indices){
        resource = new MeshResource(indices.length);
        glBindBuffer(GL_ARRAY_BUFFER, resource.getVbo());
        glBufferData(GL_ARRAY_BUFFER, Vector2f.Utils.createFlippedBuffer(vertices), GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, resource.getIbo());
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, Vector2f.Utils.createFlippedBuffer(indices), GL_STATIC_DRAW);
    }

    public void draw(){
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);

        glBindBuffer(GL_ARRAY_BUFFER, resource.getVbo());
        glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, Vertex.SIZE * 4, 12);
        glVertexAttribPointer(2, 3, GL_FLOAT, false, Vertex.SIZE * 4, 20);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, resource.getIbo());
        glDrawElements(GL_TRIANGLES, resource.getSize(), GL_UNSIGNED_INT, 0);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
    }

    private void loadMesh(String fileName)
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

        addVertices(vertexData, Vector2f.Utils.toIntArray(indexData));
    }

//    @Override
//    protected void finalize() throws Throwable {
//        destroyRes();
//        super.finalize();
//    }
//
    public void destroyRes(){
        resource.destroy();
    }
}
