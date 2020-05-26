package general;

import gameobjects.Collidable;
import geometry.Point;

public class CollisionInfo {
    Point collisionPoint;
    Collidable collisionObject;
    public CollisionInfo(Point point, Collidable object){
        this.collisionPoint = point;
        this.collisionObject= object;

    }
    public Point getCollisionPoint(){return collisionPoint;}
    public Collidable getCollisionObject(){return collisionObject;}

    // the point at which the collision occurs.
    public Point collisionPoint(){return this.collisionPoint;}

    // the collidable object involved in the collision.
    public Collidable collisionObject(){return this.collisionObject;}
}