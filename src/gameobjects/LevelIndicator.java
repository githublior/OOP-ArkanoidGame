package gameobjects;

import biuoop.DrawSurface;
import levels.LevelInformation;
import listeners.Counter;

import java.awt.*;

public class LevelIndicator implements Sprite {
    private LevelInformation levelInfo;
    private Color color;
    public LevelIndicator(LevelInformation levelInfo, Color color){
        this.levelInfo = levelInfo;
        this.color= color;
    }

    public void drawOn(DrawSurface d){
        d.setColor(this.color);
        d.drawText(d.getWidth()-200 , 25 , "Level:" + String.valueOf(this.levelInfo.levelName()) , 18);
    }

    public void timePassed(){}
}
