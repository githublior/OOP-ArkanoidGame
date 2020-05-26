import biuoop.GUI;
import biuoop.DrawSurface;
import gameobjects.Ball;
import gameobjects.Velocity;

import java.util.ArrayList;

import java.awt.*;
import java.util.Random;



public class MultipleBouncingBallsAnimation {
    public static void main(String[] args) {

        Random rand = new Random(); // create a random-number generator
        GUI gui = new GUI("MultipleBouncingBallsAnimation", 600, 600);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        DrawSurface d = gui.getDrawSurface();
        ArrayList<Ball> BallsList= new ArrayList<Ball>();
        for(int i=0 ; i< args.length; i++) {
            int x = rand.nextInt(Math.min(d.getHeight(), d.getWidth()) - Integer.parseInt(args[i]));
            int y = rand.nextInt(Math.min(d.getHeight(), d.getWidth()) - Integer.parseInt(args[i]));

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

            for(int i=0; i<BallsList.size(); i++){
                Ball ball = BallsList.get(i);
                ball.moveOneStepInRect(0,0,0+e.getWidth(),0+e.getHeight());
                ball.drawOn(e);
            }

            gui.show(e);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }

    }
}