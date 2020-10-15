/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-01
 */

import game.Game;

/**
 * Create a game object, initializes and runs it.
 */
public class Ass5Game {

    /**
     * Create a game object, initializes and runs it.
     *
     * @param args arguments from command line - ni use in this main
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
