package gameobjects;

import biuoop.DrawSurface;
import java.awt.*;
import java.util.*;
import java.util.List;

import general.GameEnvironment;
import general.GameLevel;
import geometry.Rectangle;
import geometry.Point;
import general.GameLevel;
import listeners.HitListener;

public class Block implements Collidable , Sprite, HitNotifier{
    private Rectangle shape;
    private int maxHits;
    private Color color;
    private List<HitListener> hitListeners= new ArrayList<>();
    private Point hitPoint;
    private boolean deathBlock=false;

    public Block(Rectangle shape, int maxHits, Color col){
        this.shape = shape;
        this.maxHits = maxHits;
        this.color = col;
//        environment.addCollidable(this);

    }
    public void setDeathBlock(boolean bool){this.deathBlock=bool;}
    public boolean getDeathBlock(){return this.deathBlock;}

    public void addHitListener(HitListener hl) {
        this.hitListeners.add( hl);
    }
    public void removeHitListener(HitListener hl){this.hitListeners.remove(hl);}

    public String getCol(){return "Block";}
    public String wichSprite(){return "Block";}
    public void setHitPoints(Point p){ this.hitPoint = p;}
    public Point getHitPoints(){return this.hitPoint;}

    public Rectangle getCollisionRectangle(){return this.shape;}
    public Color getColor(){return this.color;}
    public int getMaxHits(){return  this.maxHits;}

    public void drawOn(DrawSurface surface) {
        if(this.maxHits>=0) {
            surface.setColor(Color.BLACK);
            surface.drawRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(), (int) this.shape.getWidth(), (int) this.shape.getHeight());

            surface.setColor(this.color);
            surface.fillRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(), (int) this.shape.getWidth(), (int) this.shape.getHeight());
            surface.setColor(Color.DARK_GRAY);
            if(this.deathBlock == false) {
                if (this.maxHits > 0) {
                    surface.drawText((int) (this.shape.getUpperLeft().getX() + this.shape.getWidth() / 2), (int) (this.shape.getUpperLeft().getY() + this.shape.getHeight() / 2), Integer.toString(this.maxHits), 10);
                } else {
                    surface.drawText((int) (this.shape.getUpperLeft().getX() + this.shape.getWidth() / 2), (int) (this.shape.getUpperLeft().getY() + this.shape.getHeight() / 2), "X", 10);

                }
            }

        }
        else{System.out.print("PBM ! asked to draw a removed block!#BLOCK #DRAWON\n");}
    }

    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity){
        setHitPoints(collisionPoint);
        final int up=0, right=1,down=2,left=3;
        double dx=currentVelocity.dx , dy=currentVelocity.dy;

        Velocity newVelocity = null;

        this.maxHits-- ;

        int side = this.shape.wichSidePHitMe(collisionPoint);
        switch (side){

            case up:
                newVelocity = new Velocity(dx, -java.lang.Math.abs(dy));
                break;
            case down:
                newVelocity = new Velocity(dx, java.lang.Math.abs(dy));
                break;
            case left:
                newVelocity = new Velocity(-java.lang.Math.abs(dx),dy);
                break;
            case right:
                newVelocity = new Velocity(java.lang.Math.abs(dx),dy);
                break;
            default:
                System.out.println("Error: no velocity");
        }
        this.notifyHit(hitter);

//        if(this.maxHits < 0){
//            this.removeFromGame(this.environment.getGame());
//        }
        return  newVelocity;
    }
    public void timePassed(){}
    public void addToGame(GameLevel g){
        g.addSprite(this);
        g.addCollidable(this);
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    public void removeFromGame(GameLevel game){
        game.removeCollidable(this);
        game.removeSprite(this);
    }
}
