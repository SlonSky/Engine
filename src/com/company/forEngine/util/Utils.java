package com.company.forEngine.util;

import com.company.forEngine.core.Input;
import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * Created by Slon on 10.02.2016.
 */
public class Utils {

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
