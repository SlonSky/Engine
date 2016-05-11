package com.company.Engine.rendering.meshManagment;

/**
 * Created by Slon on 12.02.2016.
 */
import com.company.Engine.util.Vector2f;
import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.GL30.*;
public class Texture {
    private static HashMap<String, TextureResource> textures = new HashMap<>();

    private TextureResource resource;

    public Texture(String filename){
        TextureResource oldRes = textures.get(filename);
        if(oldRes == null){
            loadTexture(filename);
            textures.put(filename, resource);
        } else {
            resource = oldRes;
        }
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, resource.getId());
    }

    public static void unbind(){
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public int getID() {
        return resource.getId();
    }

    private void loadTexture(String fileName) {
        try {
            BufferedImage image = ImageIO.read(new File("./res/textures/" + fileName));
            ByteBuffer buffer = Texture.loadToBuffer(image);

            int texture = glGenTextures();

            glBindTexture(GL_TEXTURE_2D, texture);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

            glGenerateMipmap(GL_TEXTURE_2D);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_LOD_BIAS, -1);

            resource = new TextureResource(texture, new Vector2f(image.getWidth(), image.getHeight()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ByteBuffer loadToBuffer(BufferedImage image){
        boolean hasAlpha = image.getColorModel().hasAlpha();
        int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());

        ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * 4);

        for(int y = 0; y < image.getHeight(); y++){
            for(int x = 0; x < image.getWidth(); x++){
                int pixel = pixels[y * image.getWidth() + x];

                buffer.put((byte) ((pixel >> 16) & 0xFF));
                buffer.put((byte) ((pixel >> 8) & 0xFF));
                buffer.put((byte) ((pixel >> 0) & 0xFF));
                if(hasAlpha){
                    buffer.put((byte) ((pixel >> 24) & 0xFF));
                } else {
                    buffer.put((byte)(0xFF));
                }
            }
        }
        buffer.flip();
        return buffer;
    }

    public Vector2f getSize() {
        return resource.getSize();
    }
}
