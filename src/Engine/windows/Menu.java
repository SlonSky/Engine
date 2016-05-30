package Engine.windows;

import Engine.rendering.guis.GUITexture;
import Engine.util.Vector2f;
import Engine.util.Vector3f;
import Game.GameComponent;

import java.util.ArrayList;

/**
 * Created by Slon on 19.05.2016.
 */
public class Menu extends UIComponent{

    private Image background;
    private ArrayList<Button> buttons;

    public Menu(Vector2f pos, Vector2f size, String background){
        this.background = new Image(pos, size, background, false);
        buttons = new ArrayList<>();
    }

    public void addButton(Button button){
        buttons.add(button);
    }

//    @Override
//    public GUITexture getGui() {
//        return background.getGui();
//    }

    @Override
    public ArrayList<UIComponent> getComponents() {
        ArrayList<UIComponent> components = new ArrayList<>();
        components.add(background);
        components.addAll(buttons);
        return components;
    }

    @Override
    public void addToFrame(UIFrame frame) {
        frame.addComponent(background);
        for(Button b: buttons){
            frame.addComponent(b);
        }
    }
}
