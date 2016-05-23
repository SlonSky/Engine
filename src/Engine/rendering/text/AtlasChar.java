package Engine.rendering.text;

/**
 * Created by Slon on 27.03.2016.<br>
 * The <code>AtlasChar</code> represents unit of font file -
 * font character with it's own characteristics. Uses for
 * font loading and processing.
 */
public class AtlasChar {
    private int id;
    private float x;
    private float y;
    private float width;
    private float height;
    private float xOffset;
    private float yOffset;
    private float xAdvance;

    /**
     * Creates <code>AtlasChar</code> from font file data.
     * @param id character id
     * @param x x coordinate on font atlas
     * @param y y coordinate on font atlas
     * @param width character width
     * @param height character height
     * @param xOffset character x offset
     * @param yOffset character y offset
     * @param xAdvance character x padding when line builds
     */
    public AtlasChar(int id, float x, float y, float width, float height, float xOffset, float yOffset, float xAdvance) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.xAdvance = xAdvance;
    }

    /**
     * Gets character ID
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets character x coordinate in font atlas
     * @return x
     */
    public float getX() {
        return x;
    }

    /**
     * Gets character y coordinate in font atlas
     * @return y
     */
    public float getY() {
        return y;
    }

    /**
     * Gets character width
     * @return width
     */
    public float getWidth() {
        return width;
    }

    /**
     * Gets character height
     * @return height
     */
    public float getHeight() {
        return height;
    }

    /**
     * Gets character x offset
     * @return x offset
     */
    public float getxOffset() {
        return xOffset;
    }

    /**
     * Gets character y offset
     * @return y offset
     */
    public float getyOffset() {
        return yOffset;
    }

    /**
     * Gets character x advance on line building
     * @return x advance
     */
    public float getxAdvance() {
        return xAdvance;
    }
}
