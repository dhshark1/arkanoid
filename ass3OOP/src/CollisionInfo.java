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
     * @param collisionObject the Collidable object that was collided with
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * returns the point at which the collision occurs.
     *
     * @return Point the collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * returns the Collidable involved in the collision.
     *
     * @return Collidable the collision object
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}