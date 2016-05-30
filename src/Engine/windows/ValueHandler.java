package Engine.windows;

/**
 * Created by Slon on 30.05.2016.
 */
public class ValueHandler implements Variable{

    private float value;

    @Override
    public void setValue(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }
}
