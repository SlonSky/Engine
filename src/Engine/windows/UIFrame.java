package Engine.windows;

import Engine.audio.Sound;
import Engine.audio.Source;
import Engine.core.Input;
import Engine.rendering.text.Font;

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
    private WindowManager manager;

//    private Sound ambient;
    private int actKey;
    private UIState onKeyAct;


    public UIFrame() {
        components = new ArrayList<>();
    }

    public void setKeyAct(int key, UIState act){
        actKey = key;
        onKeyAct = act;
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

    public void setManager(WindowManager listener) {
        this.manager = listener;
        for(UIComponent component: components){
            component.setManager(listener);
        }
    }

    public void update(){
        for(UIComponent component: components){
            component.update();
        }
        chekcKey();
    }

    private void alert(UIState state){
        if(manager != null){
            manager.alert(state);
        }
    }

    private void chekcKey(){
        if(onKeyAct != null){
            if(Input.getKey(actKey)){
                alert(onKeyAct);
            }
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
