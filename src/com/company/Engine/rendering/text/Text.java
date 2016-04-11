package com.company.Engine.rendering.text;

import com.company.Engine.core.Window;
import com.company.Engine.rendering.Transform;
import com.company.Engine.rendering.gui.GUITexture;
import com.company.Engine.rendering.meshManagment.Mesh;
import com.company.Engine.util.Quaternion;
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
    private Vector3f color;

    private float size;

    private Transform transform;

    public Text(float X, float Y, float size, String line, Font font, Vector3f color){
        chars = new ArrayList<>();
        this.color = color;
        this.font = font;
        transform = new Transform(new Vector3f(X, Y, 0), new Quaternion(0,0,0,1), new Vector3f(size/font.getSize()/ GUITexture.ar, size/font.getSize(), 1));

        edit(line);

//? ???

    }

//    public void edit(String line){
//        float X = pos.getX();
//        float Y = pos.getY();
//        edit(X, Y, line);
//    }

    public Transform getTransform() {
        return transform;
    }

    public void edit(String line){

        chars.clear();

        float cursorX = 0;
        float cursorY = font.getLineHeight();
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



    }



    public void draw(){
        for(Mesh m: chars){
            m.draw();
        }
    }

    public void setPos(Vector2f pos) {
        transform.setPosition(new Vector3f(pos.getX(), pos.getY(), 0));
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }

    public void setSize(float size) {
        this.size = size;
        transform.setScale(new Vector3f(size/font.getSize()/ GUITexture.ar, size/font.getSize(), 1));
    }

    public Font getFont() {
        return font;
    }

//    public Vector2f getPos() {
//        return pos;
//    }

    public Vector3f getColor() {
        return color;
    }

    public float getSize() {
        return size;
    }
}
