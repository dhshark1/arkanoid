/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-01
 */
package game;

import gamesprites.Collidable;
import gamesprites.CollisionInfo;
import geometry.Line;
import geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * a collection of objects a geometry.Ball can collide with.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * constructor.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * this method adds a collidable to the list of collidables.
     *
     * @param c gameSprites.Collidable to be added
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * this method removes a collidable from the collidables list.
     *
     * @param c collidable to be removed
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * this method returns the collection of collidables in the game.
     *
     * @return List<gameSprites.Collidable> all the collidables in the game
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the line in which the object is moving
     * @return gameSprites.CollisionInfo
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //initialize arrays to collect info for collisions
        List<CollisionInfo> collisions = new ArrayList<>();
        List<Point> collisionPoints = new ArrayList<>();
        /*
        for all the collidables in the game, check the closest intersection
        to the start of the line
         */
        for (Collidable c : this.collidables) {
            Point cPoint = trajectory.closestIntersectionToStartOfLine(
                    c.getCollisionRectangle());
            //if the point is not null then add it to array of collisions
            if (cPoint != null) {
                collisions.add(new CollisionInfo(cPoint, c));
            }
        }
        //for all of the collisions, collect all collision points in an array
        for (CollisionInfo c : collisions) {
            collisionPoints.add(c.collisionPoint());
        }
        //the closest collision point to the start of the line
        Point closest = trajectory.getClosestPoint(collisionPoints);
        //if a point exists and its on the trajectory path, return it
        if (closest != null && trajectory.pointInSegment(closest)) {
            for (CollisionInfo c : collisions) {
                if (closest.equals(c.collisionPoint())) {
                    return c;
                }
            }
        }
        //no collision, return null
        return null;
    }

}