package gameobjects;


import biuoop.DrawSurface;

import java.lang.reflect.Array;
import java.util.*;

public class SpriteCollection {
    List<Sprite> SpriteList = new ArrayList<>();

    public List<Sprite> getSpriteList(){return this.SpriteList;}
    public void addSprite(Sprite s){ SpriteList.add(s);}
    public void removeSprite(Sprite s){SpriteList.remove(s);}


    // call timePassed() on all sprites.
    public void notifyAllTimePassed(){ for (int i = 0; i < SpriteList.size() ; i++){SpriteList.get(i).timePassed();}}

    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d){ for (int i = 0; i < SpriteList.size() ; i++){SpriteList.get(i).drawOn(d);}}
}