package Engine.rendering.guis;

import Engine.rendering.Transform;
import Engine.rendering.meshManagment.Texture;
import Engine.util.*;
import org.lwjgl.opengl.Display;

/**
 * Created by Slon on 11.04.2016.
 */
public class GUITexture {
    public static final float ASPECT_RATIO = (float)Display.getWidth()/(float)Display.getHeight();
    private Texture texture;
    private Vector4f mask;
    private Transform transform;
    private boolean hidden = false;

    public GUITexture(Texture texture, Vector2f pos, Vector2f scale, boolean quad) {
        this.texture = texture;
        transform = new Transform();
        transform.setPosition(new Vector3f(pos.getX(), pos.getY(), 0));
        transform.setScale(new Vector3f(scale.getX() / (quad ? ASPECT_RATIO : 1), scale.getY(), 1));
        mask = new Vector4f(1,1,1,1);
//        setScale(new Vector2f(scale.getX()*ASPECT_RATIO, scale.getY()/ASPECT_RATIO));
    }

    public GUITexture(Texture texture, Vector2f pos, Vector2f scale) {
        this(texture, pos, scale, true);
    }

    public void move(Vector2f delta){
        transform.setPosition(transform.getPosition().add(new Vector3f(delta.getX(), delta.getY(), 0)));
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setPos(Vector2f pos) {
        transform.setPosition(new Vector3f(pos.getX(), pos.getY(), 0));
    }

    public void setScale(Vector2f scale) {
        transform.setScale(new Vector3f(scale.getX(), scale.getY(), 1));
    }

    public Transform getTransform() {
        return transform;
    }

    public Vector2f getTextureSize(){
        return texture.getSize();
    }

    public Vector2f getScreenSize(){
      return transform.getScale().getXY();
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public Vector4f getMask() {
        return mask;
    }

    public void setMask(Vector4f mask) {
        this.mask = mask;
    }
}
