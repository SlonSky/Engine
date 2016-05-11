package com.company.Engine.windows;

import com.company.Engine.core.Input;
import com.company.Engine.core.Time;
import com.company.Engine.rendering.guis.GUITexture;
import com.company.Engine.util.Vector2f;
import com.company.Engine.util.Vector4f;

/**
 * Created by Slon on 09.05.2016.
 */
public class BlinkImage extends Image{

    private double time = 0;
    private double blinkTime;

    private UIState actState;

    public BlinkImage(Vector2f pos, Vector2f size, String image, boolean quad, double blinkTime) {
        super(pos, size, image, quad);
        this.blinkTime = blinkTime;
    }

    public void setActState(UIState actState) {
        this.actState = actState;
    }


    @Override
    public void update() {
        super.update();
        time += Time.getDelta();
        blink();
        alert();
    }

    private void alert(){
        if(time >= blinkTime){
            if(listener != null){
                listener.alert(actState);
            }
        }
    }

    private void blink(){
        super.getGui().setMask(new Vector4f(1, 1, 1, (float) Math.sin((time / blinkTime) * Math.PI)));
    }

    @Override
    public GUITexture getGui() {
        return time > blinkTime ? null : super.getGui();
    }
}
