package Engine.windows;

import Engine.core.Input;

import java.util.ArrayList;

/**
 * Created by Slon on 10.05.2016.
 */
public class RollPanel extends UIComponent {
    private ArrayList<UIComponent> components;
    private float rollDelta;
    private int axis;

    private float deltaAmount = 0;
    private float minDelta;
    private float maxDelta;

    public RollPanel(float rollDelta, int axis, float minDelta, float maxDelta){
        components = new ArrayList<>();
        this.rollDelta = rollDelta;
        this.axis = axis;
        this.minDelta = minDelta;
        this.maxDelta = maxDelta;
    }

    public void addComponent(UIComponent component){
        components.add(component);
        ArrayList<UIComponent> components = component.getComponents();
        if(components != null){
            for(UIComponent c: components){
                this.components.add(c);
            }
        }
    }

    @Override
    public void update() {
        super.update();
        roll();
    }

    private void roll(){
        float delta = rollDelta * Input.getWheelDelta();
        if(    (deltaAmount > minDelta && deltaAmount < maxDelta)
            || (deltaAmount <= minDelta && delta > 0)
            || (deltaAmount >= maxDelta && delta < 0)){
            deltaAmount += delta;
            for(UIComponent component: components){
                component.roll(delta, axis);
            }
        }
    }

    @Override
    public void addToFrame(UIFrame frame) {
        for(UIComponent component: components){
            frame.addComponent(component);
        }
    }


}
