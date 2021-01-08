package Game;

import Utilities.Vector2D;

import java.util.ArrayList;
import java.util.List;

public interface MapElement {

    String getPathToJPG();

    Vector2D getUpperLeftCorner();
    Vector2D getLowerRightCorner();


}
