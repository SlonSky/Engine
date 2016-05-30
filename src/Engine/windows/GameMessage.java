package Engine.windows;

import java.util.ArrayList;

/**
 * Created by Slon on 30.05.2016.
 */
public class GameMessage extends UIComponent {

    private Image background;
    private ArrayList<Label> messages;
    private ArrayList<Button> buttons;

    public GameMessage(Image background) {
        this.background = background;
        this.messages = new ArrayList<>();
        this.buttons = new ArrayList<>();
    }

    @Override
    public void addToFrame(UIFrame frame) {
        frame.addComponent(background);
        for(Label message: messages) {
            frame.addComponent(message);
        }
        for(Button button: buttons) {
            frame.addComponent(button);
        }
    }

    @Override
    public ArrayList<UIComponent> getComponents() {
        ArrayList<UIComponent> components = new ArrayList<>();
        components.add(background);
        for(Label message: messages) {
            components.add(message);
        }
        for(Button button: buttons) {
            components.add(button);
        }
        return components;
    }


    public void addButton(Button button){
        buttons.add(button);
    }
    public void addMessage(Label label){
        messages.add(label);
    }
}
