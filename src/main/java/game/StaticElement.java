package game;

import utilities.Vector2D;

public abstract class StaticElement extends MapElement {
    protected final Vector2D center;

    public StaticElement(Vector2D center) {
        this.center = center;
    }

    @Override
    public Vector2D getCenter() {
        return center;
    }
}
