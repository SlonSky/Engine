package Game.entities;

import Engine.rendering.Transform;
import Engine.rendering.meshManagment.Texture;
import Engine.rendering.particles.Particle;
import Engine.rendering.particles.ParticleTexture;
import Engine.util.Quaternion;
import Engine.util.Vector3f;
import Game.gun.Ray;

import java.util.ArrayList;

/**
 * Created by Slon on 28.05.2016.
 */
public class CombatManager {
    private static CombatManager instance = new CombatManager();

    private ArrayList<Opponent> opponents;
    private Combating combating;

    private int score;

    public static CombatManager getInstance() {
        return instance;
    }

    private CombatManager() {
        opponents = new ArrayList<>();
        score = 0;
    }

    public void hitOpponent(float damage, Ray shotRay){
        for(Opponent opponent: opponents){
            if(opponentHit(opponent, shotRay)){
                opponent.getDamage(damage);
                return;
            }
        }
    }

    private ParticleTexture p = new ParticleTexture(new Texture("fire.png"), 8);

    private boolean opponentHit(Opponent opponent, Ray shotRay){
        Vector3f bx = new Vector3f(0,0,0);
        boolean t = opponent.getCollider().checkRayIntersection(shotRay, bx);
//        System.out.println(bx);
        //todo: blood in same dir
        if(opponent.getCollider().getfLFraction() < 1) {
            new Particle(new Transform(bx, new Quaternion(0, 0, 0, 1), new Vector3f(1, 1, 1)), p, new Vector3f(0, 0, 0), 0, 50, true);
            return t;
        }
        return false;

    }

    public void hitCombating(float damage){
        combating.getDamage(damage);
    }

    public void setCombating(Combating combating) {
        this.combating = combating;
    }

    public void addOpponent(Opponent opponent){
        opponents.add(opponent);
    }

    public void removeOpponent(Opponent opponent){
        opponents.remove(opponent);
        score++;
    }
}
