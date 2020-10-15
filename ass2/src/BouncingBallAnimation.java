/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-04-19
 */
import biuoop.Sleeper;
import biuoop.GUI;
import biuoop.DrawSurface;

/**
 * A class that shows one bouncing ball on the GUI.
 * The ball always stays within the boundaries on the screen
 */
public class BouncingBallAnimation {
    /**
     * A function that draws the animation of the bouncing ball.
     * @param start is the coordinate of the ball's starting point
     * @param dx the velocity of the ball along the x-axis
     * @param dy the velocity of the ball along the y-axis
     */
    private static void drawAnimation(Point start, double dx, double dy) {
        //Initializing gui and sleeper
        GUI gui = new GUI("title", 200, 200);
        Sleeper sleeper = new Sleeper();
        //initializing ball
        Ball ball = new Ball(start.getX(), start.getY(), 30, java.awt.Color.BLACK);
        //setting ball's velocity
        ball.setVelocity(dx, dy);
        while (true) {
            //drawing ball on screen
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            // wait for 50 milliseconds.
            sleeper.sleepFor(10);
        }
    }

    /**
     * the main method for the class.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        double[] arr = new double[args.length];
        //convert all arguments to double values
        for (int i = 0; i < args.length; i++) {
            arr[i] = Double.parseDouble(args[i]);
        }
        //use command line arguments as parameters for drawAnimation
        Point p = new Point(arr[0], arr[1]);
        drawAnimation(p, arr[2], arr[3]);

    }

}
