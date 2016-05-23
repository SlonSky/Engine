package Engine.windows;

import Engine.util.Vector2f;
import Engine.util.Vector3f;

/**
 * Created by Slon on 08.05.2016.
 */
public class Button extends Label {

    private Vector3f mainColor;
    private Vector3f hoverColor;
    private Vector3f clickColor;

    private UIState actState;

    public Button(Vector2f pos, float size, String text, Vector3f mainColor, Vector3f blinkColor, Vector3f clickColor) {
        super(pos, size, text, mainColor);
        this.mainColor = mainColor;
        this.hoverColor = blinkColor;
        this.clickColor = clickColor;
    }

    public void setStateOnClick(UIState state){
        this.actState = state;
    }

    @Override
    public void update() {
        changeColor();
        sound();
        alert();
    }

    private boolean playClick = false;
    private boolean playHover = false;

    private void sound(){
        if(isClicked()){
            if(playClick){
                UIFrame.uiSource.play(UIFrame.clickSound.getBufferId(), true);
                playClick = false;
            }
        } else if(isHover()){
            if(playHover){
                UIFrame.uiSource.play(UIFrame.hoverSound.getBufferId(), true);
                playHover = false;
            }
            playClick = true;
        } else {
            playClick = true;
            playHover = true;
        }
    }

    private void alert(){
        // todo: is it observer?? no
        if(isReleased() && listener != null){
            listener.alert(actState);
        }
    }
    private void changeColor(){
        if(isHolding()) {
            setColor(clickColor);
        } else if(isHover()){
            setColor(hoverColor);
        } else {
            setColor(mainColor);
        }
    }
}
