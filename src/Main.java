import Editor.LevelEditor.SyncEditor;
import Engine.core.CoreEngine;
import Game.Initializer;
import Game.games.TestGame;
import Game.games.WindowedGame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        initGUIEditor();
        initGame();

        while (true){
            System.out.println(Engine.core.Input.getKeyDown(0) + " down");
            System.out.println(Engine.core.Input.getKeyUp(0) + " up");
            if(Engine.core.Input.getKeyDown(Engine.core.Input.KEY_E)){
                break;
            }
        }
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
//                new TestGame()
//                new DemoGame()
//                new EditingGame()
                new WindowedGame()
//                new MeshEditorGame()
        );
        core.createWindow("game",
                true
//                false
        );
        core.start();
    }
}
