package geometry;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Rectangle {
    Point upperLeft;
    double width;
    double height;


    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height){
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }


    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public List intersectionPoints(Line line){

        List<Line> lines = this.MyLines();


        List<Point> intersPoint = new ArrayList<>();

        for(int i=0; i< lines.size(); i++){
            Point cross = line.intersectionWith(lines.get(i));
            if(cross != null){
                intersPoint.add(cross);
            }
        }

        return intersPoint;

    }

    // Return the width and height of the rectangle
    public double getWidth(){return this.width;}
    public double getHeight(){return this.height;}

    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft(){return this.upperLeft;}
    public void setUpperLeft(String xory, double val){
        if(xory.equals("x")){
            this.upperLeft.x = val;}
        else{if(xory.equals("y")){
            this.upperLeft.y = val;}
        else{
            System.out.print("pbm modif paddle droite ou gauche: i get "+ xory+"\n");}}
    }

    public List<Point> getPoints(){
        List<Point> listPoints = new ArrayList<>();


        double xb = this.upperLeft.x + this.width;
        double yb = this.upperLeft.y;
        Point b = new Point(xb,yb);
        double xc = this.upperLeft.x + this.width;
        double yc = this.upperLeft.y + this.height;
        Point c = new Point(xc,yc);
        double xd = this.upperLeft.x;
        double yd = this.upperLeft.y + this.height;
        Point d = new Point(xd,yd);

        listPoints.add(this.upperLeft);
        listPoints.add(b);
        listPoints.add(c);
        listPoints.add(d);

        return listPoints;
    }


    public List<Line> MyLines(){
//        double xb = this.upperLeft.x + this.width;
//        double yb = this.upperLeft.y;
//        Point b = new Point(xb,yb);
//        double xc = this.upperLeft.x + this.width;
//        double yc = this.upperLeft.y - this.height;
//        Point c = new Point(xc,yc);
//        double xd = this.upperLeft.x;
//        double yd = this.upperLeft.x - this.height;
//        Point d = new Point(xd,yd);

        List<Point> l = this.getPoints();
        Line up = new Line(l.get(0), l.get(1));
        Line right = new Line(l.get(1), l.get(2));
        Line down = new Line(l.get(3),l.get(2));
        Line left = new Line(l.get(0),l.get(3));

        List<Line> lines = new ArrayList<>();
        lines.add(up);
        lines.add(right);
        lines.add(down);
        lines.add(left);
        return lines;

    }

    public int wichSidePHitMe(Point p){
        int i;
        for(i=0; i<this.MyLines().size() ; i++){
            if(p.isInLine(this.MyLines().get(i))){
                break;
            }
        }
        return i;
    }
}