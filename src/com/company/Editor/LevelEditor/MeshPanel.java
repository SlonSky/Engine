package com.company.Editor.LevelEditor;

import javax.swing.*;

/**
 * Created by Slon on 05.04.2016.
 */
public class MeshPanel extends JPanel {
    public MeshPanel(){
        super();

        JList meshList = new JList();
        add(meshList);
        add(new JButton("add mesh"));

    }
}
