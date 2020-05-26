import animations.AnimationRunner;
import biuoop.DrawSurface;
import biuoop.GUI;
import general.GameFlow;
import general.GameLevel;
import general.GameLevel;
import levels.DirectHit;
import levels.LevelInformation;
import levels.SkyScraper;

import java.util.ArrayList;
import java.util.List;

public class ass5game {

    public static void main(String[] args) {
        int width =800, height=600;

        GUI gui = new GUI("Akanoid Game", width, height);
        AnimationRunner ar =new AnimationRunner(gui,60);

        DirectHit l1 = new DirectHit();
        SkyScraper l2 =new SkyScraper();

        List<LevelInformation> levelist = new ArrayList<>();
        levelist.add(l1);
        levelist.add(l2);

        // getting the levels from the inputs
        List<LevelInformation> newlevelist = new ArrayList<>();

        for (int i = 0; i < args.length ; i++) {
            try{
            if(Integer.parseInt(args[i])>0 && Integer.parseInt(args[i])<=levelist.size()){
                newlevelist.add(levelist.get(Integer.parseInt(args[i])-1));
            }}
            catch (NumberFormatException e){continue;}
        }


        GameFlow gf = new GameFlow(ar,gui.getKeyboardSensor(),4);
        // checking wether playing the inputs level, or else all the level.
       if (newlevelist.size()>=1){ gf.runLevels(newlevelist); }
       else{gf.runLevels(levelist);}

    }
}
