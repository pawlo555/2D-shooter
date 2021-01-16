package Utilities;

public abstract class Collider {
    private Vector2D center;

    public Collider(Vector2D center) {
        this.center = center.add(new Vector2D(0,0));
    }

    public void changeCenterPosition(Vector2D newPosition) {
        center = newPosition;
    }

    public abstract boolean pointInside(Vector2D position);

    public abstract double lengthToEnd(Vector2D otherCollider);

    public Vector2D getCenter() {
        return center;
    }
    public void updateCenter(Vector2D newCenter){
        this.center = newCenter;
    }
}
