package Engine.rendering.text;

import Engine.rendering.meshManagment.Texture;
import org.lwjgl.opengl.Display;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.*;
import java.lang.Character;
import java.util.HashMap;

/**
 * Created by Slon on 27.03.2016.<br>
 *
 * The <code>Font</code> class represents fonts, which are used to
 * render text in a visible way.
 * Can be made from the <a href="https://github.com/libgdx/libgdx/wiki/Hiero">Hiero</a>
 * built font (.fnt font file and .png font texture atlas).
 *
 */


public class Font {

    private Texture atlas;
    private float atlasWidth;
    private float atlasHeight;
    private float lineHeight;
    private float size;
    private HashMap<Character, AtlasChar> characters;

    /**
     * Creates a new <code>Font</code> from existing
     * texture atlas and font file
     * @param texAtlasName name of texture atlas PNG file
     * @param fntFileName name of font file
     */
    public Font(String texAtlasName, String fntFileName){
        atlas = new Texture(texAtlasName);
        characters = new HashMap<>();
        loadFont("res/fonts/" + fntFileName);
    }


    private void loadFont(String filename){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = reader.readLine()) != null){
                String[] tokens = line.split(" ");
                if(tokens[0].equals("info")){
                    initFontSize(tokens);
                }
                if(tokens[0].equals("common")){
                    initCommon(tokens);
                }
                if(tokens[0].equals("char")){
                    addCharacter(tokens);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initFontSize(String[] tokens){
        for(String token: tokens){
            if(token.startsWith("size")){
                size = Float.parseFloat(token.split("=")[1]);
            }
        }
    }

    private void initCommon(String[] tokens){
        for(String token: tokens){
            if(token.startsWith("lineHeight")){
                lineHeight = Float.parseFloat(token.split("=")[1])/ Display.getHeight();
            } else if(token.startsWith("scaleW")){
                atlasWidth = Integer.parseInt(token.split("=")[1]);
            } else if(token.startsWith("scaleH")){
                atlasHeight = Integer.parseInt(token.split("=")[1]);
            }
        }
    }

    private void addCharacter(String[] tokens){
        int id = 0;
        float x=0, y=0, width=0, height=0, xOffset=0, yOffset=0, xAdvance=0;
        for(String token: tokens){
            if(token.startsWith("id")){
                id = Integer.parseInt(token.split("=")[1]);
            } else if(token.startsWith("xoffset")){
                xOffset = Integer.parseInt(token.split("=")[1])/atlasWidth;
            } else if(token.startsWith("xadvance")){
                xAdvance = Integer.parseInt(token.split("=")[1])/atlasWidth;
            }  else if(token.startsWith("x")){
                x = Integer.parseInt(token.split("=")[1])/atlasWidth;
            } else if(token.startsWith("yoffset")){
                yOffset = Integer.parseInt(token.split("=")[1])/atlasHeight;
            } else if(token.startsWith("y")){
                y = Integer.parseInt(token.split("=")[1])/atlasHeight;
            } else if(token.startsWith("width")){
                width = Integer.parseInt(token.split("=")[1]);
            }  else if(token.startsWith("height")){
                height = Integer.parseInt(token.split("=")[1]);
            }
        }
        characters.put((char)id, new AtlasChar(id, x, y, width, height, xOffset, yOffset, xAdvance));
    }

    /**
     * Gets texture atlas of the current font
     * @return texture atlas
     */
    public Texture getAtlas() {
        return atlas;
    }

    /**
     * Gets existing characters described in font in hash map
     * form (key - default Character, value - {@link AtlasChar})
     * @return hash map Character-{@link AtlasChar}
     */
    public HashMap<Character, AtlasChar> getCharacters() {
        return characters;
    }

    /**
     * Gets font texture atlas width
     * @return atlas width
     */
    public float getAtlasWidth() {
        return atlasWidth;
    }

    /**
     * Gets font texture atlas height
     * @return atlas height
     */
    public float getAtlasHeight() {
        return atlasHeight;
    }

    /**
     * Gets default line height declared in font file
     * @return line height in pt
     */
    public float getLineHeight() {
        return lineHeight;
    }

    /**
     * Gets font size declared in font file
     * @return size in pt
     */
    public float getSize() {
        return size;
    }
}
