package backgrounds;

        import biuoop.DrawSurface;
        import gameobjects.Block;
        import gameobjects.Sprite;
        import general.GameLevel;
        import geometry.Rectangle;

        import javax.imageio.ImageIO;
        import java.awt.*;
        import java.awt.image.ImageObserver;
        import java.awt.image.ImageProducer;
        import java.io.File;
        import java.nio.file.Path;
        import java.util.ArrayList;

public class BgLevel2 implements Sprite {
    private DrawSurface d;
    int blockWidth = 20, blockHeight =20;

    public void drawOn(DrawSurface d){
        this.d = d;
        d.setColor(Color.BLUE);
        d.fillRectangle(0,0 , this.d.getWidth(), this.d.getHeight());

//        d.drawImage(0,0 ,);

    }
    // notify the sprite that time has passed
    public void timePassed(){ }
}
//    String wichSprite();
