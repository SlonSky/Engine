package com.company;

import com.company.Editor.LevelEditor.EditingGame;
import com.company.Editor.LevelEditor.LevelEditor;
import com.company.Editor.LevelEditor.SyncEditor;
import com.company.Engine.audio.AudioEngine;
import com.company.Engine.audio.Source;
import com.company.Engine.core.CoreEngine;
import com.company.Game.TestGame;
import org.lwjgl.Sys;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;

import javax.swing.*;
import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        initGUIEditor();
        initGame();
    }

    private static void initGUIEditor(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SyncEditor();
//                new LevelEditor();
            }
        });
    }

    private static void initGame() {
        CoreEngine core = new CoreEngine(800, 600, 60,
//                new UserGame()
                new TestGame()
//                new EditingGame()
//                new MeshEditorGame()
        );
        core.createWindow("game", false);
        core.start();
    }
}
