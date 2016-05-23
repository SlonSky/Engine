package Engine.audio;

import Engine.util.Vector3f;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.util.WaveData;
import org.newdawn.slick.openal.OggData;
import org.newdawn.slick.openal.OggDecoder;

import static org.lwjgl.openal.AL10.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Slon on 17.04.2016.
 */
public class AudioEngine {

    private static ArrayList<Integer> buffers = new ArrayList<>();

//    private static Vec

    public static void init(){
        try {
            AL.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    public static void setListenerData(Vector3f pos){
        alListener3f(AL_POSITION, pos.getX(), pos.getY(), pos.getZ());
        alListener3f(AL_VELOCITY, 0,0,0);
    }


    public static int loadSound(String filename){
        String[] parts = filename.split("\\.");
        if(parts.length == 2) {
            switch (parts[1]) {
                case "wav":
                    return loadWav(filename);
                case "ogg":
                    return loadOgg(filename);
                default:
                    System.out.println("Cannot load sound: " + filename);
                    break;
            }
        } else {
            System.out.println("Wrong filename. Cannot load: " + filename);
        }
        return 0;
    }

    private static int loadWav(String filename){
        int id = alGenBuffers();
        buffers.add(id);
        try {
            WaveData data = WaveData.create(new BufferedInputStream(new FileInputStream(filename)));
            alBufferData(id, data.format, data.data, data.samplerate);
            data.dispose();
            return id;
        } catch (FileNotFoundException e) {
            System.out.println("Failed to load: " + filename);
            e.printStackTrace();
        }
        return 0;
    }


    private static int loadOgg(String filename){
        int id = alGenBuffers();
        buffers.add(id);
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filename));
            OggDecoder decoder = new OggDecoder();
            OggData data= decoder.getData(in);
            alBufferData(id, data.channels > 1 ? AL_FORMAT_STEREO16 : AL_FORMAT_MONO16, data.data, data.rate);
            return id;
        } catch (Exception e) {
            System.out.println("Failed to load: " + filename);
            e.printStackTrace();
        }
        return 0;
    }


    public static void dispose(){
        for(Integer id: buffers){
            alDeleteBuffers(id);
        }
        AL.destroy();
    }


}
