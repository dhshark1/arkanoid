/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-01
 */
package geometry;

import biuoop.DrawSurface;
import game.GameEnvironment;
import gamesprites.CollisionInfo;
import gamesprites.Sprite;
import gamesprites.Velocity;
import game.Game;
import hitlisteners.HitListener;
import hitlisteners.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A class that creates the object ball.
 */
public class Ball implements Sprite, HitNotifier {
    //radius on the ball
    private int r;
    //center of the ball
    private Point center;
    //color of the ball
    private Color color;
    //velocity of the ball
    private Velocity velocity;
    //game environment of the ball
    private GameEnvironment game;
    private ArrayList<HitListener> hitListeners;

    /**
     * Constructor with configurable x and y coordinates, radius, and color.
     *
     * @param x     the x coordinate of the center of the ball
     * @param y     the y coordinate of the center of the ball
     * @param r     the radius of the ball
     * @param color the color of the ball
     */
    public Ball(double x, double y, int r, Color color) {
        //create the fields of the ball
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructor with configurable x and y coordinates, radius, and color.
     *
     * @param x     the x coordinate of the center of the ball
     * @param y     the y coordinate of the center of the ball
     * @param r     the radius of the ball
     * @param v     the velocity of the ball
     * @param game  the game environment of the ba;;
     * @param color the color of the ball
     */
    public Ball(double x, double y, int r, Color color, Velocity v, GameEnvironment game) {
        //create the fields of the ball
        this.center = new Point(x, y);
        this.r = r;
        this.velocity = v;
        this.color = color;
        this.game = game;
        this.hitListeners = new ArrayList<>();
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
    public Color getColor() {
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


    @Override
    public void timePassed() {
        this.moveOneStep();
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
     * set game environment for this ball.
     *
     * @param uGame the game.GameEnvironment to set
     */
    public void setGame(GameEnvironment uGame) {
        this.game = uGame;
    }

    /**
     * This method returns the velocity of the ball.
     *
     * @return gameSprites.Velocity of ball
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * This method applies the velocity of the ball to the ball.
     */
    public void moveOneStep() {
        //the projected line of the ball's movement
        Line trajectory = new Line(this.getX(), this.getY(),
                this.getX() + this.getVelocity().getDx(),
                this.getY() + this.getVelocity().getDy());
        //get the closest collision to the ball, if exists
        CollisionInfo collision =
                this.game.getClosestCollision(trajectory);
        //if there is no collision, move normally
        if (collision == null) {
            this.center = this.getVelocity().applyToPoint(
                    new Point(this.getX(), this.getY()));
        } else {
            //otherwise, move the ball to almost the hit location
            this.center = new Point(moveOneStepHelper(collision).getX(),
                    moveOneStepHelper(collision).getY());
            //and notify hit method
            this.setVelocity(collision.collisionObject().hit(this,
                    collision.collisionPoint(), this.velocity));
        }
    }

    /**
     * this method moves the ball to almost the hit location.
     * this is done accordint to the existing velocity
     *
     * @param collision the closest collision to the ball
     * @return geometry.Point the center of the ball right before the collision
     */
    private Point moveOneStepHelper(CollisionInfo collision) {
        //the new points of the ball at hit location, according to velocity
        double newX = this.getSize() + collision.collisionPoint().getX();
        double newY = this.getSize() + collision.collisionPoint().getY();
        double newX2 = collision.collisionPoint().getX() - this.getSize();
        double newY2 = collision.collisionPoint().getY() - this.getSize();
        //velocity of the ball
        double dx = this.velocity.getDx();
        double dy = this.velocity.getDy();
        //if the ball is moving down and right
        if (dx > 0 && dy > 0) {
            return new Point(newX2, newY2);
            //if the ball is moving left and up
        } else if (dx < 0 && dy < 0) {
            return new Point(newX, newY);
            //if the ball is moving left and down
        } else if (dx < 0 && dy > 0) {
            return new Point(newX, newY2);
            //if the ball is moving right and up
        } else if (dx > 0 && dy < 0) {
            return new Point(newX2, newY);
            //if ball is moving to the right
        } else if (dx > 0 && dy == 0) {
            return new Point(newX, this.center.getY());
            //if ball is moving to the left
        } else if (dx < 0 && dy == 0) {
            return new Point(newX2, this.center.getY());
            //if ball is moving down
        } else if (dx == 0 && dy > 0) {
            return new Point(this.center.getX(), newY2);
            //if ball is moving up
        } else if (dx == 0 && dy < 0) {
            return new Point(this.center.getX(), newY);
        }
        return this.center;
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    @Override
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }
}
