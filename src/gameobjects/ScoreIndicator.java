package gameobjects;

import biuoop.DrawSurface;
import listeners.Counter;

import java.awt.*;

public class ScoreIndicator implements Sprite {
    private Counter currentScore;
    private Color color;
    public ScoreIndicator(Counter score, Color color){
        this.currentScore = score;
        this.color = color;

    }

    public void drawOn(DrawSurface d){
        d.setColor(color);
        d.drawText(d.getWidth()/2-20 , 25 , "Score:" + String.valueOf(this.currentScore.getValue()) , 20);
    }

    public void timePassed(){}
}
