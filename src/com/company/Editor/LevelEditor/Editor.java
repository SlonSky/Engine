package com.company.Editor.LevelEditor;

import javax.swing.*;

/**
 * Created by Slon on 05.04.2016.
 */
public class Editor {

    private JFrame frame;

    public Editor(){
        init();
        frame.setVisible(true);
    }

    private void init(){
        frame = new JFrame("Post Mortem editor");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setSize(1000, 200);

        // menus
        JMenuBar bar = new JMenuBar();
        bar.add(new JMenu("Menus..."));
        frame.setJMenuBar(bar);

        // add object panel
        JTabbedPane pane = new JTabbedPane();

        pane.addTab("Transform", new TransformPanel());
        pane.addTab("Mesh", new MeshPanel());
        frame.add(pane);
        // transform panel

        frame.pack();

    }
}
