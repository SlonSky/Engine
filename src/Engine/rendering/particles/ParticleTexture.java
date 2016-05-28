package Engine.rendering.particles;

import Engine.rendering.meshManagment.Texture;

/**
 * Created by Slon on 27.05.2016.
 */
public class ParticleTexture {
    private Texture texture;
    private int numberOfRows;

    public ParticleTexture(Texture texture, int numberOfRows) {
        this.texture = texture;
        this.numberOfRows = numberOfRows;
    }

    public Texture getTexture() {
        return texture;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }
}
