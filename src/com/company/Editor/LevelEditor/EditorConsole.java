package com.company.Editor.LevelEditor;

import com.company.Engine.core.CoreEngine;
import com.company.Engine.util.Vector3f;
import com.company.Game.TestGame;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * Created by Slon on 07.04.2016.
 */
public class EditorConsole {

    private CoreEngine engine;
    private Scanner scanner;

    public static String name = "";
    public static Vector3f p = new Vector3f(0,0,0);

    public EditorConsole(
            CoreEngine engine
    ){
        this.engine = engine;
        scanner = new Scanner(System.in);

        read();
        System.out.println("Console closed.");
    }

    private void read(){
        String s = "";
        System.out.println("Console started:");
        do{
            s = scanner.nextLine();
//            mes = s;
            if(s.startsWith("add")){
                String[] str =s.split(" ");
                if(str.length == 5) {
                    name = str[1];
                    try {
                        p = new Vector3f(Integer.parseInt(str[2]), Integer.parseInt(str[3]), Integer.parseInt(str[4]));
                    } catch (NumberFormatException e) {
                        System.err.println("wrong command");
                    }
                } else {
                    System.err.println("wrong command");
                }

            }
//            readCommand(s);
        } while (!s.equals("exit"));

    }


    // todo: threads messaging
//    private void readCommand(String s){
//        System.out.print(s);
//        switch (s){
//            case "new":
//                engine.getGame().doTemp(); break;
//        }
//    }
}
