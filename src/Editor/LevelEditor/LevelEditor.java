package Editor.LevelEditor;

import org.lwjgl.opengl.Display;

import javax.swing.*;

/**
 * Created by Slon on 05.04.2016.
 */
public class LevelEditor {

    private JFrame frame;

    public LevelEditor(){
        init();
        frame.setVisible(true);
//        Window.setLocation(300, 100); todo?
    }

    private void init(){
        frame = new JFrame("Post Mortem editor");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setSize(1000, 200);
        // menus


        // add object panel
        JTabbedPane pane = new JTabbedPane();

        pane.addTab("Transform", new TransformPanel());
        pane.addTab("Mesh", new MeshPanel());
        frame.add(pane);
        // transform panel

        JMenuBar bar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem save = new JMenuItem("Save");
        file.add(open);
        file.add(load);
        file.add(new JSeparator());
        file.add(save);
        bar.add(file);

        JMenu tools = new JMenu("Tools");
        JMenuItem loadMesh = new JMenuItem("Add mesh");
//        JMenuItem loadTexture = new JMenuItem("Load texture");

        tools.add(loadMesh);
//        tools.add(loadTexture);
        bar.add(tools);



        frame.setJMenuBar(bar);

        frame.pack();
        frame.setLocation(0, Display.getY());

    }
}
