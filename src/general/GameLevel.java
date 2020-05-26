package general;
import animations.Animation;
import animations.AnimationRunner;
import animations.CountdownAnimation;
import animations.PauseScreen;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gameobjects.*;
import geometry.*;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;
import listeners.*;
import java.util.Random;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import gameobjects.Velocity;


public class GameLevel implements Animation {

    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment;
    private GUI gui;
    private biuoop.KeyboardSensor keyboard;
    private PrintingHitListener hl;
    private Counter remainingBlocks, ballsCounter, scoreCounter, numberOfLives;
    private ScoreTrackingListener stl;
    private LivesIndicator livesIndicator;
    private  Paddle paddle;
    private int ballsRadius=4;
    private BallRemover balRemover;
    private BlockRemover blockRemover;
    private boolean init=true;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation levelInfo;
    private int width=800, height=600;  // frame size
    private LevelIndicator levelIndicator;
    private int nbrOfLives;


    public GameLevel(LevelInformation li, KeyboardSensor ks , AnimationRunner ar, Counter nbrOFlives, Counter scoreCounter){
        this.levelInfo = li;
        this.keyboard =ks;
        this.runner =ar;
        this.numberOfLives = nbrOFlives;
        this.gui= this.runner.getGui();
        this.scoreCounter = scoreCounter;
    }

    public Counter getNumberOfLives(){return this.numberOfLives;}

    public boolean shouldStop() { return !this.running; }

    public void doOneFrame(DrawSurface d) {
        // the logic from the previous playOneTurn method goes here.
        // the `return` or `break` statements should be replaced with
        // this.running = false;

        // game-specific logic
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        //Pause condition
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }

        // Stop Conidition
        // printing final score & return wether there are no blocks left or no balls left.
        if(this.balRemover.getRemainingBalls().getValue()<=0){
//            System.out.print("Game Over!\n Youre final score is :"+ this.scoreCounter.getValue() +"\n");
            System.out.print("no more balls for this level!");
            this.numberOfLives.decrease(1);
            this.running=false;
        }
        if(this.remainingBlocks.getValue()<=0){
            System.out.print("You finished the level! \n Your current score is :"+ this.scoreCounter.getValue() +"\n");
            this.running=false;
        }
    }

    public LevelInformation getLevelInfo(){return this.levelInfo;}

    public void playOneTurn() {
        this.paddle.getShape().setUpperLeft("x", this.environment.getSurface().getWidth()/2-30);
        this.createBalls(this.levelInfo.numberOfBalls(), this.levelInfo.initialBallVelocities()); // or a similar method


        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(3,3,this.sprites));

        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }


    public int getNbrRemainingBlock(){return  this.remainingBlocks.getValue();}
    public void addCollidable(Collidable c){ this.environment.addCollidable(c); }
    public void addSprite(Sprite s){ this.sprites.addSprite(s); }
    public void removeCollidable(Collidable c){this.environment.removeCollidable(c);}
    public void removeSprite(Sprite s){this.sprites.removeSprite(s);}
    public GUI getGui(){return this.gui;}
    public GameEnvironment getEnvironment(){return this.environment;}
    public int getScore(){return this.scoreCounter.getValue();}


    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void oldInitialize() {
        int width=600, height=600;
        this.gui = new GUI("Akanoid Game", width, height);
        keyboard = gui.getKeyboardSensor();
        DrawSurface g = this.gui.getDrawSurface();


        this.environment = new GameEnvironment(g, this);
        this.hl = new PrintingHitListener();


        geometry.Point a = new geometry.Point(0, 0);
        geometry.Point b = new geometry.Point(10, 10);
        geometry.Point c = new geometry.Point(200, 30);
        geometry.Point d = new geometry.Point(60, 200);
        geometry.Point e = new geometry.Point(150, 150);
        geometry.Point forpaddle = new geometry.Point((int)width/2-30, height-30);



        Line one = new Line(a, b);
        Line two = new Line(c, d);




        geometry.Rectangle rect1 = new geometry.Rectangle(e,300, 100);
//        Rectangle rect2 = new Rectangle(d, 40, 40);
//        Rectangle rect3 = new Rectangle(c, 50, 90);

        geometry.Rectangle forpad = new geometry.Rectangle(forpaddle, 60,10);
        gameobjects.Paddle paddle = new gameobjects.Paddle(keyboard, forpad, Color.DARK_GRAY, 3, 0, width);
        paddle.addToGame(this);

        gameobjects.Block b1 = new gameobjects.Block(rect1, 100, Color.YELLOW);
        b1.addToGame(this);
//        Block b2 = new Block(rect2, 10, Color.BLUE,environment);
//        b2.addToGame(this);
//        Block b3 = new Block(rect3, 1, Color.GREEN, environment);
//        b3.addToGame(this);

        Ball ball = new Ball(300, 300, this.ballsRadius, Color.RED, environment);
        gameobjects.Velocity v = new gameobjects.Velocity(0, -5);
        ball.setVelocity(v);
        ball.addToGame(this);



    }


    // Run the game -- start the animation loop.
    public void oldAss5layOneTurn(){
        //...
        biuoop.Sleeper sleeper = new biuoop.Sleeper();

        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        // move the paddle in the middle bottom.
        this.paddle.getShape().setUpperLeft("x", this.environment.getSurface().getWidth()/2-30);

        // create balls
        Velocity v= new Velocity(-1,-1);
        List<Velocity> vl = new ArrayList<Velocity>();
        vl.add(v);
        createBalls(2, vl);

        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            //Game Sprecific logic
            this.sprites.drawAllOn(d);
            this.sprites.notifyAllTimePassed();

            gui.show(d);

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                 sleeper.sleepFor(milliSecondLeftToSleep);
            }


            // printing final score & return wether there are no blocks left or no balls left.
            if(this.balRemover.getRemainingBalls().getValue()<=0){
                System.out.print("Game Over!\n Youre final score is :"+ this.scoreCounter.getValue() +"\n");
//                gui.close();
                break;
            }
            if(this.remainingBlocks.getValue()<=0){
                System.out.print("Game Over!\n Youre final score is :"+ this.scoreCounter.getValue() +"\n");
//                gui.close();
                break;
            }
        }
    }


    public void run(){
        this.runner=new AnimationRunner(this.gui, 60);
        do{
            playOneTurn();
            this.numberOfLives.decrease(1);
            System.out.print("new live\n");
        }while (this.numberOfLives.getValue()>0);
        gui.close();
        return;
    }


    public void initializeAss3() {
        int width=900, height=600;
        int widthrect=90, heightRect=30;
        int MaxHits =5;
        this.gui = new GUI("Ass3 Akanoid Game", width, height);
        keyboard = gui.getKeyboardSensor();


        DrawSurface g = this.gui.getDrawSurface();

        this.environment = new GameEnvironment(g, this);
        this.hl = new PrintingHitListener();



        Point a = new Point(width-widthrect, 100);

        java.util.List<geometry.Point> allFirstInters = new ArrayList<>();


        java.util.List<gameobjects.Block> blockList = new ArrayList<>();
        java.util.List <Color> colList = new ArrayList<>();
        colList.add(Color.YELLOW);
        colList.add(Color.BLUE);
        colList.add(Color.GREEN);
        colList.add(Color.ORANGE);
        colList.add(Color.PINK);
        colList.add(Color.CYAN);

        int k= 8;
        for (int i = 0; i <6 ; i++) {
            int l= k-i;
            for (int j = 0; j <l ; j++) {
                geometry.Point b = new geometry.Point(a.getX() - widthrect*j, a.getY() + heightRect* i);
                Rectangle rec = new Rectangle(b, widthrect, heightRect);
                Block block = new Block(rec, MaxHits,colList.get(i));
                block.addHitListener(this.hl);
                block.addToGame(this);



            }
        }
        Point forpaddle = new Point((int)width/2-30, height-30);
        Rectangle forpad = new Rectangle(forpaddle, 60,10);
        Paddle paddle = new Paddle(keyboard, forpad, Color.DARK_GRAY, 3, 0, width);
        paddle.addToGame(this);



        Ball ball1 = new Ball(700, 800, this.ballsRadius, Color.RED, environment);
        gameobjects.Velocity v1 = new gameobjects.Velocity(0, -5);
        ball1.setVelocity(v1);
        ball1.addToGame(this);

        Ball ball2 = new Ball(20, 600, this.ballsRadius, Color.RED, environment);
        gameobjects.Velocity v2 = new gameobjects.Velocity(1, 1);
        ball2.setVelocity(v2);
        ball2.addToGame(this);

    }


    public void initialize() {




        // paramaters

        int widthrect=90, heightRect=30; // standard Block size
//        int MaxHits =2;


        //adding the background Level
        this.sprites.addSprite(this.levelInfo.getBackground());
        // keyboard and name of the frame
//        this.gui = new GUI("Ass5 Akanoid Game", this.width, this.height);
//        keyboard = gui.getKeyboardSensor();


        DrawSurface g = this.gui.getDrawSurface();
        this.environment = new GameEnvironment(g, this);


        // initialize Score
//        this.scoreCounter = new Counter(0);
        this.stl = new ScoreTrackingListener(this.scoreCounter, this);
        this.addSprite(new ScoreIndicator(this.scoreCounter,this.levelInfo.titleColorforthislevel()));
//        //initialize lives
//        this.numberOfLives = new Counter(this.nbrOfLives);
        this.livesIndicator = new LivesIndicator(this.numberOfLives,this.levelInfo.titleColorforthislevel());
        this.addSprite(this.livesIndicator);
        // initialize level
        this.levelIndicator = new LevelIndicator(this.levelInfo, this.levelInfo.titleColorforthislevel());
        this.addSprite(this.levelIndicator);



//        this.hl = new PrintingHitListener ();

        // nbr of block in initilial game
        int initialNbrOfBlocks = this.levelInfo.numberOfBlocksToRemove();
        this.remainingBlocks = new Counter(initialNbrOfBlocks);
        this.blockRemover = new BlockRemover(this, this.remainingBlocks);

        // nbr of ball in initial game
//        createBalls(2);


//        int initialNbrOfBalls =2;
//        this.ballsCounter = new Counter(initialNbrOfBalls);
        this.balRemover = new BallRemover(this, this.ballsCounter);



//        Point a = new Point(width-widthrect, 100);
//
//        java.util.List<geometry.Point> allFirstInters = new ArrayList<>();
//
//
//        java.util.List<gameobjects.Block> blockList = new ArrayList<>();
//        java.util.List <Color> colList = new ArrayList<>();
//        colList.add(Color.YELLOW);
//        colList.add(Color.BLUE);
//        colList.add(Color.GREEN);
//        colList.add(Color.ORANGE);
//        colList.add(Color.PINK);
//        colList.add(Color.CYAN);
//
//
//         Creating the blocks of the level
//        int k= 5;
//        for (int i = 0; i <1 ; i++) {
//            int l= k-i;
//            for (int j = 0; j <l ; j++) {
//                Point b = new Point(a.getX() - widthrect*j, a.getY() + heightRect* i);
//                Rectangle rec = new Rectangle(b, widthrect, heightRect);
//                Block block = new Block(rec, MaxHits,colList.get(i));
////                block.addHitListener(this.hl);
//                block.addHitListener(this.blockRemover);
//                block.addHitListener(this.stl);
//                block.addToGame(this);
//            }
//        }

        // Creating the blocks of the level
        for (int i = 0; i <this.levelInfo.numberOfBlocksToRemove() ; i++) {
            Block block = this.levelInfo.blocks().get(i);
            block.addHitListener(this.blockRemover);
            block.addHitListener(this.stl);
            block.addToGame(this);
        }


        //DEATH ZONE
        Point ulDeathrect =new Point(0,height-20);
        Rectangle deathRect = new Rectangle(ulDeathrect, this.width,20);
        Block deathBlock = new Block(deathRect,Integer.MAX_VALUE, Color.black);
        deathBlock.setDeathBlock(true);
        deathBlock.addHitListener(this.balRemover);
        deathBlock.addToGame(this);

        //PADDLE
        Point forpaddle = new Point((int)this.width/2-30, this.height-30);
        Rectangle forpad = new Rectangle(forpaddle, this.levelInfo.paddleWidth(),10);
        Paddle paddle = new Paddle(this.keyboard, forpad, Color.DARK_GRAY, this.levelInfo.paddleSpeed(), 0, this.width);
        this.paddle=paddle;
        this.paddle.addToGame(this);

//        //BALLS
//        Ball ball1 = new Ball(100, 80, 4, Color.RED, environment);
//        Velocity v1 = new gameobjects.Velocity(0.4, -2);
//        ball1.setVelocity(v1);
//        ball1.addHitListener(balRemover);
//        ball1.addToGame(this);
//
//        Ball ball2 = new Ball(20, 60, 4, Color.RED, environment);
//        Velocity v2 = new gameobjects.Velocity(1, 1);
//        ball2.setVelocity(v2);
//        ball2.addHitListener(balRemover);
//        ball2.addToGame(this);
//

        this.init =false;

    }

    public void createBalls(int nbrOfBalls, List<Velocity> velocityList){
//        Random rand = new Random(); // create a random-number generator

        this.ballsCounter = new Counter(nbrOfBalls);
        this.balRemover.setRemainingBalls(this.ballsCounter);
//        this.balRemover = new BallRemover(this, this.ballsCounter);  //hitlistener



        for (int i = 0; i <nbrOfBalls ; i++) {
//            int x= rand.nextInt(this.environment.getSurface().getWidth()- this.ballsRadius);
//            int y = rand.nextInt(this.environment.getSurface().getHeight()/2-30)+30;
            int x = this.width/2;
            int y = this.height-60;
            Ball ball = new  Ball(x, y, this.ballsRadius, Color.RED, environment);
            Velocity v = velocityList.get(i);
            ball.setVelocity(v);
            ball.addHitListener(this.balRemover);
            ball.addToGame(this);
        }
    }
}