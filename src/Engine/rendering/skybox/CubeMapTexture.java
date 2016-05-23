package Engine.rendering.skybox;

import Engine.rendering.meshManagment.Texture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL13.*;

/**
 * Created by Slon on 24.03.2016.
 */
public class CubeMapTexture{
    private String name;
    private int cubeMapID;

    public CubeMapTexture(String name){
        this.name = name;
        load(name);
    }

    private void load(String name){
        try {
            BufferedImage up = ImageIO.read(new File("res/skybox/"+name+"_UP.png"));
            BufferedImage down = ImageIO.read(new File("res/skybox/"+name+"_DOWN.png"));
            BufferedImage left = ImageIO.read(new File("res/skybox/"+name+"_LEFT.png"));
            BufferedImage right = ImageIO.read(new File("res/skybox/"+name+"_RIGHT.png"));
            BufferedImage front = ImageIO.read(new File("res/skybox/"+name+"_FRONT.png"));
            BufferedImage back = ImageIO.read(new File("res/skybox/"+name+"_BACK.png"));

            cubeMapID = glGenTextures();
            glBindTexture(GL_TEXTURE_CUBE_MAP, cubeMapID);

            glTexImage2D(GL_TEXTURE_CUBE_MAP_POSITIVE_Y, 0, GL_RGBA, up.getWidth(), up.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, Texture.loadToBuffer(up));
            glTexImage2D(GL_TEXTURE_CUBE_MAP_NEGATIVE_Y, 0, GL_RGBA, down.getWidth(), down.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, Texture.loadToBuffer(down));

            glTexImage2D(GL_TEXTURE_CUBE_MAP_NEGATIVE_X, 0, GL_RGBA, left.getWidth(), left.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, Texture.loadToBuffer(left));
            glTexImage2D(GL_TEXTURE_CUBE_MAP_POSITIVE_X, 0, GL_RGBA, right.getWidth(), right.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, Texture.loadToBuffer(right));
            glTexImage2D(GL_TEXTURE_CUBE_MAP_NEGATIVE_Z, 0, GL_RGBA, front.getWidth(), front.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, Texture.loadToBuffer(front));
            glTexImage2D(GL_TEXTURE_CUBE_MAP_POSITIVE_Z, 0, GL_RGBA, back.getWidth(), back.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, Texture.loadToBuffer(back));

            glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_BORDER);
            glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_BORDER);
            glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_R, GL_CLAMP_TO_BORDER);

            glTexParameterf(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
            glTexParameterf(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MAG_FILTER, GL_LINEAR);


        } catch (IOException e) {
            System.out.println("Can't load cubemap: " + name);
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void bind(){
        glBindTexture(GL_TEXTURE_CUBE_MAP, cubeMapID);
    }

}
