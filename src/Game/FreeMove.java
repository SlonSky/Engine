package Game;

import Engine.core.Input;
import Engine.core.Time;
import Engine.util.Vector3f;
import Game.GameComponent;
import Game.GameObject;

/**
 * Created by Slon on 03.04.2016.
 */
public class FreeMove extends GameComponent {

    private static final Vector3f Y_AXIS = new Vector3f(0,1,0);
    private int forward;
    private int backward;
    private int left;
    private int right;
    private float speed;

    private Vector3f translation;

    public FreeMove(int forward, int backward, int left, int right, float speed) {
        this.forward = forward;
        this.backward = backward;
        this.left = left;
        this.right = right;
        this.speed = speed;

//        translation = new Vector3f(0,2,0);
    }

    public void input(){
        float movAmt = (float)(speed * Time.getDelta());
//        translation = getTransform().getPosition();
//        translation = new Vector3f(0,0,0);

        if(Input.getKeyDown(Input.KEY_LSHIFT)){
            movAmt *= 10;
        }
        if(Input.getKey(forward)){
            translation.set(translation.add(getTranslation(
                    getTransform().getRotation().getForward().normalized(), movAmt)));
        }
        if(Input.getKey(backward)){
            translation.set(translation.add(getTranslation(getTransform().getRotation().getForward().normalized(), -movAmt)));
        }
        if(Input.getKey(right)){
            translation.set(translation.add(getTranslation(getTransform().getRotation().getRight().normalized(), movAmt)));
        }
        if(Input.getKey(left)){
            translation.set(translation.add(getTranslation(getTransform().getRotation().getLeft().normalized(), movAmt)));
        }
        // template!
        if(Input.getKey(Input.KEY_U)){
            translation.set(translation.add(getTranslation(Y_AXIS, 9*movAmt)));
        }

        if(Input.getKey(Input.KEY_J)){
            translation.set(translation.add(getTranslation(Y_AXIS, -movAmt)));
        }
    }

    @Override
    public void update() {
        super.update();

        move(translation);
        translation = new Vector3f(0,0,0);
    }

    private void move(Vector3f dir, float amt){
        getTransform().setPosition(getTransform().getPosition().add(dir.mul(amt)));
    }

    private void move(Vector3f translation){
        getTransform().setPosition(getTransform().getPosition().add(translation));
    }

    private Vector3f getTranslation(Vector3f dir, float amt){
        return (dir.mul(amt));
    }

    public Vector3f getTranslation() {
        return translation;
    }

    public void setTranslation(Vector3f translation) {
        this.translation = translation;
    }

    @Override
    public void setParent(GameObject parent) {
        super.setParent(parent);
        translation = getTransform().getPosition();

    }
}

