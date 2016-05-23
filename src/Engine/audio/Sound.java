package Engine.audio;

/**
 * Created by Slon on 19.04.2016.
 */
public class Sound {
    private int bufferId;

    public Sound(){
        bufferId = 0;
    }

    public Sound(String filename){
        bufferId = AudioEngine.loadSound(filename);
    }

    public int getBufferId() {
        return bufferId;
    }
}
