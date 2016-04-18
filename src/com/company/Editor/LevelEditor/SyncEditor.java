package com.company.Editor.LevelEditor;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

/**
 * Created by Slon on 27.03.2016.
 */
public class SyncEditor {
    private JFrame frame;

    public static int a = 0;
    public static int b = 0;
    public static int c = 0;

    public static int x = 0;
    public static int y = 0;
    public static int z = 0;

    public static boolean s = false;

    public SyncEditor(){
        frame = new JFrame("Post Mortem editor");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setLayout(new GridLayout(2, 4));
        frame.setBounds(new Rectangle(100, 800));

        JScrollBar xBar = new JScrollBar(Adjustable.VERTICAL, 0, 0, -100, 100);
        JScrollBar yBar = new JScrollBar(Adjustable.VERTICAL, 0, 0, -100, 100);
        JScrollBar zBar = new JScrollBar(Adjustable.VERTICAL, 0, 0, -100, 100);
//        xBar
        xBar.addAdjustmentListener(e -> a = e.getValue());
        yBar.addAdjustmentListener(e -> b = e.getValue());
        zBar.addAdjustmentListener(e -> c = e.getValue());

        JScrollBar xrBar = new JScrollBar(Adjustable.VERTICAL, 0, 0, 0, 100);
        JScrollBar yrBar = new JScrollBar(Adjustable.VERTICAL, 0, 0, -100, 100);
        JScrollBar zrBar = new JScrollBar(Adjustable.VERTICAL, 0, 0, -100, 100);
//        xBar
        xrBar.addAdjustmentListener(e -> x = e.getValue());
        yrBar.addAdjustmentListener(e -> y = e.getValue());
        zrBar.addAdjustmentListener(e -> z = e.getValue());

        frame.add(xBar);
        frame.add(yBar);
        frame.add(zBar);

        frame.add(xrBar);
        frame.add(yrBar);
        frame.add(zrBar);

        JButton b = new JButton("l");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s = !s;
            }
        });
frame.add(b);
        frame.pack();
        frame.setVisible(true);
    }
}
