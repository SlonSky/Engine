package Engine.windows;

import Engine.util.Vector2f;
import Engine.util.Vector3f;

/**
 * Created by Slon on 10.05.2016.
 */
public class Icon extends Image {

    private Vector3f defaultScale;

    public Icon(Vector2f pos, Vector2f size, String image, boolean quad) {
        super(pos, size, image, quad);
        defaultScale = getGui().getTransform().getScale();
    }

    private boolean playClick = false;
    private boolean playHover = false;

    // todo: listener, messages

    @Override
    public void update() {
        super.update();
        scaleOnHover();
        sound();
        alert();
    }

    private void alert(){
        if(isReleased()){
            // todo
        }
    }

    private void scaleOnHover(){
        if(isHover()){
            getGui().getTransform().setScale(defaultScale.mul(1.1f));
        } else {
            getGui().getTransform().setScale(defaultScale);
        }
    }

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
}
