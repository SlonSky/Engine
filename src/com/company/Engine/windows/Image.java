package com.company.Engine.windows;

import com.company.Editor.LevelEditor.SyncEditor;
import com.company.Engine.rendering.guis.GUITexture;
import com.company.Engine.rendering.meshManagment.Texture;
import com.company.Engine.rendering.text.Text;
import com.company.Engine.util.Vector2f;
import com.company.Engine.util.Vector4f;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;

/**
 * Created by Slon on 08.05.2016.
 */
public class Image extends UIComponent {

    private GUITexture image;

    public Image(Vector2f pos, Vector2f size, String image, boolean quad){
        this.image = new GUITexture(new Texture(image), pos, size, quad);
        this.pos = pos;
        this.size = this.image.getScreenSize();
    }

    @Override
    public GUITexture getGui() {
        return image;
    }

}
