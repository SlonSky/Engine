package com.company.Engine.util;

/**
 * Created by Slon on 10.02.2016.
 */
public class Vertex {
    public static final int SIZE = 8;

    private Vector3f pos;
    private Vector2f texCoord;
    private Vector3f normal;

    public Vertex(Vector3f pos, Vector2f texCoord, Vector3f normal) {
        this.pos = pos;
        this.texCoord = texCoord;
        this.normal = normal;
    }

    public Vertex(Vector3f pos, Vector2f tex){
        this(pos, tex, new Vector3f(0,0,0));
    }

    public Vertex(Vector3f pos) {
        this(pos, new Vector2f(0,0), new Vector3f(0,0,0));
    }

    public Vector3f getPos() {
        return pos;
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
    }

    public Vector2f getTexCoord() {
        return texCoord;
    }

    public void setTexCoord(Vector2f texCoord) {
        this.texCoord = texCoord;
    }

    public Vector3f getNormal() {
        return normal;
    }

    public void setNormal(Vector3f normal) {
        this.normal = normal;
    }

    public boolean equals(Object v){
        return pos.equals(((Vertex)v).getPos()) && texCoord.equals(((Vertex)v).getTexCoord()) && normal.equals(((Vertex)v).getNormal());
    }
}
