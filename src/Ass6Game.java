/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-18
 */
import game.GameFlow;
import gamesprites.Velocity;
import levels.DirectHit;
import levels.Green;
import levels.FinalFour;
import levels.LevelInformation;
import levels.WideEasy;

import java.util.ArrayList;
import java.util.List;

/**
 * Create a game object, initializes and runs it.
 */
public class Ass6Game {

    /**
     * Create a game object, initializes and runs it.
     *
     * @param args if not empty - the levels to run in this game
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        if (args.length > 0) {
            int levelNum;
            for (String arg : args) {
                try {
                    levelNum = Integer.parseInt(arg);
                    addLevel(levelNum, levels);
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }
        if (levels.size() == 0) {
            // if the are no arguments entered or wrong arguments
            addAllLevels(levels);
        }
        GameFlow game = new GameFlow();
        game.runLevels(levels);
    }

    /**
     * add level to a list of levels.
     *
     * @param levelNum the num of the level to add
     * @param levels   the list of levels to add new level to
     */
    private static void addLevel(int levelNum, List<LevelInformation> levels) {
        List<LevelInformation> allLevels = new ArrayList<>();
        addAllLevels(allLevels);
        if (levelNum <= allLevels.size()) {
            // check if there is level with this num, then add
            levels.add(allLevels.get(levelNum - 1));
        } // else - do nothing
    }

    /**
     * add all levels available to list given.
     *
     * @param levels the list to add levels to
     */
    private static void addAllLevels(List<LevelInformation> levels) {
        levels.add(new DirectHit());
        levels.add(new WideEasy());
        levels.add(new Green());
        levels.add(new FinalFour());
    }
}