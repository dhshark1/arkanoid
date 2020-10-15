/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-04-19
 */
import java.awt.Color;
import biuoop.DrawSurface;

/**
 * A class that creates the object ball.
 */
public class Ball {
    //radius on the ball
    private int r;
    //center of the ball
    private Point center;
    //color of the ball
    private Color color;
    //velocity of the ball
    private Velocity velocity;

    /**
     * Constructor with configurable x and y coordinates, radius, and color.
     *
     * @param x     the x coordinate of the center of the ball
     * @param y     the y coordinate of the center of the ball
     * @param r     the radius of the ball
     * @param color the color of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        //create the fields of the ball
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * This method returns the x coordinate of the center of the ball.
     *
     * @return int of the x coordinate
     */
    public int getX() {
        //return center's x
        return (int) this.center.getX();
    }

    /**
     * This method returns the y coordinate of the center of the ball.
     *
     * @return int of the y coordinate
     */
    public int getY() {
        //return center's y
        return (int) this.center.getY();
    }

    /**
     * This method returns the radius of the ball.
     *
     * @return int the radius of the ball
     */
    public int getSize() {
        //return this ball's radius
        return this.r;
    }

    /**
     * This method returns the color of the ball.
     *
     * @return Color the color of the ball
     */
    public java.awt.Color getColor() {
        //return this ball's color
        return this.color;
    }

    /**
     * This method draws a ball on a given surface.
     *
     * @param surface the given surface where the ball will be drawn
     */
    public void drawOn(DrawSurface surface) {
        //set the color of the ball
        surface.setColor(this.color);
        //draw the ball
        surface.drawCircle((int) this.center.getX(),
                (int) this.center.getY(), this.r);
        surface.fillCircle((int) center.getX(), (int) this.center.getY(), r);
    }

    /**
     * This method set's the velocity of the ball given a velocity.
     *
     * @param v desired velocity of the ball
     */
    public void setVelocity(Velocity v) {
        //ball's velocity will equal v now
        this.velocity = v;
    }

    /**
     * This method sets the ball's velocity given an x velocity and y velocity.
     *
     * @param dx the velocity along the x axis
     * @param dy the velocity along the y axis
     */
    public void setVelocity(double dx, double dy) {
        //ball's velocity will equal dx and dy now
        this.velocity.setDx(dx);
        this.velocity.setDy(dy);
    }

    /**
     * This method returns the velocity of the ball.
     *
     * @return Velocity of ball
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * This method moves the ball one step according to it's velocity.
     * This method works only for a default grid
     */
    public void moveOneStep() {
        //apply velocity to ball's center
        this.moveOneStep(0, 0, 200, 200);
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * This method ensures that a ball stays in the screen.
     *
     * @param startX x coordinate of where boundary starts
     * @param startY y coordinate of where boundary starts
     * @param width  width of boundary
     * @param height height of boundary
     */
    public void bounceWall(int startX, int startY, int width, int height) {
        //if ball x coordinate is smaller than start or bigger than width
        if (this.getX() + this.getSize() + velocity.getDx() >= startX + width
                || this.getX() - this.getSize() + velocity.getDx() < startX) {
            //change direction of x velocity
            setVelocity(velocity.getDx() * -1, velocity.getDy());
        }
        //if ball y coordinate is smaller than start or bigger than height
        if (this.getY() + this.getSize() + velocity.getDy() >= startY + height
                || this.getY() - this.getSize() + velocity.getDy() < startY) {
            //change direction of y velocity
            setVelocity(velocity.getDx(), velocity.getDy() * -1);
        }
    }

    /**
     * This method applies the velocity of the ball to the ball.
     *
     * @param startX x coordinate of where boundary starts
     * @param startY y coordinate of where boundary starts
     * @param width  width of boundary
     * @param height height of boundary
     */
    public void moveOneStep(int startX, int startY, int width, int height) {
        //apply velocity to ball's center
        this.center = this.getVelocity().applyToPoint(
                new Point(this.getX(), this.getY()));
        //make sure ball isn't out of boundary
        this.bounceWall(startX, startY, width, height);
    }
}
