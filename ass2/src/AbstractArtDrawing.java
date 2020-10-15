/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-04-19
 */
import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;

/**
 * A class that draw 10 random lines on a gui.
 * all of the intersection points between the lines are drawn in red
 * all middle points drawn in blue
 */
public class AbstractArtDrawing {
    //constant to all point's radius
    static final int R = 3;

    /**
     * A method that generates random lines within the screen.
     *
     * @return new Line with a start and end coordinates
     */
    public Line generateRandomLines() {
        Random rand = new Random();
        //generate 4 random coordinates
        double x1 = rand.nextInt(400) + 1; // get integer in range 1-400
        double x2 = rand.nextInt(400) + 1; // get integer in range 1-400
        double y1 = rand.nextInt(300) + 1; // get integer in range 1-300
        double y2 = rand.nextInt(300) + 1; // get integer in range 1-300
        //return new coordinates as a new line
        return new Line(x1, y1, x2, y2);
    }

    /**
     * This method draws a line on a surface.
     *
     * @param l is the given line to be drawn
     * @param d is the given surface to be drawn on
     */
    public void drawLine(Line l, DrawSurface d) {
        //use function drawLine to draw the line
        d.drawLine((int) l.start().getX(), (int) l.start().getY(),
                (int) l.end().getX(), (int) l.end().getY());
        //get middle coordinates
        double x = l.middle().getX();
        double y = l.middle().getY();
        //color the middle point in blue
        d.setColor(Color.BLUE);
        d.fillCircle((int) x, (int) y, R);
        d.setColor(Color.BLACK);
    }

    /**
     * This function draws the random lines on the screen.
     * It also draws all the points of intersection on the screen in red
     */
    public void drawRandomLines() {
        //Initialize a new array of lines
        Line[] arr = new Line[10];
        //Initialize a gui
        GUI gui = new GUI("Abstract Art Drawing", 400, 300);
        //initialize a new drawing surface
        DrawSurface d = gui.getDrawSurface();
        /*
        this loop generates 10 random lines
        draws each on the screen
        then adds them to the lines array
         */
        for (int i = 0; i < 10; i++) {
            //generate random line
            Line l = generateRandomLines();
            //draw it on the screen
            drawLine(l, d);
            //add to array
            arr[i] = l;
        }
        d.setColor(Color.RED);
        /*
        these loops find intersection points between the lines
        if lines intersect draw red circle on intersection
         */
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                //if it is not the same line we are comparing
                if (j != i) {
                    //if lines are intersecting
                    if (arr[i].isIntersecting(arr[j])) {
                        //get intersection points
                        double x = arr[i].intersectionWith(arr[j]).getX();
                        double y = arr[i].intersectionWith(arr[j]).getY();
                        d.fillCircle((int) x, (int) y, R);
                    }
                }
            }
        }
        //show on gui
            gui.show(d);
    }

    /**
     * ths is the main method of the class.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        //draw the abstract art
        AbstractArtDrawing window = new AbstractArtDrawing();
        window.drawRandomLines();
    }

}