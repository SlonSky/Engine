package Engine.windows;

import Engine.rendering.guis.GUITexture;
import Engine.rendering.meshManagment.Texture;
import Engine.util.Vector2f;

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
