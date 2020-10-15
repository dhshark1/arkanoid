/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-18
 */
package levels;

import gamesprites.Block;
import gamesprites.Sprite;
import gamesprites.Velocity;

import java.util.List;

/**
 * this is the levelInformation interface.
 */
public interface LevelInformation {
    /**
     * this method returns the number of balls in the level.
     *
     * @return integer of number of balls
     */
    int numberOfBalls();

    /**
     * this is the intial velocity of each ball.
     *
     * @return a list of velocities for each of the balls.
     */
    List<Velocity> initialBallVelocities();

    /**
     * this method returns the paddle speed in the level.
     *
     * @return integer of the speed of the paddle
     */
    int paddleSpeed();

    /**
     * this method return the width of the paddle in the level.
     *
     * @return integer of the width of the paddle.
     */
    int paddleWidth();

    /**
     * the level name will be displayed on the top of the screen.
     *
     * @return a string of the level name.
     */
    String levelName();

    /**
     * this method returns the background of the level.
     *
     * @return Background of the current level.
     */
    Sprite getBackground();

    /**
     * this is the list of blocks that will make up this level.
     * each block contains its size, color, and location
     *
     * @return List of blocks
     */
    List<Block> blocks();

    /**
     * the number of blocks that should be removed.
     * when level is done number should be zero.
     *
     * @return integer that represents number of blocks to be removed
     */
    int numberOfBlocksToRemove();
}
