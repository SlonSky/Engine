package com.company.Engine.windows;

import com.company.Engine.core.Input;
import com.company.Engine.core.Window;
import com.company.Engine.util.Vector2f;

import java.util.ArrayList;

/**
 * Created by Slon on 11.05.2016.
 */
public class ScrollBar extends UIComponent {
    private Image bar;
    private Image slider;

    private float maxValue;
    private float minValue;
    private float value;


    private float maxSliderX;
    private float minSliderX;

    public ScrollBar(Vector2f pos, String barTexture, String sliderTexture, float min, float max, float value){
        bar = new Image(pos, new Vector2f(0.3f, 0.02f), barTexture, false);
        slider = new Image(pos, new Vector2f(0.03f, 0.03f), sliderTexture, true);
        this.minValue = min;
        this.maxValue = max;
        maxSliderX = pos.getX() + bar.getGui().getScreenSize().getX();
        minSliderX = pos.getX() - bar.getGui().getScreenSize().getX();
        this.value = value;
        this.size = slider.getGui().getScreenSize();
        this.size.setX(this.size.getX() * 4f);
        this.pos = slider.pos;
    }

    @Override
    public void update() {
        change();
        sound();
    }

    private void change(){
        if(isClicked()){
            float cursorX = Input.getMousePosition().getX()/(float)Window.getWidth()*2-1;
            setSliderPos(cursorX);
            checkBounds();
            setValue();
        }
    }


    private boolean playClick = false;
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

    private void setValue(){
        value = (pos.getX()-minSliderX)/(maxSliderX-minSliderX)*(maxValue-minValue);
    }

    // todo: scroll blocking on bounds
    private void checkBounds(){
        float sliderPos = pos.getX();
        if(sliderPos < minSliderX){
            pos.setX(minSliderX);
            slider.getGui().setPos(pos);
        } else if(sliderPos > maxSliderX){
            pos.setX(maxSliderX);
            slider.getGui().setPos(pos);
        }
    }

    private void setSliderPos(float posX){
        pos.setX(posX);
        slider.getGui().setPos(pos);
    }

    @Override
    public void addToFrame(UIFrame frame) {
        frame.addComponent(bar);
        frame.addComponent(slider);
    }

    @Override
    public ArrayList<UIComponent> getComponents() {
        ArrayList<UIComponent> components = new ArrayList<>();
        components.add(bar);
        components.add(slider);
        return components;
    }
}
