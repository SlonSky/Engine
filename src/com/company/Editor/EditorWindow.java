package com.company.Editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

/**
 * Created by Slon on 27.03.2016.
 */
public class EditorWindow {
    private JFrame frame;

    public static int a = 0;
    public static int b = 0;
    public static int c = 0;

    public EditorWindow(){
        frame = new JFrame("Post Mortem editor");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setLayout(new GridLayout(1, 2));

        JScrollBar bar1 = new JScrollBar();
        bar1.setMaximum(200);
        bar1.setMinimum(-200);

        bar1.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                a = e.getValue();
                System.out.println("a = " + a);
            }
        });

        JScrollBar bar2 = new JScrollBar();
        bar2.setMaximum(200);
        bar2.setMinimum(-200);

        bar2.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                b = e.getValue();
                System.out.println("b = " + b);
            }
        });

        JScrollBar bar3 = new JScrollBar();
        bar3.setMaximum(100);
        bar3.setMinimum(-100);

        bar3.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                c = e.getValue();
                System.out.println("c = " + c);
            }
        });

        frame.add(bar1);
        frame.add(bar2);
        frame.add(bar3);

        frame.pack();
        frame.setVisible(true);
    }
}
