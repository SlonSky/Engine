package com.company;

import com.company.Editor.EditorWindow;
import com.company.Engine.core.CoreEngine;
import com.company.Game.Game;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EditorWindow();
            }
        });

         CoreEngine c = new CoreEngine(800, 600, 60, new Game());
        c.createWindow("game", true);
        c.start();
    }
}
