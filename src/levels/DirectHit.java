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
import geometry.DrawableShape;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * this class implements the first level of the game.
 */
public class DirectHit implements LevelInformation {
    private static final int WIDTH = GameLevel.WIDTH;
    private static final int BLOCK_SIZE = 40;
    private static final int INFO_HEIGHT = GameLevel.INFO_HEIGHT;

    private Background levelBG;

    /**
     * constructor.
     */
    public DirectHit() {
        levelBG = createBackground();
    }

    /**
     * this method crates the background of the level.
     *
     * @return the new background for the level.
     */
    public Background createBackground() {
        int lineLength = 230;
        List<DrawableShape> elements = new ArrayList<>();
        Line l = new Line(WIDTH / 2, INFO_HEIGHT,
                WIDTH / 2, INFO_HEIGHT + lineLength);
        l.setColor(Color.BLUE);
        elements.add(l);
        l = new Line(WIDTH / 2 - lineLength / 2, INFO_HEIGHT + lineLength / 2,
                WIDTH / 2 + lineLength / 2, INFO_HEIGHT + lineLength / 2);
        l.setColor(Color.BLUE);
        elements.add(l);
        int circleRadius = 40;
        Point circleCenter = new Point(WIDTH / 2,
                INFO_HEIGHT + lineLength / 2);
        for (int i = 0; i < 3; i++) {
            Circle c = new Circle(circleCenter, circleRadius,
                    false, Color.BLUE);
            elements.add(c);
            circleRadius += 30;
        }
        return new Background(Color.GRAY, elements);
    }

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocities = new ArrayList<>();
        Velocity v = Velocity.fromAngleAndSpeed(0, 5);
        ballVelocities.add(v);
        return ballVelocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return levelBG;
    }

    @Override
    public List<Block> blocks() {
        List<Block> gameBlocks = new ArrayList<>();
        Block block = new Block(new Rectangle(new Point((WIDTH / 2) - (BLOCK_SIZE / 2),
                115), BLOCK_SIZE, BLOCK_SIZE), Color.RED, 1);
        gameBlocks.add(block);
        return gameBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
