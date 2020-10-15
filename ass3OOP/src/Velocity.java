/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-04-19
 */

/**
 * A class that configures the velocity of the ball and applies it.
 */
public class Velocity {
    //velocity in the horizontal direction
    private double dx;
    //velocity in the vertical direction
    private double dy;

    /**
     * contructor with configurable velocity values.
     *
     * @param dx is the given velocity for the x axis
     * @param dy is the given velocity for the y axis
     */
    public Velocity(double dx, double dy) {
        //create the velocities for this ball
        this.dx = Math.round(dx * 100.0) / 100.0;;
        this.dy = Math.round(dy * 100.0) / 100.0;;
    }

    /**
     * This function calculates the velocity given the angle and speed.
     *
     * @param angle is the angle of the velocity of the ball.
     *              relative to the x axis
     * @param speed is the overall speed of the ball not in x and y directions
     * @return new Velocity of ball in x and y directions
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //convert angle from degrees to radians
        double radians = Math.toRadians(angle);
        //velocity in x direction
        double dx = Math.sin(radians) * speed;
        //velocity in y direction
        double dy = Math.cos(radians) * speed * (-1);
        return new Velocity(dx, dy);
    }

    /**
     * calculate current speed using pythagoras.
     *
     * @return current speed
     */
    public double getCurrentSpeed() {
        return Math.sqrt((Math.pow(this.dy, 2)) + (Math.pow(this.dx, 2)));
    }

    /**
     * calculate current angle of velocity.
     *
     * @return current angle of velocity
     */
    public double getCurrentAngle() {
        return Math.toDegrees(Math.asin(this.dx / getCurrentSpeed()));
    }

    /**
     * This function applies the ball's velocity to its current coordinates.
     *
     * @param p is the current coordinates of the center of the ball
     * @return new Point of the center of the ball with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        //apply velocity to x coordinate
        double x = p.getX() + this.dx;
        //apply velocity to y coordinate
        double y = p.getY() + this.dy;
        return new Point(x, y);
    }

    /**
     * This function returns the ball's velocity in the horizontal direction.
     *
     * @return double value of this ball's dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * This function returns the velocity of the ball in the vertical direction.
     *
     * @return double value of this ball's dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * This function sets the ball's velocity in the horizontal direction.
     *
     * @param newDx is the given value of this ball's x-axis velocity
     */
    public void setDx(double newDx) {
        this.dx = Math.round(newDx * 100.0) / 100.0;
    }

    /**
     * This function sets ball's velocity in the vertical direction.
     *
     * @param newDy is the given value of this ball's y-axis velocity
     */
    public void setDy(double newDy) {
        this.dy = Math.round(newDy * 100.0) / 100.0;
    }
}
