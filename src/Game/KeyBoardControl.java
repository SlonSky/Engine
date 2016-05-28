package Game;

import Engine.core.Input;
import Engine.core.Time;
import Engine.util.Vector3f;

/**
 * Created by Slon on 21.03.2016.
 */
public class KeyBoardControl extends GameComponent {
    private static final Vector3f Y_AXIS = new Vector3f(0,1,0);
    private static final float JUMP_HEIGHT = 14;

    private int forward;
    private int backward;
    private int left;
    private int right;
    private int reloadButton;
    private int jumpButton;

    private float speed;

    private Vector3f movementVector;

    private boolean reloading;
    private boolean moving;

//    private boolean shoot;

    public KeyBoardControl(int forward, int backward, int left, int right, int reload, int jump, float speed) {
        this.forward = forward;
        this.backward = backward;
        this.left = left;
        this.right = right;
        this.speed = speed;
        this.jumpButton = jump;

        //todo: WARNING!
        this.reloadButton = reload;

        reloading = false;
        moving = false;
    }

    public void input(){
        float movAmt = (float)(speed * Time.getDelta());
        Vector3f rightVec =  getTransform().getRotation().getRight().normalized();
        moving = false;
        if(Input.getKey(forward)){
            movementVector.set(movementVector.add(getTranslation(rightVec.cross(Y_AXIS), movAmt)));
            moving = true;
        }
        if(Input.getKey(backward)){
            movementVector.set(movementVector.add(getTranslation(rightVec.cross(Y_AXIS), -movAmt)));
            moving = true;
        }
        if(Input.getKey(right)){
            movementVector.set(movementVector.add(getTranslation(rightVec, movAmt)));
            moving = true;
        }
        if(Input.getKey(left)){
            movementVector.set(movementVector.add(getTranslation(rightVec, -movAmt)));
            moving = true;
        }
        if(Input.getKeyDown(reloadButton)){
            reloading = true;
        }

        // todo: move to Jumping with onGround checks
        if(Input.getKeyDown(jumpButton)){
            movementVector.set(movementVector.add(getTranslation(Y_AXIS, JUMP_HEIGHT * movAmt)));
            moving = true;
//            jumping = true;
        }

        // template!
//        if(Input.getKeyDown(Input.KEY_U)){
//            movementVector.set(movementVector.add(getTranslation(Y_AXIS, 9 * movAmt)));
//            moving = true;
//        }
//
//        if(Input.getKey(Input.KEY_J)){
//            movementVector.set(movementVector.add(getTranslation(Y_AXIS, -movAmt)));
//            moving = true;
//        }

        if(!moving){
            movementVector = new Vector3f(0,0,0);
        }

    }

    @Override
    public void update() {
        super.update();

//        move(movementVector);
//        updateState();
        movementVector = new Vector3f(0,0,0);
    }

//    private void updateState(){
//        jump = (onGround && !jump && movementVector.getY() > 0);
//        onGround = movementVector.getY() == 0;
//    }

    private void move(Vector3f dir, float amt){
        getTransform().setPosition(getTransform().getPosition().add(dir.mul(amt)));
    }

    private void move(Vector3f translation){
        getTransform().setPosition(getTransform().getPosition().add(translation));
    }

    private Vector3f getTranslation(Vector3f dir, float amt){
        return (dir.mul(amt));
    }

    public Vector3f getMovementVector() {
        return movementVector;
    }

    public void setMovementVector(Vector3f movementVector) {
        this.movementVector = movementVector;
    }

    @Override
    public void setParent(GameObject parent) {
        super.setParent(parent);
        movementVector = getTransform().getPosition();

    }

    public boolean isReloading() {
        return reloading;
    }

    public void setReloading(boolean reload) {
        this.reloading = reload;
    }

    public boolean isMoving() {
        return moving;
    }

}
