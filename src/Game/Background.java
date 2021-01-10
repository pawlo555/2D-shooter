package Game;

import Utilities.Vector2D;

public class Background extends StaticElement {
    private final Vector2D lowerRightCorner;

    public Background(double height, double width) {
        lowerRightCorner = new Vector2D(width, height);
    }

    @Override
    public String getPathToJPG() {
        return "JPG/background.jpg";
    }

    @Override
    public Vector2D getUpperLeftCorner() {
        return new Vector2D(0,0);
    }

    @Override
    public Vector2D getLowerRightCorner() {
        return lowerRightCorner;
    }
}
