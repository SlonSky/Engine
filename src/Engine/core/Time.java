package Engine.core;

/**
 * Created by Slon on 09.02.2016.
 */
public class Time {
    public static final long SECOND = 1_000_000_000;

    private static double delta;

    public static double getTime(){
        return System.nanoTime();
    }

    public static double getDelta() {
        return delta;
    }

    public static void setDelta(double delta) {
        Time.delta = delta;
    }
}
