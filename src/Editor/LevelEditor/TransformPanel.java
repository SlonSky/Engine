package Editor.LevelEditor;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by Slon on 05.04.2016.
 */
public class TransformPanel extends JPanel {



    public TransformPanel(){
        super(new FlowLayout(FlowLayout.LEFT));

        JSpinner xPos = new JSpinner(new SpinnerNumberModel(0, -10000.0, 10000.0, 0.5));
        JSpinner yPos = new JSpinner(new SpinnerNumberModel(0, -10000.0, 10000.0, 0.5));
        JSpinner zPos = new JSpinner(new SpinnerNumberModel(0, -10000.0, 10000.0, 0.5));

        JSpinner xRot = new JSpinner(new SpinnerNumberModel(0, -180.0, 180.0, 0.1));
        JSpinner yRot = new JSpinner(new SpinnerNumberModel(0, -180.0, 180.0, 0.1));
        JSpinner zRot = new JSpinner(new SpinnerNumberModel(0, -180.0, 180.0, 0.1));

        JSpinner xScale = new JSpinner(new SpinnerNumberModel(1, -100.0, 100.0, 0.1));
        JSpinner yScale = new JSpinner(new SpinnerNumberModel(1, -100.0, 100.0, 0.1));
        JSpinner zScale = new JSpinner(new SpinnerNumberModel(1, -100.0, 100.0, 0.1));

        xPos.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

            }
        });


        JPanel pos = new JPanel();
        pos.setLayout(new BoxLayout(pos, BoxLayout.Y_AXIS));
        pos.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Position"));
        pos.setSize(200, 200);
        pos.add(new Label("x"));
        pos.add(xPos);
        pos.add(new Label("y"));
        pos.add(yPos);
        pos.add(new Label("z"));
        pos.add(zPos);
        add(pos);

        JPanel rot = new JPanel();
        rot.setLayout(new BoxLayout(rot, BoxLayout.Y_AXIS));
        rot.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Rotation"));
        rot.add(new Label("x"));
        rot.add(xRot);
        rot.add(new Label("y"));
        rot.add(yRot);
        rot.add(new Label("z"));
        rot.add(zRot);
        add(rot);

        JPanel scale = new JPanel();
        scale.setLayout(new BoxLayout(scale, BoxLayout.Y_AXIS));
        scale.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Scale"));
        scale.add(new Label("x"));
        scale.add(xScale);
        scale.add(new Label("y"));
        scale.add(yScale);
        scale.add(new Label("z"));
        scale.add(zScale);
        add(scale);

//        add(new Label("x"));
//        add(xRot);
//        add(new Label("y"));
//        add(yRot);
//        add(new Label("z"));
//        add(zRot);

    }
}
