package Game;

import Engine.audio.Sound;
import Engine.audio.Source;
import Engine.windows.ValueHandler;

import java.util.ArrayList;

/**
 * Created by Slon on 30.05.2016.
 */
public class SoundManager {
    private static SoundManager instance = new SoundManager();

    private static ArrayList<Source> effects;
    private static ArrayList<Source> music;

    private float effectsVolume;
    private float musicVolume;

    private ValueHandler effectsVolumeHandler;
    private ValueHandler musicVolumeHandler;

    public static SoundManager getInstance() {
        return instance;
    }

    private SoundManager(){
        effects = new ArrayList<>();
        music = new ArrayList<>();
        effectsVolumeHandler = new ValueHandler();
        musicVolumeHandler = new ValueHandler();
    }

    public void addEffect(Source source){
        effects.add(source);
    }

    public void addMusic(Source source){
        music.add(source);
    }

    public void update(){
        effectsVolume = effectsVolumeHandler.getValue()/100f;
        musicVolume = musicVolumeHandler.getValue()/100f;
        for(Source source: effects){
            source.setVolume(effectsVolume);
        }
        for(Source source: music){
            source.setVolume(musicVolume);
        }
    }

    public ValueHandler getEffectsVolumeHandler() {
        return effectsVolumeHandler;
    }

    public ValueHandler getMusicVolumeHandler() {
        return musicVolumeHandler;
    }
}
