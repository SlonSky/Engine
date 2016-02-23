package com.company.Engine.rendering;

/**
 * Created by Slon on 12.02.2016.
 */
import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
public class Texture {
    private int id;

    public Texture(String fileName)
    {
        this(loadTexture(fileName));
    }

    public Texture(int id)
    {
        this.id = id;
    }

    public void bind()
    {
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public static void unbind(){
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public int getID()
    {
        return id;
    }

    private static int loadTexture(String fileName)
    {
        try {
            BufferedImage image = ImageIO.read(new File("./res/textures/" + fileName));
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

            int texture = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, texture);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

            return texture;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
