package Utilities;

public class RectangleCollider extends Collider {
    private final double x;
    private final double y;

    public RectangleCollider(Vector2D center, double x, double y) {
        super(center);
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean pointInside(Vector2D position) {
        return false;
    }

    @Override
    public double lengthToEnd(Vector2D otherCollider) {
        Vector2D shift = otherCollider.add(getCenter().multiply(-1));
        double X = shift.getX();
        double Y = shift.getY();
        double dy = x*Y/X;
        double dx = y/Y*X;
        return Math.sqrt(dy*dy+dx*dx);
    }
}
