package com.company;

import com.company.Engine.core.CoreEngine;
import com.company.Game.Game;

public class Main {

    public static void main(String[] args) {
         CoreEngine c = new CoreEngine(800, 600, 60, new Game());
        c.createWindow("game", true);
        c.start();
    }
}
