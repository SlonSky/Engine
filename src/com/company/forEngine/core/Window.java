package com.company.forEngine.core;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * Created by Slon on 09.02.2016.
 */
public class Window {
    public static void createWindow(int width, int height, String title){
        try {
            Display.setTitle(title);
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    public static void createFullWindow(String title){
        try {
            Display.setTitle(title);
            Display.setDisplayMode(Display.getDesktopDisplayMode());
            Display.setFullscreen(true);
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    public static int getWidth(){
        return Display.getWidth();
    }

    public static int getHeight(){
        return Display.getHeight();
    }

    public static boolean isCloseRequested(){
        return Display.isCloseRequested();
    }

    public static void render(){
        Display.update();
    }

    public static void destroy(){
        Display.destroy();
    }
}
