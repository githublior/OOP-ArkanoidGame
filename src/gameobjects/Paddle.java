package gameobjects;


import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gameobjects.Sprite;
import geometry.Rectangle;
import geometry.Point;
import gameobjects.Collidable;

import general.GameLevel;



import java.awt.*;

public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle shape;
    private Color col;
    private int speed;
    private double leftedge, rightEdge;


    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle shape, Color col, int speed, double leftedge, double rightEdge){
        this.keyboard = keyboard;
        this.shape =shape;
        this.col=col;
        this.speed =speed;
        this.leftedge=this.shape.getUpperLeft().getX();
        this.rightEdge=this.shape.getUpperLeft().getX()+this.shape.getWidth();
        this.leftedge = leftedge;
        this.rightEdge = rightEdge;

    }
    public String getCol(){return "Paddle";}

    public void moveLeft() {
        if (this.shape.getUpperLeft().getX() - this.speed >= this.leftedge) {
            this.shape.setUpperLeft("x" , this.shape.getUpperLeft().getX() - 1 * this.speed);
        } else {
            this.shape.setUpperLeft("x" , this.leftedge);
        }
    }

    public void moveRight(){
        if (this.shape.getUpperLeft().getX() + this.shape.getWidth()+ this.speed <= this.rightEdge) {
        this.shape.setUpperLeft("x",  this.shape.getUpperLeft().getX() + 1 * this.speed);
    } else {
        this.shape.setUpperLeft("x", this.rightEdge - this.shape.getWidth());
    }
}

    // Sprite
    public void timePassed(){
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
//            System.out.println("the 'left arrow' key is pressed");
            moveLeft();
        }
        else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
//            System.out.println("the 'left arrow' key is pressed");
            moveRight();
        }

    }
    public void drawOn(DrawSurface d){
        d.setColor(this.col);
        d.fillRectangle((int)this.shape.getUpperLeft().getX(), (int)this.shape.getUpperLeft().getY(), (int)this.shape.getWidth(), (int)this.shape.getHeight() );
    }

    // Collidable
    public Rectangle getCollisionRectangle(){return this.shape;}
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        double paddleLength = this.shape.getWidth();
        double dx = currentVelocity.dx, dy = currentVelocity.dy;
        Velocity newVelocity = null;

        double wichblock = this.shape.getUpperLeft().distance(collisionPoint);
        int angle;
        double ballSpeed = currentVelocity.getSpeed();
        if (this.shape.getUpperLeft().getX() <= collisionPoint.getX()) {
            if (wichblock <= paddleLength / 5) {
                angle = 300;
                newVelocity = Velocity.fromAngleAndSpeed(angle, ballSpeed);
            } else if (wichblock <= 2 * paddleLength / 5) {
                angle = 330;
                newVelocity = Velocity.fromAngleAndSpeed(angle, ballSpeed);
            } else if (wichblock <= 3 * paddleLength / 5) {
                newVelocity = new Velocity(dx, -java.lang.Math.abs(dy));
            } else if (wichblock <= 4 * paddleLength / 5) {
                angle = 30;
                newVelocity = Velocity.fromAngleAndSpeed(angle, ballSpeed);
            } else if (wichblock * .9 <= paddleLength) {
                angle = 60;
                newVelocity = Velocity.fromAngleAndSpeed(angle, ballSpeed);
            }

            return newVelocity;
        }
        System.out.print("pbm when ball hitting the paddle: not in the correct field");
        return null;
    }


    // Add this paddle to the game.
    public void addToGame(GameLevel g){
        g.addSprite(this);
        g.addCollidable(this);
    }
    public Rectangle getShape(){ return this.shape;}

}