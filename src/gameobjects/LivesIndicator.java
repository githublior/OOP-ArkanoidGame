package gameobjects;

import biuoop.DrawSurface;
import listeners.Counter;

import java.awt.*;

public class LivesIndicator implements Sprite{

    private Color color;
    private Counter nbrOfLives;
    public LivesIndicator(Counter nbrOfLives, Color color){
        this.nbrOfLives = nbrOfLives;
        this.color = color;
    }

    public void drawOn(DrawSurface d){
        d.setColor(this.color);
        d.drawText(20 , 25 , "Lives:" + String.valueOf(this.nbrOfLives.getValue()) , 20);
    }

    public void timePassed(){}

}
