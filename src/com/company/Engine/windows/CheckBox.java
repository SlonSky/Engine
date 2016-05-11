package com.company.Engine.windows;

import com.company.Engine.rendering.guis.GUITexture;
import com.company.Engine.util.Vector2f;

import java.util.ArrayList;

/**
 * Created by Slon on 11.05.2016.
 */
public class CheckBox extends UIComponent {
    private Image rect;
    private Image mark;

    private boolean checked;

    public CheckBox(Vector2f pos, String rectTex, String markTex, boolean checked){
        rect = new Image(pos, new Vector2f(0.05f, 0.05f), rectTex, true);
        mark = new Image(pos, new Vector2f(0.05f, 0.05f), markTex, true);
        this.pos = pos;
        this.size = rect.getGui().getScreenSize();
        this.checked = checked;
    }

    @Override
    public void update() {
        setChecked();
        sound();
//        alert();
    }

    private int a = 0;
    private void setChecked(){
        a = isClicked() ? a+1 : 0;
        if(a == 1) {
            checked = !checked;
            mark.getGui().setHidden(!checked);
        }
    }

    private boolean playClick;

    private void sound(){
        if(isClicked()){
            if(playClick){
                UIFrame.uiSource.play(UIFrame.clickSound.getBufferId(), true);
                playClick = false;
            }
        } else {
            playClick = true;
        }
    }

    @Override
    public ArrayList<UIComponent> getComponents() {
        ArrayList<UIComponent> components = new ArrayList<>();
        components.add(rect);
        components.add(mark);
        return components;
    }

    @Override
    public void addToFrame(UIFrame frame) {
        frame.addComponent(rect);
        frame.addComponent(mark);
    }
}
