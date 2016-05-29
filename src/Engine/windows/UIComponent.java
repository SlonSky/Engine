package Engine.windows;

import Engine.core.Input;
import Engine.core.Window;
import Engine.rendering.guis.GUITexture;
import Engine.rendering.text.Text;
import Engine.util.Vector2f;

import java.util.ArrayList;

/**
 * Created by Slon on 25.04.2016.
 */
public abstract class UIComponent {
    public static final int Y_AXIS = 1;
    public static final int X_AXIS = 2;

    protected Vector2f pos;
    protected Vector2f size;
    protected WindowManager manager;

    public void update(){}

    public boolean isHover(){
        float difX = Math.abs(Input.getMousePosition().getX()/ Window.getWidth()*2-1 - pos.getX());
        float difY = Math.abs(Input.getMousePosition().getY()/ Window.getHeight()*2-1 - pos.getY());
        return difX <= size.getX() && difY <= size.getY();
    }

    public boolean isHolding(){
        return Input.getMouse(0) && isHover();
    }

    public boolean isClicked(){
        return Input.getMouseDown(0) && isHover();
    }

    public boolean isReleased(){
        return Input.getMouseUp(0) && isHover();
    }

    public void roll(float delta, int axis){
        Vector2f incr = new Vector2f(axis == X_AXIS ? delta : 0, axis == Y_AXIS ? delta : 0);
        Text text = getText();
        GUITexture gui = getGui();

        if(text != null){
            text.move(incr);
        }
        if(gui != null){
            gui.move(incr);
        }
        pos = pos.add(incr);
    }


    public void setManager(WindowManager listener) {
        this.manager = listener;
    }

    public Text getText(){
        return null;
    }
    public GUITexture getGui(){
        return null;
    }
    public void addToFrame(UIFrame frame){}
    public ArrayList<UIComponent> getComponents(){
        return null;
    }
}
