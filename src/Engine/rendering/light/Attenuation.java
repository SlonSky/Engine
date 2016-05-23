package Engine.rendering.light;

import Engine.util.Vector3f;

/**
 * Created by Slon on 15.02.2016.
 */
public class Attenuation extends Vector3f{

    private float range;
    public Attenuation(float constant, float linear, float exponent) {
        super(constant, linear, exponent);
    }

    public float getConstant(){
        return getX();
    }

    public float getLinear(){
        return getY();
    }

    public float getExponent(){
        return getZ();
    }
}
