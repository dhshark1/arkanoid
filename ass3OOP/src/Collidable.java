/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-04-19
 */

/**
 * this is an interface for a collidable object.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return the "collision shape" of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit.
     *
     * @param collisionPoint  the point of collision
     * @param currentVelocity the velocity the object collided with
     * @return the new velocity
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}