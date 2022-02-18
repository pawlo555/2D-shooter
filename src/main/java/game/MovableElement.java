package game;

import utilities.Angle;
import utilities.Vector2D;

public abstract class MovableElement extends MapElement {
    protected Vector2D center;
    protected Angle angle;

    public MovableElement(Vector2D center, Angle angle) {
        this.center = center;
        this.angle = angle;
    }

    public void turnBy(int angle) {
        this.angle.turnByAngle(angle);
    }

    public void moveBy(double x) {
        Vector2D newCenter = center.add(angle.toUnitVector().multiply(x));
        center = newCenter;
        collider.changeCenterPosition(newCenter);
    }

    public Vector2D getCenter() {
        return this.center;
    }

    public Angle getAngle() {
        return new Angle(angle.getAngle());
    }
}
