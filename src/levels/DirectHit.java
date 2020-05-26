package levels;

import backgrounds.BgLevel1;
import biuoop.DrawSurface;
import gameobjects.Block;
import gameobjects.Sprite;
import gameobjects.Velocity;
import general.GameLevel;
import geometry.Point;
import geometry.Rectangle;

import java.awt.*;
import java.util.*;
import java.util.List;

public class DirectHit implements LevelInformation {
//    private GameLevel game;
    private int width=800, height=600;  // frame size
    private int blockWidth = 20, blockHeight =20;

//    public DirectHit(GameLevel game){
//        this.game=game;
//    }
    public int numberOfBalls(){return 1;}
    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    public List<Velocity> initialBallVelocities(){
        List<Velocity> listV = new ArrayList<>();
        Velocity v = new Velocity(0, -4);
        listV.add(v);
        return listV;
    }
    public int paddleSpeed(){return 6;}
    public int paddleWidth(){return 60;}
    // the level name will be displayed at the top of the screen.
    public String levelName(){return "Direct Hit";}
    // Returns a sprite with the background of the level
    public Sprite getBackground(){
        BgLevel1 bgl1 = new BgLevel1();
        return  bgl1;
    }
    // The Blocks that make up this level, each block contains
    // its size, color and location.
    public List<Block> blocks(){
        List<Block> listB = new ArrayList<>();


        Point ul = new Point(this.width/2-(this.blockWidth/2), this.height/8 - this.blockHeight);
        Rectangle rectangle = new Rectangle(ul, this.blockWidth,this.blockHeight);
        Block block = new Block(rectangle,0, Color.RED);
        listB.add(block);
        return listB;
    }
    // Number of levels that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    public int numberOfBlocksToRemove(){return 1;}
    public Color titleColorforthislevel(){return Color.WHITE;}
}
