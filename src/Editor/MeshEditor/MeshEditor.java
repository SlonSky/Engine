package Editor.MeshEditor;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Slon on 10.04.2016.
 */
public class MeshEditor {

    private JFrame frame;

    private int id;

    public MeshEditor(){
        init();
        frame.setVisible(true);
    }

    private void init(){
        frame = new JFrame("Post Mortem Mesh LevelEditor");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setSize(1000, 200);

        /**
         * panel
         * set id
         * set/edit culling box
         * set/edit bounding box
         * edit texture
         */

        JPanel mainPanel = new JPanel(new FlowLayout());

        JTextField id = new JTextField("id");

        frame.pack();

    }
}
