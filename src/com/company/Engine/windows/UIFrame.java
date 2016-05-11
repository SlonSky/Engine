package com.company.Engine.windows;

import com.company.Editor.LevelEditor.SyncEditor;
import com.company.Engine.audio.Sound;
import com.company.Engine.audio.Source;
import com.company.Engine.rendering.RenderingEngine;
import com.company.Engine.rendering.guis.GUITexture;
import com.company.Engine.rendering.meshManagment.Texture;
import com.company.Engine.rendering.text.Font;
import com.company.Engine.rendering.text.Text;
import com.company.Engine.util.Vector2f;
import com.company.Engine.util.Vector3f;

import java.util.ArrayList;

/**
 * Created by Slon on 23.04.2016.
 */
public class UIFrame {
    static Font font;

    static Source uiSource;
    static Sound hoverSound;
    static Sound clickSound;

    private ArrayList<UIComponent> components;
    private WindowListener listener;

    private Sound ambient;

    public UIFrame() {

        //todo: create observer with editable behaviour
        components = new ArrayList<>();
    }

    public static void setFont(Font font) {
        UIFrame.font = font;
    }

    public static void setUiSource() {
        UIFrame.uiSource = new Source();
    }

    public static void setHoverSound(String hoverSound) {
        UIFrame.hoverSound = new Sound(hoverSound);
    }

    public static void setClickSound(String clickSound) {
        UIFrame.clickSound = new Sound(clickSound);
    }

    public void setListener(WindowListener listener) {
        this.listener = listener;
        for(UIComponent component: components){
            component.setListener(listener);
        }
    }

    public void update(){
        for(UIComponent component: components){
            component.update();
        }
    }

    public void addComponent(UIComponent component){
        components.add(component);
        component.addToFrame(this);
    }

    public ArrayList<UIComponent> getComponents() {
        return components;
    }
}
