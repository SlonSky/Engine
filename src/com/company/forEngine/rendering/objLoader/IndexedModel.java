package com.company.forEngine.rendering.objLoader;

import com.company.forEngine.util.Vector2f;
import com.company.forEngine.util.Vector3f;

import java.util.ArrayList;

/**
 * Created by Slon on 12.02.2016.
 */
public class IndexedModel {
    private ArrayList<Vector3f> positions;
    private ArrayList<Vector2f> texCoords;
    private ArrayList<Vector3f> normals;
    private ArrayList<Integer> indices;

    public IndexedModel(){
        positions = new ArrayList<>();
        texCoords = new ArrayList<>();
        normals = new ArrayList<>();
        indices = new ArrayList<>();
    }

    public ArrayList<Vector3f> getPositions() {
        return positions;
    }

    public ArrayList<Vector2f> getTexCoords() {
        return texCoords;
    }

    public ArrayList<Vector3f> getNormals() {
        return normals;
    }

    public ArrayList<Integer> getIndices() {
        return indices;
    }
}
