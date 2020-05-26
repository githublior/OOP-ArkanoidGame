package geometry;

import java.awt.*;
import java.util.List;


public class Line {
    // constructors
    Point start;
    Point end;
    double slope;
    public Line(Point start, Point end) {

        this.start = start;
        this.end = end;
        this.slope = slope();
    }
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1,y1);
        this.end = new Point(x2,y2);
        this.slope = slope();
    }

    // Return the length of the line
    public double length() { return this.start.distance(this.end); }

    // Returns the middle point of the line
    public Point middle() {
        double x= (this.end.x-this.start.x)/2;
        double y= (this.end.y-this.start.y)/2;
        Point mid = new Point(x+this.start.x,y+this.start.y);
        return mid;
    }

    // Returns the start point of the line
    public Point getstart() { return this.start;}

    // Returns the end point of the line
    public Point getEnd() {return this.end; }

    public double getSlope() { return slope; }

    public double slope() {
        double dx, dy;
        dx = this.start.getX() - this.end.getX();
        dy = this.start.getY() - this.end.getY();
        if(dx == 0){
//            System.out.printf("pbm with slope: divided by zero");
            return Double.POSITIVE_INFINITY;
        }
        else{return (dy / dx);}
    }


    /**
     * Check intersection between 2 lines.
     * @param x - the x/y value of the intersection point.
     * @param x1Start - the x/y value of the start point of the line.
     * @param x1End - the x/y value of the end point of the line.
     * @param x2Start - the x/y value of the start point of the other line.
     * @param x2End - the x/y value of the end point of the other line.
     * @return true if the intersection in the both lines domains.  */
    boolean isInTheDomain(double x, double x1Start, double x1End, double x2Start, double x2End) {
        return (((x >= x1Start && x <= x1End) || (x <= x1Start && x >= x1End))
                && ((x >= x2End && x <= x2Start) || (x <= x2End && x >= x2Start)));
    }
    boolean XisInSegment(double x, Line line){
        return  ((x>=line.start.getX()&& x<=line.end.getX())||(x<=line.start.getX()&& x>=line.end.getX()));
    }
    boolean YisInSegment(double y, Line line){
        return  ((y>=line.start.getY()&& y<=line.end.getY())||(y<=line.start.getY()&& y>=line.end.getY()));
    }


    /**
     * Create the intersection point between the lines.
     * Create 2 equations y = m * x - b and check the where the y values are equal.
     * @param other - another line to check the intersection.
     * @return the intersection point, if the lines don't intersected returns null.  */
    public Point intersectionWith(Line other) {


        double x, y, m1, m2, b1, b2;
        // find the m and b to create the equation y = m * x -b.
        m1 = this.getSlope();
        m2 = other.getSlope();
        b1 = this.start.getY() - m1 * this.start.getX();
        b2 = other.start.getY() - m2 * other.start.getX();
        // If the slopes equal there isn't intersection.
        if (m1 == m2) {
            return null;
        } else if (Double.isInfinite(m1)) {
            // If one of the slopes in Infinity then the x value is known and we find the y value.
            x = this.start.getX();
            y = m2 * x + b2;
            // If no intersection return null.
//            if (!isInTheDomain(y, this.start.getY(), this.end.getY(), other.start.getY(), other.end.getY())) {

//            if(!YisInSegment(y, other)){
//                return null;
//            }
            if (!XisInSegment(x, this) || !XisInSegment(x,other) || !YisInSegment(y, this)|| !YisInSegment(y,other)){
                return null;
            }
        } else if (Double.isInfinite(m2)) {
            x = other.start.getX();
            y = m1 * x + b1;
//            if (!isInTheDomain(y, this.start.getY(), this.end.getY(), other.start.getY(), other.end.getY())) {
//            if(!YisInSegment(y, other)){
//                return null;
//            }
            if (!XisInSegment(x, this) || !XisInSegment(x,other) || !YisInSegment(y, this)|| !YisInSegment(y,other)){
                return null;
            }
        } else {
            // In other cases find the x and y of the intersection and check if this is in the both lines.
            x = (b2 - b1) / (m1 - m2);
            y = m1 * x + b1;
//            if (!isInTheDomain(x, this.start.getX(), this.end.getX(), other.start.getX(), other.end.getX())) {
//                return null;
//            }
            if (!XisInSegment(x, this) || !XisInSegment(x,other) || !YisInSegment(y, this)|| !YisInSegment(y,other)){
                return null;
            }
        }
        // Create the intersection point.
        Point cross = new Point(x, y);
        return cross;
    }

    // equals -- return true if the lines are equal, false otherwise
    public boolean equals(Line other) {
        if(this.start.equals(other.start) && this.end.equals(other.end)){return true;}
        else{
            if(this.start.equals(other.end) && this.end.equals(other.start)){return true;}
            else{return false;}
        }
    }


    public boolean isIntersecting(Line other) {
        Point intersection = intersectionWith(other);
        if (intersection != null) {
            return true;
        }
        return false;
    }

    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect){

        List<Point> intersPoint = rect.intersectionPoints(this);


        if(intersPoint.size() >= 1) {

            double mind = Double.POSITIVE_INFINITY;
            Point minPoint = intersPoint.get(0);

            for (int i = 0; i < intersPoint.size(); i++) {
                double dist = this.start.distance(intersPoint.get(i));
                if (dist < mind) {
                    mind = dist;
                    minPoint = intersPoint.get(i);
                }
            }
            return minPoint;
        }
        else{return null;}
    }
}