/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-04-19
 */
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;
import java.util.Random;

/**
 * A class that displays multiple bouncing balls.
 * half of the balls are in a big bray frame
 * the second half are in a smaller yellow frame
 */
public class MultipleFramesBouncingBallsAnimation {
    //initializing the width and height of the screen
    static final int WIDTH = 800;
    static final int HEIGHT = 600;

    /**
     * This method takes care of drawing the balls on the gui.
     * The balls are drawn like the description of the class
     *
     * @param balls a list of balls to be drawn
     */
    private static void drawMultipleAnimation(Ball[] balls) {
        //initialize a gui
        GUI gui = new GUI("Multiple Bouncing Balls Animation Multiple Frames",
                WIDTH, HEIGHT);
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
            d.setColor(Color.GRAY);
            //draw a big gray rectangle
            d.drawRectangle(50, 50, 450, 450);
            d.fillRectangle(50, 50, 450, 450);
            d.setColor(Color.YELLOW);
            //draw a small yellow triangle
            d.drawRectangle(450, 450, 150, 150);
            d.fillRectangle(450, 450, 150, 150);
            //draw the first half of the balls on the gray rectangle
            for (int i = 0; i < (balls.length / 2); i++) {
                balls[i].moveOneStep(50, 50, 450, 450);
                balls[i].drawOn(d);
            }
            //draw the second half of balls on the yellow triangle
            for (int j = balls.length / 2; j < balls.length; j++) {
                balls[j].moveOneStep(450, 450, 150, 150);
                balls[j].drawOn(d);
                }
            //show on gui
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
        //choose random number between max and min
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    /**
     * The main method of the class.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        //initialize the list of balls
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
            Color color = new Color(r, g, b);
            /*
            if we're in the first half of balls draw
            according to gray rectangle boundaries
             */
            if (i < args.length / 2) {
                Ball ball = new Ball(randomBounds(50 + Integer.parseInt(args[i]),
                        500 - Integer.parseInt(args[i])),
                        randomBounds(50 + Integer.parseInt(args[i]),
                                500 - Integer.parseInt(args[i])),
                        Integer.parseInt(args[i]), color);
                ballList[i] = ball;
            /*
            if we're in the second half of balls draw
            according to yellow rectangle boundaries
             */
            } else if (i >= args.length / 2) {
                Ball ball = new Ball(randomBounds(450 + Integer.parseInt(args[i]), 600 - Integer.parseInt(args[i])),
                        randomBounds(450 + Integer.parseInt(args[i]), 600 - Integer.parseInt(args[i])),
                        Integer.parseInt(args[i]), color);
                ballList[i] = ball;
            }
        }
        //draw the balls on the screen
        drawMultipleAnimation(ballList);
    }
}
