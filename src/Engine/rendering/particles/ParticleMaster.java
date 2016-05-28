package Engine.rendering.particles;

import Engine.rendering.Camera;
import Engine.rendering.RenderingEngine;
import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry;

import java.util.*;

/**
 * Created by Slon on 27.05.2016.
 */
public class ParticleMaster {
    private static HashMap<ParticleTexture, ArrayList<Particle>> particles = new HashMap<>();
    private static ParticleRenderer renderer;

   public static void init(){
       renderer = new ParticleRenderer();
   }


   public static void update(Camera camera){
       Iterator<Map.Entry<ParticleTexture, ArrayList<Particle>>> mapIterator = particles.entrySet().iterator();
        while (mapIterator.hasNext()){
            ArrayList<Particle> list = mapIterator.next().getValue();
            Iterator<Particle> iterator = list.iterator();
            while(iterator.hasNext()){
                Particle p = iterator.next();
                boolean stillAlive = p.update(camera);
                if(!stillAlive){
                    iterator.remove();
                    if(list.isEmpty()){
                        mapIterator.remove();
                    }
                }
            }
            list.sort(new Comparator<Particle>() {
                @Override
                public int compare(Particle o1, Particle o2) {
                    if(o1.getDistance() > o2.getDistance()){
                        return 1;
                    } else if(o1.getDistance() < o2.getDistance()){
                        return -1;
                    }
                    return 0;
                }
            });
        }


//       System.out.println(particles.size());
   }

    public static void render(RenderingEngine renderingEngine){
        renderer.render(particles, renderingEngine);
    }

    public static void addParticle(Particle particle){
        ArrayList<Particle> list = particles.get(particle.getTexture());
        if(list == null){
            list = new ArrayList<>();
            particles.put(particle.getTexture(), list);
        }
        list.add(particle);
    }
}
