package Engine.core;

import Engine.audio.AudioEngine;
import Engine.rendering.RenderingEngine;
import Game.Game;

/**
 * Created by Slon on 09.02.2016.
 */
public class CoreEngine {
    private boolean isRunning;
    private int width;
    private int height;
    private Game game;
    private RenderingEngine renderingEngine;
    private double frameTime;
    private int FPS;
//    private AudioEngine audioEngine;

    public CoreEngine(int width, int height, double frameRate, Game game) {
        isRunning = false;
        this.width = width;
        this.height = height;
        this.frameTime = 1.0 / frameRate;
        this.game = game;
        game.setEngine(this);
    }

    public void createWindow(String title, boolean full){
        if(full){
            Window.createFullWindow(title);
        } else {
            Window.createWindow(width, height, title);
        }
        renderingEngine = new RenderingEngine(this);
        AudioEngine.init();
//        audioEngine = new AudioEngine();
    }

    public void start(){
        if (isRunning){
            return;
        }
        run();
    }

    public void stop(){
        if(!isRunning){
            return;
        }
        isRunning = false;
    }

    public void run(){
        isRunning = true;

        int frames = 0;
        long frameCounter = 0;
        FPS =0;
        game.init();

        double lastTime = Time.getTime();
        double unprocessedTime = 0;
        while (isRunning){
            boolean render = false;
            double startTime = Time.getTime();
            double passedTime = startTime - lastTime;
            lastTime = startTime;

            unprocessedTime += passedTime / (double)Time.SECOND;
            frameCounter += passedTime;

            while (unprocessedTime > frameTime){
                render = true;

                unprocessedTime -= frameTime;
                if(Window.isCloseRequested()){
                    stop();
                }

                Time.setDelta(frameTime);

                // update/update
                game.input();
                game.update();
                Input.update();

                if(frameCounter >= Time.SECOND){
//                    System.out.println(frames);
                    FPS = frames;
                    frames = 0;
                    frameCounter = 0;
                }
            }
            if(render){
                game.render(renderingEngine);
                Window.render();
                frames++;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        cleanUp();
    }

    private void cleanUp(){
        game.destroy();
        Window.destroy();
        AudioEngine.dispose();
        System.exit(0);
    }

    public RenderingEngine getRenderingEngine() {
        return renderingEngine;
    }

    public Game getGame() {
        return game;
    }

    public int getFPS() {
        return FPS;
    }
}
