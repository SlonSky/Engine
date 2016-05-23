package Game.gun;

/**
 * Created by Slon on 22.05.2016.
 */
public interface Equipment {
    int getMagazineCapacity();
    int getBulletsInMagazine();
    int getBulletsAmount();
    void setBulletsInMagazine(int amount);
    void setBulletsAmount(int amount);
}
