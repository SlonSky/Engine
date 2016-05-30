package Engine.windows;

import Engine.rendering.guis.GUITexture;
import Engine.rendering.meshManagment.Texture;
import Engine.rendering.text.Text;
import Engine.util.Vector2f;
import Game.Initializer;
import Game.Level;

import java.util.ArrayList;

/**
 * Created by Slon on 24.05.2016.
 */
public class LevelIcon extends Icon{

    private GUITexture lockedImage;
    private boolean locked;

    private int levelID;

    public LevelIcon(Vector2f pos, Vector2f size, String unlockedImage, String lockedImage, boolean quad, boolean locked, int levelID){
        super(pos, size, unlockedImage, quad);
        this.lockedImage = new GUITexture(new Texture(lockedImage), pos, size, quad);
        this.locked = locked;
        this.levelID = levelID;
    }

    @Override
    public void update() {
        super.update();
        if (isClicked() && !locked){
            Initializer.getInstance().setLevelToLoad(levelID);
            manager.alert(UIState.LOADING);
        }
    }

    @Override
    public void roll(float delta, int axis) {
        super.roll(delta, axis);

//        if(!locked){

//        }

    }

//    private void chngState(){
//        if(isReleased()){
//            locked = !locked;
//        }
//        // temp
//        if (!locked){
//            lockedImage.move(new Vector2f(axis == X_AXIS ? delta : 0, axis == Y_AXIS ? delta : 0));
//        } else {
//            super.getGui().move(new Vector2f(axis == X_AXIS ? delta : 0, axis == Y_AXIS ? delta : 0));
//        }
//    }

    @Override
    public GUITexture getGui() {
        if(locked){
            return lockedImage;
        }
        return super.getGui();
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
