package com.company.Engine.rendering.text;

import com.company.Engine.core.Window;
import com.company.Engine.rendering.Transform;
import com.company.Engine.rendering.guis.GUITexture;
import com.company.Engine.rendering.meshManagment.Mesh;
import com.company.Engine.util.Quaternion;
import com.company.Engine.util.Vector2f;
import com.company.Engine.util.Vector3f;
import com.company.Engine.util.Vertex;

import java.util.ArrayList;

/**
 * Created by Slon on 27.03.2016.
 */
//public class Text {
//    private static final char SPACE_CHAR = ' ';
//
//    private ArrayList<Mesh> chars;
//    private Font font;
//    private Vector3f color;
//
//    private float size;
//
//    // todo: what's wrong with RAM????
//    private Transform transform;
//
////    private String line;
//
//    public Text(float X, float Y, float size, String line, Font font, Vector3f color){
//        chars = new ArrayList<>();
//        this.color = color;
//        this.font = font;
//        transform = new Transform(new Vector3f(X, Y, 0), new Quaternion(0,0,0,1),
//                new Vector3f(0,0, 1));
//        setSize(size);
//        edit(line);
//
////? ???
//
//    }
//
////    public void edit(String line){
////        float X = pos.getX();
////        float Y = pos.getY();
////        edit(X, Y, line);
////    }
//
//    public Transform getTransform() {
//        return transform;
//    }
//
//    public void edit(String line){
//
//        // clear buffers
//        for(Mesh mesh: chars){
//            mesh.destroyRes();
//        }
//
//        chars.clear();
//
//        float cursorX = 0;
//        float cursorY = font.getLineHeight();
//        int[] indices = {
//                0,1,2, 0,2,3
//        };
//
//        for(int i=0; i<line.length(); i++){
//            char t = line.charAt(i);
//            if(t == SPACE_CHAR){
//                cursorX += font.getCharacters().get('a').getWidth();
//                System.out.println("Space");
//                continue;
//            } // ?
//            AtlasChar c = font.getCharacters().get(t);
//            cursorX += c.getxOffset();
//            Vertex[] v = {
//                    new Vertex(new Vector3f(cursorX+c.getWidth()+c.getxOffset(), cursorY+c.getyOffset(), 0),
//                            new Vector2f(c.getX()+c.getWidth()/font.getAtlasWidth(), c.getY()+c.getHeight()/font.getAtlasHeight())),
//                    new Vertex(new Vector3f(cursorX+c.getxOffset(), cursorY+c.getyOffset(), 0),
//                            new Vector2f(c.getX(), c.getY()+c.getHeight()/font.getAtlasHeight())),
//                    new Vertex(new Vector3f(cursorX+c.getxOffset(), cursorY+c.getHeight()+c.getxOffset(), 0),
//                            new Vector2f(c.getX(), c.getY())),
//                    new Vertex(new Vector3f(cursorX+c.getWidth()+c.getxOffset(), cursorY+c.getHeight()+c.getyOffset(), 0),
//                            new Vector2f(c.getX()+c.getWidth()/font.getAtlasWidth(), c.getY()))
//            };
//            chars.add(new Mesh(v, indices));
//            cursorX += c.getWidth() + c.getxAdvance();
//
//        }
//    }
//
//
//
//    public void draw(){
//        for(Mesh m: chars){
//            m.draw();
//        }
//    }
//
//    public void setPos(Vector2f pos) {
//        transform.setPosition(new Vector3f(pos.getX(), pos.getY(), 0));
//    }
//
//    public void setColor(Vector3f color) {
//        this.color = color;
//    }
//
//    public void setSize(float size) {
//        this.size = size;
//        transform.setScale(
////                new Vector3f(size / font.getSize(), size / font.getSize(), 1)
//                new Vector3f(size*(font.getSize()/ Display.getWidth())/ GUITexture.ASPECT_RATIO, size*(font.getLineHeight()/Display.getHeight()), 1)
//        );
//
//        System.out.print("t s " + size*(font.getSize()/ Display.getWidth())/ GUITexture.ASPECT_RATIO + " t h " + size*(font.getLineHeight()/Display.getHeight()));
//    }
//
//    public Font getFont() {
//        return font;
//    }
//
////    public Vector2f getScreenPos() {
////        return pos;
////    }
//
//    public Vector3f getColor() {
//        return color;
//    }
//
//    public float getSize() {
//        return size;
//    }
//}

public class Text {
    private ArrayList<Mesh> chars;
    private Font font;
    private Vector3f color;

    private float size;

    private float len;

    private Transform transform;

    public Text(float X, float Y, float size, String line, Font font, Vector3f color){
        chars = new ArrayList<>();
        this.color = color;
        this.font = font;
        transform = new Transform(new Vector3f(X, Y, 0), new Quaternion(0,0,0,1), new Vector3f(size/font.getSize()/ GUITexture.ASPECT_RATIO, size/font.getSize(), 1));
        this.size = size;
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
//        transform.setPosition(new Vector3f(transform.getPosition().getX() - size*cursorX, transform.getPosition().getY(), 0));



    }



    public void draw(){
        for(Mesh m: chars){
            m.draw();
        }
    }

    public void move(Vector2f delta){
        transform.setPosition(transform.getPosition().add(new Vector3f(delta.getX(), delta.getY(), 0)));
    }

    public void setPos(Vector2f pos) {
        transform.setPosition(new Vector3f(pos.getX(), pos.getY(), 0));
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }

    public void setSize(float size) {
        this.size = size;
        transform.setScale(new Vector3f((size/font.getSize()/ GUITexture.ASPECT_RATIO), size/font.getSize(), 1));

//        this.size = size;
//        transform.setScale(new Vector3f(size*font.getSize()/ GUITexture.ASPECT_RATIO, size*font.getSize(), 1));

//        transform.setPosition(transform.getPosition().sub(new Vector3f(this.size - size, this.size - size, 0)));
//        System.out.println("size " + size); // ????
    }

    public Font getFont() {
        return font;
    }

    public Vector3f getColor() {
        return color;
    }

    public float getSize() {
        return size;
    }

    public Vector2f getScreenPos() {
        return new Vector2f(transform.getPosition().getX()+ getScreenScale().getX(), transform.getPosition().getY()+getScreenScale().getY());
    }

    public Vector2f getScreenScale(){
        return new Vector2f(transform.getScale().getX()*len/2, transform.getScale().getY()*font.getLineHeight()* Window.getHeight()/2);
    }
}