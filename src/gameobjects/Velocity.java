package gameobjects;
import geometry.Point;


// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    // constructor
    double dx, dy;
    public Velocity(double dx, double dy){
        this.dx = dx;
        this.dy=dy;
    }
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(Math.toRadians(angle - 90));
        double dy =speed * Math.sin(Math.toRadians(angle - 90));
        return new Velocity(dx, dy);
    }
    public double getSpeed(){return Math.sqrt(this.dx * this.dx + this.dy * this.dy); }

    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p){
        Point newPoint = new Point(p.getX()+dx ,p.getY()+dy);
        return newPoint;
    }

}