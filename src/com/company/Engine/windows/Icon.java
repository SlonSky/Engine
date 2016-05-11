package com.company.Engine.windows;

import com.company.Engine.rendering.guis.GUITexture;
import com.company.Engine.util.Vector2f;
import com.company.Engine.util.Vector3f;

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
        if(isClicked()){
            if(UIFrame.uiSource != null && playClick){
                UIFrame.uiSource.play(UIFrame.clickSound.getBufferId(), true);
                playClick = false;
            }
        } else if(isHover()){
            getGui().getTransform().setScale(defaultScale.mul(1.1f));
            if(UIFrame.uiSource != null && playHover){
                UIFrame.uiSource.play(UIFrame.hoverSound.getBufferId(), true);
                playHover = false;
            }
            playClick = true;
        } else {
            getGui().getTransform().setScale(defaultScale);
            playClick = true;
            playHover = true;
        }

    }
}
