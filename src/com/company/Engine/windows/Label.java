package com.company.Engine.windows;

import com.company.Engine.rendering.guis.GUITexture;
import com.company.Engine.rendering.meshManagment.Texture;
import com.company.Engine.rendering.text.Text;
import com.company.Engine.util.Vector2f;
import com.company.Engine.util.Vector3f;

/**
 * Created by Slon on 08.05.2016.
 */
public class Label extends UIComponent {

    private Text text;

    public Label(Vector2f pos, float size, String text, Vector3f color){
        this.text = new Text(pos.getX(), pos.getY(), size, text, UIFrame.font, color);
        this.size = this.text.getScreenScale();
        this.pos = this.text.getScreenPos();
    }

    public void setColor(Vector3f color){
        text.setColor(color);
    }

    @Override
    public Text getText() {
        return text;
    }
}
