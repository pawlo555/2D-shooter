package Utilities;

public class Rectangle extends Shape{
    private final double x;
    private final double y;

    public Rectangle(Vector2D center, double x, double y) {
        super(center);
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean pointInside(Vector2D position) {
        return false;
    }
}
