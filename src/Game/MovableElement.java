package Game;

import Utilities.Angle;
import Utilities.CircleCollider;
import Utilities.Collider;
import Utilities.Vector2D;

public abstract class MovableElement extends MapElement {
    protected Vector2D center;
    protected Angle angle;

    public MovableElement(Vector2D center, Angle angle) {
        this.center = center;
        this.angle = angle;
    }

    public void turnBy(int angle) {
        this.angle.turnByAngle(angle);
        System.out.println(this.angle.toUnitVector().toString());
    }

    public void moveBy(double x) {
        center = center.add(angle.toUnitVector().multiply(x));
    }

    public Vector2D getCenter() {
        return this.center;
    }

    public Angle getAngle() {
        return this.angle;
    }
}
