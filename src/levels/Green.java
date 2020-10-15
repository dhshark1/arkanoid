/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-18
 */
package levels;

import game.GameLevel;
import gamesprites.Block;
import gamesprites.Sprite;
import gamesprites.Velocity;
import geometry.Circle;
import geometry.Point;
import geometry.DrawableShape;
import geometry.Line;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * this class implements the third level of the game.
 */
public class Green implements LevelInformation {
    static final int R = 3;
    private static final int WIDTH = GameLevel.WIDTH;
    private static final int HEIGHT = GameLevel.HEIGHT;
    private static final int BLOCK_HEIGHT = 30;
    private static final int BLOCK_WIDTH = 50;
    private static final int FRAME_HEIGHT = GameLevel.FRAME_HEIGHT;
    private static final int MAX_ROWS = 6;
    private static final int MAX_BLOCKS = 13;
    private Background levelBG;

    /**
     * constructor.
     */
    public Green() {
        this.levelBG = createBackground();
    }

    /**
     * A method that generates random lines within the screen.
     *
     * @return new Line with a start and end coordinates
     */
    private Line generateRandomLines() {
        Random rand = new Random();
        //generate 4 random coordinates
        double x1 = rand.nextInt(WIDTH) + 1; // get integer in range of width
        double x2 = rand.nextInt(WIDTH) + 1; // get integer in range of width
        double y1 = rand.nextInt(HEIGHT) + 1; // get integer in range of height
        double y2 = rand.nextInt(HEIGHT) + 1; // get integer in range of height
        //return new coordinates as a new line
        return new Line(x1, y1, x2, y2);
    }

    /**
     * this method creates the background for the level.
     *
     * @return the new background of the level
     */
    private Background createBackground() {
        List<DrawableShape> elements = new ArrayList<>();
        addLines(elements);
        return new Background(new Color(50, 200, 80), elements);
    }

    /**
     * this method adds the lines to be drawn on the screen to elements.
     *
     * @param elements the array the contains all the items to be drawn
     */
    private void addLines(List<DrawableShape> elements) {
        for (int i = 0; i < 10; i++) {
            //generate random line
            Line l = generateRandomLines();
            //get middle coordinates
            double x = l.middle().getX();
            double y = l.middle().getY();
            Circle middle = new Circle(new Point((int) x, (int) y), R, true, Color.RED);
            //add to array
            elements.add(middle);
            elements.add(l);
        }
    }

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocities = new ArrayList<>();
        double angle = 50;
        Velocity v = Velocity.fromAngleAndSpeed(angle, 6);
        ballVelocities.add(v);
        v = Velocity.fromAngleAndSpeed(-angle, 6);
        ballVelocities.add(v);
        return ballVelocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return levelBG;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int pointY = 100;
        for (int i = 1; i <= MAX_ROWS; i++) {
            Color color = GameLevel.createColor();
            int counter = 1;
            for (int j = 1; j <= MAX_BLOCKS - i; j++) {
                if (i == 1) {
                    // only top row has counter of 2. all other rows - 1
                    counter = 2;
                }
                Point startingPoint =
                        new Point(WIDTH - FRAME_HEIGHT - (BLOCK_WIDTH * j),
                                pointY + (BLOCK_HEIGHT * i));
                Block block = new Block(new Rectangle(startingPoint,
                        BLOCK_WIDTH, BLOCK_HEIGHT), color, counter);
                blocks.add(block);
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
