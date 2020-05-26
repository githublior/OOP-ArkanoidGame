package backgrounds;

import biuoop.DrawSurface;
import gameobjects.Sprite;
import general.GameLevel;

import java.awt.*;

public class BgLevel1 implements Sprite {
    private DrawSurface d;
    int blockWidth = 20, blockHeight =20;
//    public BgLevel1(DrawSurface d){this.d = d;}

    public void drawOn(DrawSurface d){
        this.d = d;
        d.setColor(Color.BLACK);
        d.fillRectangle(0,0 , this.d.getWidth(), this.d.getHeight());
        int XcenterofBlock = d.getWidth()/2;
        int YcenterofBlock =d.getHeight()/8 -this.blockHeight/2;

        d.setColor(Color.BLUE);
        d.drawCircle(XcenterofBlock, YcenterofBlock, 30);
        d.drawCircle(XcenterofBlock, YcenterofBlock, 50);
        d.drawCircle(XcenterofBlock, YcenterofBlock, 70);

        int lineLength = 40;

        // left line
        int y1= YcenterofBlock;
        int x2= d.getWidth()/2 - this.blockWidth/2-10;
        int x1 = x2-lineLength ;
        int y2=y1;
        d.drawLine(x1,y1,x2,y2);

        // right line
        x1 = d.getWidth()/2 + this.blockWidth/2 + 10;
        x2 = x1+lineLength ;
        d.drawLine(x1,y1,x2,y2);
        
        // upper line
        x1= x2= XcenterofBlock;
        y2= YcenterofBlock - this.blockHeight/2 -10;
        y1= y2- lineLength;
        d.drawLine(x1,y1,x2,y2);

        //bottom line
        y1= YcenterofBlock + this.blockHeight/2  + 10;
        y2 =y1 + lineLength;
        d.drawLine(x1,y1,x2,y2);

    }
    // notify the sprite that time has passed
    public void timePassed(){}
//    String wichSprite();
}