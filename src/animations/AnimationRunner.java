package animations;


import biuoop.DrawSurface;
import biuoop.GUI;

public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private biuoop.Sleeper sleeper=  new biuoop.Sleeper();


    public AnimationRunner(GUI gui, int framesPerSecond){
        this.gui=gui;
        this.framesPerSecond=framesPerSecond;

    }
    // ...
    public GUI getGui(){return this.gui;}
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}