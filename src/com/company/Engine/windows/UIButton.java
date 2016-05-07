package com.company.Engine.windows;

import com.company.Engine.core.Input;
import com.company.Engine.rendering.meshManagment.Texture;
import com.company.Engine.rendering.text.Font;
import com.company.Engine.util.Vector2f;
import com.company.Engine.util.Vector3f;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;

/**
 * Created by Slon on 04.05.2016.
 */
public class UIButton extends UIComponent{

    public UIButton(Vector2f pos, Vector2f scale, Texture background, String label, float textSize, Font font, Vector3f textColor) {
        super(pos, scale, background, label, textSize, font, textColor);
        this.pos = new Vector2f((pos.getX()+1)/2*Display.getWidth(), (pos.getY()+1)/2*Display.getHeight());
//        this.scale =
    }

    public void update(){
        hover();
    }

    public void hover(){
        if(Math.abs(Input.getMousePosition().getX() - pos.getX()) < 10f &&
                Math.abs(Input.getMousePosition().getY() - pos.getY()) < 10f){
            label.setColor(new Vector3f(0, 0, 1));
        } else {
            label.setColor(new Vector3f(0,1,0));
        }

    }
}
