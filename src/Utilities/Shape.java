package Utilities;

public abstract class Shape {

    private Vector2D center;

    public Shape(Vector2D center) {
        this.center = center;
    }

    public void changeCenterPosition(Vector2D moveBy) {
        center = center.add(moveBy);
    }

    public abstract boolean pointInside(Vector2D position);

    public Vector2D getCenter() {
        return center;
    }
}
