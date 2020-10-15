import biuoop.DrawSurface;

import java.awt.*;

/**
 * this class creates the block object.
 */
public class Block implements Collidable, Sprite {
    //creating the fields for the block
    private Rectangle shape;
    //default color
    private Color color = Color.BLACK;
    private int hitsCounter;

    /**
     * constructor.
     *
     * @param shape the rectangular shape of the block
     */
    public Block(Rectangle shape) {
        this.shape = shape;
    }

    /**
     * constructor.
     *
     * @param shape       the rectangular shape of the block.
     * @param color       the color of the block
     * @param hitsCounter the hits counter displayed on the block
     */
    public Block(Rectangle shape, Color color, int hitsCounter) {
        this.shape = shape;
        this.color = color;
        this.hitsCounter = hitsCounter;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    @Override
    public void timePassed() {

    }

    /**
     * this method returns the hits counter for the block.
     *
     * @return int hit counter
     */
    public int getHitsCounter() {
        return this.hitsCounter;
    }

    /**
     * this method sets the hit counter for the block.
     *
     * @param counter in the desired hit counter
     */
    public void setHitCounter(int counter) {
        this.hitsCounter = counter;
    }

    /**
     * this method draws the counter on the block on the DrawSurface.
     *
     * @param d the DrawSurface to be drawn on
     */
    private void drawCounter(DrawSurface d) {
        //hit counter will be written in white in size 12 font
        d.setColor(Color.WHITE);
        int fontSize = 12;
        //middle point of the block
        Point middle = new Point(this.shape.topLine().middle().getX(),
                this.shape.rightLine().middle().getY());
        //if the hits counter is greater than zero write that number
        if (hitsCounter > 0) {
            d.drawText((int) middle.getX(), (int) middle.getY(),
                    String.valueOf(this.hitsCounter), fontSize);
            //otherwise write a X instead of a number
        } else {
            d.drawText((int) middle.getX(), (int) middle.getY(),
                    "X", fontSize);
        }
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        //change the horizontal velocity if ball hits right or left sides
        if (this.shape.leftLine().pointInSegment(collisionPoint)
                || this.shape.rightLine().pointInSegment(collisionPoint)) {
            currentVelocity.setDx(-1 * currentVelocity.getDx());
        }
        //change the vertical velocity if ball hits top or bottom sides
        if (this.shape.topLine().pointInSegment(collisionPoint)
                || this.shape.bottomLine().pointInSegment(collisionPoint)) {
            currentVelocity.setDy(-1 * currentVelocity.getDy());
        }
        //decrease the counter and return the updates velocity
        decreaseCounter();
        return currentVelocity;
    }

    /**
     * this method draws the block on the DrawSurface.
     *
     * @param d the DrawSurface to be drawn on
     */
    public void drawOn(DrawSurface d) {
        //get parameters of the shape to be drawn
        int x = (int) this.shape.getUpperLeft().getX();
        int y = (int) this.shape.getUpperLeft().getY();
        int width = (int) this.shape.getWidth();
        int height = (int) this.shape.getHeight();
        //set color of block
        d.setColor(this.color);
        d.fillRectangle(x, y, width, height);
        //set color of borders
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, width, height);
        //write the counter
        this.drawCounter(d);
    }

    /**
     * this method sets the color of the block
     *
     * @param uColor the desired color
     */
    public void setColor(Color uColor) {
        this.color = uColor;
    }

    /**
     * this method sets the shape of the block.
     *
     * @param uShape the desired shape
     */
    public void setShape(Rectangle uShape) {
        this.shape = uShape;
    }

    /**
     * this method decreases the hits counter of the block in case of a hit.
     */
    private void decreaseCounter() {
        this.hitsCounter -= 1;
    }

    /**
     * this method sets the hits counter of a specific block.
     *
     * @param hits
     */
    public void setHitsCounter(int hits) {
        this.hitsCounter = hits;
    }

    /**
     * add this block to the game given.
     *
     * @param g the game to add sprite to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

}