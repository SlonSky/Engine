package com.company.Engine.windows;

import com.company.Editor.LevelEditor.SyncEditor;
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
    private ArrayList<UIComponent> components;
    private GUITexture background;
    private Text title;

    public UIFrame(Texture background, String title) {
        components = new ArrayList<>();
        this.background = new GUITexture(background, new Vector2f(0,0), new Vector2f(1,1));
        this.title =new Text(0, 0, 20f, title, new Font("rus.png", "rus.fnt"), new Vector3f(1,0,0));
}

    public void update(){
        title.setSize((float) SyncEditor.a / 100f);
        title.setPos(new Vector2f((float)SyncEditor.b/100f, (float)SyncEditor.c/100f));

        System.out.println(title.getSize());
    }

    public void addComponent(UIComponent component){
        components.add(component);
    }


//    // todo: layer component rendering (getComponents..)
//    public ArrayList<GUITexture> getAllGuis(){
//        ArrayList<GUITexture> guis = new ArrayList<>();
//        guis.add(background);
//        for(UIComponent component: components){
//            guis.add(component.getGui());
//        }
//        return guis;
//    }
//
//    public ArrayList<Text> getAllTexts(){
//        ArrayList<Text> texts = new ArrayList<>();
//        texts.add(title);
////        texts.add(null);
//        for(UIComponent component: components){
//            texts.add(component.getText());
//        }
//        return texts;
//    }

    public ArrayList<UIComponent> getComponents() {
        return components;
    }

    public GUITexture getBackground() {
        return background;
    }

    public Text getText() {
        return title;
    }
}
