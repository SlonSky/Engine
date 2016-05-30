package Engine.audio;

import Game.GameComponent;
import Engine.util.Vector3f;
import Game.SoundManager;

import static org.lwjgl.openal.AL10.*;

/**
 * Created by Slon on 18.04.2016.
 */
public class Source extends GameComponent{
    private int sourceId;

    public Source(){
        sourceId = alGenSources();
        alSourcef(sourceId, AL_GAIN, 1);
        alSourcef(sourceId, AL_PITCH, 1);
        alSource3f(sourceId, AL_POSITION, 0, 0, 0);
        SoundManager.getInstance().addEffect(this);
    }

    public Source(boolean music){
        sourceId = alGenSources();
        alSourcef(sourceId, AL_GAIN, 1);
        alSourcef(sourceId, AL_PITCH, 1);
        alSource3f(sourceId, AL_POSITION, 0, 0, 0);
        SoundManager.getInstance().addMusic(this);
    }

    public void play(int buffer){
        play(buffer, false);
    }

    public void play(int buffer, boolean once){
        if(once) {
            if(!isPlaying()) {
                alSourcei(sourceId, AL_BUFFER, buffer);
                continuePlaying();
            }
        } else {
            stop();
            alSourcei(sourceId, AL_BUFFER, buffer);
            continuePlaying();
        }

    }



    public void pause(){
        alSourcePause(sourceId);
    }

    public void continuePlaying(){
        alSourcePlay(sourceId);
    }

    public void stop(){
        alSourceStop(sourceId);
    }

    public void delete(){
        alDeleteSources(sourceId);
    }

    public void setVelocity(Vector3f velocity){
        alSource3f(sourceId, AL_POSITION, velocity.getX(), velocity.getY(), velocity.getZ());
    }

    public void setLooping(boolean loop){
        alSourcei(sourceId, AL_LOOPING, loop ? AL_TRUE : AL_FALSE);
    }

    public boolean isPlaying(){
        return alGetSourcei(sourceId, AL_SOURCE_STATE) == AL_PLAYING;
    }

    public void setVolume(float volume){
        alSourcef(sourceId, AL_GAIN, volume);
    }

    public void setPitch(float pitch){
        alSourcef(sourceId, AL_PITCH, pitch);
    }

    public void setPosition(Vector3f pos){
        alSource3f(sourceId, AL_POSITION, pos.getX(), pos.getY(), pos.getZ());
    }


}
