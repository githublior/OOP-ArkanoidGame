package listeners;


import gameobjects.Ball;
import gameobjects.Block;
import general.GameLevel;

public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    private GameLevel game;


    public ScoreTrackingListener(Counter scoreCounter, GameLevel game) {
        this.currentScore = scoreCounter;
        this.game=game;
    }


//    public int getCurrentScore(){return this.currentScore.getValue();}


    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(5);
       if(beingHit.getMaxHits()<=0){
           this.currentScore.increase(10);
       }
       if(this.game.getNbrRemainingBlock()<=0){
           this.currentScore.increase(100);
       }
    }
}