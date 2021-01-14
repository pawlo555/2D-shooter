package Game;

import Utilities.RectangleCollider;
import Utilities.Vector2D;

public class Obstacle extends StaticElement {
    private final Vector2D upperLeftCorner;
    private final Vector2D lowerRightCorner;
    private final Vector2D center;

    public Obstacle(Vector2D center, double x) {
        this.upperLeftCorner = new Vector2D(center.getX()-x,center.getY()-x);
        this.lowerRightCorner = new Vector2D(center.getX()+x,center.getY()+x);
        this.center = center;
        collider = new RectangleCollider(center,x,x);
    }

    @Override
    public String getPathToJPG() {
        return  "C:\\Users\\spawe\\OneDrive\\Pulpit\\Studia\\ProgramowanieObiektowe\\Strzelanka2D\\src\\Images\\box.png";
    }

    @Override
    public Vector2D getUpperLeftCorner() {
        return upperLeftCorner;
    }

    @Override
    public Vector2D getLowerRightCorner() {
        return lowerRightCorner;
    }

    @Override
    public Vector2D getCenter() {
        return center;
    }
}
