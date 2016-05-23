package Engine.audio;

import Game.GameComponent;

/**
 * Created by Slon on 17.04.2016.
 */
public class AudioComponent extends GameComponent {

    private Source source;

    public AudioComponent(){
        source = new Source();
    }

    @Override
    public void update() {
        source.setPosition(getTransform().getPosition());
    }

    public void play(int sound){
        source.play(sound);
    }
}
