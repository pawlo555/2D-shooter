package Utilities;

public class Circle extends Shape{

    private final double radius;

    public Circle(Vector2D center, double radius) {
        super(center);
        this.radius = radius;
    }

    @Override
    public boolean pointInside(Vector2D position) {
        return this.getCenter().distanceBetweenPoints(position) < radius;
    }
}
