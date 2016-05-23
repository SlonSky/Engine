package Game;

import Engine.core.Input;
import Engine.core.Window;
import Engine.util.Quaternion;
import Engine.util.Vector2f;
import Engine.util.Vector3f;
import Game.GameComponent;

/**
 * Created by Slon on 21.03.2016.
 */
public class MouseControl extends GameComponent {
    private static final Vector3f Y_AXIS = new Vector3f(0,1,0);
    private static final Vector2f CENTER_POS = new Vector2f(Window.getWidth()/2, Window.getHeight()/2);

    private int shootKey;

    private boolean shooting;

    private float sensitivity;

    public MouseControl(int lockKey, float sensitivity) {
        this.shootKey = lockKey;
        this.sensitivity = sensitivity;

        shooting = false;
        Input.setCursor(false);
    }

    public boolean isShooting() {
        return shooting;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

    public void input(){

        if(Input.getMouse(shootKey)){
            shooting = true;
        }
        if(Input.getMouseUp(shootKey)){
            shooting = false;
        }

            Vector2f deltaPos = Input.getMousePosition().sub(CENTER_POS);
            boolean rotX = deltaPos.getY() != 0;
            boolean rotY = deltaPos.getX() != 0;

            if(rotX){
                rotateX(-deltaPos.getY() * sensitivity);
            }
            if(rotY){
                rotateY(deltaPos.getX() * sensitivity);
            }
            if(rotX || rotY){
                Input.setMousePosition(CENTER_POS);
            }

    }

    private void rotateY(float angle){
        getTransform().setRotation(
                    (new Quaternion(Y_AXIS, (float) Math.toRadians(angle))).mul(getTransform().getRotation()).normalized());
    }

    private void rotateX(float angle){
        Quaternion rot = new Quaternion(getTransform().getRotation().getRight(),
                (float) Math.toRadians(angle)).mul(getTransform().getRotation()).normalized();
        float yNew = rot.getForward().normalized().getY();
        if(yNew > -0.8 && yNew < 0.8) {
            getTransform().setRotation(rot);
        }
    }

}
