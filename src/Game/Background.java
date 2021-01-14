package Game;

import Utilities.Vector2D;

public class Background {
    private final Vector2D lowerRightCorner;

    public Background(double height, double width) {
        lowerRightCorner = new Vector2D(width, height);
    }

    public String getPathToJPG() {
        return "C:\\Users\\spawe\\OneDrive\\Pulpit\\Studia\\ProgramowanieObiektowe\\Strzelanka2D\\src\\Images\\background.jpg";
    }
}
