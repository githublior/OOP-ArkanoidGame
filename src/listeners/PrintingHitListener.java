package listeners;

import gameobjects.Ball;
import gameobjects.Block;
import listeners.HitListener;


public class PrintingHitListener implements HitListener {
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block with " + beingHit.getHitPoints() + " points was hit.");
    }
}