/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-04-19
 */
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.util.Random;
import java.awt.Color;

/**
 * This class displays multiple bouncing balls on the screen.
 * The balls all stay within the screen's boundaries
 */
public class MultipleBouncingBallsAnimation {
    //width of the screen
    static final int WIDTH = 400;
    //height of the screen
    static final int HEIGHT = 300;
    //boundaries for function moveOneStep
    static final int STARTX = 0;
    static final int STARTY = 0;

    /**
     * A function that draws the animation of the balls on the gui.
     *
     * @param balls an array of balls to be drawn
     */
    private static void drawMultipleAnimation(Ball[] balls) {
        //initializing a new gui
        GUI gui = new GUI("Multiple Bouncing Balls Animation", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        /*
        this loop sets the velocity of each ball in the array
        for balls with size bigger than 50 their velocity does not vary
         */
        for (Ball ball : balls) {
            //if ball's radius is smaller than 50
            if (ball.getSize() < 50) {
                //velocity is 51 divided by the radius
                ball.setVelocity(50 / ball.getSize(), 50 / ball.getSize());
            } else {
                ball.setVelocity(1, 1);
            }
        }
        while (true) {
            //initialize a drawing screen on the gui
            DrawSurface d = gui.getDrawSurface();
            /*
            draw each ball in the array on the screen
             */
            for (Ball ball : balls) {
                ball.moveOneStep(STARTX, STARTY, WIDTH, HEIGHT);
                ball.drawOn(d);
            }
            //show on the gui
            gui.show(d);
            sleeper.sleepFor(10);  // wait for 50 milliseconds.
        }
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
     * This is the main method of the class.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        //initialize a new array of balls
        Ball[] ballList = new Ball[args.length];
        Random rand = new Random();
        /*
        this loop chooses a random color and initializes a new ball
        then it appends it to the ball list
         */
        for (int i = 0; i < args.length; i++) {
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            //new color through rgb
            Color color = new Color(r, g, b);
            //initialize a new ball following boundary requirements
            Ball ball = new Ball(randomBounds(Integer.parseInt(args[i]),
                    WIDTH - Integer.parseInt(args[i])),
                    randomBounds(Integer.parseInt(args[i]),
                            HEIGHT - Integer.parseInt(args[i])),
                    Integer.parseInt(args[i]), color);
            //add to ball list
            ballList[i] = ball;
        }
        //draw the balls on the screen
        drawMultipleAnimation(ballList);
    }
}
