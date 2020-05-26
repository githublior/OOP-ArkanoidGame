package levels;

import backgrounds.BgLevel1;
import backgrounds.BgLevel2;
import gameobjects.Block;
import gameobjects.Sprite;
import gameobjects.Velocity;
import geometry.Point;
import geometry.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SkyScraper implements  LevelInformation{
    //    private GameLevel game;
    private int width =800, height=600;  // frame size
    private int widthrect =90, heightRect =30;

    //    public DirectHit(GameLevel game){
//        this.game=game;
//    }
    public int numberOfBalls(){return 4;}
    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    public List<Velocity> initialBallVelocities(){
        List<Velocity> listV = new ArrayList<>();
        Velocity v1 = new Velocity(0, -4);
        Velocity v2 = new Velocity(1, -4);
        Velocity v3 = new Velocity(2, -4);
        Velocity v4 = new Velocity(-1, -4);
        listV.add(v1);
        listV.add(v2);
        listV.add(v3);
        listV.add(v4);
        return listV;
    }
    public int paddleSpeed(){return 6;}
    public int paddleWidth(){return 80;}
    // the level name will be displayed at the top of the screen.
    public String levelName(){return "SkyScraper";}
    // Returns a sprite with the background of the level
    public Sprite getBackground(){
        BgLevel2 bgl2 = new BgLevel2();
        return  bgl2;
    }
    // The Blocks that make up this level, each block contains
    // its size, color and location.
    public List<Block> blocks(){
        List<Block> listB = new ArrayList<>();

        geometry.Point a = new geometry.Point(width-widthrect, 100);

        java.util.List<gameobjects.Block> blockList = new ArrayList<>();
        java.util.List <Color> colList = new ArrayList<>();
        colList.add(Color.YELLOW);
        colList.add(Color.BLUE);
        colList.add(Color.GREEN);
        colList.add(Color.ORANGE);
        colList.add(Color.PINK);
        colList.add(Color.CYAN);



        //        Creating the blocks of the level
        int k= 6;
        for (int i = 0; i <6 ; i++) {
            int l= k-i;
            for (int j = 0; j <l ; j++) {
                geometry.Point b = new geometry.Point(a.getX() - widthrect*j, a.getY() + heightRect* i);
                geometry.Rectangle rec = new Rectangle(b, widthrect, heightRect);
                Block block = new Block(rec, l,colList.get(i));
                listB.add(block);
            }
        }


        return listB;
    }
    // Number of levels that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    public int numberOfBlocksToRemove(){return 21;}
    public Color titleColorforthislevel(){return Color.BLACK;}


}


