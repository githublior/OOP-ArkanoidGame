import biuoop.GUI;
import biuoop.DrawSurface;
//import java.awt.*;
//import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Random;
import java.awt.Color;
import geometry.Line;
import geometry.Point;

public class SimpleGuiExample {

    public void drawRandomCircles() {
        Random rand = new Random(); // create a random-number generator
        // Create a window with the title "Random Circles Example"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random Circles Example", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        for (int i = 0; i < 10; ++i) {
            int x = rand.nextInt(400) + 1; // get integer in range 1-400
            int y = rand.nextInt(300) + 1; // get integer in range 1-300
            int r = 5*(rand.nextInt(4) + 1); // get integer in 5,10,15,20
            d.setColor(Color.RED);
            d.fillCircle(x,y,r);
        }
        gui.show(d);
    }

//    public Line generateRandonmLine(int numberOfLines, int width,int height){
//
//    }

    public void drawRandomLines() {
        Random rand = new Random(); // create a random-number generator
        // Create a window with the title "Random Lines Example"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random Lines Example", 1000, 1000);
        DrawSurface d = gui.getDrawSurface();

        ArrayList<Line> LinesList= new ArrayList<Line>();
        for (int i = 0; i < 10; ++i) {
            int x1 = rand.nextInt(1000);
            int y1 = rand.nextInt(1000);
            int x2 = rand.nextInt(1000);
            int y2 = rand.nextInt(1000);
//            int x2 =100 , y2=100, x1=100, y1=700;
            Line l = new Line(x1, y1, x2, y2);

            d.setColor(Color.BLACK);
            d.drawLine(x1, y1, x2, y2);
            d.setColor(Color.RED);
            Point mid = l.middle();
            d.fillCircle((int) mid.getX(), (int) mid.getY(), 3);
            int size = LinesList.size();
            d.setColor(Color.BLUE);
            for (int j = 0; j<size; j++) {
                if (!LinesList.isEmpty()) {
                    Line other = LinesList.get(j);
                    Point inter = l.intersectionWith(other);
                    if (inter != null) {
                        d.fillCircle((int) inter.getX(), (int) inter.getY(), 3);
                    }
                }
            }
            LinesList.add(l);
        }
        gui.show(d);
    }

    public static void main(String[] args) {
        SimpleGuiExample example = new SimpleGuiExample();
        example.drawRandomLines();
    }
}