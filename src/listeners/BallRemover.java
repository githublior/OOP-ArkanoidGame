
package listeners;

import gameobjects.Ball;
import gameobjects.Block;
import general.GameLevel;
// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls =remainingBalls;
    }
    public Counter getRemainingBalls(){return  this.remainingBalls;}
    public void setRemainingBalls(Counter Balls){this.remainingBalls =Balls;}

    // Blocks that are hit and reach 0 hit-points should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    public void hitEvent(Block deathBlock, Ball fallenBall) {
        if(deathBlock.getDeathBlock() == true) {

            System.out.print("a ball Hit the DEATH BLOCK! \n");
            fallenBall.removeHitListener(this);
            fallenBall.removeFromGame(this.game);
            this.remainingBalls.decrease(1);

            System.out.print("nbr a remianing balls are:" + this.remainingBalls.getValue() + "\n");
        }

    }
}