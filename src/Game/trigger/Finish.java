package Game.trigger;

import Game.GameplayManager;

/**
 * Created by Slon on 30.05.2016.
 */
public class Finish implements TriggerEvent {

    @Override
    public void run() {
        GameplayManager.getInstance().setLevelEnded(true);
    }
}
