package Utilities;

public class CircleCollider extends Collider {
    private final double radius;

    public CircleCollider(Vector2D center, double radius) {
        super(center);
        this.radius = radius;
    }

    @Override
    public boolean pointInside(Vector2D position) {
        return this.getCenter().distanceBetweenPoints(position) < radius;
    }

    @Override
    public double lengthToEnd(Vector2D otherCollider) {
        return radius;
    }
}
