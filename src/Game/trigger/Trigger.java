package Game.trigger;

import Engine.util.Vector3f;

/**
 * Created by Slon on 19.04.2016.
 */
public class Trigger {
    // todo: action on reach
    // boolean reached; update;
    // Action; - pattern Command?

    private boolean reached;
    private float radius;
    private Vector3f position;
    private TriggerEvent event;

    public Trigger(float radius, Vector3f position, TriggerEvent event) {
        this.radius = radius;
        this.position = position;
        this.event = event;
        this.reached = false;
    }

    public void check(Vector3f playerPos){
        if(playerPos.sub(position).length() <= radius && !reached){
            event.run();
            reached = true;
        }
    }
}
