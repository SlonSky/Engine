package Engine.util;

import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * Created by Slon on 11.02.2016.
 */
public class Vector2f {
    private float x;
    private float y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float dot(Vector2f r){
        return x * r.getX() + y * r.getY();
    }

    public float length(){
        return (float)Math.sqrt(x * x + y * y);
    }

    public Vector2f normalized(){
        float length = length();
        return new Vector2f(x / length, y / length);
    }

    public Vector2f add(Vector2f r){
        return new Vector2f(x + r.getX(), y + r.getY());
    }

    public Vector2f add(float r){
        return new Vector2f(x + r, y + r);
    }

    public Vector2f sub(Vector2f r){
        return new Vector2f(x - r.getX(), y - r.getY());
    }

    public Vector2f sub(float r){
        return new Vector2f(x - r, y - r);
    }

    public Vector2f mul(Vector2f r){
        return new Vector2f(x * r.getX(), y * r.getY());
    }

    public Vector2f mul(float r){
        return new Vector2f(x * r, y * r);
    }

    public Vector2f div(Vector2f r){
        return new Vector2f(x / r.getX(), y / r.getY());
    }

    public Vector2f div(float r){
        return new Vector2f(x / r, y / r);
    }
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean equals(Object v){
        return x == ((Vector2f)v).getX() && y == ((Vector2f)v).getY();
    }

    @Override
    public String toString() {
        return "(" + x + " " + y + ")";
    }

    /**
     * Created by Slon on 10.02.2016.
     */
    public static class Utils {

        public static FloatBuffer createFlippedBuffer(Vertex[] vertices){
            FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length * Vertex.SIZE);
            for(Vertex vertex: vertices){
                buffer.put(vertex.getPos().getX());
                buffer.put(vertex.getPos().getY());
                buffer.put(vertex.getPos().getZ());

                buffer.put(vertex.getTexCoord().getX());
                buffer.put(vertex.getTexCoord().getY());

                buffer.put(vertex.getNormal().getX());
                buffer.put(vertex.getNormal().getY());
                buffer.put(vertex.getNormal().getZ());
            }
            buffer.flip();
            return buffer;
        }

        public static FloatBuffer createFlippedBuffer(Matrix4f matrix){
            FloatBuffer buffer = BufferUtils.createFloatBuffer(4 * 4);
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    buffer.put(matrix.get(i, j));
                }
            }
            buffer.flip();
            return buffer;
        }

        public static FloatBuffer createFlippedBuffer(float[] data){
            FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
            buffer.put(data);
            buffer.flip();
            return buffer;
        }

        public static ByteBuffer createFlippedBuffer(byte[] data){
            ByteBuffer buffer = BufferUtils.createByteBuffer(data.length);
            buffer.put(data);
            buffer.flip();
            return buffer;
        }

        public static IntBuffer createFlippedBuffer(int[] data){
            IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
            buffer.put(data);
            buffer.flip();
            return buffer;
        }

        public static int[] toIntArray(Integer[] data){
            int[] result = new int[data.length];
            for(int i = 0; i < data.length; i++){
                result[i] = data[i];
            }
            return result;
        }
    }
}
