/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-01
 */
package game;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gamesprites.Block;
import gamesprites.BlockRemover;
import gamesprites.Sprite;
import gamesprites.Collidable;
import gamesprites.SpriteCollection;
import gamesprites.Paddle;
import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;
import hitlisteners.BallRemover;
import hitlisteners.HitListener;
import hitlisteners.ScoreTrackingListener;
import indicators.LevelIndicator;
import indicators.ScoreIndicator;
import levels.LevelInformation;
import other.Counter;

import java.awt.Color;
import java.util.Random;

/**
 * this class creates a level of game object that simulates the game of arkanoid.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private LevelInformation levelInfo;
    private Counter numOfBlocks = new Counter(0);
    private Counter NUM_OF_BALLS = new Counter(0);
    public static final int HEIGHT = 600;
    public static final int WIDTH = 800;
    public static final int INFO_HEIGHT = 17;
    public static final int FRAME_HEIGHT = 25;
    private final int PADDLE_HEIGHT = 5;
    private final int BALL_RADIUS = 5;
    public static final int COUNTER = 0;
    private GUI gui;
    private AnimationRunner runner;
    private Counter score;
    private boolean running;
    private KeyboardSensor keyboard;

    /**
     * constructor.
     *
     * @param levelInfo the information of the current level.
     * @param score the score of the game.
     * @param gui the gui the game is run on.
     * @param runner the runner running the animation.
     * @param keyboard the keyboard used in the game.
     */
    public GameLevel(LevelInformation levelInfo, Counter score, GUI gui,
                     AnimationRunner runner, KeyboardSensor keyboard) {
        this.gui = gui;
        this.runner = runner;
        this.keyboard = keyboard;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.score = score;
        this.running = true;
        this.levelInfo = levelInfo;
    }

    /**
     * this method adds a collidable to the game environment.
     *
     * @param c the collidable to be added
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * this method adds a sprite to the collection of sprites of the game.
     *
     * @param s the sprite to be added
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * this method removes a collidable from the game.
     *
     * @param c collidable to be removed
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * this method removes a sprite from the game.
     *
     * @param s the sprite to be removed
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * this method creates a random color with rgb.
     *
     * @return a new Color type color
     */
    public static Color createColor() {
        //creating new random color
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        return new Color(r, g, b);
    }

    /**
     * create new blocks for the game.
     */
    private void createBlocks() {
        ScoreTrackingListener scoreTracking = createScore();
        BlockRemover blockRemover = new BlockRemover(this, numOfBlocks);
        for (Block block : levelInfo.blocks()) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTracking);
            numOfBlocks.increase(1);
        }
    }

    /**
     * create scoreTracking object to track the game score
     * and an indicator to display the score.
     *
     * @return the score tracking
     */
    private ScoreTrackingListener createScore() {
        ScoreTrackingListener scoreTracking = new ScoreTrackingListener(score);
        ScoreIndicator indicator = new ScoreIndicator(score);
        indicator.addToGame(this);
        return scoreTracking;
    }

    /**
     * create "death region" - a block that using the hit listener will remove
     * balls from the game.
     *
     * @param ballRemover the hit listener
     */
    private void createDeathRegion(HitListener ballRemover) {
        //create the death region
        Block deathRegion = new Block(new Rectangle(new Point(0, HEIGHT - FRAME_HEIGHT),
                WIDTH, HEIGHT), Color.BLACK);
        //add collidable to game
        deathRegion.addToGame(this);
        deathRegion.addHitListener(ballRemover);
    }

    /**
     * this method creates the frame of the screen with blocks.
     */
    private void createFrame() {
        Color frameColor = createColor();
        Block upperBlock = new Block(new Rectangle(new Point(0, 0),
                WIDTH, FRAME_HEIGHT), frameColor, COUNTER);
        Block rightBlock = new Block(new Rectangle(new Point(
                WIDTH - FRAME_HEIGHT, 0), FRAME_HEIGHT, HEIGHT),
                frameColor, COUNTER);
        Block leftBlock = new Block(new Rectangle(
                new Point(0, 0), FRAME_HEIGHT, HEIGHT), frameColor, COUNTER);
        upperBlock.addToGame(this);
        rightBlock.addToGame(this);
        leftBlock.addToGame(this);
    }

    /**
     * this method creates the paddle object and adds it to the game.
     *
     * @return new paddle for the game
     */
    private Paddle createPaddle() {
        double paddleX = FRAME_HEIGHT;
        double paddleY = HEIGHT - FRAME_HEIGHT - PADDLE_HEIGHT
                - 2 * BALL_RADIUS;
        Paddle paddle = new Paddle(new Rectangle(new Point(paddleX, paddleY),
                levelInfo.paddleWidth(), PADDLE_HEIGHT), keyboard,
                WIDTH - FRAME_HEIGHT, FRAME_HEIGHT);
        paddle.addToGame(this);
        return paddle;
    }

    /**
     * this method intializes a new game with blocks, ball, and paddle.
     */
    public void initialize() {
        levelInfo.getBackground().addToGame(this);
        levelInfo.getBackground().addToGame(this);
        double ballY = HEIGHT - FRAME_HEIGHT - BALL_RADIUS - 50;
        double ballX = WIDTH / 2;
        for (int i = 0; i < levelInfo.numberOfBalls(); i++) {
            Ball ball = new Ball(ballX, ballY, BALL_RADIUS, createColor(),
                    levelInfo.initialBallVelocities().get(i), environment);
            ball.addToGame(this);
            NUM_OF_BALLS.increase(1);
        }
        LevelIndicator levelIndicator = new LevelIndicator(levelInfo);
        levelIndicator.addToGame(this);
        createFrame();
        BallRemover ballRemover = new BallRemover(this, NUM_OF_BALLS);
        createDeathRegion(ballRemover);
        createBlocks();
    }

    /**
     * this method starts the animation loop and starts the game.
     */
    public void run() {
        while (running) {
            DrawSurface d = gui.getDrawSurface();
            Paddle player = createPaddle();
            // countdown before turn starts.
            this.runner.run(new CountdownAnimation(2, 3, sprites));
            this.running = true;
            runner.run(this);
            gui.show(d);
            if (!running) {
                player.removeFromGame(this);
                break;
            }
        }
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        sprites.drawAllOn(d);
        sprites.notifyAllTimePassed();
        if (numOfBlocks.getValue() == 0) {
            // destroying all blocks is worth another 100 points.
            score.increase(100);
            running = false;
        }
        if (NUM_OF_BALLS.getValue() == 0) {
            running = false;
        }
        if (keyboard.isPressed("p")) {
            //run the pause screen
            runner.run(new KeyPressStoppableAnimation(keyboard, new PauseScreen(), KeyboardSensor.SPACE_KEY));
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * this method returns the number of blocks in the level.
     *
     * @return Counter of number of blocks
     */
    public Counter getNumOfBlocks() {
        return numOfBlocks;
    }

    /**
     * this method returns the number of blocks in the level.
     *
     * @return Counter of number of blocks
     */
    public Counter getNumOfBalls() {
        return NUM_OF_BALLS;
    }
}
