package Game;

import Utilities.Vector2D;

public abstract class MovableElement implements MapElement {

    @Override
    public Vector2D getUpperLeftCorner() {
        return null;
    }

    @Override
    public Vector2D getLowerRightCorner() {
        return null;
    }
}
