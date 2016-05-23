package Engine.rendering.text;

import Engine.core.Window;
import Engine.rendering.Transform;
import Engine.rendering.guis.GUITexture;
import Engine.rendering.meshManagment.Mesh;
import Engine.util.Quaternion;
import Engine.util.Vector2f;
import Engine.util.Vector3f;
import Engine.util.Vertex;

import java.util.ArrayList;

/**
 * Created by Slon on 27.03.2016.<br>
 * The <code>Text</code> represents line of text
 * rendered onto the screen.
 *
 */
public class Text {
    private ArrayList<Mesh> chars;
    private Font font;
    private Vector3f color;

    private float size;
    private float len;

    private Transform transform;

    /**
     * Creates <code>Text</code> as line of text
     * @param X screen x position
     * @param Y screen y position
     * @param size size in proportion to declared in font file size
     * @param line line of text
     * @param font text {@link Font}
     * @param color color in RGB
     */
    public Text(float X, float Y, float size, String line, Font font, Vector3f color){
        chars = new ArrayList<>();
        this.color = color;
        this.font = font;
        transform = new Transform(new Vector3f(X, Y, 0), new Quaternion(0,0,0,1), new Vector3f(size/font.getSize()/ GUITexture.ASPECT_RATIO, size/font.getSize(), 1));
        this.size = size;
        edit(line);
    }

    /**
     * Gets text transform
     * @return transform
     */
    public Transform getTransform() {
        return transform;
    }

    /**
     * Edits text
     * @param line of new <code>Text</code>
     */
    public void edit(String line){

        chars.clear();

        float cursorX = 0;
        float cursorY = -font.getLineHeight();
        int[] indices = {
                0,1,2, 0,2,3
        };

        for(int i=0; i<line.length(); i++){
            char t = line.charAt(i);
            if(t == ' '){ t = 13;} // ?
            AtlasChar c = font.getCharacters().get(t);
            cursorX += c.getxOffset();
            Vertex[] v = {
                    new Vertex(new Vector3f(cursorX+c.getWidth()+c.getxOffset(), cursorY+c.getyOffset(), 0),
                            new Vector2f(c.getX()+c.getWidth()/font.getAtlasWidth(), c.getY()+c.getHeight()/font.getAtlasHeight())),
                    new Vertex(new Vector3f(cursorX+c.getxOffset(), cursorY+c.getyOffset(), 0),
                            new Vector2f(c.getX(), c.getY()+c.getHeight()/font.getAtlasHeight())),
                    new Vertex(new Vector3f(cursorX+c.getxOffset(), cursorY+c.getHeight()+c.getxOffset(), 0),
                            new Vector2f(c.getX(), c.getY())),
                    new Vertex(new Vector3f(cursorX+c.getWidth()+c.getxOffset(), cursorY+c.getHeight()+c.getyOffset(), 0),
                            new Vector2f(c.getX()+c.getWidth()/font.getAtlasWidth(), c.getY()))
            };
            chars.add(new Mesh(v, indices));
            cursorX += c.getWidth() + c.getxAdvance();
        }
        len = cursorX;
    }

    /**
     * Draws the text meshes
     */
    public void draw(){
        for(Mesh m: chars){
            m.draw();
        }
    }

    /**
     * Moves text
     * @param delta displacement of text
     */
    public void move(Vector2f delta){
        transform.setPosition(transform.getPosition().add(new Vector3f(delta.getX(), delta.getY(), 0)));
    }

    /**
     * Sets position of text
     * @param pos new position
     */
    public void setPos(Vector2f pos) {
        transform.setPosition(new Vector3f(pos.getX(), pos.getY(), 0));
    }

    /**
     * Sets color of text
     * @param color new color
     */
    public void setColor(Vector3f color) {
        this.color = color;
    }

    /**
     * Sets size of text
     * @param size new size
     */
    public void setSize(float size) {
        this.size = size;
        transform.setScale(new Vector3f((size/font.getSize()/ GUITexture.ASPECT_RATIO), size/font.getSize(), 1));
    }

    /**
     * Gets text {@link Font}
     * @return text font
     */
    public Font getFont() {
        return font;
    }

    /**
     * Gets text color
     * @return color
     */
    public Vector3f getColor() {
        return color;
    }

    /**
     * Gets text size
     * @return size
     */
    public float getSize() {
        return size;
    }

    /**
     * Gets screen position of text
     * @return position
     */
    public Vector2f getScreenPos() {
        return new Vector2f(transform.getPosition().getX()+ getScreenScale().getX(), transform.getPosition().getY()+getScreenScale().getY());
    }

    /**
     * Gets size of text line in proportions to screen
     * @return 2 dimensional vector of size
     */
    public Vector2f getScreenScale(){
        return new Vector2f(transform.getScale().getX()*len/2, transform.getScale().getY()*font.getLineHeight()* Window.getHeight()/2);
    }
}