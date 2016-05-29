package Game.entities;

import Engine.physics.Collider;

/**
 * Created by Slon on 28.05.2016.
 */
public interface Opponent {
    Collider getCollider();
    void getDamage(float damage);
}
