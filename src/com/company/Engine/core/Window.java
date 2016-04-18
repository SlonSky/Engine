package com.company.Engine.core;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.awt.image.BufferedImage;

/**
 * Created by Slon on 09.02.2016.
 */
public class Window {

    public static void createWindow(int width, int height, String title){
        try {
            Display.setTitle(title);
            Display.setDisplayMode(new DisplayMode(width, height));
//            Display.setVSyncEnabled(true);
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    public static void setLocation(int x, int y){
        Display.setLocation(x, y);
    }

    public static void createFullWindow(String title){
        try {
            Display.setTitle(title);
            Display.setDisplayMode(Display.getDesktopDisplayMode());
            Display.setFullscreen(true);
//            Display.setVSyncEnabled(true);
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    public static void setVSync(boolean on){
        Display.setVSyncEnabled(on);
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
