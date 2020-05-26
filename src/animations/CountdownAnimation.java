package animations;

import biuoop.DrawSurface;
import biuoop.Sleeper;

import gameobjects.SpriteCollection;

import java.awt.*;

// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) seconds, before
// it is replaced with the next one.
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countfrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private Sleeper sleeper;
    private double frameRate;


    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds=numOfSeconds;
        this.countfrom = countFrom;
        this.gameScreen=gameScreen;
        this.stop=false;
        this.sleeper = new Sleeper();
        this.frameRate = (numOfSeconds * 1000) / (countFrom);
    }
    public void doOneFrame(DrawSurface d) {
        long startTime = System.currentTimeMillis();
        this.gameScreen.drawAllOn(d);

        //countdown logic
        if (this.countfrom > 0) {
            d.drawText(d.getWidth() / 2 - 6, d.getHeight() / 2 + 10, Integer.toString(this.countfrom), 32);
            this.countfrom--;
        } else {
            d.drawText(d.getWidth() / 2 - 6, d.getHeight() / 2 + 10, Integer.toString(this.countfrom), 32);
            this.stop = true;
        }
        // timing
        long usedTime = System.currentTimeMillis() - startTime;
        long milliSecondLeftToSleep = (long)this.frameRate - usedTime;
        if (milliSecondLeftToSleep > 0) {
            this.sleeper.sleepFor(milliSecondLeftToSleep);
        }

//        for (int i = this.countfrom; i > 0; i--) {
//
//            d.drawText(10, d.getHeight() / 2, "Game Start in :" + i, 32);
//            this.sleeper.sleepFor(1000*(long)this.numOfSeconds/this.countfrom);
//        }

    }
    public boolean shouldStop() {return this.stop; }
}