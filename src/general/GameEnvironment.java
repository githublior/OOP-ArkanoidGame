package general;

import java.awt.*;
import java.util.*;
import java.util.List;

import biuoop.GUI;
import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import gameobjects.Collidable;

public class GameEnvironment{

    List<Collidable> collidableList;
//    Point UpperBoundFrame;
//    Point LowerBoundFrame;
    DrawSurface surface;
    GameLevel game;

    public GameEnvironment(DrawSurface s, GameLevel game){
        this.setSurface(s);
        this.collidableList = new ArrayList<>();
        this.game = game;
    }

    public DrawSurface getSurface(){return surface;}
    public void setSurface(DrawSurface s){ this.surface=s;}

    public GameLevel getGame(){return this.game;}


//    public Point getUpperBoundFrame(){ return this.UpperBoundFrame; }
//    public Point getLowerBoundFrame(){return this.LowerBoundFrame;}

    // add-remove the given collidable to the environment.
    public void addCollidable(Collidable c){ collidableList.add(c); }
    public void removeCollidable(Collidable c){ collidableList.remove(c); }



    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory){
        // first: get all the the first intersection with all the collidables (even when there are no intersection)
        List<Point> allFirstInters = new ArrayList<>();
        for(int i=0 ; i< this.collidableList.size(); i++){
            Point intersPoint = trajectory.closestIntersectionToStartOfLine(this.collidableList.get(i).getCollisionRectangle());
            allFirstInters.add(intersPoint);
        }
        // second : get the closest amoung them
        if(allFirstInters.size() >=1){
            double a= Double.POSITIVE_INFINITY;
            int index = -1;
            Point minDist = new Point(a, a);
            for (int i = 0; i <allFirstInters.size() ; i++) {
                if(allFirstInters.get(i) != null){
                    if(trajectory.getstart().distance(allFirstInters.get(i))< trajectory.getstart().distance(minDist)){
                        minDist = allFirstInters.get(i);
                        index = i;
                    }
                }
            }
            if(index !=-1) {
                CollisionInfo info = new CollisionInfo(minDist, collidableList.get(index));
                return info;
            }
        }

//        System.out.print("No collision on this trajectory.");
        return null;

    }

}