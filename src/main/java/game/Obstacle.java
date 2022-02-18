package game;

import utilities.RectangleCollider;
import utilities.Vector2D;

public class Obstacle extends StaticElement {
    private final Vector2D upperLeftCorner;
    private final Vector2D lowerRightCorner;

    public Obstacle(Vector2D center, double x) {
        super(center);
        this.upperLeftCorner = new Vector2D(center.getX()-x,center.getY()-x);
        this.lowerRightCorner = new Vector2D(center.getX()+x,center.getY()+x);
        collider = new RectangleCollider(center,x,x);
    }

    @Override
    public String getPathToJPG() {
        return this.getClass().getResource("../images/box.png").getPath();
    }

    @Override
    public Vector2D getUpperLeftCorner() {
        return upperLeftCorner;
    }

    @Override
    public Vector2D getLowerRightCorner() {
        return lowerRightCorner;
    }

}
