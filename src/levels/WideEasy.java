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
import geometry.Point;
import geometry.Rectangle;
import geometry.Line;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * this class implements the second level of the game.
 */
public class WideEasy implements LevelInformation {
    private static final int G_WIDTH = GameLevel.WIDTH;
    private static final int BLOCK_HEIGHT = 30;
    private static final int BLOCK_WIDTH = 50;
    private static final int FRAME_HEIGHT = GameLevel.FRAME_HEIGHT;

    private Background levelBG;

    /**
     * Constructor.
     */
    public WideEasy() {
        this.levelBG = createBackground();
    }

    /**
     * this method creates the background for the level.
     *
     * @return new background for the level
     */
    public Background createBackground() {
        List<DrawableShape> elements = new ArrayList<>();
        Color[] sun = new Color[3];
        sun[0] = new Color(255, 236, 124);
        sun[1] = new Color(246, 226, 105);
        sun[2] = new Color(251, 224, 63);
        Point sunCenter = new Point(150, 150);
        double linesEndX = FRAME_HEIGHT;
        double linesEndY = 300;
        for (int i = 0; i < 90; i++) {
            Line l = new Line(sunCenter, new Point(linesEndX, linesEndY));
            l.setColor(sun[2]);
            elements.add(l);
            linesEndX += 8;
        }
        int sunRadius = 70;
        for (int i = 0; i < 3; i++) {
            Circle c = new Circle(sunCenter, sunRadius, true, sun[i]);
            elements.add(c);
            sunRadius -= 10;
        }

        return new Background(Color.WHITE, elements);
    }

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocities = new ArrayList<>();
        double angleRight = 0;
        double angleLeft = 0;
        for (int i = 0; i < numberOfBalls(); i++) {
            angleRight += 10;
            angleLeft -= 10;
            Velocity v1 = Velocity.fromAngleAndSpeed(angleRight, 6);
            Velocity v2 = Velocity.fromAngleAndSpeed(angleLeft, 6);
            ballVelocities.add(v1);
            ballVelocities.add(v2);
        }
        return ballVelocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return G_WIDTH - 2 * FRAME_HEIGHT - 80;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return levelBG;
    }

    @Override
    public List<Block> blocks() {
        List<Block> gameBlocks = new ArrayList<>();
        List<Color> colors = createColorsList();
        int blockX = FRAME_HEIGHT;
        for (int i = 0; i < numberOfBlocksToRemove(); i++) {
            Block block = new Block(
                    new Rectangle(new Point(blockX, 300), BLOCK_WIDTH, BLOCK_HEIGHT),
                    colors.get(i), 1);
            gameBlocks.add(block);
            blockX += BLOCK_WIDTH;
        }
        return gameBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }

    /**
     * creates colors list (random colors).
     *
     * @return colors list (random colors)
     */
    private List<Color> createColorsList() {
        List<Color> colors = new ArrayList<>();
        for (int i = 0; i < numberOfBlocksToRemove(); i++) {
            colors.add(createColor());
        }
        return colors;
    }

    /**
     * returns random color.
     *
     * @return random color
     */
    private Color createColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }
}
