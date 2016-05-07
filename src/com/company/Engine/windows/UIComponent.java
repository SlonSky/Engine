package com.company.Engine.windows;

import com.company.Engine.rendering.guis.GUITexture;
import com.company.Engine.rendering.meshManagment.Texture;
import com.company.Engine.rendering.text.Font;
import com.company.Engine.rendering.text.Text;
import com.company.Engine.util.Vector2f;
import com.company.Engine.util.Vector3f;
import com.company.Game.TestGame;

/**
 * Created by Slon on 25.04.2016.
 */
public abstract class UIComponent {
//    private Vector2f pos;

    protected Text label;
    protected GUITexture gui;
    protected Vector2f pos;
    protected Vector2f scale;
    public UIComponent(Vector2f pos, Vector2f scale, Texture background, String label, float textSize, Font font, Vector3f textColor) {
        this.pos = pos;
//        this.scale = scale;
        this.label = new Text(pos.getX(), pos.getY(), textSize, label, font, textColor);
        this.gui = new GUITexture(background, pos, scale);
    }

    public Text getText(){
        return label;
    }

    public GUITexture getGui(){
        return gui;
    }
}
