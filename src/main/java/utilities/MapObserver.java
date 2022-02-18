package utilities;

import game.MapElement;

public interface MapObserver {

    void ElementAddToMap(MapElement element);

    void ElementDeleteFromMap(MapElement element);
}
