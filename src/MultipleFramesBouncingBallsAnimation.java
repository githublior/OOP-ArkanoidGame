import biuoop.GUI;
import biuoop.DrawSurface;
import gameobjects.Ball;
import gameobjects.Velocity;

import java.util.ArrayList;

import java.awt.*;
import java.util.Random;



public class MultipleFramesBouncingBallsAnimation {
    public static void main(String[] args) {

        Random rand = new Random(); // create a random-number generator
        GUI gui = new GUI("Multiple Frames", 650, 650);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
//        DrawSurface d = gui.getDrawSurface();
        ArrayList<Ball> BallsList= new ArrayList<Ball>();
        int x,y;
        for(int i=0 ; i< args.length; i++) {
            if(i <= (int)BallsList.size()/2){
                x = rand.nextInt(450 - Integer.parseInt(args[i]))+50;
                y = rand.nextInt(450 - Integer.parseInt(args[i]))+50;

            }
            else {
                x = rand.nextInt(150 - Integer.parseInt(args[i])) + 450;
                y = rand.nextInt(150 - Integer.parseInt(args[i])) + 450;
            }
            Ball ball = new Ball(x, y, Integer.parseInt(args[i]), Color.RED);
            int angle = rand.nextInt(360);
            int q = Integer.parseInt(args[i]);
            int speed = 50 / q;
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            ball.setVelocity(v);
            BallsList.add(ball);
//        ball.setVelocity(5, -5);
        }
        while (true) {
            DrawSurface e = gui.getDrawSurface();
//            e.drawRectangle(0,0,50,50);

//            e.drawRectangle(450,450,600,600);
//            e.setColor(Color.YELLOW);
//            e.drawRectangle(450,450,60,600);
            int a,b;
            for(int i=0; i<BallsList.size(); i++){
                Ball ball = BallsList.get(i);
                if(i <= (int)BallsList.size()/2){
                    a=50;
                    b=500;}
                    else {a=450;
                    b=600;}
                ball.moveOneStepInRect(a,a,b,b);
                ball.drawOn(e);
            }
            e.setColor(Color.GRAY);
            e.drawRectangle(50,50,450,450);
            e.setColor(Color.YELLOW);
            e.drawRectangle(450,450,150,150);

            gui.show(e);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}