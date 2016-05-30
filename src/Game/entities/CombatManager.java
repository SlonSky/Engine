package Game.entities;

import Engine.rendering.Transform;
import Engine.rendering.meshManagment.Texture;
import Engine.rendering.particles.Particle;
import Engine.rendering.particles.ParticleTexture;
import Engine.util.Quaternion;
import Engine.util.Vector3f;
import Game.gun.Ray;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Slon on 28.05.2016.
 */
public class CombatManager {
    private static CombatManager instance = new CombatManager();

    private ArrayList<Opponent> opponents;
    private Combating combating;

    private int score;

    private Blood blood = new Blood(new ParticleTexture(new Texture("blood2.png"), 3));

    public static CombatManager getInstance() {
        return instance;
    }

    private CombatManager() {
        opponents = new ArrayList<>();
        score = 0;
    }

    public void hitOpponent(float damage, Ray shotRay){
        opponents.sort(new Comparator<Opponent>() {
            @Override
            public int compare(Opponent o1, Opponent o2) {
                float dis1 = combating.getPosition().sub(o1.getCollider().getPosition()).length();
                float dis2 = combating.getPosition().sub(o2.getCollider().getPosition()).length();
                if(dis1 > dis2){
                    return 1;
                } else if(dis1 < dis2){
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        for(Opponent opponent: opponents){
            if(opponentHit(opponent, shotRay)){
                opponent.getDamage(damage);
                return;
            }
        }
    }

    private boolean opponentHit(Opponent opponent, Ray shotRay){
        Vector3f hitPos = new Vector3f(0,0,0);
        boolean hit = opponent.getCollider().checkRayIntersection(shotRay, hitPos);
        if(opponent.getCollider().getfLFraction() < 1 && hit) {
            blood.setDir(shotRay.end.sub(shotRay.start));
//            blood.emitParticle(hitPos);
            blood.generateParticles(hitPos);
        }
        return hit;

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

    public int getScore() {
        return score;
    }
}
