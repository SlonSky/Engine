package Game.enemy;

import Engine.rendering.animation.Animation;

/**
 * Created by Slon on 23.05.2016.
 */
public interface EnemyState {
    void enter();
    void update();
    void  exit();

    Animation getAnimation();
}
