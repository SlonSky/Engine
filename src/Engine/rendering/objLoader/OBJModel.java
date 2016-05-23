package Engine.rendering.objLoader;

import Engine.util.Vector2f;
import Engine.util.Vector3f;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Slon on 12.02.2016.
 */
public class OBJModel {
    private ArrayList<Vector3f> positions;
    private ArrayList<Vector2f> texCoords;
    private ArrayList<Vector3f> normals;
    private ArrayList<OBJIndex> indices;
    private boolean hasTexCoords;
    private boolean hasNormals;

    public OBJModel(String filename){
        positions = new ArrayList<>();
        texCoords = new ArrayList<>();
        normals = new ArrayList<>();
        indices = new ArrayList<>();
        hasNormals = false;
        hasTexCoords = false;

        BufferedReader meshReader = null;

        try {
            meshReader = new BufferedReader(new FileReader(filename));
            String line;

            while((line = meshReader.readLine()) != null) {
                String[] tokens = line.split(" ");
//                tokens = Util.removeEmptyStrings(tokens);

                if(tokens.length == 0 || tokens[0].equals("#")) {
                    continue;
                } else if(tokens[0].equals("v")) {
                    positions.add(new Vector3f(Float.valueOf(tokens[1]),
                            Float.valueOf(tokens[2]),
                            Float.valueOf(tokens[3])));
                } else if(tokens[0].equals("vt")) {
                    texCoords.add(new Vector2f(Float.valueOf(tokens[1]),
                            Float.valueOf(tokens[2])));
                } else if(tokens[0].equals("vn")) {
                    normals.add(new Vector3f(Float.valueOf(tokens[1]),
                            Float.valueOf(tokens[2]),
                            Float.valueOf(tokens[3])));
                }
                else if(tokens[0].equals("f")) {
                    for (int i = 0; i < tokens.length - 3; i++){
                        indices.add(parseOBJIndex(tokens[1]));
                        indices.add(parseOBJIndex(tokens[2 + i]));
                        indices.add(parseOBJIndex(tokens[3 + i]));
                    }
                }
            }

            meshReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public IndexedModel toIndexModel(){
        IndexedModel result = new IndexedModel();
        IndexedModel normalModel = new IndexedModel();
        HashMap<OBJIndex, Integer> resultIndexMap = new HashMap<OBJIndex, Integer>();
        HashMap<Integer, Integer> normalIndexMap = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> indexMap = new HashMap<Integer, Integer>();

        for(int i = 0; i < indices.size(); i++){
            OBJIndex currentIndex = indices.get(i);

            Vector3f currentPosition = positions.get(currentIndex.vertexIndex);
            Vector2f currentTexCoord;
            Vector3f currentNormal;

            if(hasTexCoords){
                currentTexCoord = texCoords.get(currentIndex.texCoordIndex);
            } else {
                currentTexCoord = new Vector2f(0,0);
            }

            if(hasNormals){
                currentNormal = normals.get(currentIndex.normalIndex);
            } else {
                currentNormal = new Vector3f(0,0,0);
            }

            Integer modelVertexIndex = resultIndexMap.get(currentIndex);

            if(modelVertexIndex == null){
                modelVertexIndex =  result.getPositions().size();
                resultIndexMap.put(currentIndex, result.getPositions().size());

                result.getPositions().add(currentPosition);
                result.getTexCoords().add(currentTexCoord);
                if (hasNormals){
                    result.getNormals().add(currentNormal);
                }

            }

            Integer normalModelIndex = normalIndexMap.get(currentIndex.vertexIndex);
            if(normalModelIndex == null){

                normalIndexMap.put(currentIndex.vertexIndex, normalModel.getPositions().size());
                normalModelIndex =  normalModel.getPositions().size();

                normalModel.getPositions().add(currentPosition);
                normalModel.getTexCoords().add(currentTexCoord);
                normalModel.getNormals().add(currentNormal);
            }
            result.getIndices().add(modelVertexIndex);
            normalModel.getIndices().add(normalModelIndex);
            indexMap.put(modelVertexIndex, normalModelIndex);

        }

        if(!hasNormals){
            normalModel.calcNormals();

            for(int i = 0; i < result.getPositions().size(); i++){
                result.getNormals().add(normalModel.getNormals().get(indexMap.get(i)));
            }
        }

        return result;
    }

    private OBJIndex parseOBJIndex(String token){
        String[] values = token.split("/");
        OBJIndex result = new OBJIndex();
        result.vertexIndex = Integer.parseInt(values[0]) - 1;
        if(values.length > 1){
            hasTexCoords = true;
            result.texCoordIndex = Integer.parseInt(values[1]) - 1;
            if(values.length > 2){
                hasNormals = true;
                result.normalIndex = Integer.parseInt(values[2]) - 1;
            }
        }
        return result;
    }
//
//    public ArrayList<Vector3f> getPositions() {
//        return positions;
//    }
//
//    public ArrayList<Vector2f> getTexCoords() {
//        return texCoords;
//    }
//
//    public ArrayList<Vector3f> getNormals() {
//        return normals;
//    }
//
//    public ArrayList<OBJIndex> getIndices() {
//        return indices;
//    }
}
