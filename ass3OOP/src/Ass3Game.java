/**
 * Create a game object, initializes and runs it.
 */
public class Ass3Game {

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