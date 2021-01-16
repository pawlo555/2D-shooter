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
        double a = Math.abs(this.getCenter().getX()-otherCollider.getX());
        double b = Math.abs(this.getCenter().getY()-otherCollider.getY());
        double c = Math.sqrt(a*a+b*b);
        if (b > a) {
            return c*y/b;
        }
        else
            return c*x/a;
    }
}
