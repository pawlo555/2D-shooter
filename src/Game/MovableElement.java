package Game;

import Utilities.Angle;
import Utilities.CircleCollider;
import Utilities.Collider;
import Utilities.Vector2D;

public abstract class MovableElement extends MapElement {
    protected Vector2D center;

    public MovableElement(Vector2D center) {
        this.center = center;
    }

    private Angle angle = new Angle();

    public void turnBy(int angle) {
        this.angle.turnByAngle(angle);
    }

    public void moveBy(double x) {
        center = center.add(angle.toUnitVector().multiply(x));
    }

    public Vector2D getCenter() {
        return this.center;
    }

}
