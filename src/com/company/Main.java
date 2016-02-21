package com.company;

import com.company.forEngine.core.CoreEngine;
import com.company.forEngine.Game;

public class Main {

    public static void main(String[] args) {
         CoreEngine c = new CoreEngine(800, 600, 60, new Game());
        c.createWindow("game", false);
        c.start();
    }
}
