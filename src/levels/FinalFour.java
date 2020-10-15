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
import geometry.DrawableShape;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * this class implements the fourth level of the game
 */
public class FinalFour implements LevelInformation {
    private static final int WIDTH = GameLevel.WIDTH;
    private static final int HEIGHT = GameLevel.HEIGHT;
    private static final int BLOCK_HEIGHT = 30;
    private static final int BLOCK_WIDTH = 50;
    private static final int FRAME_HEIGHT = GameLevel.FRAME_HEIGHT;
    private static final int MAX_ROWS = 7;
    private static final int MAX_BLOCKS = 16;
    private Background levelBG;

    /**
     * constructor.
     */
    public FinalFour() {
        this.levelBG = createBackground();
    }

    /**
     * this method creates the background for the level.
     *
     * @return new background of the level.
     */
    private Background createBackground() {
        List<DrawableShape> elements = new ArrayList<>();
        addRectangles(elements);
        return new Background(new Color(20, 111, 213), elements);
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
     * this method generates random rectangles on the screen.
     *
     * @return the rectangle generated
     */
    private Rectangle generateRandomRectangles() {
        int x = randomBounds(0, WIDTH);
        int y = randomBounds(0, HEIGHT);
        return new geometry.Rectangle(new Point(x, y),
                randomBounds(75, 300), randomBounds(75, 300));
    }

    /**
     * this method adds the rectangles to the elements list.
     * it will be drawn on the screen.
     *
     * @param elements the elements to be drawn on the screen.
     */
    private void addRectangles(List<DrawableShape> elements) {
        for (int i = 0; i < 5; i++) {
            elements.add(generateRandomRectangles());
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
        Velocity v = Velocity.fromAngleAndSpeed(angle, 5);
        ballVelocities.add(v);
        v = Velocity.fromAngleAndSpeed(-angle, 5);
        ballVelocities.add(v);
        return ballVelocities;
    }

    @Override
    public int paddleSpeed() {
        return 4;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return levelBG;
    }

    @Override
    public List<Block> blocks() {
        List<Block> gameBlocks = new ArrayList<>();
        double pointY = 100;
        for (int i = 1; i <= MAX_ROWS; i++) {
            Color color = GameLevel.createColor();
            for (int j = 1; j < MAX_BLOCKS; j++) {
                Point recPoint = new Point(WIDTH - FRAME_HEIGHT - (BLOCK_WIDTH * j), pointY);
                Block block = new Block(
                        new Rectangle(recPoint, BLOCK_WIDTH, BLOCK_HEIGHT),
                        color, 1);
                gameBlocks.add(block);
            }
            pointY += BLOCK_HEIGHT;
        }
        return gameBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
