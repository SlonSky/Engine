package com.company;

import com.company.Editor.LevelEditor.EditingGame;
import com.company.Editor.LevelEditor.Editor;
import com.company.Editor.LevelEditor.EditorConsole;
import com.company.Editor.LevelEditor.EditorWindow;
import com.company.Engine.core.CoreEngine;
import com.company.Game.Ser;
import com.company.Game.TestGame;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        initGUIEditor();
        initGame();

//        Ser s = new Ser(2);
//        try {
//            FileWriter w = new FileWriter("./ser");
//            ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("./ser")));
//            out.writeObject(s);
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(s.getA() + " next:");
//
//        try {
//            ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("./ser")));
//            s = null;
//            s = (Ser)in.readObject();
//
//            System.out.println(s.getA() + " read");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    private static void initGUIEditor(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EditorWindow();
//                new Editor();
            }
        });
    }

    private static void initGame(){
        CoreEngine core = new CoreEngine(1380, 800, 60,
                new TestGame()
//                new EditingGame()
        );
        core.createWindow("game", false);
        core.start();
    }
}
