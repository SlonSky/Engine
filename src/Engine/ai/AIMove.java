package Engine.ai;

import Engine.core.Time;
import Engine.util.Vector3f;
import Game.GameComponent;

import java.util.Random;

/**
 * Created by Slon on 22.03.2016.
 */
public class AIMove extends GameComponent {
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
    public static Vector3f moveTo = new Vector3f(0,0,0);

    private Random r = new Random();
    private float speed = r.nextInt(10);

    private Vector3f translation = new Vector3f(0,0,0);

    public void update(){
        move();
    }

    public void move(){
        getTransform().setPosition(getTransform().getPosition().add(translation));
    }

    public Vector3f getTranslation() {
        Vector3f position = getTransform().getPosition();
        if(position.sub(moveTo).abs().length() > 3) {
            translation = moveTo.sub(position).normalized()
                    .mul(new Vector3f(speed * (float) Time.getDelta(), 0, speed * (float) Time.getDelta()));
        } else {
            translation = new Vector3f(0,0,0);
        }
        return translation;
    }

    public void setTranslation(Vector3f translation) {
        this.translation = translation;
    }
}
