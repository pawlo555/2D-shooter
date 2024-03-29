package game;

import utilities.Collider;
import utilities.Vector2D;

public abstract class MapElement {
    protected Collider collider;

    public abstract String getPathToJPG();
    public abstract Vector2D getUpperLeftCorner();
    public abstract Vector2D getLowerRightCorner();
    public abstract Vector2D getCenter();

    public Collider getCollider() {
        return collider;
    }
}
