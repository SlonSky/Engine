package Engine.rendering.particles;

import Engine.rendering.RenderingEngine;
import Engine.rendering.Shader;
import Engine.rendering.Transform;
import Engine.rendering.meshManagment.Material;
import Engine.util.Vector2f;

/**
 * Created by Slon on 27.05.2016.
 */
public class ParticleShader extends Shader {

    private boolean billBoard;

    public ParticleShader(){
        super();

        addVertexShader("particleVertex.txt");
        addFragmentShader("particleFragment.txt");
        compileShader();
//
        addUniform("MVP");
        addUniform("texOffset1");
        addUniform("texOffset2");
        addUniform("texCoordInfo");
    }

    public void loadParticleInfo(Vector2f offset1, Vector2f offset2, float numRows, float blend, boolean billboard){
        setUniform("texOffset1", offset1);
        setUniform("texOffset2", offset2);
        setUniform("texCoordInfo", new Vector2f(numRows, blend));
        this.billBoard = billboard;
    }

    @Override
    public void updateUniforms(Transform transformation, Material material, RenderingEngine renderingEngine) {
        if(billBoard) {
            setUniform("MVP", transformation.getProjectedBillBoardTransformation());
        } else {
            setUniform("MVP", transformation.getProjectedTransformation());
        }
    }
}
