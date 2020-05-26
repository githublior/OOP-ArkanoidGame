
package geometry;


import java.rmi.MarshalException;

public class Point {
    // constructor
    double x;
    double y;

    public Point(double x, double y){
        this.x=x;
        this.y=y;
    }

    // distance -- return the distance of this point to the other point
    public double distance(Point other) {
        double dist = 0;
        dist = java.lang.Math.sqrt((this.x-other.x)*(this.x-other.x)+(this.y-other.y)*(this.y-other.y));
        return dist;
    }

    // equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        if((x==other.x) & (y==other.y)) {return true;
        } else {return false;}
    }

    public double getX() { return this.x; }
    public double getY() { return this.y; }



    public Boolean isInLine(Line line) {
        double a = line.slope;
        double b;
        if(java.lang.Double.isInfinite(a)){
            b = line.start.getY();
            if((Math.abs(line.getstart().getX() - this.getX())<=0.5) && (this.getY()>= line.getstart().getY()) && (this.getY()<=line.getEnd().getY())){
                return true;
            }
            else return false;
        }
       else {
            b = line.start.getY() - a * line.start.getX();

            if (java.lang.Math.abs(this.getY() - (a * this.getX() + b)) <= 0.1) {
                return true;
            } else return false;
        }

    }


//        return (((this.x >= line.start.getX()) && (this.x<=line.end.getX()))||((this.x >= line.start.getX()) && (this.x<=line.end.getX())))


}
