package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import listeners.Counter;

public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter lives, score;
    public EndScreen(KeyboardSensor k, Counter nbrOfLives, Counter score) {
        this.keyboard = k;
        this.stop = false;
        this.lives = nbrOfLives;
        this.score =score;

    }
    public void doOneFrame(DrawSurface d) {
       //  this.gameScreen.drawAllOn(d);   // gamesceenn = sprite colletion this.sprites

        if(this.lives.getValue() >0) {
            d.drawText(10, d.getHeight() / 2-100, " YOU  WIN!" , 32);
            d.drawText(10, d.getHeight() / 2-50, "youre score is:" + this.score.getValue() ,32);
            d.drawText(10, d.getHeight() / 2, " - press space to quit", 32);

        }

        else{
            d.drawText(10, d.getHeight() / 2-100, " Game Over!" , 32);
            d.drawText(10, d.getHeight() / 2-50, "youre score is:" + this.score.getValue() ,32);
            d.drawText(10, d.getHeight() / 2, " - press space to quit", 32);
        }
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }
    public boolean shouldStop() { return this.stop; }
}