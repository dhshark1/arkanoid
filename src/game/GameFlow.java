/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-18
 */
package game;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import other.Counter;

import java.util.List;

/**
 * this class is in charge of moving between levels.
 */
public class GameFlow {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private AnimationRunner runner;
    private GUI gui;
    private Counter score;
    private KeyboardSensor keyboard;

    /**
     * constructor.
     */
    public GameFlow() {
        this.gui = new GUI("Arkanoid", WIDTH, HEIGHT);
        this.runner = new AnimationRunner(gui);
        this.score = new Counter(0);
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * this method runs all the levels in the game.
     *
     * @param levels levels to be run
     */
    public void runLevels(List<LevelInformation> levels) {
        int levelCounter = 0;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, score, gui, runner, keyboard);
            level.initialize();
            levelCounter++;
            while (level.getNumOfBlocks().getValue() > 0
                    && level.getNumOfBalls().getValue() > 0) {
                // if there are others blocks to destroyed and the player
                // have others LIVES, play another turn
                level.run();
            }
            // if player lost
            if (level.getNumOfBalls().getValue() == 0 || levels.size() == levelCounter) {
                // check the reason the game ended - end screen accordingly
                runner.run(new KeyPressStoppableAnimation(keyboard,
                        new EndScreen(score.getValue(), level.getNumOfBalls()), KeyboardSensor.SPACE_KEY));
                break;
            }
        }
        gui.close();
    }
}