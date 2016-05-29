package Game.gun;

import Engine.util.Vector3f;

/**
 * Created by Slon on 22.05.2016.
 */
public interface Equipment {
    int getMagazineCapacity();
    int getBulletsInMagazine();
    int getBulletsAmount();
    void setBulletsInMagazine(int amount);
    void setBulletsAmount(int amount);

    Ray getShotRay();
}
