package com.company.Engine.rendering.text;

import com.company.Engine.rendering.meshManagment.Mesh;
import com.company.Engine.util.Vector2f;
import com.company.Engine.util.Vector3f;
import com.company.Engine.util.Vertex;

import java.util.ArrayList;

/**
 * Created by Slon on 27.03.2016.
 */
public class Text {
    private ArrayList<Mesh> chars;
    private Font font;
    private Vector2f pos;
    private Vector3f color;
    private float size;

    public Text(float X, float Y, float size, String line, Font font, Vector3f color){
        chars = new ArrayList<>();
        pos = new Vector2f(X, Y);
        this.color = color;
        this.font = font;
        this.size = size/font.getSize();

        float cursorX = 0;
        float cursorY = 0;
        int[] indices = {
                0,1,2, 0,2,3
        };

        for(int i=0; i<line.length(); i++){
            AtlasChar c = font.getCharacters().get(line.charAt(i));
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


    }

    public void update(String line){}

    public void draw(){
        for(Mesh m: chars){
            m.draw();
        }
    }

    public Font getFont() {
        return font;
    }

    public Vector2f getPos() {
        return pos;
    }

    public Vector3f getColor() {
        return color;
    }

    public float getSize() {
        return size;
    }
}
