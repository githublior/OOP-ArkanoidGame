package gameobjects;
import java.awt.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.GUI;
import biuoop.DrawSurface;
import general.CollisionInfo;
import general.GameLevel;
import general.GameEnvironment;
import general.CollisionInfo;
import geometry.Line;
import geometry.Point;
import listeners.HitListener;


public class Ball implements Sprite {
    double epsilon = 10;
    // constructor
    geometry.Point center;
    int radius;
    java.awt.Color color;
    Velocity v;
    GameEnvironment environment;
//    Paddle pad;
    private List<HitListener> hitListeners= new ArrayList<>();



    public Ball(geometry.Point center, int r, java.awt.Color color){
        this.center = center;
        this.radius = r;
        this.color = color;
//        this.pad = pad;

    };
    public Ball(double x, double y, int r , java.awt.Color color){
        this.center =new geometry.Point(x,y);
        this.radius =r;
        this.color = color;
//        this.pad = pad;
    }
    public Ball(geometry.Point center, int r, java.awt.Color color, GameEnvironment environment){
        this(center, r, color);
        this.environment = environment;
    }

    public Ball(double x, double y, int r , java.awt.Color color, GameEnvironment environment){
        this.center =new geometry.Point(x,y);
        this.radius =r;
        this.color = color;
        this.environment = environment;
//        this.pad =pad;

    }

    // accessors
    public int getX(){return (int)this.center.getX(); };
    public int getY(){return (int)this.center.getY();};
    public int getSize(){return this.radius;};
    public java.awt.Color getColor(){return this.color;};

    public void setVelocity(Velocity v){this.v = v;}
    public void setVelocity(double dx, double dy){
        this.v = new Velocity(dx,dy);
    }
    public Velocity getVelocity(){return this.v;}

    public void moveOneStepInRect(int i1,int i2, int i3, int i4) {
        if ((this.center.getX() + this.radius >= i1) && (this.center.getX() + this.radius >= i3)){
            this.setVelocity(-(java.lang.Math.abs(this.v.dx)), this.v.dy); }
        if ((this.center.getX() - this.radius <= i1) && (this.center.getX() - this.radius <= i3)) {
            this.setVelocity(java.lang.Math.abs(this.v.dx), this.v.dy); }

        if((this.center.getY() + this.radius>= i2)&&(this.center.getY() + this.radius>= i4)){
            this.setVelocity(this.v.dx, -java.lang.Math.abs(this.v.dy));}
        if ((this.center.getY() - this.radius<=i2)&& (this.center.getY() - this.radius<=i4)){
                this.setVelocity(this.v.dx, java.lang.Math.abs(this.v.dy));}


        this.center = this.getVelocity().applyToPoint(this.center);
    }

    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface){
        surface.setColor(this.color);
        surface.fillCircle(getX(), getY(), getSize());
    }
    public Line getTrajectory(){
        double width= this.environment.getSurface().getWidth();
        double height = this.environment.getSurface().getHeight();
//        double width = Math.abs(this.environment.getUpperBoundFrame().getX()- this.environment.getLowerBoundFrame().getX());
//        double height = Math.abs(this.environment.getUpperBoundFrame().getY() - this.environment.getLowerBoundFrame().getY());

        double maxLength = Math.sqrt(2)* Math.max(width, height);

        geometry.Point endPoint = new Point(this.center.getX() + maxLength * this.v.dx , this.center.getY()+ maxLength*this.v.dy);

        Line trajectory = new Line(this.center , endPoint);
        return  trajectory;

    }
    public void moveOneStep(){

        Line trajectory = getTrajectory();
        CollisionInfo collision = environment.getClosestCollision(trajectory);
        if(collision != null) {
            double distFromBallToCollidable = this.center.distance(collision.getCollisionPoint());
//            System.out.print("I see collidable in front,  suppose collision at y= "+Double.toString(collision.collisionPoint().getY())+ collision.collisionObject.getCol() +"\n");

            if (distFromBallToCollidable <= this.radius + epsilon) {
//                System.out.print("collision Point : "+ Double.toString(collision.collisionPoint.getX())+ " and "+Double.toString(collision.collisionPoint().getY())+ collision.collisionObject.getCol()+"\n");
                this.v = collision.getCollisionObject().hit(this, collision.getCollisionPoint(), this.v);

            }
        }

        moveOneStepInRect(0,0, 0 + environment.getSurface().getWidth(), 0+environment.getSurface().getHeight());

    }
    public void timePassed(){
        moveOneStep();
    }
    public void addToGame(GameLevel g){
        g.addSprite(this);
    }



    public void addHitListener(HitListener hl) {
        this.hitListeners.add( hl);
    }
    public void removeHitListener(HitListener hl){this.hitListeners.remove(hl);}

    public void removeFromGame(GameLevel g){
        g.removeSprite(this);
    }

}