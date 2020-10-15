/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-01
 */
package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import gamesprites.Block;
import gamesprites.Velocity;
import gamesprites.SpriteCollection;
import gamesprites.BlockRemover;
import gamesprites.Paddle;
import gamesprites.Collidable;
import gamesprites.Sprite;
import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;
import hitlisteners.BallRemover;
import hitlisteners.HitListener;
import hitlisteners.ScoreTrackingListener;
import other.Counter;

import java.awt.Color;
import java.util.Random;

/**
 * this class creates a game object that simulates the game of arkanoid.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter numOfBlocks = new Counter(0);
    private Counter numOfBalls = new Counter(3);
    private static final int HEIGHT = 600;
    private static final int WIDTH = 800;
    private static final int MAX_ROWS = 6;
    private static final int BLOCK_WIDTH = 60;
    private static final int BLOCK_HEIGHT = 30;
    private static final int MAX_BLOCKS = 10;
    private static final int START_OF_ROWS_Y = 100;
    private static final int FRAME_HEIGHT = 25;
    private static final int PADDLE_HEIGHT = 5;
    private static final int PADDLE_WIDTH = 100;
    private static final int BALL_RADIUS = 5;
    public static final int COUNTER = 0;
    private Counter score;
    private GUI gui;

    /**
     * constructor.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.score = new Counter(0);
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
     * this function generates a random point between two bounds.
     *
     * @param min the minimum bound
     * @param max the maximum bound
     * @return an integer between the two bounds
     */
    private static int randomBounds(int min, int max) {
        //return random number between min and max
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    /**
     * this method creates a random color with rgb.
     *
     * @return a new Color type color
     */
    private Color createColor() {
        //creating new random color
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        return new Color(r, g, b);
    }

    /**
     * create new ball and add to this game.
     */
    private void createBall() {
        Velocity v = gamesprites.Velocity.fromAngleAndSpeed(25, 5);
        Color color = createColor();
        double ballY = HEIGHT - FRAME_HEIGHT - BALL_RADIUS - 50;
        double ballX = WIDTH / 2;
        Ball ball = new Ball(ballX, ballY, BALL_RADIUS,
                color, v, this.environment);
        ball.addToGame(this);
    }

    /**
     * create new ball and add to this game.
     */
    private void createBall2() {
        Velocity v = Velocity.fromAngleAndSpeed(340, 5);
        Color color = createColor();
        double ballY = HEIGHT - FRAME_HEIGHT - BALL_RADIUS - 50;
        double ballX = WIDTH / 2;
        Ball ball = new Ball(ballX, ballY, BALL_RADIUS,
                color, v, this.environment);
        ball.addToGame(this);
    }

    /**
     * create new ball and add to this game.
     */
    private void createBall3() {
        Velocity v = Velocity.fromAngleAndSpeed(330, 5);
        Color color = createColor();
        double ballY = HEIGHT - FRAME_HEIGHT - BALL_RADIUS - 50;
        double ballX = WIDTH / 2;
        Ball ball = new Ball(ballX, ballY, BALL_RADIUS,
                color, v, this.environment);
        ball.addToGame(this);
    }

    /**
     * create new blocks for the game.
     */
    private void createBlocks() {
        ScoreTrackingListener scoreTracking = createScore();
        BlockRemover blockRemover = new BlockRemover(this, numOfBlocks);
        for (int i = 1; i <= MAX_ROWS; i++) {
            Color color = createColor();
            int counter = 1;
            for (int j = 1; j <= MAX_BLOCKS - i; j++) {
                if (i == 1) {
                    // only top row has counter of 2. all other rows - 1
                    counter = 2;
                }
                Point startingPoint =
                        new Point(WIDTH - FRAME_HEIGHT - (BLOCK_WIDTH * j),
                                START_OF_ROWS_Y + (BLOCK_HEIGHT * i));
                Block block = new Block(new Rectangle(startingPoint,
                        BLOCK_WIDTH, BLOCK_HEIGHT), color, counter);
                block.addToGame(this);
                this.numOfBlocks.increase(1);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTracking);
            }
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
     */
    private void createPaddle() {
        double paddleX = FRAME_HEIGHT;
        double paddleY = HEIGHT - FRAME_HEIGHT - PADDLE_HEIGHT
                - 2 * BALL_RADIUS;
        biuoop.KeyboardSensor keyboard = this.gui.getKeyboardSensor();
        Paddle paddle = new Paddle(new Rectangle(new Point(paddleX, paddleY),
                PADDLE_WIDTH, PADDLE_HEIGHT), keyboard,
                WIDTH - FRAME_HEIGHT, FRAME_HEIGHT);
        paddle.addToGame(this);
    }

    /**
     * this method intializes a new game with blocks, ball, and paddle.
     */
    public void initialize() {
        this.gui = new GUI("Arkanoid", WIDTH, HEIGHT);
        createBall();
        createBall2();
        createBall3();
        BallRemover ballRemover = new BallRemover(this, numOfBalls);
        createDeathRegion(ballRemover);
        createFrame();
        createBlocks();
        createPaddle();
    }

    /**
     * this method starts the animation loop and starts the game.
     */
    public void run() {
        boolean running = true;
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (running) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            //if there are no more blocks in the game
            if (numOfBlocks.getValue() == 0) {
                score.increase(100);
                running = false;
            }
            //if the are no more balls in the game
            if (numOfBalls.getValue() == 0) {
                running = false;
            }
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        gui.close();
    }

    /**
     * this is the main method of the class.
     * it initializes and runs the game.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
