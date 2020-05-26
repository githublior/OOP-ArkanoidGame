package listeners;

import gameobjects.Ball;
import gameobjects.Block;
import general.GameLevel;
// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks= remainingBlocks;
    }

    // Blocks that are hit and reach 0 hit-points should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    public void hitEvent(Block beingHit, Ball hitter) {
        if(beingHit.getMaxHits()<0) {
//            System.out.print("a block was removed\n");
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.game);
            this.remainingBlocks.decrease(1);
        }
//        System.out.print("nbr a remianing blocks are:"+ this.remainingBlocks.getValue()+"\n");

    }
}