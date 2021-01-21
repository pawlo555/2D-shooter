package Utilities;

public class CircleCollider extends Collider {
    private final double radius;

    public CircleCollider(Vector2D center, double radius) {
        super(center);
        this.radius = radius;
    }

    @Override
    public double lengthToEnd(Vector2D otherCollider) {
        return radius;
    }
}
