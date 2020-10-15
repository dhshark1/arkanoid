/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-01
 */
package gamesprites;

import biuoop.DrawSurface;
import game.Game;
import geometry.Ball;
import geometry.Rectangle;
import hitlisteners.HitListener;
import hitlisteners.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;

/**
 * this class creates the block object.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    //creating the fields for the block
    private Rectangle shape;
    //default color
    private Color color = Color.BLACK;
    private ArrayList<HitListener> hitListeners;
    private int hitsCounter;

    /**
     * constructor.
     *
     * @param shape the rectangular shape of the block
     */
    public Block(geometry.Rectangle shape) {
        this.shape = shape;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * constructor.
     *
     * @param shape       the rectangular shape of the block.
     * @param color       the color of the block
     * @param hitsCounter the hits counter displayed on the block
     */
    public Block(geometry.Rectangle shape, Color color, int hitsCounter) {
        this.shape = shape;
        this.color = color;
        this.hitListeners = new ArrayList<>();
        this.hitsCounter = hitsCounter;
    }

    /**
     * constructor.
     *
     * @param rectangle the rectangular shape of the block.
     * @param color     the color of the block.
     */
    public Block(Rectangle rectangle, Color color) {
        this.shape = rectangle;
        this.hitListeners = new ArrayList<>();
        this.color = color;
    }

    @Override
    public geometry.Rectangle getCollisionRectangle() {
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
     * this method draws the counter on the block on the DrawSurface.
     *
     * @param d the DrawSurface to be drawn on
     */
    private void drawCounter(DrawSurface d) {
        //hit counter will be written in white in size 12 font
        d.setColor(Color.WHITE);
        int fontSize = 12;
        //middle point of the block
        geometry.Point middle = new geometry.Point(this.shape.topLine().middle().getX(),
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
    public Velocity hit(Ball hitter, geometry.Point collisionPoint,
                        Velocity currentVelocity) {
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
        if (hitsCounter > 0) {
            decreaseCounter();
        }
        notifyHit(hitter);
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
     * this method sets the color of the block.
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
    public void setShape(geometry.Rectangle uShape) {
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
     * @param hits number of hitsCounter as a block
     */
    public void setHitsCounter(int hits) {
        this.hitsCounter = hits;
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    public void removeFromGame(Game g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * whenever a hit occurs, notify all of this block listeners
     * by calling their hitEvent method.
     *
     * @param hitter the ball that hit this block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        ArrayList<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}