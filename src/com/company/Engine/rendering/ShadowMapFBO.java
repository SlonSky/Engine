package com.company.Engine.rendering;

/**
 * Created by Slon on 17.02.2016.
 */

import com.company.Engine.core.Window;
import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL13.*;


import static org.lwjgl.opengl.GL11.*;

public class ShadowMapFBO {
    private int fbo;
    private int shadowMap;

    public ShadowMapFBO(){
        fbo = glGenFramebuffers();

        shadowMap = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, shadowMap);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_DEPTH_COMPONENT, Window.getWidth(), Window.getHeight(), 0, GL_DEPTH_COMPONENT,
                GL_FLOAT,BufferUtils.createFloatBuffer(Window.getWidth()*Window.getHeight()));
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

        glBindFramebuffer(GL_DRAW_FRAMEBUFFER, fbo);
        glFramebufferTexture2D(GL_DRAW_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_TEXTURE_2D, shadowMap, 0);
        glDrawBuffer(GL_NONE);
        glReadBuffer(GL_NONE);

        int status = glCheckFramebufferStatus(GL_FRAMEBUFFER);
        if(status != GL_FRAMEBUFFER_COMPLETE){
            System.err.println("FB error!");
        }
    }

    public void bindForWriting(){
        glBindFramebuffer(GL_DRAW_FRAMEBUFFER, fbo);
    }

    public void bindForReading(int textureUnit){
        glActiveTexture(textureUnit);
        glBindTexture(GL_TEXTURE_2D, shadowMap);
    }

    public void finalize(){
        try {
            super.finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        if(fbo != 0){
            glDeleteFramebuffers(fbo);
        }
        if(shadowMap != 0){
            glDeleteFramebuffers(shadowMap);
        }
    }
}
