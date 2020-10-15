/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-01
 */
package gamesprites;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Game;
import geometry.Point;
import geometry.Rectangle;
import geometry.Ball;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * this class creates a paddle object that is controlled by the keyboard.
 */
public class Paddle implements Sprite, Collidable {
    //fields for the paddle
    private static final int ONE_STEP = 5;
    private KeyboardSensor keyboard;
    private Rectangle shape;
    private int rightBorder;
    private int leftBorder;

    /**
     * constructor.
     *
     * @param shape       the shape of the paddle
     * @param keyboard    the keyboard controlling the paddle
     * @param rightBorder the right border of the paddle
     * @param leftBorder  the left border of the paddle
     */
    public Paddle(Rectangle shape, KeyboardSensor keyboard,
                  int rightBorder, int leftBorder) {
        this.shape = shape;
        this.keyboard = keyboard;
        this.rightBorder = rightBorder;
        this.leftBorder = leftBorder;
    }

    /**
     * this method moves the paddle left.
     * this is done as long as it doesn't meet the left border
     */
    private void moveLeft() {
        double distanceFromBorder = this.shape.getUpperLeft().getX()
                - this.leftBorder;
        //move the minimum between distance from border and step distance
        double movement = Math.min(distanceFromBorder, ONE_STEP);
        //new upper left point
        Point newUpperLeft = new Point(
                this.shape.getUpperLeft().getX() - movement,
                this.shape.getUpperLeft().getY());
        this.shape.setUpperLeft(newUpperLeft);
    }

    /**
     * this method moves the paddle right.
     * this is done as long as it doesn't meet the right border
     */
    private void moveRight() {
        double distanceFromBorder = this.rightBorder
                - (this.shape.getUpperLeft().getX() + this.shape.getWidth());
        //move the minimum between distance from border and step distance
        double movement = Math.min(distanceFromBorder, ONE_STEP);
        Point newUpperLeft = new Point(
                this.shape.getUpperLeft().getX() + movement,
                this.shape.getUpperLeft().getY());
        this.shape.setUpperLeft(newUpperLeft);
    }

    @Override
    public void timePassed() {
        //if left key pressed move left
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        //if right key pressed move right
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    @Override
    public void removeFromGame(Game game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }


    /**
     * this method generates a random color with rgb.
     *
     * @return Color random color
     */
    private Color createColor() {
        Random rand = new Random();
        //random rgb values
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        return new Color(r, g, b);
    }

    /**
     * this method draws the paddle on the screen.
     *
     * @param d DrawSurface the canvas we draw on
     */
    public void drawOn(DrawSurface d) {
        //x and y coordinates where the paddle will start
        int x = (int) this.shape.getUpperLeft().getX();
        int y = (int) this.shape.getUpperLeft().getY();
        //width and height of paddle
        int width = (int) this.shape.getWidth();
        int height = (int) this.shape.getHeight();
        //draw the rectangle
        d.setColor(createColor());
        d.fillRectangle(x, y, width, height);
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, width, height);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //divide the paddle to regions of angularBlocks and put them in array
        List<AngularBlock> regions = divideToRegions(currentVelocity);
        int regionOfHit = -1;
        /*
        this loop checks all the regions if the ball hit them
        if so where and assign the region number to a variable
         */
        for (int i = 0; i < regions.size(); i++) {
            if (regions.get(i).getCollisionRectangle().topLine().
                    pointInSegment(collisionPoint)
                    || regions.get(i).getCollisionRectangle().rightLine().
                    pointInSegment(collisionPoint)
                    || regions.get(i).getCollisionRectangle().leftLine().
                    pointInSegment(collisionPoint)
                    || regions.get(i).getCollisionRectangle().bottomLine().
                    pointInSegment(collisionPoint)) {
                regionOfHit = i;
                break;
            }
        }
        //perform hit function on specific region
        return regions.get(regionOfHit).hit(hitter, collisionPoint, currentVelocity);
    }

    /**
     * this method assigns an angle to each region.
     * the angle of the ball changes according to the region it hit
     *
     * @param i               the region
     * @param currentVelocity the current velocity of the ball
     * @return double the angle of the region
     */
    private double angleByRegion(int i, Velocity currentVelocity) {
        double angle = currentVelocity.getCurrentAngle();
        if (i == 1) {
            angle = 300;
        } else if (i == 2) {
            angle = 330;
        } else if (i == 3) {
            angle *= -1;
        } else if (i == 4) {
            angle = 30;
        } else if (i == 5) {
            angle = 60;
        }
        return angle;
    }

    /**
     * this method returns a list of the divided regions of the paddle.
     * this is done according to the paddle's size.
     *
     * @param currentVelocity current velocity of the ball
     * @return List<gameSprites.AngularBlock> list of angular blocks that form the paddle
     */
    private List<AngularBlock> divideToRegions(Velocity currentVelocity) {
        //regions list
        List<AngularBlock> regions = new ArrayList<>();
        //divide the width by the number of regions we want to have
        double regionWidth = this.shape.getWidth() / 5;
        //height of the paddle
        double height = this.shape.getHeight();
        //start of the paddle
        Point upperLeft = this.shape.getUpperLeft();
        /*
        this loop adds each region to the list
        with their respective angles.
         */
        for (int i = 0; i < 5; i++) {
            double angle = angleByRegion(i, currentVelocity);
            regions.add(new AngularBlock(new Rectangle(upperLeft, regionWidth, height), angle));
            //upperLeft point updated to form next region
            upperLeft = new Point(upperLeft.getX() + regionWidth, upperLeft.getY());
        }
        //return all regions
        return regions;
    }

    /**
     * adds this paddle to the game.
     *
     * @param g the game to add the collidable/sprite to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
