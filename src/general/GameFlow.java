package general;

import animations.AnimationRunner;
import animations.EndScreen;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import levels.LevelInformation;
import listeners.Counter;

import java.awt.*;
import java.util.List;

public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private Counter NbrOfLives;
    private Counter scoreCounter;

    public GameFlow(AnimationRunner ar, KeyboardSensor ks, int NbrOfLives) {
        this.ar =ar;
        this.ks=ks;
        this.NbrOfLives=new Counter(NbrOfLives);
    }

    public void runLevels(List<LevelInformation> levels) {
        // ...
        Sleeper sleeper = new Sleeper();
        this.scoreCounter = new Counter(0);
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.ks, this.ar, this.NbrOfLives, this.scoreCounter);

            level.initialize();
            this.ar = new AnimationRunner(this.ar.getGui(), 60);
            while (level.getNbrRemainingBlock() > 0 && this.NbrOfLives.getValue() > 0) {
                level.playOneTurn();
//                this.NbrOfLives.decrease(1);
//                System.out.print("new life\n");
            }
        }

        this.ar.run(new EndScreen(this.ks, this.NbrOfLives, this.scoreCounter));
        this.ar.getGui().close();
        return;




    }
}