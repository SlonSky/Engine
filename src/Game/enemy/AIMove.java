package Game.enemy;

import Engine.core.Time;
import Engine.util.Quaternion;
import Engine.util.Vector3f;
import Game.GameComponent;

import java.util.Random;

/**
 * Created by Slon on 22.03.2016.
 */
public class AIMove extends GameComponent {

    private static final Vector3f Y_AXIS = new Vector3f(0,1,0);
    private static final float CHASE_DISTANCE = 22;
    private static final float ATTACK_DISTANCE = 3;
    private static final float ROTATION_SPEED = 5;

    /**
     * todo:
     *  ai observer
     *  level grid - graph for pathfinding
     *  A*
     *  ***************
     *  **ooooooooo**o*
     *  **ooooooo***oo*
     *  **ooo**oo***oo*
     *  **ooo**oo***oo*
     *  **ooooooo***oo*
     *  **ooooooo***oo*
     *  *******ooooooo*
     *  *******ooooooo*
     *  ***************
     *  keep all paths
     *
     *  attack/sleep distance
     *
     */

    // temp!
    public static Vector3f playerPos = new Vector3f(0,0,0);
    public static Vector3f lookAt = new Vector3f(0,0,0);

    private Random r = new Random();
    private float speed = r.nextInt(8)+7;

    private Vector3f movementVector = new Vector3f(0,0,0);

    public void update(){

    }

    public void faceToPlayer(){
        Quaternion newRot = getTransform().getLookAtRotation(playerPos.mul(new Vector3f(1,0,1)), Y_AXIS);
        getTransform().setRotation(getTransform().getRotation().nlerp(newRot, (float)Time.getDelta() * ROTATION_SPEED, true));

    }

    public boolean isInChaseArea(){
        float distance = getTransform().getPosition().sub(playerPos).length();
        return distance <= CHASE_DISTANCE && distance >= ATTACK_DISTANCE;
    }

    public boolean isInAttackArea(){
        float distance = getTransform().getPosition().sub(playerPos).length();
        return distance <= ATTACK_DISTANCE;
    }

    public Vector3f getMovementVector() {
        Vector3f position = getTransform().getPosition();
        if(position.sub(playerPos).abs().length() > 3) {
            movementVector = playerPos.sub(position).normalized()
                    .mul(new Vector3f(speed * (float) Time.getDelta(), 0, speed * (float) Time.getDelta()));
        } else {
            movementVector = new Vector3f(0,0,0);
        }
        return movementVector;
    }

}
