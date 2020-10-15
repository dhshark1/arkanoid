/**
 * this class implements a special block.
 * when the ball hits the angular block, the angle of its velocity changes
 */
public class AngularBlock implements Collidable {
    //fields for the angular block
    private Rectangle shape;
    private double changeAngle;


    /**
     * constructor.
     *
     * @param shape the shape of angular block
     * @param angle the angle to change to after a hit
     */
    public AngularBlock(Rectangle shape, double angle) {
        //initialize the fields of the angular block
        this.shape = shape;
        this.changeAngle = angle;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;

    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        return Velocity.fromAngleAndSpeed(this.changeAngle,
                currentVelocity.getCurrentSpeed());
    }

    /**
     * add this angular block to the game given.
     *
     * @param g the game to add sprite to
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
    }
}