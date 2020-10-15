/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-01
 */
package gamesprites;

import geometry.Point;

/**
 * this class collects all the information about a collision in the game.
 */
public class CollisionInfo {
    //fields of the class
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor.
     *
     * @param collisionPoint the point of the collision
     * @param collisionObject the gameSprites.Collidable object that was collided with
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * returns the point at which the collision occurs.
     *
     * @return geometry.Point the collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * returns the gameSprites.Collidable involved in the collision.
     *
     * @return gameSprites.Collidable the collision object
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}