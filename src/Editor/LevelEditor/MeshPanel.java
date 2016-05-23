package Editor.LevelEditor;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by Slon on 05.04.2016.
 */
public class MeshPanel extends JPanel {
    public MeshPanel(){
        super();

        // bound box
        // frustum box
        // save -> add to list

        // todo: components behaviour!

        JSpinner xBound = new JSpinner(new SpinnerNumberModel(0, -10000.0, 10000.0, 0.5));
        JSpinner yBound = new JSpinner(new SpinnerNumberModel(0, -10000.0, 10000.0, 0.5));
        JSpinner zBound = new JSpinner(new SpinnerNumberModel(0, -10000.0, 10000.0, 0.5));


        JSpinner xCulling = new JSpinner(new SpinnerNumberModel(0, -10000.0, 10000.0, 0.5));
        JSpinner yCulling = new JSpinner(new SpinnerNumberModel(0, -10000.0, 10000.0, 0.5));
        JSpinner zCulling = new JSpinner(new SpinnerNumberModel(0, -10000.0, 10000.0, 0.5));

        JPanel collider = new JPanel();
        collider.setLayout(new BoxLayout(collider, BoxLayout.Y_AXIS));
        collider.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Collider"));
        collider.setSize(200, 200);
        collider.add(new Label("x"));
        collider.add(xBound);
        collider.add(new Label("y"));
        collider.add(yBound);
        collider.add(new Label("z"));
        collider.add(zBound);
        add(collider);


        JPanel cullingBox = new JPanel();
        cullingBox.setLayout(new BoxLayout(cullingBox, BoxLayout.Y_AXIS));
        cullingBox.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Culling box"));
        cullingBox.setSize(200, 200);
        cullingBox.add(new Label("x"));
        cullingBox.add(xCulling);
        cullingBox.add(new Label("y"));
        cullingBox.add(yCulling);
        cullingBox.add(new Label("z"));
        cullingBox.add(zCulling);
        add(cullingBox);

        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.add(new JCheckBox("collider"));
        left.add(new JCheckBox("culling"));
        left.add(new JButton("add mesh"));
        left.add(new JButton("save"));
        add(left);
    }
}
